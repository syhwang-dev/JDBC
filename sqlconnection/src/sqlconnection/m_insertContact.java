package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class m_insertContact {
	
	private void insertContactWithStatement(Connection con) {
		Statement st = null;
		String[] cates = {"friend", "family", "co-worker", "etc"};
		Random rand = new Random();
		int totcnt = 1000010;
		
		try {
			st = con.createStatement();
			
			for (int i=11; i <= totcnt; i++) {
				String name = "name" + i;
				String cate = cates[rand.nextInt(4)];  // 0 ~ 3
				String addr = "addr" + i;
				String company = "company" + i;
				String birth = String.format("%4d-%02d-%02d",
						rand.nextInt(1950, 2022), rand.nextInt(1, 13), rand.nextInt(1, 29));
				
				String sql = String.format("insert into contact (CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDATY) "
						+ "values (%d, '%s', '%s', '%s', '%s', '%s')", i, name, cate, addr, company, birth);
				
				st.executeUpdate(sql);
				
				System.out.println(String.format("%.2f:%d/%d", i*100/(double)totcnt, i, totcnt));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void insertPhoneWithPreparedStatement(Connection con) {
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String[] types = {"cellphone", "company", "home"};
		Random rand = new Random();
		int totcnt = 1000;
		
		try {
			String sql = "insert into phone (CID, SEQ, NUMBER, TYPE) values (?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			
			for (int i=1; i<=totcnt; i++) {
				
				int seq = i + 1;
				String number = String.format("010-%04d-%04d",
						rand.nextInt(1, 10000), rand.nextInt(1, 10000));
				String type = types[rand.nextInt(3)];

				ps.setInt(1, i);
				ps.setInt(2, seq);
				ps.setString(3, number);
				ps.setString(4, type);
				ps.executeUpdate();
//				rs = ps.executeQuery();
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb", "scott", "tiger");
			System.out.println("데이터베이스가 연결되었습니다.");
//			return true;
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	private void closeDB(Connection con) {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		m_insertContact tt = new m_insertContact();
		
		Connection con = tt.connectDB();
		if (con != null) {
//			tt.insertContactWithStatement(con);
			tt.insertPhoneWithPreparedStatement(con);
			
			tt.closeDB(con);
		}		
	}

}
