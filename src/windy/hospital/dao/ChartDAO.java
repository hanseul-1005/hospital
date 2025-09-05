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
import windy.hospital.model.OrderModel;
import windy.hospital.model.SiteModel;

public class ChartDAO {
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
				
				String content = rs.getString("chart_content");
				
				if("임상병리".equals(rs.getString("chart_classify"))) {
					content = rs.getString("chart_test")+" 검사 결과 : "+rs.getString("chart_test_result")+"\n"
							+"소견 : "+rs.getString("chart_content");
				} else {
					content = rs.getString("chart_content");
				}

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
