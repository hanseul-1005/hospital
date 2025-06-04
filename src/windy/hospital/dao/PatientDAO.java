package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.PatientModel;
import windy.hospital.model.SiteModel;

public class PatientDAO {
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
	public int insertPatient(PatientModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO patient_info(patient_code, patient_name, patient_birth, patient_age, patient_gender, " 
							+ "patient_blood, patient_tel, patient_addr, patient_addr_detail, "							
							+ "room_no, room_name, patient_guardian_name, patient_guardian_tel, patient_guardian_relation, "
							+ "patient_state, hospital_no, patient_evacuation_reason, patient_admission_date, patient_diagnosis_date, "
							+ "patient_evacuation_date, patient_return_date, patient_discharge_date, patient_death_date, pcr_group_no)  "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			pstmt.setString(1, modelParam.getCode());
			pstmt.setString(2, modelParam.getName());
			pstmt.setString(3, modelParam.getBirth());
			pstmt.setInt(4, modelParam.getAge());
			pstmt.setString(5, modelParam.getGender());
			pstmt.setString(6, modelParam.getBlood());
			pstmt.setString(7, modelParam.getTel());
			pstmt.setString(8, modelParam.getAddr());
			pstmt.setString(9, modelParam.getAddrDetail());
			pstmt.setInt(10, modelParam.getRoomNo());
			pstmt.setString(11, modelParam.getRoomName());
			pstmt.setString(12, modelParam.getGuardianName());
			pstmt.setString(13, modelParam.getGuardianTel());
			pstmt.setString(14, modelParam.getGuardianRelation());
			pstmt.setInt(15, modelParam.getState());
			pstmt.setLong(16, modelParam.getHospitalNo());
			pstmt.setString(17, modelParam.getEvacuationReason());
			pstmt.setString(18, modelParam.getAdmissionDate());
			pstmt.setString(19, modelParam.getDiagnosisDate());
			pstmt.setString(20, modelParam.getEvacuationDate());
			pstmt.setString(21, modelParam.getReturnDate());
			pstmt.setString(22, modelParam.getDischargeDate());
			pstmt.setString(23, modelParam.getDeathDate());
			pstmt.setLong(24, modelParam.getPcrGroupNo());
			
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
	public int updatePatient(PatientModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE patient_info SET "
							+ "patient_code=?, patient_name=?, patient_birth=?, patient_age=?, patient_gender=?, " 
							+ "patient_blood=?, patient_tel=?, patient_addr=?, patient_addr_detail=?, "							
							+ "room_no=?, room_name=?, patient_guardian_name=?, patient_guardian_tel=?, patient_guardian_relation=?, "
							+ "patient_state=?, hospital_no=?, patient_evacuation_reason=?, patient_admission_date=?, patient_diagnosis_date=?, "
							+ "patient_evacuation_date=?, patient_return_date=?, patient_discharge_date=?, patient_death_date=?, pcr_group_no=?  "
					+ "WHERE patient_no=? ");

			pstmt.setString(1, modelParam.getCode());
			pstmt.setString(2, modelParam.getName());
			pstmt.setString(3, modelParam.getBirth());
			pstmt.setInt(4, modelParam.getAge());
			pstmt.setString(5, modelParam.getGender());
			pstmt.setString(6, modelParam.getBlood());
			pstmt.setString(7, modelParam.getTel());
			pstmt.setString(8, modelParam.getAddr());
			pstmt.setString(9, modelParam.getAddrDetail());
			pstmt.setInt(10, modelParam.getRoomNo());
			pstmt.setString(11, modelParam.getRoomName());
			pstmt.setString(12, modelParam.getGuardianName());
			pstmt.setString(13, modelParam.getGuardianTel());
			pstmt.setString(14, modelParam.getGuardianRelation());
			pstmt.setInt(15, modelParam.getState());
			pstmt.setLong(16, modelParam.getHospitalNo());
			pstmt.setString(17, modelParam.getEvacuationReason());
			pstmt.setString(18, modelParam.getAdmissionDate());
			pstmt.setString(19, modelParam.getDiagnosisDate());
			pstmt.setString(20, modelParam.getEvacuationDate());
			pstmt.setString(21, modelParam.getReturnDate());
			pstmt.setString(22, modelParam.getDischargeDate());
			pstmt.setString(23, modelParam.getDeathDate());
			pstmt.setLong(24, modelParam.getPcrGroupNo());
			pstmt.setLong(25, modelParam.getNo());
			
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
	public int deletePatient(long no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"DELETE FROM patient_info WHERE patient_no=? ");

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
	public SiteModel selectSite(int no) {

		SiteModel site = new SiteModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT site_no, site_name, site_person_name, site_belong, site_tel, site_note, site_del "
					+ "FROM site_info "
					+ "WHERE site_no = ? ");
			
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				site.setNo(rs.getInt("site_no"));
				site.setName(rs.getString("site_name"));
				site.setPersonName(rs.getString("site_person_name"));
				site.setBelong(rs.getString("belong"));
				site.setTel(rs.getString("site_tel"));
				site.setNote(rs.getString("site_note"));
				site.setDel(rs.getString("site_del"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return site;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<SiteModel> selectListSite() {
		
		List<SiteModel> listSite = new ArrayList<SiteModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT site_no, site_name, site_person_name, site_belong, site_tel, site_note, site_del "
					+ "FROM site_info "
					+ "WHERE site_del = 'N' "
					+ "ORDER BY site_no DESC ");

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SiteModel site = new SiteModel();
				
				site.setNo(rs.getInt("site_no"));
				site.setName(rs.getString("site_name"));
				site.setPersonName(rs.getString("site_person_name"));
				site.setBelong(rs.getString("site_belong"));
				site.setTel(rs.getString("site_tel"));
				site.setNote(rs.getString("site_note"));
				site.setDel(rs.getString("site_del"));
				
				listSite.add(site);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listSite;				
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
