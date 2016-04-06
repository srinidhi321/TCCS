package databases;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * mysql> CREATE TABLE java_objects ( 
 * id INT AUTO_INCREMENT, 
 * name varchar(128), +
 * object_value BLOB, 
 * primary key (id));
 **/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import TCCS.Manager;

public class Serialize {
  static final String WRITE_OBJECT_SQL = "INSERT INTO java_objects(name, object_value) VALUES (?, ?)";

  static final String READ_OBJECT_SQL = "SELECT object_value FROM java_objects WHERE id = ?";

  public static Connection getConnection() throws Exception {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost/tccs";
    String username = "root";
    String password = "udupi";
    Class.forName(driver);
    Connection conn = DriverManager.getConnection(url, username, password);
    return conn;
  }

  public static long writeJavaObject(Connection conn, Object object) throws Exception {
	 int previd = Id.getId();
    String className = object.getClass().getName();
    PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL,Statement.RETURN_GENERATED_KEYS);
    Statement stmt=null;
    // set input parameters
    pstmt.setString(1, className);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(object);
    oos.flush();
    oos.close();
    pstmt.setBytes(2, baos.toByteArray());
    pstmt.executeUpdate();
    // get the generated key for the id
    ResultSet rs = pstmt.getGeneratedKeys();
    int id = -1;
    if (rs.next()) {
      id = rs.getInt(1);
    }
    rs.close();
    pstmt.close();
    stmt = conn.createStatement();
    String sql = "DELETE FROM java_objects " +
                 "WHERE id ="+ previd;
    stmt.executeUpdate(sql);
    System.out.println("writeJavaObject: done serializing: " + className);
    return id;
  }

  public static Object readJavaObject(Connection conn, long id) throws Exception {
    PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
    pstmt.setLong(1, id);
    ResultSet rs = pstmt.executeQuery();
    rs.next();
    byte[] b = rs.getBytes("object_value");
    InputStream is = new ByteArrayInputStream(b);
    ObjectInputStream ois = new ObjectInputStream(is);
    Object object = ois.readObject();
    String className = object.getClass().getName();
    rs.close();
    pstmt.close();
    System.out.println("readJavaObject: done de-serializing: " + className);
    return object;
  }
  public static void main(String args[])throws Exception {
    Connection conn = null;
    try {
      conn = getConnection();
      System.out.println("conn=" + conn);
      conn.setAutoCommit(false);
      Manager m = new Manager("partha pratim das","ppd","ppd");

      long objectID = writeJavaObject(conn, m);
      conn.commit();
      System.out.println("Serialized objectID => " + objectID);
      Manager listFromDatabase = (Manager) readJavaObject(conn, objectID);
      System.out.println("[After De-Serialization] list=" + listFromDatabase);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      conn.close();
    }
  }
}
