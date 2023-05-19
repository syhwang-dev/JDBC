package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class h_sqlconnection_insert_10 {
	Connection con = null;
	Statement st = null;
	
	private void insertData(int CID, String NAME, String CATEGORY, String ADDRESS, String WORK, String BIRTHDATY) {
		try {
			Statement st = con.createStatement();
			int cnt = st.executeUpdate(String.format("insert into contact (CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDATY) values ('%d', '%s', '%s', '%s', '%s', '%s')", CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDATY));
			System.out.println("데이터가 " + cnt + "개 입력되었습니다!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		h_sqlconnection_insert_10 tt = new h_sqlconnection_insert_10();
		if (tt.connectDB()) {
			tt.insertData(5, "HSZ", "family", "사상구", "F Company", "1998-01-01");
			tt.insertData(6, "MBQ", "etc", "부산진구", "A Company", "2002-01-01");
			tt.insertData(7, "DSS", "family", "해운대구", "B Company", "1990-01-01");
			tt.insertData(8, "UHN", "co-worker", "수영구", "Z Company", "2000-01-01");
			tt.insertData(9, "AQP", "friend", "동래구", "G Company", "1980-01-01");
			tt.closeDB();
		}
	}
}

