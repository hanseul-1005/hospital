package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.SiteModel;

public class HospitalDAO {
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
	public int insertHospital(HospitalModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO hospital_info(hospital_name, hospital_tel, hospital_room_cnt, hospital_note, hospital_del) "
					+ "VALUES(?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getTel());
			pstmt.setInt(3, modelParam.getRoomCnt());
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
	public int updateHospital(HospitalModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE hospital_info SET hospital_name=?, hospital_tel=?, hospital_room_cnt=?, hospital_note=?, hospital_del=? "
					+ "WHERE hospital_no=? ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getTel());
			pstmt.setInt(3, modelParam.getRoomCnt());
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
	public int deleteHospital(long no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE hospital_info SET hospital_del='Y' "
					+ "WHERE hospital_no=? ");

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
	public HospitalModel selectHospital(long no) {

		HospitalModel hospital = new HospitalModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT hospital_no, hospital_name, hospital_tel, hospital_room_cnt, hospital_note, hospital_tel, hospital_del "
					+ "FROM hospital_info "
					+ "WHERE hospital_no = ? ");
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				hospital.setNo(rs.getLong("hospital_no"));
				hospital.setName(rs.getString("hospital_name"));
				hospital.setTel(rs.getString("hospital_tel"));
				hospital.setRoomCnt(rs.getInt("hospital_room_cnt"));
				hospital.setNote(rs.getString("hospital_note"));
				hospital.setTel(rs.getString("hospital_tel"));
				hospital.setDel(rs.getString("hospital_del"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return hospital;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<HospitalModel> selectListHospital(String name) {
		
		List<HospitalModel> listHospital = new ArrayList<HospitalModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT hospital_no, hospital_name, hospital_tel, hospital_room_cnt, hospital_note, hospital_tel, hospital_del "
					+ "FROM hospital_info "
					+ "WHERE hospital_del = 'N' AND hospital_name like concat('%', ?, '%') "
					+ "ORDER BY hospital_no DESC ");
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HospitalModel hospital = new HospitalModel();
				
				hospital.setNo(rs.getLong("hospital_no"));
				hospital.setName(rs.getString("hospital_name"));
				hospital.setTel(rs.getString("hospital_tel"));
				hospital.setRoomCnt(rs.getInt("hospital_room_cnt"));
				hospital.setNote(rs.getString("hospital_note"));
				hospital.setTel(rs.getString("hospital_tel"));
				hospital.setDel(rs.getString("hospital_del"));
				
				listHospital.add(hospital);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listHospital;				
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
