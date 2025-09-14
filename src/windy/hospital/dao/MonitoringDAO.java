package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.AmbulanceModel;
import windy.hospital.model.DatabaseModel;
import windy.hospital.model.EmployeeModel;
import windy.hospital.model.PatientModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SiteModel;
import windy.hospital.model.VolunteerModel;

public class MonitoringDAO {
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
	public int insertAmbulance(AmbulanceModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO ambulance_info(ambulance_belong, ambulance_cnt) "
					+ "VALUES(?, ?) ");

			pstmt.setString(1, modelParam.getBelong());
			pstmt.setInt(2, modelParam.getCnt());
			
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
	public int deleteAmbulance() {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"DELETE FROM ambulance_info");
			
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
	public List<AmbulanceModel> selectListAmbulance() {
		
		List<AmbulanceModel> listAmbulance = new ArrayList<AmbulanceModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT ambulance_no, ambulance_belong, ambulance_cnt "
					+ "FROM ambulance_info "
					+ "ORDER BY ambulance_no ASC ");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AmbulanceModel ambulance = new AmbulanceModel();
				ambulance.setBelong(rs.getString("ambulance_belong"));
				ambulance.setCnt(rs.getInt("ambulance_cnt"));
				
				listAmbulance.add(ambulance);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listAmbulance;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 등록
	// //////////////////////////////////////////////////
	public int insertVolunteer(VolunteerModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO volunteer_info(volunteer_belong, volunteer_cnt) "
					+ "VALUES(?, ?) ");

			pstmt.setString(1, modelParam.getBelong());
			pstmt.setInt(2, modelParam.getCnt());
			
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
	public int deleteVolunteer() {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"DELETE FROM volunteer_info");
			
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
	public List<VolunteerModel> selectListVolunteer() {
		
		List<VolunteerModel> listVolunteer = new ArrayList<VolunteerModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT volunteer_no, volunteer_belong, volunteer_cnt "
					+ "FROM volunteer_info "
					+ "ORDER BY volunteer_no ASC ");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				VolunteerModel volunteer = new VolunteerModel();
				volunteer.setBelong(rs.getString("volunteer_belong"));
				volunteer.setCnt(rs.getInt("volunteer_cnt"));
				
				listVolunteer.add(volunteer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listVolunteer;				
	}
			
	// //////////////////////////////////////////////////

	
	

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<EmployeeModel> selectListEmployee(String department) {
		
		List<EmployeeModel> listEmp = new ArrayList<EmployeeModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT employee_no, employee_name, employee_belong, employee_tel, employee_department, "
							+ "employee_major, employee_id, employee_pw, employee_room_no, employee_room_name, "
							+ "employee_on_off, employee_del "
					+ "FROM employee_info "
					+ "WHERE employee_department like concat('%', ?, '%') "
					+ "ORDER BY employee_no DESC ");
			
			pstmt.setString(1, department);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeModel emp = new EmployeeModel();
				emp.setNo(rs.getLong("employee_no"));
				emp.setName(rs.getString("employee_name"));
				emp.setBelong(rs.getString("employee_belong"));
				emp.setTel(rs.getString("employee_tel"));
				emp.setDepartment(rs.getString("employee_department"));
				emp.setMajor(rs.getString("employee_major"));
				emp.setId(rs.getString("employee_id"));
				emp.setPw(rs.getString("employee_pw"));
				emp.setRoomNo(rs.getInt("employee_room_no"));
				emp.setRoomName(rs.getString("employee_room_name"));
				emp.setOnOff(rs.getString("employee_on_off"));
				emp.setDel(rs.getString("employee_del"));
				
				listEmp.add(emp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listEmp;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<EmployeeModel> selectListEmployeeForNurse(String department, String onOff) {
		
		List<EmployeeModel> listEmp = new ArrayList<EmployeeModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT employee_no, employee_name, employee_belong, employee_tel, employee_department, "
							+ "employee_major, employee_id, employee_pw, employee_room_no, employee_room_name, "
							+ "employee_on_off, employee_del "
					+ "FROM employee_info "
					+ "WHERE employee_department like concat('%', ?, '%') AND employee_on_off=? "
					+ "ORDER BY employee_no DESC ");
			
			pstmt.setString(1, department);
			pstmt.setString(2, onOff);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeModel emp = new EmployeeModel();
				emp.setNo(rs.getLong("employee_no"));
				emp.setName(rs.getString("employee_name"));
				emp.setBelong(rs.getString("employee_belong"));
				emp.setTel(rs.getString("employee_tel"));
				emp.setDepartment(rs.getString("employee_department"));
				emp.setMajor(rs.getString("employee_major"));
				emp.setId(rs.getString("employee_id"));
				emp.setPw(rs.getString("employee_pw"));
				emp.setRoomNo(rs.getInt("employee_room_no"));
				emp.setRoomName(rs.getString("employee_room_name"));
				emp.setOnOff(rs.getString("employee_on_off"));
				emp.setDel(rs.getString("employee_del"));
				
				listEmp.add(emp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listEmp;				
	}
			
	// //////////////////////////////////////////////////

	
	

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<PatientModel> selectListPatient(String roomName) {
		
		List<PatientModel> listPatient = new ArrayList<PatientModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT patient_no, patient_code, patient_name, patient_birth, patient_age, patient_gender, patient_blood, patient_tel, "
					+ "patient_addr, patient_addr_detail, room_no, patient_guardian_name, patient_guardian_tel, patient_guardian_relation, "
					+ "patient_state, hospital_no, patient_evacuation_reason, patient_admission_date, patient_diagnosis_date, patient_evacuation_date, "
					+ "patient_return_date, patient_discharge_date, patient_death_date, pcr_group_no, "
					+ "(select room_name from room_info where room_no=p.room_no) as room_name "
					+ "FROM patient_info p "
					+ "WHERE patient_state=1 AND (select room_name from room_info where room_no=p.room_no)=? "
					+ "ORDER BY patient_no DESC ");
			
			pstmt.setString(1, roomName);
			
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

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<PatientModel> selectListPatient2(String roomName) {
		
		List<PatientModel> listPatient = new ArrayList<PatientModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT patient_no, patient_code, patient_name, patient_birth, patient_age, patient_gender, patient_blood, patient_tel, "
					+ "patient_addr, patient_addr_detail, room_no, patient_guardian_name, patient_guardian_tel, patient_guardian_relation, "
					+ "patient_state, hospital_no, patient_evacuation_reason, patient_admission_date, patient_diagnosis_date, patient_evacuation_date, "
					+ "patient_return_date, patient_discharge_date, patient_death_date, pcr_group_no, "
					+ "(select room_name from room_info where room_no=p.room_no) as room_name "
					+ "FROM patient_info p "
					+ "WHERE patient_state=1 AND (select room_name from room_info where room_no=p.room_no)=? "
					+ "ORDER BY patient_no DESC ");
			
			pstmt.setString(1, roomName);
			
			rs = pstmt.executeQuery();
			
			int i = 0;
			while(rs.next()) {
				
				PatientModel patient = new PatientModel();
				System.out.println(rs.getString("patient_name")+"("+rs.getString("patient_gender")+", "+rs.getInt("patient_age")+"세)");
				if(i==0) {
	 				patient.setName(rs.getString("patient_name")+"("+rs.getString("patient_gender")+", "+rs.getInt("patient_age")+"세)");	
				} else if(i==1) {
					patient.setName2(rs.getString("patient_name")+"("+rs.getString("patient_gender")+", "+rs.getInt("patient_age")+"세)");
				}
				
				listPatient.add(patient);
				
				i++;
				
				if(i==1) {
					i=0;
				}
				
				
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
