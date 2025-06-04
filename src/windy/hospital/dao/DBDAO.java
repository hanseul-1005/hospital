package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DBModel;
import windy.hospital.model.DatabaseModel;
import windy.hospital.model.SuppliesModel;

public class DBDAO {
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DatabaseModel dbModel = new DatabaseModel();
	// DB Driver
  
    String dbDriver = "org.mariadb.jdbc.Driver";
	private String jdbcUrl = dbModel.getJdbcUrl();
    //private String jdbcUrl = "jdbc:mariadb://192.168.0.60:33308/bicycledb";
	private String user = dbModel.getUser();         
	private String password = dbModel.getPassword();
	

	// //////////////////////////////////////////////////
	// - 용품 등록
	// //////////////////////////////////////////////////
	public int insertDB(DBModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO db_info(db_code, db_title, db_detail, start_date, end_date, db_name, use_yn) "
					+ "VALUES(?, ?, ?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getCode());
			pstmt.setString(2, modelParam.getTitle());
			pstmt.setString(3, modelParam.getDetail());
			pstmt.setString(4, modelParam.getStartDate());
			pstmt.setString(5, modelParam.getEndDate());
			pstmt.setString(6, modelParam.getName());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return result;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 등록
	// //////////////////////////////////////////////////
	public int updateDB(DBModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE db_info "
					+ "SET db_title=?, db_detail=?, start_date=?, end_date=?, db_name=? "
					+ "WHERE db_no=? ");

			pstmt.setString(1, modelParam.getTitle());
			pstmt.setString(2, modelParam.getDetail());
			pstmt.setString(3, modelParam.getStartDate());
			pstmt.setString(4, modelParam.getEndDate());
			pstmt.setString(5, modelParam.getName());
			pstmt.setInt(6, modelParam.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return result;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 조회
	// //////////////////////////////////////////////////
	public DBModel selectDB(int no) {
		
		DBModel db = new DBModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT db_no, db_code, db_title, db_detail, start_date, end_date, db_name, use_yn "
					+ "FROM db_info "
					+ "WHERE db_no = ? ");

			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				db.setNo(rs.getInt("db_no"));
				db.setCode(rs.getString("db_code"));
				db.setTitle(rs.getString("db_title"));
				db.setDetail(rs.getString("db_detail"));
				db.setStartDate(rs.getString("start_date"));
				db.setEndDate(rs.getString("end_date"));
				db.setName(rs.getString("db_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return db;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 등록
	// //////////////////////////////////////////////////
	public int deleteDB(int no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"DELETE FROM db_info WHERE db_no=? ");

			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return result;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<DBModel> selectListDB() {
		
		List<DBModel> listDB = new ArrayList<DBModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT db_no, db_code, db_title, db_detail, start_date, end_date, db_name, use_yn "
					+ "FROM db_info "
					+ "WHERE use_yn = 'N' "
					+ "ORDER BY db_no ASC ");

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DBModel db = new DBModel();
				db.setNo(rs.getInt("db_no"));
				db.setCode(rs.getString("db_code"));
				db.setTitle(rs.getString("db_title"));
				db.setDetail(rs.getString("db_detail"));
				db.setStartDate(rs.getString("start_date"));
				db.setEndDate(rs.getString("end_date"));
				db.setName(rs.getString("db_name"));
				
				listDB.add(db);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listDB;				
	}
			
	// //////////////////////////////////////////////////

	////////////////////////////////////////////////////
	//	- 데이터베이스 관련 객체 정리 -
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
