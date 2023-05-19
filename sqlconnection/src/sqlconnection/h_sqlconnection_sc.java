package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class h_sqlconnection_sc {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	private void insertDB() {
		Scanner sc = new Scanner(System.in);
		try {
			int CID;
			String NAME, CATEGORY, ADDRESS, WORK, BIRTHDATY;
			while(true) {
				System.out.print("CID : ");
				CID = sc.nextInt();
				
				System.out.print("NAME : ");
				NAME = sc.next();
				
				System.out.print("CATEGORY : ");
				CATEGORY = sc.next();
				
				System.out.print("ADDRESS : ");
				ADDRESS = sc.next();
				
				System.out.print("WORK : ");
				WORK = sc.next();
				
				System.out.print("BIRTHDATY : ");
				BIRTHDATY = sc.next();
				
				String sql = "insert into dept (CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDATY) values (?, ?, ?, ?, ?, ?)";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, CID);
				ps.setString(2, NAME);
				ps.setString(3, CATEGORY);
				ps.setString(4, ADDRESS);
				ps.setString(5, WORK);
				ps.setString(6, BIRTHDATY);
				ps.executeUpdate();
				if(CID > 7) break;
			}
			st = con.createStatement();
			rs = st.executeQuery("Select * from contact order by dno");
			
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
		h_sqlconnection_sc tt = new h_sqlconnection_sc();
		
		
		
		if (tt.connectDB()) {
			for (int i=1; i<101; i++) {
				tt.insertDB();
			}
			tt.closeDB();
		}
	}
}