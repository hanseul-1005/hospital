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
import windy.hospital.model.VolunteerModel;

public class AdministrationDAO {
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
