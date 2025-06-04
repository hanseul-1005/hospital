package windy.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import windy.hospital.model.DatabaseModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SiteModel;

public class RoomDAO {
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
	public int insertRoom(RoomModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"INSERT INTO room_info(room_code, room_code_no, room_name, room_cnt, room_del) "
					+ "VALUES(?, ?, ?, ?, 'N') ");

			pstmt.setString(1, modelParam.getCode());
			pstmt.setInt(2, modelParam.getCodeNo());
			pstmt.setString(3, modelParam.getName());
			pstmt.setInt(4, modelParam.getCnt());
			
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
	public int updateRoom(RoomModel modelParam) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE room_info "
					+ "SET room_code=?, room_code_no=?, room_name=?, room_cnt=?, room_del=? "
					+ "WHERE room_no=? ");

			pstmt.setString(1, modelParam.getCode());
			pstmt.setInt(2, modelParam.getCodeNo());
			pstmt.setString(3, modelParam.getName());
			pstmt.setInt(4, modelParam.getCnt());
			pstmt.setString(5, modelParam.getDel());
			pstmt.setInt(6, modelParam.getNo());
			
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
	public int deleteRoom(int no) {
		
		int result = -1;
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"UPDATE room_info "
					+ "SET room_del='Y' "
					+ "WHERE room_no=? ");

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
	public RoomModel selectRoom(int no) {

		RoomModel room = new RoomModel();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT room_no, room_code, room_code_no, room_name, room_cnt, room_del "
					+ "FROM room_info "
					+ "WHERE room_no = ? ");
			
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				room.setNo(rs.getInt("room_no"));
				room.setCode(rs.getString("room_code"));
				room.setCodeNo(rs.getInt("room_code_no"));
				room.setName(rs.getString("room_name"));
				room.setCnt(rs.getInt("room_cnt"));
				room.setDel(rs.getString("room_del"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return room;				
	}
			
	// //////////////////////////////////////////////////

	// //////////////////////////////////////////////////
	// - 용품 목록 조회
	// //////////////////////////////////////////////////
	public List<RoomModel> selectListRoom(String name) {
		
		List<RoomModel> listRoom = new ArrayList<RoomModel>();
		
		try {
			// 데이터베이스 객체 생성
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(jdbcUrl, user, password);

			pstmt = connection.prepareStatement(
					"SELECT room_no, room_code, room_code_no, room_name, room_cnt, room_del "
					+ "FROM room_info "
					+ "WHERE room_del = 'N' AND room_name like concat ('%', ?, '%') "
					+ "ORDER BY room_no ASC, room_code_no ASC ");
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RoomModel room = new RoomModel();
				
				room.setNo(rs.getInt("room_no"));
				room.setCode(rs.getString("room_code"));
				room.setCodeNo(rs.getInt("room_code_no"));
				room.setName(rs.getString("room_name"));
				room.setCnt(rs.getInt("room_cnt"));
				room.setDel(rs.getString("room_del"));
				
				listRoom.add(room);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용한 객체 종료
			close(rs, pstmt, connection);
		}
		return listRoom;				
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
