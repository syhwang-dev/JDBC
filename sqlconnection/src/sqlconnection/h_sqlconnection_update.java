package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class h_sqlconnection_update {
	Connection con = null;
	Statement st = null;
	
	private boolean connectDB() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "scott", "tiger");
			System.out.println("데이터베이스가 연결되었습니다.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	
	private void closeDB() {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void updateData(String dno, String dname, int budget) {
		try {
		Statement st = con.createStatement();
		int cnt = st.executeUpdate(String.format("update dept set dname='%s', budget=%d where dno='%s'", dname, budget, dno));
 
		System.out.println("데이터가 " + cnt + "개 업데이트 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		h_sqlconnection_update tt = new h_sqlconnection_update();
		if (tt.connectDB()) {
			tt.updateData("d1", "dname1000", 1000);
			tt.closeDB();
		}
	}
}
