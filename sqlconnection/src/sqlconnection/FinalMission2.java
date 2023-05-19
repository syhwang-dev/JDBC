package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class QueryExe2 {
	int num;
	String text;
	
	QueryExe2(int num, String text) {
		this.num = num;
		this.text = text;
	}
	
	int getNum() { return num; }
	String getText() { return text; }
}

public class FinalMission2 {
	
	private static HashMap<Integer, String[]> queryMap;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void resultMap() {
		HashMap<Integer, String[]> queryMap = new HashMap<Integer, String[]>();
		queryMap.put(1, new String[] {
				"select jname, city from j where city = 'London'",
				"System.out.println(String.format('%s, %s', rs.getString('jname'), rs.getString('city')))"});
		queryMap.put(2, new String[] {
				"select sname, jno from s, j where jno = 'J1' and j.city = s.city",
				"System.out.println(String.format('%s, %s', rs.getString('sname'), rs.getString('jno')))"});
		queryMap.put(3, new String[] {
				"select sno, pno, qty from spj where 300 < qty and qty < 750;",
				"System.out.println(String.format('%s, %s, %d', rs.getString('sno'), rs.getString('pno'), rs.getInt(qty)))"});
		queryMap.put(4, new String[] {
				"select distinct color, city from p",
				"System.out.println(String.format('%s, %s', rs.getString('color'), rs.getString('city')))"});
		queryMap.put(5, new String[] {
				"select sname, jno from s, j where jno = 'J1' and j.city = s.city",
				"System.out.println(String.format('%s, %s', rs.getString('sname'), rs.getString('jno')))"});
		
	}
	
	public static void exe01() {
		
		Connection con = connectDB();
		Statement st = null; 
		ResultSet rs = null;
		
		try {
			String[] query = queryMap.get(1);
			
			st = con.createStatement();
			rs = st.executeQuery(query[0]);
			
			while (rs.next()) {
				String printStr = query[1];
				System.out.println(printStr);
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
			
			
			
//			if 
			if (sel == 0) break;
			
			switch(sel) {
			case 1 : exe01(); break;
			default : System.out.println("다시 선택해주세요.");
			}
		}
		System.out.println("종료합니다.");
	}
}