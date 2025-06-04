package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.EquipmentModel;
import windy.hospital.model.SiteModel;

public class EquipmentDAO {
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
	public int insertEquipment(EquipmentModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO equipment_info(equipment_name, equipment_date, equipment_as, equipment_note, equipment_del) "
					+ "VALUES(?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getDate());
			pstmt.setString(3, modelParam.getAs());
			pstmt.setString(4, modelParam.getNote());
			
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
	public int updateEquipment(EquipmentModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE equipment_info SET equipment_name=?, equipment_date=?, equipment_as=?, equipment_note=?, equipment_del=? "
					+ "WHERE equipment_no=? ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getDate());
			pstmt.setString(3, modelParam.getAs());
			pstmt.setString(4, modelParam.getNote());
			pstmt.setString(5, modelParam.getDel());
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
	// - 용품 등록
	// //////////////////////////////////////////////////
	public int deleteEquipment(int no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE equipment_info SET equipment_del='Y' "
					+ "WHERE equipment_no=? ");

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
	// - 용품 조회
	// //////////////////////////////////////////////////
	public EquipmentModel selectEquipment(int no) {

		EquipmentModel equipment = new EquipmentModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT equipment_no, equipment_name, equipment_date, equipment_as, equipment_note, equipment_del "
					+ "FROM equipment_info "
					+ "WHERE equipment_no = ? ");
			
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				equipment.setNo(rs.getInt("equipment_no"));
				equipment.setName(rs.getString("equipment_name"));
				equipment.setDate(rs.getString("equipment_date"));
				equipment.setAs(rs.getString("equipment_as"));
				equipment.setNote(rs.getString("equipment_note"));
				equipment.setDel(rs.getString("equipment_del"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return equipment;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<EquipmentModel> selectListEquipment(String name) {
		
		List<EquipmentModel> listEquipment = new ArrayList<EquipmentModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT equipment_no, equipment_name, equipment_date, equipment_as, equipment_note, equipment_del "
					+ "FROM equipment_info "
					+ "WHERE equipment_del = 'N' AND equipment_name like concat('%', ?, '%') "
					+ "ORDER BY equipment_no DESC ");
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EquipmentModel equipment = new EquipmentModel();
				
				equipment.setNo(rs.getInt("equipment_no"));
				equipment.setName(rs.getString("equipment_name"));
				equipment.setDate(rs.getString("equipment_date"));
				equipment.setAs(rs.getString("equipment_as"));
				equipment.setNote(rs.getString("equipment_note"));
				equipment.setDel(rs.getString("equipment_del"));
				
				listEquipment.add(equipment);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listEquipment;				
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
