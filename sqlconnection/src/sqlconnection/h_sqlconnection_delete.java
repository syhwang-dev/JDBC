package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class h_sqlconnection_delete {
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

	
	private void deleteData(int from, int to) {
		try {
		Statement st = con.createStatement();
		int cnt = st.executeUpdate(String.format("delete from dept where %d <= id and id <= %d", from, to));
 
		System.out.println("데이터가 " + cnt + "개 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		h_sqlconnection_delete tt = new h_sqlconnection_delete();
		if (tt.connectDB()) {
			tt.deleteData(8, 10);
			tt.closeDB();
		}
	}
}
