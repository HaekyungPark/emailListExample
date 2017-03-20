package com.bit2017.email.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2017.email.vo.EmailVo;

public class EmailDao {
	public List<EmailVo> getList(){
		List<EmailVo> list = new ArrayList<EmailVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1.JDBC Driver Loading (JDBC Class Loading)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection 얻어오기 (connect to DB)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.SQL문 준비
			String sql = "select no, first_name, last_name, email from emailList order by no";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//5.execute SQL
			
			//conn.close(); --여기다 쓰면 안됨
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - "+ e );
			
		} catch(SQLException e){
			System.out.println("error 1: " + e);
		
		}finally{ 
			//3.자원정리
			try{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
				conn.close();
			}catch(SQLException e){
				System.out.println("error: " + e );
			}
		}	
		return list;
}
	
	public boolean insert(EmailVo emailVo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//1.JDBC Driver Loading (JDBC Class Loading)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.Connection 얻어오기 (connect to DB)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3.SQL문 준비
			String sql = "insert INTO emailList values(seq_emailList.nextval, ?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//4.데이터 바인딩
			pstmt.setString(1, emailVo.getFirstName());
			pstmt.setString(2, emailVo.getLastName() );
			pstmt.setString(3, emailVo.getEmail());
			
			//5.execute SQL
			int count =pstmt.executeUpdate();
			boolean result = (count==1);
			return result;
			
			//conn.close(); --여기다 쓰면 안됨
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - "+ e );
			return false;
		} catch(SQLException e){
			System.out.println("error 1: " + e);
			return false;
		}finally{ 
			//3.자원정리
			try{
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
				conn.close();
			}catch(SQLException e){
				System.out.println("error: " + e );
			}
		}	
	}
}
