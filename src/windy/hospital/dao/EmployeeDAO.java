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
import windy.hospital.model.EmployeeModel;

public class EmployeeDAO {
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
	public int insertEmployee(EmployeeModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO employee_info(employee_name, employee_belong, employee_tel, employee_department, employee_major, "
							+ "employee_id, employee_pw, employee_room_no, employee_room_name, employee_on_off, employee_del) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getBelong());
			pstmt.setString(3, modelParam.getTel());
			pstmt.setString(4, modelParam.getDepartment());
			pstmt.setString(5, modelParam.getMajor());
			pstmt.setString(6, modelParam.getId());
			pstmt.setString(7, modelParam.getPw());
			pstmt.setInt(8, modelParam.getRoomNo());
			pstmt.setString(9, modelParam.getRoomName());
			pstmt.setString(10, modelParam.getOnOff());
			
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
	public int updateEmployee(EmployeeModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE employee_info SET employee_belong=?, employee_tel=?, employee_department=?, employee_major=?, "
							+ "employee_id=?, employee_room_no=?, employee_room_name=? "
					+ "WHERE employee_no=? ");

			pstmt.setString(1, modelParam.getBelong());
			pstmt.setString(2, modelParam.getTel());
			pstmt.setString(3, modelParam.getDepartment());
			pstmt.setString(4, modelParam.getMajor());
			pstmt.setString(5, modelParam.getId());
			pstmt.setInt(6, modelParam.getRoomNo());
			pstmt.setString(7, modelParam.getRoomName());
			pstmt.setLong(8, modelParam.getNo());
			
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
	public int deleteEmployee(long no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE employee_info SET employee_del='Y' WHERE employee_no=? ");

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
	// - 용품 등록
	// //////////////////////////////////////////////////
	public int updateEmployeeRoom(EmployeeModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE employee_info SET employee_room_no=?, employee_room_name=? WHERE employee_no=? ");

			pstmt.setInt(1, modelParam.getRoomNo());
			pstmt.setString(2, modelParam.getRoomName());
			pstmt.setLong(3, modelParam.getNo());
			
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
	public EmployeeModel selectEmployee(long no) {

		EmployeeModel emp = new EmployeeModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT employee_no, employee_name, employee_belong, employee_tel, employee_department, "
							+ "employee_major, employee_id, employee_pw, employee_room_no, employee_room_name, "
							+ "employee_on_off, employee_del "
					+ "FROM employee_info "
					+ "WHERE employee_no = ? ");
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return emp;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<EmployeeModel> selectListEmployee(EmployeeModel modelParam) {
		
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
					+ "WHERE employee_del like concat('%', ?, '%') AND employee_department like concat('%', ?, '%') AND employee_name like concat('%', ?, '%') "
					+ "ORDER BY employee_no DESC ");
			
			pstmt.setString(1, modelParam.getDel());
			pstmt.setString(2, modelParam.getDepartment());
			pstmt.setString(3, modelParam.getName());
			
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
