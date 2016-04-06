/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import SQL_DataHandler.DataHandler;
import Book.*;
import Employee.*;
import Exceptions.BooksOutOfRangeException;
import Exceptions.InvalidPhoneException;
import Exceptions.LessAccessRightsException;
import Exceptions.PropertyNotUniqueException;
import Exceptions.PropertyValueMismatchException;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author ridri
 */
public class Database {
	private SalesDatabase sales;
	private EmployeeDatabase employees;
	private InventoryDatabase inventory;
	private String dbUserId;
	private String dbPassword;

	public Database(String dbUserId, String dbPassword) throws LessAccessRightsException {
		sales = new SalesDatabase(dbUserId, dbPassword);
		employees = new EmployeeDatabase(dbUserId, dbPassword);
		inventory = new InventoryDatabase(dbUserId, dbPassword);
	}

	public Database(String ownerName, String ownerAdd, double ownerSalary, long ownerPhone, String ownerEmail,
			String dbUserId, String dbPassword) throws SQLException, InvalidPhoneException, LessAccessRightsException {
		sales = new SalesDatabase(dbUserId, dbPassword);
		employees = new EmployeeDatabase(dbUserId, dbPassword);
		inventory = new InventoryDatabase(dbUserId, dbPassword);
		try {
			delAllEmployees();
			delAllRequests();
			delAllStock();
			delAllTransactions();
			addOwner(new Owner(ownerName, ownerAdd, ownerSalary, ownerPhone, ownerEmail));
		} catch (SQLException | InvalidPhoneException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public String addEmployee(Employee employee, String managerId) throws SQLException, LessAccessRightsException {
		if (employees.isManager(managerId))
			return employees.addEmployee(employee);
		else
			throw new LessAccessRightsException("add Employee");
	}

	public boolean delEmployee(String[] property, String[] value, String managerId)
			throws SQLException, LessAccessRightsException, PropertyValueMismatchException, PropertyNotUniqueException,
			InvalidPhoneException {
		if (employees.isManager(managerId))
			return employees.delEmployee(property, value);
		else
			throw new LessAccessRightsException("delete Employee");
	}

	// only used during uninstallation and installation
	private boolean delAllEmployees() throws SQLException {
		DataHandler datahandler = new DataHandler();

		try {
			datahandler.executeQuery("delete from Employee commit");
			datahandler.close();
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		return true;
	}

	private void delAllRequests() throws SQLException {
		DataHandler datahandler = new DataHandler();
		try {
			datahandler.executeQuery("delete from Requests commit");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
	}

	private void delAllStock() throws SQLException {
		DataHandler datahandler = new DataHandler();
		try {
			datahandler.executeQuery("delete from Stock commit");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
	}

	private void delAllTransactions() throws SQLException {
		DataHandler datahandler = new DataHandler();
		try {
			datahandler.executeQuery("delete from Sales commit");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
	}

	public ArrayList<Employee> getEmployeeList(String managerId)
			throws SQLException, LessAccessRightsException, InvalidPhoneException {
		if (employees.isManager(managerId))
			return employees.getEmployeeList();
		else
			throw new LessAccessRightsException("get Employee List");
	}

	public Employee getEmployee(String[] property, String[] value, String managerId)
			throws SQLException, LessAccessRightsException, PropertyValueMismatchException, PropertyNotUniqueException,
			InvalidPhoneException {
		if (employees.isManager(managerId))
			return employees.getEmployee(property, value);
		else
			throw new LessAccessRightsException("get Employee");
	}

	public Employee getEmployeeById(String empId)
			throws SQLException, PropertyValueMismatchException, PropertyNotUniqueException, InvalidPhoneException {
		String prop[] = { "emp_id" };
		String value[] = { empId };
		return employees.getEmployee(prop, value);
	}

	public Manager getManager(String empId) throws SQLException, LessAccessRightsException, InvalidPhoneException {
		if (employees.isManager(empId) || employees.isOwner(empId)) {
			return employees.getManager();
		} else {
			throw new LessAccessRightsException("get Manager");
		}
	}

	public Owner getOwner(String empId) throws SQLException, InvalidPhoneException, LessAccessRightsException {
		if (employees.isManager(empId) || employees.isOwner(empId)) {
			return employees.getOwner();
		} else {
			throw new LessAccessRightsException("get Owner");
		}
	}

	public String addManager(Manager manager, String ownerId) throws SQLException, LessAccessRightsException {
		if (employees.isOwner(ownerId))
			return employees.addManager(manager);
		else
			throw new LessAccessRightsException("add Manager");
	}

	private String addOwner(Owner owner) throws SQLException {
		return employees.addOwner(owner);
	}

	public String changeOwner(Owner newOwner, String prev_ownerId) throws SQLException, LessAccessRightsException {
		if (employees.isOwner(prev_ownerId)) {
			return employees.changeOwner(newOwner);
		} else {
			throw new LessAccessRightsException("change Manager");
		}

	}

	public String changeManager(Manager newManager, String ownerId) throws SQLException, LessAccessRightsException {
		if (employees.isOwner(ownerId)) {
			return employees.changeManager(newManager);
		} else {
			throw new LessAccessRightsException("change Manager");
		}
	}

	public String changeEmployee(String[] property, String[] value, Employee newEmp, String managerId)
			throws SQLException, PropertyNotUniqueException, PropertyValueMismatchException, LessAccessRightsException {
		if (employees.isManager(managerId)) {
			return employees.changeEmployee(property, value, newEmp);
		} else {
			throw new LessAccessRightsException("change Employee");
		}
	}

	public String addTransaction(BookInStock bs, int copyNum, String cusName, LocalDateTime date, String cusAddress,
			long cusPhone, String clerkId, String customerEmail)
			throws SQLException, InvalidPhoneException, LessAccessRightsException {
		if (employees.isSalesClerk(clerkId))
			return sales.addTransaction(
					new Transaction(bs, copyNum, date, cusName, cusAddress, customerEmail, cusPhone, clerkId));
		else
			throw new LessAccessRightsException("purchase (only by Sales Clerk)");
	}

	public ArrayList<Transaction> getTransaction(String cusName, LocalDateTime date, String transId, String clerkId)
			throws SQLException, LessAccessRightsException, InvalidPhoneException {
		if (employees.isSalesClerk(clerkId))
			return sales.getTransaction(cusName, date, transId);
		else
			throw new LessAccessRightsException("get transaction (only by Sales Clerk)");
	}

	public ArrayList<String> generateSalesStatistics(String ownerId)
			throws SQLException, LessAccessRightsException, InvalidPhoneException {
		if (!employees.isOwner(ownerId)) {
			throw new LessAccessRightsException("generate Sales Statistics");
		}
		ArrayList<BookInStock> bs = searchBookInStock(null, null, null, null);
		ArrayList<String> statistics = new ArrayList<>();
		bs.forEach(e -> {
			statistics.add(e.toString(true) + "\nRevenue of book:" + e.getProfit());
		});
		return statistics;
	}

	public String addToInventory(String title, String author, String publisher, double costPrice, int copyNum,
			int noOfDaysToProcure, int rackNum, double sellingPrice, Vendor vendor, String empId)
			throws SQLException, LessAccessRightsException {
		if (employees.isEmployee(empId)) {
			return inventory.addtoInventory(new BookInStock(title, author, publisher, costPrice, copyNum,
					noOfDaysToProcure, rackNum, sellingPrice, vendor));
		} else
			throw new LessAccessRightsException("add Book to stock");
	}

	public String addToInventory(BookInStock book, String empId) throws SQLException, LessAccessRightsException {
		if (employees.isEmployee(empId)) {
			return inventory.addtoInventory(book);
		} else
			throw new LessAccessRightsException("add Book to stock");
	}

	public void addToRequests(String title, String author, String publisher, double cost_price) throws SQLException {
		inventory.addToRequests(title, author, publisher, cost_price);
	}

	public boolean removeBookInStock(String ISBN, int copyNum, String empId)
			throws SQLException, BooksOutOfRangeException, LessAccessRightsException {
		if (employees.isEmployee(empId)) {
			return inventory.removeBookInStock(ISBN, copyNum);
		} else {
			throw new LessAccessRightsException("remove Book In Stock");
		}
	}

	public ArrayList<BookInStock> searchBookInStock(String ISBN, String title, String author, String publish)
			throws SQLException, InvalidPhoneException {
		return inventory.searchBookInStock(ISBN, title, author, publish);
	}

	public String changeBookInStock(String ISBN, BookInStock newBsm, String empId)
			throws SQLException, LessAccessRightsException {
		if (employees.isEmployee(empId))
			return inventory.changeBookInStock(ISBN, newBsm);
		else
			throw new LessAccessRightsException("change Book");
	}

	public ArrayList<BookNotInStock> searchBookNotInStock(String title, String author, String publish, String managerId)
			throws SQLException, LessAccessRightsException {
		if (employees.isManager(managerId))
			return inventory.searchBookNotInStock(title, author, publish);
		else
			throw new LessAccessRightsException("search Book Requests");
	}

	public ArrayList<BookInStock> getBooksBelowThreshold(String ownerId)
			throws SQLException, InvalidPhoneException, LessAccessRightsException {
		if (employees.isOwner(ownerId))
			return inventory.getBooksBelowThreshold();
		else
			throw new LessAccessRightsException("get Books Below Threshold");
	}

	public String purchaseBook(BookInStock bs, int copyNum, String cusName, String cusAddress, long cusPhone,
			String clerkId, String customerEmail)
			throws SQLException, BooksOutOfRangeException, LessAccessRightsException, InvalidPhoneException {
		if (employees.isSalesClerk(clerkId)) {
			removeBookInStock(bs.getISBN(), copyNum, clerkId);
			DataHandler d = new DataHandler();
			try {
				d.executeQuery("update Stock set copies_sold = " + (bs.getCopiesSold() + 1));
			} catch (SQLException e) {
				System.out.println("All copies of book have been sold");
			}
			d.close();
			String date = LocalDateTime.now().toString();
			String transId = addTransaction(bs, copyNum, cusName,
					LocalDateTime.parse(date.substring(0, date.indexOf('.')), DateTimeFormatter.ISO_DATE_TIME),
					cusAddress, cusPhone, clerkId, customerEmail);
			return getTransaction(null, null, transId, clerkId).get(0).toString();
		} else
			throw new LessAccessRightsException("purchase Book");
	}

	public void commit() throws SQLException {
		DataHandler datahandler = new DataHandler();
		try {
			datahandler.executeQuery("commit");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
	}

	public void rollback() throws SQLException {
		DataHandler datahandler = new DataHandler();
		try {
			datahandler.executeQuery("rollback");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
	}

}
