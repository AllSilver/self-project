package org.zerock.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.dto.BDto;
import org.zerock.dto.MDto;

public class MDao {

	public ArrayList<MDto> Login;
	DataSource dataSource;

	public MDao() {
		
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/BoardTest");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	public MDto Login(String struserId, String struserPw) {
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MDto mdto = new MDto();
		try {
			connection = dataSource.getConnection();
			
			String sql = "select * from tbl_member where userId =? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, struserId);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
					
				// 아이디 값과 비밀번호 값을 가져옴
					String userId = resultSet.getString("userId");
					String userPw = resultSet.getString("userPw");
					
				// 아이디 값과 비밀번호 값을 MDto에 넣어줌
					mdto.setUserId(userId);
					mdto.setUserPw(userPw);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement  != null) preparedStatement.close();
				if(connection  != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return mdto;
	}  
	
	

	public void joining(String userId, String userPw, String userName, String userEmail) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		  
		 int maxid=0;
		 try{
		  connection = (Connection) dataSource.getConnection();
		  String query = "select max(Idx) as maxId from tbl_member";
		  preparedStatement = (PreparedStatement) connection.prepareStatement(query);
		  resultSet = preparedStatement.executeQuery();
		   
		  if(resultSet.next()){
		   maxid = resultSet.getInt("maxId");
		  }
		   
		 }catch(Exception e){
		  e.printStackTrace();
		 }finally{
		   
		   try {
		    if(resultSet != null)resultSet.close();
		    if(preparedStatement != null)preparedStatement.close();
		    if(connection != null)connection.close();
		   } catch (SQLException e2) {
		    // TODO Auto-generated catch block
		    e2.printStackTrace();
		   }
		 }
		  
		 try{
		  connection = dataSource.getConnection();
		  String query ="insert into tbl_member(userId, userPw, userName, userEmail)"
		    + " values (?,?,?,?)";
		      
		  preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, userId);
		  preparedStatement.setString(2, userPw);
		  preparedStatement.setString(3, userName);
		  preparedStatement.setString(4, userEmail);
		  
		  int rm =preparedStatement.executeUpdate();
		  System.out.println("insert number :" + rm);
		 }catch(Exception e){
		  e.printStackTrace();
		 }finally{
		  try{
		   if(preparedStatement != null)preparedStatement.close();
		   if(connection != null)connection.close();
		  }catch(Exception e2){
		   e2.printStackTrace();
		  }
		 }

	}//joining


	public boolean joinOk(String userId2) {
		MDao mdao = new MDao();
		MDto mdto = new MDto();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "select * from tbl_member where userId =? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userId2);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("userId").equals(userId2)) {
					return true;
				}
			}
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement  != null) preparedStatement.close();
				if(connection  != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return false;
	}  //content_view()


	public MDto loginSession(MDto mdto) {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = " select * from tbl_member where userId = #{userId} and userPw = #{userPw}";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
					
				// 아이디 값과 비밀번호 값을 가져옴
					String userId = resultSet.getString("userId");
					String userPw = resultSet.getString("userPw");
					
				// 아이디 값과 비밀번호 값을 MDto에 넣어줌
					mdto.setUserId(userId);
					mdto.setUserPw(userPw);
				
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement  != null) preparedStatement.close();
				if(connection  != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return mdto;
	}  //loginSession


	public MDto write(String strName) {
		
			MDto mdto = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = dataSource.getConnection();
				
				String sql = "select * from tbl_member where userName = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, strName);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					
					String userName = resultSet.getString("userName");
					
					mdto.setUserName(userName);
	
					System.out.println("userName 입력 : " +userName);
				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(resultSet != null) resultSet.close();
					if(preparedStatement  != null) preparedStatement.close();
					if(connection  != null) connection.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			return mdto;
		}  //content_view()
	}

