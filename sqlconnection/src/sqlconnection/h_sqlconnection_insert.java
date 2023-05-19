package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class h_sqlconnection_insert {
	Connection con = null;
	Statement st = null;
	
	private void insertDB(String dno, String dname, int budget) {
		
		try {
			String sql = "insert into dept (dno, dname, budget) values (?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dno);
			ps.setString(2, dname);
			ps.setInt(3, budget);
			ps.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터베이스가 입력되었습니다.");
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
	
	public static void main(String[] args) {
		h_sqlconnection_insert tt = new h_sqlconnection_insert();
		if (tt.connectDB()) {
			for (int i=1; i<101; i++) {
				tt.insertDB("d"+i, "dname"+i, 100+i*10);
			}
			tt.closeDB();
		}
	}
}