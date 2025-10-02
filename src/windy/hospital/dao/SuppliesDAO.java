package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.SuppliesModel;

public class SuppliesDAO {
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
	public long insertSupplies(SuppliesModel modelParam) {
		
		long suppliesNo = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO supplies_info(supplies_name, supplies_date, supplies_tel, supplies_amount, supplies_note, supplies_del) "
					+ "VALUES(?, ?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getDate());
			pstmt.setString(3, modelParam.getTel());
			pstmt.setInt(4, modelParam.getAmount());
			pstmt.setString(5, modelParam.getNote());
			
			pstmt.executeUpdate();
			
			pstmt = connection.prepareStatement(
					"SELECT supplies_no FROM supplies_info ORDER BY supplies_no DESC limit 1");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				suppliesNo = rs.getLong("supplies_no");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return suppliesNo;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 수정
	// //////////////////////////////////////////////////
	public int updateSupplies(SuppliesModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE supplies_info "
					+ "SET supplies_name=?, supplies_date=?, supplies_tel=?, supplies_amount=?, supplies_note=? "
					+ "WHERE supplies_no=? ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getDate());
			pstmt.setString(3, modelParam.getTel());
			pstmt.setInt(4, modelParam.getAmount());
			pstmt.setString(5, modelParam.getNote());
			pstmt.setLong(6, modelParam.getNo());
			
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
	// - 용품 수정
	// //////////////////////////////////////////////////
	public int deleteSupplies(long no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE supplies_info "
					+ "SET supplies_del='Y' "
					+ "WHERE supplies_no=? ");

			pstmt.setLong(1, no);
			
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
	public SuppliesModel selectSupplies(long no) {
		
		SuppliesModel supplies = new SuppliesModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT supplies_no, supplies_name, supplies_date, supplies_tel, supplies_amount, supplies_note, supplies_del "
					+ "FROM supplies_info "
					+ "WHERE supplies_no = ? ");

			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				supplies.setNo(rs.getLong("supplies_no"));
				supplies.setName(rs.getString("supplies_name"));
				supplies.setDate(rs.getString("supplies_date"));
				supplies.setTel(rs.getString("supplies_tel"));
				supplies.setAmount(rs.getInt("supplies_amount"));
				supplies.setNote(rs.getString("supplies_note"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return supplies;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<SuppliesModel> selectListSupplies(String name) {
		
		List<SuppliesModel> listSupplies = new ArrayList<SuppliesModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT supplies_no, supplies_name, supplies_date, supplies_tel, supplies_amount, supplies_note, supplies_del "
					+ "FROM supplies_info "
					+ "WHERE supplies_del = 'N' AND supplies_name like concat ('%', ?, '%') "
					+ "ORDER BY supplies_no ASC ");

			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SuppliesModel sup = new SuppliesModel();
				sup.setNo(rs.getLong("supplies_no"));
				sup.setName(rs.getString("supplies_name"));
				sup.setDate(rs.getString("supplies_date"));
				sup.setTel(rs.getString("supplies_tel"));
				sup.setAmount(rs.getInt("supplies_amount"));
				sup.setNote(rs.getString("supplies_note"));
				
				listSupplies.add(sup);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listSupplies;				
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
