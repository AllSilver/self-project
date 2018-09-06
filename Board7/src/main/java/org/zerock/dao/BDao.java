package org.zerock.dao;

import java.util.List;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import org.zerock.dto.BDto;

import spring.page.test.Criteria;

/*import com.mysql.jdbc.PreparedStatement;*/

public class BDao {
	

	static DataSource dataSource;
	
	
	public BDto replyForm;

	public String group;

	public String step;
	
	public BDao() {
		
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/BoardTest");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<BDto> list(int page){
		
		ArrayList<BDto> BDtos = new ArrayList<BDto>();
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		if (page <= 0) {
		    page = 1;
		  }
		  page = (page - 1) * 10;
		  
		try {
			connection = dataSource.getConnection();
			//쿼리에 리스트에서 누른 페이징 번호를 알맞게 쿼리에 매칭하여 입력해주는 방식으로 조회 하기!
			String sql = "SELECT bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent FROM Board WHERE bId > 0 ORDER BY bGroup DESC,  bStep ASC LIMIT ?, 10;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, page);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				// 이싸람아.. 여기는 데이터 결과를 매핑해주는 곳이잔아요...
				//여기다 해주면 안되죠..
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Date bDate = resultSet.getDate("bDate");
				
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				/*BDto dto = new BDto(bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent);*/
				BDto dto = new BDto();
				dto.setbId(bId);
				dto.setbName(bName);
				dto.setbTitle(bTitle);
				dto.setbDate(bDate);
				dto.setbHit(bHit);
	
				BDtos.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement  != null) preparedStatement.close();
				if(connection  != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
		}
		return BDtos ;		
	}	
}//list()
	public void write_view(String bName, String bTitle, String bContent, String filen, String filepa, int fileSize, String fileKey) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ResultSet resultSet = null;
		  
		 int maxid=0;
		 try{
		  connection = (Connection) dataSource.getConnection();
		  String query = "select max(bId) as maxId from Board";
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
		 
		  String query ="insert into Board(bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, filename, filepath,fileSize,fileKey) "
		    + "values (?,?,?,now(),0,?,0,0,?,?,?,?)";
		      
		  preparedStatement = connection.prepareStatement(query);
		  preparedStatement.setString(1, bName);
		  preparedStatement.setString(2, bTitle);
		  preparedStatement.setString(3, bContent);
		  preparedStatement.setInt(4, maxid); 
		  preparedStatement.setString(5, filen);
		  preparedStatement.setString(6, filepa);
		  preparedStatement.setInt(7, fileSize);
		  preparedStatement.setString(8, fileKey);
		  
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

	}//write()


	public BDto content_view(String strId) {
		
			addHit(strId);
			
			BDto bdto = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = dataSource.getConnection();
				
				String sql = "select * from Board where bId = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, Integer.parseInt(strId));
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Date bDate = resultSet.getDate("bDate");
					
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					String filename = resultSet.getString("filename"); 
					String filepath = resultSet.getString("filepath"); 
					
					bdto = new BDto();
					bdto.setbId(bId);
					bdto.setbName(bName);
					bdto.setbTitle(bTitle);
					bdto.setbContent(bContent);
					bdto.setbHit(bHit);
					bdto.setFilename(filename);
					bdto.setFilepath(filepath);
					System.out.println(" bId 입력 =" +bId);
					System.out.println(" bHit 입력 =" +bHit);
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
			
			return bdto;
		}  //content_view()

	
	private void addHit(String bId) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String sql = "update Board set bHit=bHit +1 where bId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bId);
			System.out.println("Hit 입력 =" +bId );
			System.out.println("히트수 업데이트!!");
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement  != null) preparedStatement.close();
				if(connection  != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


	public void modify(String bId, String bTitle, String bContent) {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				
				try {
					connection = dataSource.getConnection();
					
					System.out.println("커넥션 확보 !!------------------");
					
					String sql = "update Board set bTitle = ?, bContent = ? where bId = ? ";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, bTitle);
					preparedStatement.setString(2, bContent);
					preparedStatement.setInt(3, Integer.parseInt(bId));
					preparedStatement.executeUpdate();
					
					
				
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						if(preparedStatement != null) preparedStatement.close();
						if(connection != null) connection.close();
					}catch (Exception e2) {
						e2.printStackTrace();
					}
				}
		}

	public void delete(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String sql = "delete from Board where bId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}


	public BDto replyForm(String strId2) {
		
		BDto bdto = null;	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			System.out.println("replyForm 확보 !!------------------");
			String sql = "select * from Board where bId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(strId2));
			resultSet = preparedStatement.executeQuery();
			System.out.println("replyForm executeQuery()확보 !!------------------");
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Date bDate = resultSet.getDate("bDate");
				
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				String filename = resultSet.getString("filename"); 
				String filepath = resultSet.getString("filepath"); 
				
				bdto = new BDto();
				bdto.setbId(bId);
				bdto.setbTitle(bTitle);
				bdto.setbGroup(bGroup);
				bdto.setbStep(bStep);
				bdto.setbIndent(bIndent);
				System.out.println(" bId 입력 =" +bId);
				System.out.println(" bTitle 입력 =" +bTitle);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement  != null) preparedStatement.close();
				if(connection  != null) connection.close();  
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bdto;
	}


	public void replyOk(String bName, String bTitle, String bContent,String bGroup, String bStep,String bIndent, String filename, String filepath) {
		
		replySet(group, step);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		 try{
			  connection = (Connection) dataSource.getConnection();
			   
			  String query = "insert into Board(bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, filename ,filepath) values (?,?,?,now(),0,?,?,?,?,?)";
			   
			  preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			   
			  preparedStatement.setString(1,bName);
			  preparedStatement.setString(2,bTitle);
			  preparedStatement.setString(3,bContent);
			  preparedStatement.setInt(4,Integer.parseInt(bGroup));
			  preparedStatement.setInt(5,Integer.parseInt(bStep)+1);
			  preparedStatement.setInt(6,Integer.parseInt(bIndent)+1);
			  preparedStatement.setString(7,filename);
			  preparedStatement.setString(8,filepath);
			   
			  preparedStatement.executeUpdate();
			   
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
			}

	public void replySet(String group, String step){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		try {
			connection = dataSource.getConnection();
			String sql = "update Board set bStep = bStep+1 where bGroup = ? and bStep>?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(group));
			preparedStatement.setInt(2, Integer.parseInt(step));
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			  // TODO: handle exception
			  e.printStackTrace();
			 } finally {
			  try {
			   if(preparedStatement != null) preparedStatement.close();
			   if(connection != null) connection.close();
			  } catch (Exception e2) {
			   // TODO: handle exception
			   e2.printStackTrace();
		  }
	 }
}


	public BDto change_content(String strbId2) {
		
		BDto bdto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "select * from Board where bId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(strbId2));
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Date bDate = resultSet.getDate("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				bdto = new BDto();
				bdto.setbId(bId);
				bdto.setbId(bHit);
				bdto.setbName(bName);
				bdto.setbTitle(bTitle);
				bdto.setbContent(bContent);
				
				
				System.out.println(" bId 입력 =" +bId);
				
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
		
		return bdto;
	}  //content_view()


	 public List<BDto> listPage(int page) throws Exception{
			ArrayList<BDto> BDtos = new ArrayList<BDto>();
		 	List<BDto> bdto = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = dataSource.getConnection();
				
				String sql = "select bId,bName,bTitle,bContent,bDate,bHit,bGroup,bStep,bIndent from Board where bid > 0"
						+ " order by bGroup desc,  bStep asc"
						+  "limit 0, 10";
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					int bId = resultSet.getInt("bId");
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Date bDate = resultSet.getDate("bDate");
					
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
				
					((BDto) bdto).setbName(bName);
					((BDto) bdto).setbTitle(bTitle);
					((BDto) bdto).setbDate(bDate);
					((BDto) bdto).setbHit(bHit);
		
					BDtos.add((BDto) bdto);
					System.out.println(" bId 입력 =" +bId);
				
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
			
			return bdto;
		}  //List<BDto> listPage
		
	 
	public int TotalCount(){
		ArrayList<BDto> BDtos = new ArrayList<BDto>();
		Criteria criteria = new Criteria();
	 	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "SELECT COUNT(*) as totalcount FROM Board";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		
			
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
		return 0;
		
		
	}


	public static void insertBoard(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
	}


	public static List<BDto> boardList(String searchType, String keyword) {
		
		ArrayList<BDto> BDtos = new ArrayList<BDto>();
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	
		try {
			connection = dataSource.getConnection();
			//쿼리에 리스트에서 누른 페이징 번호를 알맞게 쿼리에 매칭하여 입력해주는 방식으로 조회 하기!
			String sql = "select * from Board where "; 
			if(searchType.equals("bContent")) {
				sql+="bContent";
			}else if(searchType.equals("bName")) {
				sql+="bName";
			}else {
				sql+="bTitle";
			}
			sql+=" like ? and bId > 0 ORDER BY bGroup DESC,  bStep ASC";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%"+keyword+"%");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Date bDate = resultSet.getDate("bDate");
				
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				BDto dto = new BDto();
				dto.setbId(bId);
				dto.setbName(bName);
				dto.setbTitle(bTitle);
				dto.setbDate(bDate);
				dto.setbHit(bHit);
	
				BDtos.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return BDtos ;		
	}


	}


	

	



	
	
