package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqlconnection {

	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Success");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch(Exception e) {}
		}
	}

}
