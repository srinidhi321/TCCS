/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import Book.*;
import Employee.*;
import Exceptions.BooksOutOfRangeException;
import Exceptions.InvalidPhoneException;
import Exceptions.LessAccessRightsException;
import SQL_DataHandler.DataHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author ridri
 */
public class InventoryDatabase {
	private int threshold = 5;

	public InventoryDatabase(String dbUserId, String dbPassword) throws LessAccessRightsException {
		if (!DataHandler.matchesUserId(dbUserId) || !DataHandler.matchesPassword(dbPassword))
			throw new LessAccessRightsException("Opening application");
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getThreshold() {
		return threshold;
	}

	public ArrayList<BookInStock> getBooksBelowThreshold() throws SQLException, InvalidPhoneException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		try {
			rset = datahandler.executeQuery("select * from Stock where copy_num < '" + threshold + "'");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		ArrayList<BookInStock> list = new ArrayList<>();
		while (rset.next()) {
			list.add(new BookInStock(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
					rset.getDouble(5), rset.getInt(6), rset.getInt(7), rset.getInt(8), rset.getInt(9),
					rset.getDouble(10), new Vendor(rset.getString(11), rset.getString(12), rset.getLong(13))));
		}
		datahandler.close();
		return list;
	}

	private String generateId(BookInStock b) {
		String code = LocalDateTime.now().toString().substring(2);
		StringBuilder refCode = new StringBuilder(
				code.replaceAll(" ", "").toLowerCase().replaceAll("-", "").replaceAll(":", "").replaceAll("t", ""));
		int randomInd = (int) (Math.random() * (12));
		refCode = new StringBuilder(refCode.substring(0, randomInd)
				+ new StringBuilder(refCode.substring(randomInd, refCode.indexOf("."))).reverse().toString()
				+ refCode.substring(refCode.indexOf(".")));
		return "ISBN" + refCode.reverse().toString();
	}

	public String addtoInventory(BookInStock bookInStock) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		String isbn = generateId(bookInStock);
		try {
			rset = datahandler.executeQuery("select copy_num from Stock where ISBN = '" + isbn + "'");
			if (rset.next()) {
				datahandler.executeQuery("update Stock set copy_num = '" + (rset.getInt(1) + bookInStock.getCopyNum())
						+ "' where ISBN = '" + isbn + "'");
			} else {
				datahandler.executeQuery("INSERT INTO stock values('" + isbn + "','" + bookInStock.title + "','"
						+ bookInStock.author + "','" + bookInStock.publisher + "','" + bookInStock.costPrice + "','"
						+ bookInStock.getCopiesSold() + "','" + bookInStock.getCopyNum() + "','"
						+ bookInStock.getNoOfDaysToProcure() + "','" + bookInStock.getRackNum() + "','"
						+ bookInStock.getSellingPrice() + "','" + bookInStock.getVendor().getName() + "','"
						+ bookInStock.getVendor().getAddress() + "','" + bookInStock.getVendor().getPhoneNo() + "')");
			}
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return isbn;
	}

	public boolean removeBookInStock(String ISBN, int copyNum) throws SQLException, BooksOutOfRangeException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		try {
			rset = datahandler.executeQuery("select copy_num from Stock where ISBN = '" + ISBN + "'");
			while (rset.next()) {
				if (rset.getInt(1) == copyNum) {
					datahandler.executeQuery("delete from Stock where ISBN = '" + ISBN + "'");
				} else if (rset.getInt(1) > copyNum) {
					datahandler.executeQuery("update Stock set copy_num = '" + (rset.getInt(1) - copyNum)
							+ "' where isbn = '" + ISBN + "'");
				} else {
					throw new BooksOutOfRangeException(copyNum, rset.getInt(1));
				}
			}
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return true;
	}

	public ArrayList<BookInStock> searchBookInStock(String ISBN, String title, String author, String publish)
			throws SQLException, InvalidPhoneException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		try {
			if (ISBN != null) {
				rset = datahandler.executeQuery("select * from Stock where isbn = '" + ISBN + "'");
			} else {
				StringBuilder query = new StringBuilder("select * from Stock ");
				boolean joiner = false;
				if (title != null && !"".equals(title)) {
					query.append("where title = '").append(title).append("' ");
					joiner = true;
				}
				if (author != null && !"".equals(author)) {
					if (joiner)
						query.append("and ");
					else
						query.append("where ");
					query.append("author = '").append(author).append("' ");
				}
				if (publish != null && !"".equals(publish)) {
					if (joiner)
						query.append("and ");
					else
						query.append("where ");
					query.append("publisher = '").append(publish).append("'");
				}
				rset = datahandler.executeQuery(query.toString());
			}
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		ArrayList<BookInStock> list = new ArrayList<>();
		while (rset.next()) {
			list.add(new BookInStock(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
					rset.getDouble(5), rset.getInt(6), rset.getInt(7), rset.getInt(8), rset.getInt(9),
					rset.getDouble(10), new Vendor(rset.getString(11), rset.getString(12), rset.getLong(13))));
		}
		datahandler.close();
		return list;
	}

	public String changeBookInStock(String ISBN, BookInStock newBs) throws SQLException {
		DataHandler datahandler = new DataHandler();
		String new_isbn = generateId(newBs);
		try {
			datahandler.executeQuery("UPDATE stock SET " + "ISBN = '" + new_isbn + "'," + "title = '" + newBs.title
					+ "'," + "author = '" + newBs.author + "'," + "publisher= '" + newBs.publisher + "',"
					+ "cost_price= '" + newBs.costPrice + "'," + "copies_sold= '" + newBs.getCopiesSold() + "',"
					+ "copy_num= '" + newBs.getCopyNum() + "'," + "days_to_procure= '" + newBs.getNoOfDaysToProcure()
					+ "'," + "rack_num= '" + newBs.getRackNum() + "'," + "sell_price= '" + newBs.getSellingPrice()
					+ "'," + "vendor_name = '" + newBs.getVendor().getName() + "'," + "vendor_add = '"
					+ newBs.getVendor().getAddress() + "'," + "vendor_phone= '" + newBs.getVendor().getPhoneNo() + "' "
					+ "WHERE ISBN = '" + ISBN + "'");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
		return new_isbn;
	}

	public ArrayList<BookNotInStock> searchBookNotInStock(String title, String author, String publish)
			throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		try {
			{
				StringBuilder query = new StringBuilder("select * from requests ");
				boolean joiner = false;
				if (title != null && !"".equals(title)) {
					query.append("where title = '").append(title).append("' ");
					joiner = true;
				}
				if (author != null && !"".equals(author)) {
					if (joiner)
						query.append("and ");
					else
						query.append("where ");
					query.append("author = '").append(author).append("' ");
				}
				if (publish != null && !publish.isEmpty()) {
					if (joiner)
						query.append("and ");
					else
						query.append("where ");
					query.append("publisher = '").append(publish).append("'");
				}
				rset = datahandler.executeQuery(query.toString());
			}
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		ArrayList<BookNotInStock> list = new ArrayList<>();
		while (rset.next()) {
			list.add(new BookNotInStock(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
					rset.getInt(5)));
		}
		datahandler.close();
		return list;
	}

	public void addToRequests(String title, String author, String publisher, double cost_price) throws SQLException {
		DataHandler datahandler = new DataHandler();
		ResultSet rset;
		try {
			ArrayList<BookNotInStock> bns = searchBookNotInStock(title, author, publisher);
			if (bns.size() >= 1) {
				StringBuilder query = new StringBuilder(
						"UPDATE requests SET " + "request_field= '" + (bns.get(0).getRequestField() + 1) + "' ");
				boolean joiner = false;
				if (title != null && !"".equals(title)) {
					query.append("where title = '").append(title).append("' ");
					joiner = true;
				}
				if (author != null && !"".equals(author)) {
					if (joiner)
						query.append("and ");
					else
						query.append("where ");
					query.append("author = '").append(author).append("' ");
				}
				if (publisher != null && !publisher.isEmpty()) {
					if (joiner)
						query.append("and ");
					else
						query.append("where ");
					query.append("publisher = '").append(publisher).append("'");
				}
				datahandler.executeQuery(query.toString());
			} else
				datahandler.executeQuery("INSERT INTO requests values('" + title + "','" + author + "','" + publisher
						+ "','" + cost_price + "','" + 1 + "')");
		} catch (SQLException e) {
			datahandler.close();
			throw e;
		}
		datahandler.close();
	}
}
