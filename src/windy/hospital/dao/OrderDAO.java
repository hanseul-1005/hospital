package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.OrderModel;
import windy.hospital.model.SiteModel;

public class OrderDAO {
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
	public int insertOrder(OrderModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO order_info(supplies_no, medicine_no, order_date, order_amount, order_note) "
					+ "VALUES(?, ?, ?, ?, ?) ");

			pstmt.setLong(1, modelParam.getSuppliesNo());
			pstmt.setLong(2, modelParam.getMedicineNo());
			pstmt.setString(3, modelParam.getDate());
			pstmt.setInt(4, modelParam.getAmount());
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
	public int updateOrder(OrderModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE order_info "
					+ "SET supplies_no=?, medicine_no=?, order_date=?, order_amount=?, order_note=? "
					+ "WHERE order_no=? ");

			pstmt.setLong(1, modelParam.getSuppliesNo());
			pstmt.setLong(2, modelParam.getMedicineNo());
			pstmt.setString(3, modelParam.getDate());
			pstmt.setInt(4, modelParam.getAmount());
			pstmt.setString(5, modelParam.getNote());
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
	// - 용품 등록
	// //////////////////////////////////////////////////
	public int deleteOrder(long no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"DELETE FROM order_info WHERE order_no=? ");

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
	public OrderModel selectOrder(long no) {

		OrderModel order = new OrderModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT order_no, supplies_no, medicine_no, order_date, order_maount, order_note "
					+ "FROM order_info "
					+ "WHERE order_no = ? ");
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				order.setNo(rs.getLong("order_no"));
				order.setSuppliesNo(rs.getLong("supplies_no"));
				order.setMedicineNo(rs.getLong("medicine_no"));
				order.setDate(rs.getString("order_date"));
				order.setAmount(rs.getInt("order_amount"));
				order.setNote(rs.getString("order_note"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return order;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<OrderModel> selectListOrder(String name) {
		
		List<OrderModel> listOrder = new ArrayList<OrderModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT order_no, supplies_no, medicine_no, order_date, order_maount, order_note "
					+ "FROM order_info "
					+ "WHERE order_name like concat ('%', ?, '%') "
					+ "ORDER BY order_no DESC ");
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderModel order = new OrderModel();
				
				order.setNo(rs.getLong("order_no"));
				order.setSuppliesNo(rs.getLong("supplies_no"));
				order.setMedicineNo(rs.getLong("medicine_no"));
				order.setDate(rs.getString("order_date"));
				order.setAmount(rs.getInt("order_amount"));
				order.setNote(rs.getString("order_note"));
				
				listOrder.add(order);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listOrder;				
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
