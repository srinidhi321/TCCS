/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import Book.*;
import Employee.Employee;
import Exceptions.InvalidPhoneException;
import Exceptions.LessAccessRightsException;
import SQL_DataHandler.DataHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author ridri
 */
public class SalesDatabase {
	private String userid;
	private String password;

	public SalesDatabase(String dbUserId, String dbPassword) throws LessAccessRightsException {
		if (!DataHandler.matchesUserId(dbUserId) || !DataHandler.matchesPassword(dbPassword))
			throw new LessAccessRightsException("Opening application");
		else {
			userid = dbUserId;
			password = dbPassword;
		}
	}

	public String generateId() {
		String code = LocalDateTime.now().toString().substring(2);
		StringBuilder refCode = new StringBuilder(
				code.replaceAll(" ", "").toLowerCase().replaceAll("-", "").replaceAll(":", "").replaceAll("t", ""));
		int randomInd = (int) (Math.random() * (12));
		refCode = new StringBuilder(refCode.substring(0, randomInd)
				+ new StringBuilder(refCode.substring(randomInd, refCode.indexOf("."))).reverse().toString()
				+ refCode.substring(refCode.indexOf(".")));
		return "TRN" + refCode.reverse().toString();
	}

	public String addTransaction(Transaction transaction) throws SQLException {
		DataHandler datahandler = new DataHandler();
		String res_id = generateId();
		try {
			datahandler.executeQuery("INSERT INTO sales values('" + res_id + "','" + transaction.getBookSold().getISBN()
					+ "','" + transaction.getCopiesSold() + "', to_date('"
					+ transaction.getDateOfTrans().toString().replace('T', ' ') + "','YYYY-MM-DD HH24:MI:SS'), '"
					+ transaction.getCustomerName() + "','" + transaction.getCustomerAddress() + "','"
					+ transaction.getCustomerMail() + "','" + transaction.getCustomerPhone() + "','"
					+ transaction.getClerkId() + "')");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		Transaction sold = new Transaction(res_id, transaction);
		return sold.toString(true);
	}

	public ArrayList<Transaction> getTransaction(String cusName, LocalDateTime date, String transId)
			throws SQLException, InvalidPhoneException, LessAccessRightsException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		try {
			StringBuilder query = new StringBuilder("select * from Sales ");
			boolean result = false;
			if (transId != null && !transId.isEmpty()) {
				query.append("where trans_id = '").append(transId).append("'");
			} else {
				if (cusName != null && !cusName.isEmpty()) {
					query.append("where cus_name = '").append(cusName).append("' ");
					result = true;
				}
				if (date != null) {
					if (result)
						query.append("and ");
					else
						query.append("where ");
					query.append("trunc(trans_date) = to_date('").append(date.toString().replace('T', ' '))
							.append("', 'YYYY-MM-DD') ");
				}
			}
			rset = datahandler.executeQuery(query.toString());
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		ArrayList<Transaction> t = new ArrayList<>();
		while (rset.next()) {
			t.add(new Transaction(rset.getString(1),
					new InventoryDatabase(userid, password).searchBookInStock(rset.getString(2), null, null, null)
							.get(0),
					rset.getInt(3), LocalDateTime.of(rset.getDate(4).toLocalDate(), rset.getTime(4).toLocalTime()),
					rset.getString(5), rset.getString(6), rset.getString(7), rset.getLong(8), rset.getString(9)));
		}
		datahandler.close();
		return t;
	}
}
