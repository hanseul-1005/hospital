package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.SuppliesModel;

public class MedicineDAO {
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
	public long insertMedicine(MedicineModel modelParam) {
		
		long medicineNo = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO medicine_info(medicine_name, medicine_date, medicine_amount, medicine_note, medicine_del) "
					+ "VALUES(?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getDate());
			pstmt.setInt(3, modelParam.getAmount());
			pstmt.setString(4, modelParam.getNote());
			
			pstmt.executeUpdate();
			
			pstmt = connection.prepareStatement(
					"SELECT medicine_no FROM medicine_info ORDER BY medicine_no DESC limit 1");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				medicineNo = rs.getLong("medicine_no");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return medicineNo;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 수정
	// //////////////////////////////////////////////////
	public int updateMedicine(MedicineModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE medicine_info "
					+ "SET medicine_name=?, medicine_date=?, medicine_amount=?, medicine_note=?, medicine_del=? "
					+ "WHERE medicine_no=? ");

			pstmt.setString(1, modelParam.getName());
			pstmt.setString(2, modelParam.getDate());
			pstmt.setInt(3, modelParam.getAmount());
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
	// - 용품 수정
	// //////////////////////////////////////////////////
	public int deleteMedicine(long no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE medicine_info "
					+ "SET medicine_del='Y' "
					+ "WHERE medicine_no=? ");

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
	public MedicineModel selectMedicine(long no) {
		
		MedicineModel medicine = new MedicineModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT medicine_no, medicine_name, medicine_date, medicine_amount, medicine_note, medicine_del "
					+ "FROM medicine_info "
					+ "WHERE medicine_no = ? ");

			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				medicine.setNo(rs.getLong("medicine_no"));
				medicine.setName(rs.getString("medicine_name"));
				medicine.setDate(rs.getString("medicine_date"));
				medicine.setAmount(rs.getInt("medicine_amount"));
				medicine.setNote(rs.getString("medicine_note"));
				medicine.setDel(rs.getString("medicine_del"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return medicine;				
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
