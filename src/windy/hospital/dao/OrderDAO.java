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
					+ "SET order_date=?, order_amount=?, order_note=? "
					+ "WHERE order_no=? ");

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
					"SELECT order_no, supplies_no, medicine_no, order_date, order_amount, order_note, "
					+ "(select medicine_name from medicine_info mi where oi.medicine_no = mi.medicine_no ) as medicine_name, "
					+ "(select supplies_name from supplies_info mi where oi.supplies_no = mi.supplies_no ) as supplies_name, "
					+ "IFNULL((select sum(ioi.in_out_amount) from in_out_info ioi where ioi.order_no = oi.order_no ), 0) as in_amount  "
					+ "FROM order_info oi "
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
				order.setMedicineName(rs.getString("medicine_name"));
				order.setSuppliesName(rs.getString("supplies_name"));
				order.setInAmount(rs.getInt("in_amount"));
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
		
		String whereSQL = "";
		if(!"".equals(name)) {
			whereSQL = "WHERE (select medicine_name from medicine_info mi where oi.medicine_no = mi.medicine_no ) LIKE CONCAT('%', '"+name+"', '%') "
					+ "OR (select supplies_name from supplies_info mi where oi.supplies_no = mi.supplies_no ) LIKE CONCAT('%', '"+name+"', '%') ";
		}
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT order_no, supplies_no, medicine_no, order_date, order_amount, order_note, "
					+ "(select medicine_name from medicine_info mi where oi.medicine_no = mi.medicine_no ) as medicine_name, "
					+ "(select supplies_name from supplies_info mi where oi.supplies_no = mi.supplies_no ) as supplies_name, "
					+ "IFNULL((select sum(ioi.in_out_amount) from in_out_info ioi where ioi.order_no = oi.order_no ), 0) as in_amount  "
					+ "FROM order_info oi "
					+ whereSQL
					+ "ORDER BY order_no DESC ");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderModel order = new OrderModel();
				
				order.setNo(rs.getLong("order_no"));
				order.setSuppliesNo(rs.getLong("supplies_no"));
				order.setMedicineNo(rs.getLong("medicine_no"));
				order.setDate(rs.getString("order_date"));
				order.setAmount(rs.getInt("order_amount"));
				order.setNote(rs.getString("order_note"));
				order.setMedicineName(rs.getString("medicine_name"));
				order.setSuppliesName(rs.getString("supplies_name"));
				order.setInAmount(rs.getInt("in_amount"));
				
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

	// //////////////////////////////////////////////////
	// - 용품 등록
	// //////////////////////////////////////////////////
	public void updateMedicineAmount(InOutModel modelParam) {
		
		int amount = 0;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT medicine_amount FROM medicine_info WHERE medicine_no=? ");

			pstmt.setLong(1, modelParam.getMedicineNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				amount = rs.getInt("medicine_amount");
			}
			
			pstmt = connection.prepareStatement(
					"UPDATE medicine_info SET medicine_amount=? WHERE medicine_no=? ");

			pstmt.setLong(1, amount+modelParam.getAmount());
			pstmt.setLong(2, modelParam.getMedicineNo());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}		
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 등록
	// //////////////////////////////////////////////////
	public void updateSuppliesAmount(InOutModel modelParam) {
		
		int amount = 0;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT supplies_amount FROM supplies_info WHERE supplies_no=? ");
			
			pstmt.setLong(1, modelParam.getSuppliesNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				amount = rs.getInt("supplies_amount");
			}
			
			pstmt = connection.prepareStatement(
					"UPDATE supplies_info SET supplies_amount=? WHERE supplies_no=? ");

			pstmt.setLong(1, amount+modelParam.getAmount());
			pstmt.setLong(2, modelParam.getSuppliesNo());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}		
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
