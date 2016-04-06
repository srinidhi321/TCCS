/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import Employee.*;
import Exceptions.InvalidPhoneException;
import Exceptions.LessAccessRightsException;
import Exceptions.PropertyNotUniqueException;
import Exceptions.PropertyValueMismatchException;
import SQL_DataHandler.DataHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ridri
 */
public class EmployeeDatabase {

	public EmployeeDatabase(String dbUserId, String dbPassword) throws LessAccessRightsException {
		if (!DataHandler.matchesUserId(dbUserId) || !DataHandler.matchesPassword(dbPassword))
			throw new LessAccessRightsException("Opening application");
	}

	public boolean isSalesClerk(String salId) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		boolean result = false;

		try {
			rset = datahandler.executeQuery("select * from Employee where emp_id = '" + salId + "'");
			while (rset.next())
				result = rset.getString(7).equals("SAL");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return result;
	}

	public boolean isEmployee(String empId) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		boolean result = false;

		try {
			rset = datahandler.executeQuery("select * from Employee where emp_id = '" + empId + "'");
			while (rset.next())
				result = rset.getString(7).equals("OWN") || rset.getString(7).equals("MAN")
						|| rset.getString(7).equals("SAL") || rset.getString(7).equals("EMP");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return result;
	}

	public boolean isOwner(String ownerId) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		boolean result = false;

		try {
			rset = datahandler.executeQuery("select * from Employee where emp_id = '" + ownerId + "'");
			while (rset.next())
				result = rset.getString(7).equals("OWN");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return result;
	}

	public boolean isManager(String managerId) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		boolean result = false;

		try {
			rset = datahandler.executeQuery("select * from Employee where emp_id = '" + managerId + "'");
			while (rset.next())
				result = rset.getString(7).equals("MAN");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return result;
	}

	public String addOwner(Owner employee) throws SQLException {
		DataHandler datahandler = new DataHandler();
		String emp_id = generateId(employee);
		// Exceptions like same name,
		// negative salary
		// max chars
		// have been handled here
		try {
			datahandler.executeQuery("insert into Employee Values('" + emp_id + "','" + employee.getName() + "','"
					+ employee.getAddress() + "','" + employee.getSalary() + "','" + employee.getPhoneNo() + "','"
					+ employee.getEmail() + "','" + employee.getPosition() + "')");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}

		datahandler.close();
		return emp_id;
	}

	public String addManager(Manager employee) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		String emp_id = generateId(employee);
		System.out.println("herere");

		// Exceptions like same name,
		// negative salary
		// max chars
		// have been handled here
		try {
			datahandler.executeQuery("insert into Employee Values('" + emp_id + "','" + employee.getName() + "','"
					+ employee.getAddress() + "','" + employee.getSalary() + "','" + employee.getPhoneNo() + "','"
					+ employee.getEmail() + "','" + employee.getPosition() + "')");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}

		datahandler.close();
		return emp_id;
	}

	public String addEmployee(Employee employee) throws SQLException, LessAccessRightsException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		String emp_id = generateId(employee);
		/*
		 * Exceptions like more than one Owner or manager have been handled here
		 */
		if (employee.getPosition().equals("OWN") || employee.getPosition().equals("MAN")) {
			datahandler.close();
			throw new LessAccessRightsException("add Owner/Manager");
		}
		// Exceptions like same name,
		// negative salary
		// max chars
		// have been handled here
		try {
			datahandler.executeQuery("insert into Employee Values('" + emp_id + "','" + employee.getName() + "','"
					+ employee.getAddress() + "','" + employee.getSalary() + "','" + employee.getPhoneNo() + "','"
					+ employee.getEmail() + "','" + employee.getPosition() + "')");
			datahandler.close();
		} catch (SQLException e) {
			System.out.println("sadasd" + e.getMessage());
		}

		datahandler.close();
		return emp_id;
	}

	public Employee getEmployee(String[] property, String[] value)
			throws SQLException, PropertyValueMismatchException, PropertyNotUniqueException, InvalidPhoneException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		boolean result = true;
		if (property.length != value.length || property.length <= 0) {
			datahandler.close();
			throw new PropertyValueMismatchException();
		}
		if (Arrays.asList(property).contains("salary") || Arrays.asList(property).contains("phone_no")
				|| Arrays.asList(property).contains("email") || Arrays.asList(property).contains("address")) {
			datahandler.close();
			throw new PropertyNotUniqueException();
		}
		try {
			StringBuilder query = new StringBuilder("select * from Employee where ");
			for (int i = 0; i < property.length; i++) {
				if (!(value[i].isEmpty() || value[i] == null)) {
					query.append(property[i]).append(" = '").append(value[i]).append("'");
					if (i + 1 < property.length) {
						query.append(" and ");
					}
				}
			}
			rset = datahandler.executeQuery(query.toString());
			Employee emp = null;
			while (rset.next()) {
				switch (rset.getString(7)) {
				case "OWN":
					emp = new Owner(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6));
					break;

				case "MAN":
					emp = new Manager(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6));
					break;

				case "SAL":
					emp = new SalesClerk(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6));
					break;

				default:
					emp = new Staff(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6));

				}
			}
			datahandler.close();
			return emp;
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
	}

	public Manager getManager() throws SQLException, InvalidPhoneException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;

		try {
			rset = datahandler.executeQuery("select * from Employee where emp_position = 'MAN'");
			Manager emp = null;
			while (rset.next()) {
				emp = new Manager(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
						rset.getLong(5), rset.getString(6));
			}
			datahandler.close();
			return emp;
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
	}

	public Owner getOwner() throws SQLException, InvalidPhoneException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;

		try {
			rset = datahandler.executeQuery("select * from Employee where emp_position = 'OWN'");
			Owner emp = null;
			while (rset.next()) {
				emp = new Owner(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
						rset.getLong(5), rset.getString(6));
			}
			datahandler.close();
			return emp;
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
	}

	public boolean delEmployee(String[] property, String[] value) throws SQLException, PropertyValueMismatchException,
			PropertyNotUniqueException, InvalidPhoneException, LessAccessRightsException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		boolean result = true;
		if (property.length != value.length || property.length <= 0) {
			datahandler.close();
			throw new PropertyValueMismatchException();
		}
		if (Arrays.asList(property).contains("salary") || Arrays.asList(property).contains("phone_no")
				|| Arrays.asList(property).contains("email") || Arrays.asList(property).contains("address")) {
			datahandler.close();
			throw new PropertyNotUniqueException();
		}
		try {
			Employee e = getEmployee(property, value);
			if (!e.getPosition().contains("OWN") && !e.getPosition().contains("MAN")) {
				StringBuilder query = new StringBuilder("delete from Employee where ");
				for (int i = 0; i < property.length; i++) {
					if (!(value[i].isEmpty() || value[i] == null)) {
						query.append(property[i]).append(" = '").append(value[i]).append("'");
						if (i + 1 < property.length) {
							query.append(" and ");
						}
					}
				}
				rset = datahandler.executeQuery(query.toString());
			} else {
				datahandler.close();
				throw new LessAccessRightsException("delete Owner/Manager");
			}
		} catch (SQLException e) {
			result = false;
		}
		datahandler.close();
		return result;
	}

	public ArrayList<Employee> getEmployeeList() throws InvalidPhoneException, SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		ArrayList<Employee> result = new ArrayList<>();
		try {
			rset = datahandler.executeQuery("SELECT * from Employee");
			while (rset.next()) {
				if (rset.getString(7) == null) {
					result.add(new Staff(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6)));
				}
				switch (rset.getString(7)) {
				case "OWN":
					result.add(new Owner(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6)));
					break;
				case "MAN":
					result.add(new Manager(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6)));
					break;
				default:
					result.add(new Staff(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
							rset.getLong(5), rset.getString(6)));
					break;
				case "SAL":
					result.add(new SalesClerk(rset.getString(1), rset.getString(2), rset.getString(3),
							rset.getDouble(4), rset.getLong(5), rset.getString(6)));
					break;
				}
			}
		} catch (SQLException | InvalidPhoneException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return result;
	}

	public String changeOwner(Owner newOwner) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		String new_id = generateId(newOwner);
		try {
			datahandler.executeQuery("UPDATE Employee " + "SET EMP_ID = '" + new_id + "'," + "EMP_NAME = '"
					+ newOwner.getName() + "'," + "ADDRESS = '" + newOwner.getAddress() + "'," + "SALARY = '"
					+ newOwner.getSalary() + "'," + "PHONE_NO = '" + newOwner.getPhoneNo() + "'," + "EMAIL = '"
					+ newOwner.getEmail() + "' " + "WHERE EMP_POSITION = 'OWN'");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return new_id;
	}

	String changeManager(Manager newManager) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		String new_id = generateId(newManager);
		try {
			datahandler.executeQuery("UPDATE Employee " + "SET EMP_ID = '" + new_id + "'," + "EMP_NAME = '"
					+ newManager.getName() + "'," + "ADDRESS = '" + newManager.getAddress() + "'," + "SALARY = '"
					+ newManager.getSalary() + "'," + "PHONE_NO = '" + newManager.getPhoneNo() + "'," + "EMAIL = '"
					+ newManager.getEmail() + "' " + "WHERE EMP_POSITION = 'MAN'");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return new_id;
	}

	public String changeEmployee(String[] property, String[] value, Employee newEmp)
			throws SQLException, PropertyNotUniqueException, PropertyValueMismatchException, LessAccessRightsException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		boolean result = true;
		if (property.length != value.length || property.length <= 0) {
			datahandler.close();
			throw new PropertyValueMismatchException();
		}

		if (Arrays.asList(property).contains("salary") || Arrays.asList(property).contains("phone_no")
				|| Arrays.asList(property).contains("email") || Arrays.asList(property).contains("address")) {
			datahandler.close();
			throw new PropertyNotUniqueException();
		}
		String new_id = generateId(newEmp);
		try {
			if (!newEmp.getPosition().contains("OWN") && !newEmp.getPosition().contains("MAN")) {
				StringBuilder query = new StringBuilder(
						"UPDATE Employee " + "SET EMP_ID = '" + new_id + "'," + "EMP_NAME = '" + newEmp.getName() + "',"
								+ "ADDRESS = '" + newEmp.getAddress() + "'," + "SALARY = '" + newEmp.getSalary() + "',"
								+ "PHONE_NO = '" + newEmp.getPhoneNo() + "'," + "EMAIL = '" + newEmp.getEmail() + "',"
								+ "EMP_POSITION = '" + newEmp.getPosition() + "' " + "WHERE ");

				for (int i = 0; i < property.length; i++) {
					query.append(property[i]).append(" = '").append(value[i]).append("'");
					if (i + 1 < property.length) {
						query.append(" and ");
					}
				}
				rset = datahandler.executeQuery(query.toString());
				datahandler.close();
				return new_id;
			} else {
				datahandler.close();
				throw new LessAccessRightsException("change Owner/Manager");
			}
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
	}

	private String generateId(Employee e) {
		String code = LocalDateTime.now().toString().substring(2);
		StringBuilder refCode = new StringBuilder(
				code.replaceAll(" ", "").toLowerCase().replaceAll("-", "").replaceAll(":", "").replaceAll("t", ""));
		int randomInd = (int) (Math.random() * (12));
		refCode = new StringBuilder(refCode.substring(0, randomInd)
				+ new StringBuilder(refCode.substring(randomInd, refCode.indexOf("."))).reverse().toString()
				+ refCode.substring(refCode.indexOf(".")));
		return e.getPosition() + refCode.reverse().toString();
	}
}
