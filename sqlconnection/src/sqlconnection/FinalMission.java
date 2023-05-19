package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class QueryExe {
	int num;
	String text;
	
	QueryExe(int num, String text) {
		this.num = num;
		this.text = text;
	}
	
	int getNum() { return num; }
	String getText() { return text; }
}

public class FinalMission {
	
//	private static String[] queries = {
//			"select jname, city from j where city = 'London'",
//			"select jno, jname, city from j"
//			
//	};
	
//	private static String[] results = {
//	System.out.println(String.format("%s, %s", rs.getString("jname"), rs.getString("city")));
//	
//};
	
	private static Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	private static void closeDB(Connection con) {
		try {
			con.close();
//			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void exe01() {
		
		HashMap<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(
				"select jname, city from j where city = 'London'",
				"System.out.println(String.format('%s, %s', rs.getString('jname'), rs.getString('city')))");
		
		Connection con = connectDB();
		Statement st = null; 
		ResultSet rs = null;
		
		try {
			String sql = "select jname, city from j where city = 'London'";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(String.format("%s, %s", rs.getString("jname"), rs.getString("city")));
			}
			
			
		} catch (SQLException e) {
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
		
		System.out.println("*".repeat(80));
		
		closeDB(con);
	}
	
	public static void main(String[] args) {
		FinalMission fm = new FinalMission();
		
		ArrayList<QueryExe> list = new ArrayList<>();
		
		System.out.println("문제의 번호를 입력해주세요.\n[종료를 원할 경우 0을 입력하세요.]");
		list.add(new QueryExe(1, "London에 있는 프로젝트의 이름을 찾아라."));
		list.add(new QueryExe(2, "프로젝트 j1에 참여하는 공급자의 이름을 찾아라."));
		list.add(new QueryExe(3, "공급 수량이 300 이상 750 이하인 모든 공급의 sno, pno, qty를 찾아라."));
		list.add(new QueryExe(4, "부품의 color와 city의 모든 쌍을 찾아라. 같은 쌍은 한 번만 검색되어야 한다."));
		list.add(new QueryExe(5, "같은 도시에 있는 공급자, 부품, 프로젝트의 모든 sno, pno, jno 쌍을 찾아라. 찾아진 sno, pno, jno의 city들은 모두 같아야 한다."));
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			for (QueryExe qe : list) {
				System.out.println(String.format("%d. %s", qe.getNum(), qe.getText()));
			}
			
			System.out.println("번호 입력 >");
			int sel = sc.nextInt();
			if (sel == 0) break;
			
			switch(sel) {
			case 1 : exe01(); break;
			default : System.out.println("다시 선택해주세요.");
			}
			
			if (sel == 99) {
				// 사용자가 직접 sql을 입력
			}
		}
		System.out.println("종료합니다.");
	}
}

// 부모 클래스를 많드는 것 - 객체 지향적
// 계속 메소드를 추가하는 것 - 껍데기는 객체이지만 내부는 절차 지향
