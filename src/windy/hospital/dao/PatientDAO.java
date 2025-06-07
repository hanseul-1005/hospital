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
	public int insertPatientMulti(PatientModel modelParam) {
		
		int result = -1;

		String code = (String) selectPatientCode();
		System.out.println("before code : "+code);
		if(!"".equals(code)) {
			
			code = "AA"+String.format("%04d", (Integer.parseInt(code.substring(2, 6))+1));
			
			System.out.println("before : "+Integer.parseInt(code.substring(2, 6)));
			System.out.println("parsing : "+(Integer.parseInt(code.substring(2, 6))+1));
			System.out.println("after : "+code);
		} else {
			code = "AA0001";
			System.out.println("default : "+code);
		}
		
		modelParam.setCode(code);
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO patient_info(patient_code, patient_name, patient_birth, patient_age, patient_gender, " 
							+ "patient_tel)  "
					+ "VALUES(?, ?, ?, ?, ?, ?) ");

			pstmt.setString(1, modelParam.getCode());
			pstmt.setString(2, modelParam.getName());
			pstmt.setString(3, modelParam.getBirth());
			pstmt.setInt(4, modelParam.getAge());
			pstmt.setString(5, modelParam.getGender());
			pstmt.setString(6, modelParam.getTel());
			
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
	public int insertPatient(PatientModel modelParam) {
		
		int result = -1;
		String code = (String) selectPatientCode();
		System.out.println("before code : "+code);
		if(!"".equals(code)) {
			
			code = "AA"+String.format("%04d", (Integer.parseInt(code.substring(2, 6))+1));
			
			System.out.println("before : "+Integer.parseInt(code.substring(2, 6)));
			System.out.println("parsing : "+(Integer.parseInt(code.substring(2, 6))+1));
			System.out.println("after : "+code);
		} else {
			code = "AA0001";
			System.out.println("default : "+code);
		}
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO patient_info(patient_code, patient_name, patient_birth, patient_age, patient_gender, " 
							+ "patient_blood, patient_tel, patient_addr, patient_addr_detail, "							
							+ "room_no, patient_guardian_name, patient_guardian_tel, patient_guardian_relation, "
							+ "patient_state, hospital_no, patient_evacuation_reason, patient_admission_date, patient_diagnosis_date, "
							+ "patient_evacuation_date, patient_return_date, patient_discharge_date, patient_death_date, pcr_group_no)  "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			pstmt.setString(1, code);
			pstmt.setString(2, modelParam.getName());
			pstmt.setString(3, modelParam.getBirth());
			pstmt.setInt(4, modelParam.getAge());
			pstmt.setString(5, modelParam.getGender());
			pstmt.setString(6, modelParam.getBlood());
			pstmt.setString(7, modelParam.getTel());
			pstmt.setString(8, modelParam.getAddr());
			pstmt.setString(9, modelParam.getAddrDetail());
			pstmt.setInt(10, modelParam.getRoomNo());
			pstmt.setString(11, modelParam.getGuardianName());
			pstmt.setString(12, modelParam.getGuardianTel());
			pstmt.setString(13, modelParam.getGuardianRelation());
			pstmt.setInt(14, modelParam.getState());
			pstmt.setLong(15, modelParam.getHospitalNo());
			pstmt.setString(16, modelParam.getEvacuationReason());
			pstmt.setString(17, modelParam.getAdmissionDate());
			pstmt.setString(18, modelParam.getDiagnosisDate());
			pstmt.setString(19, modelParam.getEvacuationDate());
			pstmt.setString(20, modelParam.getReturnDate());
			pstmt.setString(21, modelParam.getDischargeDate());
			pstmt.setString(22, modelParam.getDeathDate());
			pstmt.setLong(23, modelParam.getPcrGroupNo());
			
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
							+ "patient_name=?, patient_birth=?, patient_age=?, patient_gender=?, " 
							+ "patient_blood=?, patient_tel=?, patient_addr=?, patient_addr_detail=?, "							
							+ "room_no=?, patient_guardian_name=?, patient_guardian_tel=?, patient_guardian_relation=?, "
							+ "patient_state=?, hospital_no=?, patient_evacuation_reason=?, patient_admission_date=?, patient_diagnosis_date=?, "
							+ "patient_evacuation_date=?, patient_return_date=?, patient_discharge_date=?, patient_death_date=?, pcr_group_no=?  "
					+ "WHERE patient_no=? ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getBirth());
			pstmt.setInt(3, modelParam.getAge());
			pstmt.setString(4, modelParam.getGender());
			pstmt.setString(5, modelParam.getBlood());
			pstmt.setString(6, modelParam.getTel());
			pstmt.setString(7, modelParam.getAddr());
			pstmt.setString(8, modelParam.getAddrDetail());
			pstmt.setInt(9, modelParam.getRoomNo());
			pstmt.setString(10, modelParam.getGuardianName());
			pstmt.setString(11, modelParam.getGuardianTel());
			pstmt.setString(12, modelParam.getGuardianRelation());
			pstmt.setInt(13, modelParam.getState());
			pstmt.setLong(14, modelParam.getHospitalNo());
			pstmt.setString(15, modelParam.getEvacuationReason());
			pstmt.setString(16, modelParam.getAdmissionDate());
			pstmt.setString(17, modelParam.getDiagnosisDate());
			pstmt.setString(18, modelParam.getEvacuationDate());
			pstmt.setString(19, modelParam.getReturnDate());
			pstmt.setString(20, modelParam.getDischargeDate());
			pstmt.setString(21, modelParam.getDeathDate());
			pstmt.setLong(22, modelParam.getPcrGroupNo());
			pstmt.setLong(23, modelParam.getNo());
			
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
	public PatientModel selectPatient(long no) {

		PatientModel patient = new PatientModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT patient_no, patient_code, patient_name, patient_birth, patient_age, patient_gender, patient_blood, patient_tel, "
					+ "patient_addr, patient_addr_detail, room_no, patient_guardian_name, patient_guardian_tel, patient_guardian_relation, "
					+ "patient_state, hospital_no, patient_evacuation_reason, patient_admission_date, patient_diagnosis_date, patient_evacuation_date, "
					+ "patient_return_date, patient_discharge_date, patient_death_date, pcr_group_no "
					+ "FROM patient_info "
					+ "WHERE patient_no = ? ");
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				patient.setNo(rs.getLong("patient_no"));
				patient.setCode(rs.getString("patient_code"));
				patient.setName(rs.getString("patient_name"));
				patient.setBirth(rs.getString("patient_birth"));
				patient.setAge(rs.getInt("patient_age"));
				patient.setGender(rs.getString("patient_gender"));
				patient.setBlood(rs.getString("patient_blood"));
				patient.setTel(rs.getString("patient_tel"));
				patient.setAddr(rs.getString("patient_addr"));
				patient.setAddrDetail(rs.getString("patient_addr_detail"));
				patient.setRoomNo(rs.getInt("room_no"));
				patient.setGuardianName(rs.getString("patient_guardian_name"));
				patient.setGuardianTel(rs.getString("patient_guardian_tel"));
				patient.setGuardianRelation(rs.getString("patient_guardian_relation"));
				patient.setState(rs.getInt("patient_state"));
				patient.setHospitalNo(rs.getLong("hospital_no"));
				patient.setEvacuationReason(rs.getString("patient_evacuation_reason"));
				patient.setAdmissionDate(rs.getString("patient_admission_date"));
				patient.setDiagnosisDate(rs.getString("patient_diagnosis_date"));
				patient.setEvacuationDate(rs.getString("patient_evacuation_date"));
				patient.setReturnDate(rs.getString("patient_return_date"));
				patient.setDischargeDate(rs.getString("patient_discharge_date"));
				patient.setDeathDate(rs.getString("patient_death_date"));
				patient.setPcrGroupNo(rs.getLong("pcr_group_no"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return patient;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 조회
	// //////////////////////////////////////////////////
	public String selectPatientCode() {

		String code = "";
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT patient_code  "
					+ "FROM patient_info "
					+ "ORDER BY patient_code DESC limit 1 ");

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				code = rs.getString("patient_code");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return code;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<PatientModel> selectListPatient() {
		
		List<PatientModel> listPatient = new ArrayList<PatientModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT patient_no, patient_code, patient_name, patient_birth, patient_age, patient_gender, patient_blood, patient_tel, "
					+ "patient_addr, patient_addr_detail, room_no, patient_guardian_name, patient_guardian_tel, patient_guardian_relation, "
					+ "patient_state, hospital_no, patient_evacuation_reason, patient_admission_date, patient_diagnosis_date, patient_evacuation_date, "
					+ "patient_return_date, patient_discharge_date, patient_death_date, pcr_group_no "
					+ "FROM patient_info "
					+ "ORDER BY patient_no DESC ");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PatientModel patient = new PatientModel();
			
				patient.setNo(rs.getLong("patient_no"));
				patient.setCode(rs.getString("patient_code"));
				patient.setName(rs.getString("patient_name"));
				patient.setBirth(rs.getString("patient_birth"));
				patient.setAge(rs.getInt("patient_age"));
				patient.setGender(rs.getString("patient_gender"));
				patient.setBlood(rs.getString("patient_blood"));
				patient.setTel(rs.getString("patient_tel"));
				patient.setAddr(rs.getString("patient_addr"));
				patient.setAddrDetail(rs.getString("patient_addr_detail"));
				patient.setRoomNo(rs.getInt("room_no"));
				patient.setGuardianName(rs.getString("patient_guardian_name"));
				patient.setGuardianTel(rs.getString("patient_guardian_tel"));
				patient.setGuardianRelation(rs.getString("patient_guardian_relation"));
				patient.setState(rs.getInt("patient_state"));
				patient.setHospitalNo(rs.getLong("hospital_no"));
				patient.setEvacuationReason(rs.getString("patient_evacuation_reason"));
				patient.setAdmissionDate(rs.getString("patient_admission_date"));
				patient.setDiagnosisDate(rs.getString("patient_diagnosis_date"));
				patient.setEvacuationDate(rs.getString("patient_evacuation_date"));
				patient.setReturnDate(rs.getString("patient_return_date"));
				patient.setDischargeDate(rs.getString("patient_discharge_date"));
				patient.setDeathDate(rs.getString("patient_death_date"));
				patient.setPcrGroupNo(rs.getLong("pcr_group_no"));
				
				listPatient.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listPatient;				
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
