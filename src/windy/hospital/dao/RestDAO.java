package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.ChartModel;
import windy.hospital.model.DatabaseModel;
import windy.hospital.model.EmployeeModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.PatientModel;

public class RestDAO {
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
	public EmployeeModel login(EmployeeModel employee) {

		boolean check = false;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT employee_no, employee_name, employee_belong, employee_department, employee_room_no, employee_room_name "
					+ "FROM employee_info "
					+ "WHERE employee_id=? AND employee_pw=? AND employee_del='N' ");

			pstmt.setString(1, employee.getId());
			pstmt.setString(2, employee.getPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				employee.setCheck(true);
				employee.setNo(rs.getLong("employee_no"));
				employee.setName(rs.getString("employee_name"));
				employee.setBelong(rs.getString("employee_belong"));
				employee.setDepartment(rs.getString("employee_department"));
				employee.setRoomNo(rs.getInt("employee_room_no"));
				employee.setRoomName(rs.getString("employee_room_name"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return employee;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<PatientModel> selectListPatient(int roomNo) {
		
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
					+ "WHERE room_no = ? "
					+ "ORDER BY patient_no DESC ");
			
			pstmt.setInt(1, roomNo);
			
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
	public List<ChartModel> selectListChart(long patientNo) {
		
		List<ChartModel> listChart = new ArrayList<ChartModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT chart_date, chart_classify, employee_name, chart_content, chart_test, chart_test_result_check, chart_test_result "
					+ "FROM chart_info "
					+ "WHERE patient_no = ? "
					+ "ORDER BY chart_no DESC ");
			
			pstmt.setLong(1, patientNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ChartModel chart = new ChartModel();
				chart.setDate(rs.getString("chart_date"));
				chart.setClassify(rs.getString("chart_classify"));
				chart.setEmployeeName(rs.getString("employee_name"));
				chart.setContent(rs.getString("chart_content"));
				chart.setTest(rs.getString("chart_test"));
				chart.setTestResultCheck(rs.getInt("chart_test_result_check"));
				chart.setTestResult(rs.getString("chart_test_result"));

				listChart.add(chart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listChart;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 로그인
	// //////////////////////////////////////////////////
	public int insertChart(ChartModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT into chart_info(chart_classify, chart_date, chart_content, chart_test, chart_test_check, chart_test_result, chart_test_result_check, employee_no, employee_name, patient_no) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setString(1, modelParam.getClassify());
			pstmt.setString(2, modelParam.getDate());
			pstmt.setString(3, modelParam.getContent());
			pstmt.setString(4, modelParam.getTest());
			pstmt.setInt(5, modelParam.getTestCheck());
			pstmt.setString(6, modelParam.getTestResult());
			pstmt.setInt(7, modelParam.getTestResultCheck());
			pstmt.setLong(8, modelParam.getEmployeeNo());
			pstmt.setString(9, modelParam.getEmployeeName());
			pstmt.setLong(10, modelParam.getPatientNo());
			
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
	public List<MedicineModel> selectListMedicine(String name) {
		
		List<MedicineModel> listMedicine = new ArrayList<MedicineModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT medicine_no, medicine_name, medicine_date, medicine_amount, medicine_note, medicine_del "
					+ "FROM medicine_info "
					+ "WHERE medicine_del = 'N' AND medicine_name like concat ('%', ?, '%')  ");

			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MedicineModel medicine = new MedicineModel();
				
				medicine.setNo(rs.getLong("medicine_no"));
				medicine.setName(rs.getString("medicine_name"));
				medicine.setDate(rs.getString("medicine_date"));
				medicine.setAmount(rs.getInt("medicine_amount"));
				medicine.setNote(rs.getString("medicine_note"));
				medicine.setDel(rs.getString("medicine_del"));
				
				listMedicine.add(medicine);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listMedicine;				
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
