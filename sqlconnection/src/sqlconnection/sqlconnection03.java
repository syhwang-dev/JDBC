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

public class sqlconnection03 {
	Connection con = null;
	Statement st = null;
	
	// Statement를 사용하는 방법
//	private void insertDeptStatement(String dno, String dname, int budget) {
//		
//		// %d는 숫자이기 때문에 홑따옴표로 감싸지 않아도 됨.
//		String sql = String.format("insert into dept (dno, dname, budget) value ('%s', '%s', %d)", dno, dname, budget);
//		
//		try {
//			st = con.createStatement();
//			int result = st.executeUpdate(sql);  // 사실 result로 저장할 필요는 없음. 하지만 만든 김에 바로 아래 출력문에 이용해보자.
//			System.out.println("데이터베이스에 " + result + "개가 입력되었습니다!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
////		System.out.println("데이터베이스가 입력되었습니다!");
//	}
	
	// 교수님 방법
	// Statement, executeUpdate
	private void insertDeptStatement(String dno, String dname, int budget) {
		try {
			Statement st = con.createStatement();
			int cnt = st.executeUpdate(String.format("insert into dept (dno, dname, budget) value ('%s', '%s', %d)", dno, dname, budget));
			// 체인 코딩 방법도 있음 
			System.out.println("데이터가 " + cnt + "개 입력되었습니다!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// prepareStatement을 사용하는 방법
	// PreparedStatement, executeUpdate 사용
	private void insertDept(String dno, String dname, int budget) {
		
		String sql = "insert into dept (dno, dname, budget) value (?, ?, ?)";
		
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
	
	public static void main(String[] args) {
		sqlconnection03 tt = new sqlconnection03();  // static을 사용하지 않기 위해 생성
		if (tt.connectDB()) {
			tt.insertDeptStatement("d30", "dname30", 290);
			tt.closeDB();
		}
	}
}
