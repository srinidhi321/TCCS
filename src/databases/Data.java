package databases;

//STEP 1. Import required packages
import java.sql.*;

public class Data {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/tccs";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "udupi";
	public Connection conn = null;
	public Statement stmt = null;

	public Data() {
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (stmt != null)
				conn.close();
		} catch (SQLException se) {
		} // do nothing
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println("connection closed");
	}
}
