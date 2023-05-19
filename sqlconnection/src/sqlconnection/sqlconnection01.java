package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlconnection01 {

	public static void main(String[] args) {
		// finally에서도 사용하기 위해 try문 밖에서 null로 초기화
		Connection con = null;  // 연결을 위한 객체
		Statement st = null;  // 질의를 날리기 위한 객체
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");
			
			st = con.createStatement();
			rs = st.executeQuery("Select sno, pno, jno, qty from spj order by sno");  // executeQuery 메소드를 통해 rs 참조 변수에 값을 넣어줌.
			// rs 변수를 하나씩 훑는 것 (커서처럼): while문
			
			while(rs.next() ) {
				System.out.println(String.format("%s, %s, %s, %d",
						rs.getString("sno"), rs.getString("pno"), rs.getString("jno"), rs.getInt("qty")));  // 데이터에 맞는 데이터 타입으로 가져와야 함.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Connection con = null 등등을 리소스라고 하며 가급적 닫아주는 것이 좋음. 가장 마지막에 열린 rs를 먼저 닫아줌. 아래처럼 코드를 짜면 안정성이 높아짐.
			try {
				if (rs != null) rs.close();  // 단순히 rs.close(); 하면 finally에서 에러가 발생할 수 있음.
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
