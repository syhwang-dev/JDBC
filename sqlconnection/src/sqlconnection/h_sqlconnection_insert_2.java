package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class h_sqlconnection_insert_2 {
	
	private Connection con = null;

	public static void main(String[] args) {
		
		h_sqlconnection_insert_2 h01 = new h_sqlconnection_insert_2();
		if (h01.connectDB() == true) {
			for (int i=101; i<111; i++) {
				if (h01.insertData("d" + i, "dname" + i, i) == false) {
					System.out.println("데이터 입력에 실패했습니다.");
					break;
				}
			}
			h01.closeDB();
		}
	}
	
	private boolean insertData(String dno, String dname, int budget) {
		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(String.format("insert into dept (dno, dname, budget) values ('%s', '%s', '%d')",
					dno, dname, budget));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터베이스가 입력되었습니다.");
		return false;
	}
	
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
}


