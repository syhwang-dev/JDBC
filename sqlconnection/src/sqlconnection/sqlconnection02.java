package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sqlconnection02 {
	Connection con = null;
	
	// static이 없음.
	private void insertDept(String dno, String dname, int budget) {
		
		String sql = "insert into dept (dno, dname, budget) value (?, ?, ?)";  // 실제로 실행하고자 하는 sql문
//		String sql = "String.format("insert into dept (dno, dname, budget) value ('%s', '%s', '%d')", dno);  // 완결된 문장인 경우, prepareStatement()가 아닌 createStatement() 사용
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);  // prepareStatement() vs. createStatement()
			ps.setString(1, dno);
			ps.setString(2, dname);
			ps.setInt(3, budget);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터베이스가 입력되었습니다.");
	}
	
	// static이 없음.
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
	
	// static이 없음.
	private void closeDB() {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		sqlconnection02 tt = new sqlconnection02();  // static을 사용하지 않기 위해 생성
		if (tt.connectDB()) {
			// 값이 기존 테이블의 끝에서 부터 들어가지 않고 dno 기준으로 입력됨. dno 값이 중복이 될 경우, 키 값이 중복이 되므로 에러 발생
//			tt.insertDept("d10", "sales", 300);
			tt.insertDept("d21", "dname21", 170);
			tt.insertDept("d8", "dname8", 40);   
			tt.closeDB();
		}
	}
}
