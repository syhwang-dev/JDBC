package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// 총 4가지가 나올 수 있음. - 반드시 기억 / 데이터베이스에서 값을 가져올 때 반드시 사용

// Statement: 완결된 Query 문장을 실행할 때
// PreparedStatement: 변수로 값을 추가할 수 있는 Query문을 실행할 때
// select문 일 때, executeQuery 사용 - PreparedStatement, Statement
// insert/delete/update문 일 때, executeUpdate 사용 - PreparedStatement, Statement

public class sqlconnection05 {
	Connection con = null;
	Statement st = null;
	
	private boolean connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");
			System.out.println("데이터베이스가 연결되었습니다.");
			return true;  // 성공하면 true 리턴 / 만약 연결이 제대로 되지 않았으면 return false가 응답
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
	

	private void updateDeptPrepared(String dno, String dname, int budget) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("update dept set dname=?, budget=? where dno=?");
			ps.setString(1, dname);
			ps.setInt(2, budget);
			ps.setString(3, dno);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateDeptStatement(String dno, String dname, int budget) {
		try {
		Statement st = con.createStatement();
		int cnt = st.executeUpdate(String.format("update dept set dname='%s', budget=%d where dno='%s'", dname, budget, dno));  // 홑따옴표 꼭!  // 물음표일 때는 자동으로 싸주지만 직접 데이터를 넣을 때는 따옴표 필수!
 
		System.out.println("데이터가 " + cnt + "개 업데이트 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		sqlconnection05 tt = new sqlconnection05();
		if (tt.connectDB()) {
//			tt.deleteDepttriggerStatement(1, 3);
//			tt.updateDeptPrepared("d1", "dname1",1000);
			tt.updateDeptStatement("d1", "dname8", 300);
			tt.closeDB();
		}
	}
}
