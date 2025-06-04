package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.RegionModel;
import windy.hospital.model.SiteModel;

public class SiteDAO {
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
	public int insertSite(SiteModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO site_info(site_name, site_person_name, site_belong, site_tel, site_note, site_del) "
					+ "VALUES(?, ?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getPersonName());
			pstmt.setString(3, modelParam.getBelong());
			pstmt.setString(4, modelParam.getTel());
			pstmt.setString(5, modelParam.getNote());
			
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
	public int updateSite(SiteModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE site_info SET site_name=?, site_person_name=?, site_belong=?, site_tel=?, site_note=?, site_del=? "
					+ "WHERE site_no=? ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getPersonName());
			pstmt.setString(3, modelParam.getBelong());
			pstmt.setString(4, modelParam.getTel());
			pstmt.setString(5, modelParam.getNote());
			pstmt.setString(6, modelParam.getDel());
			pstmt.setLong(7, modelParam.getNo());
			
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
	public int deleteSite(int no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE site_info SET site_del='Y' "
					+ "WHERE site_no=? ");

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
	public List<SiteModel> selectListSite(String name) {
		
		List<SiteModel> listSite = new ArrayList<SiteModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT site_no, site_name, site_person_name, site_belong, site_tel, site_note, site_del "
					+ "FROM site_info "
					+ "WHERE site_del = 'N' "
					+ "AND site_name like concat ('%', ?, '%')  "
					+ "ORDER BY site_no DESC ");
			
			pstmt.setString(1, name);

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
