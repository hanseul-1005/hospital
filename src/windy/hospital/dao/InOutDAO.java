package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.InOutModel;
import windy.hospital.model.SiteModel;

public class InOutDAO {
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
	public int insertInOut(InOutModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO in_out_info(supplies_no, medicine_no, in_out_date, in_out_classify, in_out_amount, order_no, in_out_note) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?) ");

			pstmt.setLong(1, modelParam.getSuppliesNo());
			pstmt.setLong(2, modelParam.getMedicineNo());
			pstmt.setString(3, modelParam.getDate());
			pstmt.setString(4, modelParam.getClassify());
			pstmt.setInt(5, modelParam.getAmount());
			pstmt.setLong(6, modelParam.getOrderNo());
			pstmt.setString(7, modelParam.getNote());
			
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
	public int updateInOut(InOutModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE in_out_info "
					+ "SET in_out_date=?, in_out_amount=?, in_out_note=? "
					+ "WHERE in_out_no=? ");

			pstmt.setString(1, modelParam.getDate());
			pstmt.setInt(2, modelParam.getAmount());
			pstmt.setString(3, modelParam.getNote());
			pstmt.setLong(4, modelParam.getNo());
			
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
	public int deleteInOut(long no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"DELETE FROM in_out_info WHERE in_out_no=? ");

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
	public InOutModel selectInOut(long no) {

		InOutModel inout = new InOutModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT in_out_no, supplies_no, medicine_no, in_out_date, in_out_classify, in_out_amount, order_no "
					+ "FROM in_out_info "
					+ "WHERE in_out_no = ? ");
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				inout.setNo(rs.getLong("in_out_no"));
				inout.setSuppliesNo(rs.getLong("supplies_no"));
				inout.setMedicineNo(rs.getLong("medicine_no"));
				inout.setDate(rs.getString("in_out_date"));
				inout.setClassify(rs.getString("in_out_classify"));
				inout.setAmount(rs.getInt("in_out_amount"));
				inout.setOrderNo(rs.getLong("order_no"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return inout;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<InOutModel> selectListInOut(InOutModel modelParam) {
		
		List<InOutModel> listInOut = new ArrayList<InOutModel>();
		
		String whereSQL = "";
		
		if("s".equals(modelParam.getType())) {
			whereSQL = "WHERE 0< ii.supplies_no "
					+ "AND (select supplies_name from supplies_info si where ii.supplies_no = si.supplies_no ) LIKE CONCAT('%', '"+modelParam.getSuppliesName()+"', '%') ";
		} else {
			whereSQL = "WHERE 0< ii.medicine_no "
					+ "AND (select medicine_name from medicine_info mi where ii.medicine_no = mi.medicine_no ) LIKE CONCAT('%', '"+modelParam.getMedicineName()+"', '%') ";
		}
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT in_out_no, supplies_no, medicine_no, in_out_date, in_out_classify, in_out_amount, order_no, in_out_note, "
					+ "(select medicine_name from medicine_info mi where ii.medicine_no = mi.medicine_no ) as medicine_name, "
					+ "(select supplies_name from supplies_info si where ii.supplies_no = si.supplies_no ) as supplies_name, "
					+ "IFNULL((select medicine_amount from medicine_info mmi where mmi.medicine_no = ii.medicine_no), 0) as medicine_amount, "
					+ "IFNULL((select supplies_amount from supplies_info ssi where ssi.supplies_no = ii.supplies_no), 0) as supplies_amount "
					+ "FROM in_out_info ii "
					+ whereSQL
					+ "ORDER BY in_out_no DESC ");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				InOutModel inout = new InOutModel();
				
				inout.setNo(rs.getLong("in_out_no"));
				inout.setSuppliesNo(rs.getLong("supplies_no"));
				inout.setMedicineNo(rs.getLong("medicine_no"));
				inout.setDate(rs.getString("in_out_date"));
				inout.setClassify(rs.getString("in_out_classify"));
				inout.setAmount(rs.getInt("in_out_amount"));
				inout.setOrderNo(rs.getLong("order_no"));
				inout.setNote(rs.getString("in_out_note"));
				inout.setMedicineName(rs.getString("medicine_name"));
				inout.setSuppliesName(rs.getString("supplies_name"));
				inout.setMedicineAmount(rs.getInt("medicine_amount"));
				inout.setSuppliesAmount(rs.getInt("supplies_amount"));
				
				listInOut.add(inout);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listInOut;				
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
