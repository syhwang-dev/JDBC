package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class h_sqlconnection_select {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null; 
		ResultSet rs = null;
		
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "scott", "tiger");
			
			st = con.createStatement();
			rs = st.executeQuery("Select dno, dname, budget from dept order by dno");
			
			while(rs.next() ) {
				System.out.println(String.format("%s, %s, %d",
						rs.getString("dno"), rs.getString("dname"), rs.getInt("budget")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}