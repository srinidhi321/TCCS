package databases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Id {
	public static int getId() {
		Data d = GD.d;
		int temp, fare = -1;
		try {
			d.stmt = d.conn.createStatement();
			String sql = "SELECT id FROM uni";
			ResultSet rs = d.stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				fare = rs.getInt("id");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fare;
}
	public static void updateId(int id)
	{
		int previd=Id.getId();
		Data d = GD.d;
		int temp, fare = -1;
		try {
			d.stmt = d.conn.createStatement();
			String sql = "UPDATE uni SET id ="+id;
			d.stmt.executeUpdate(sql);
			// STEP 5: Extract data from result set
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
