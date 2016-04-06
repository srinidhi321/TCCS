package databases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class fareDB {
	public static int getFare(int from, int to) {
		Data d = GD.d;
		int temp, fare = -1;
		if (to < from) {
			temp = to;
			to = from;
			from = temp;
		}
		try {
			d.stmt = d.conn.createStatement();
			String sql = "SELECT id, fromm, too, f FROM fare WHERE fromm = " + from;
			ResultSet rs = d.stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				if(rs.getInt("too")==to) fare = rs.getInt("f");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fare;

	}
	
}
