package windy.hospital.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import windy.hospital.dao.HospitalDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SuppliesModel;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "room", urlPatterns = { "/room.windy" })
public class Room extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Room() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		RoomDAO rDao = new RoomDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "room");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/room/room_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/room/room_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			RoomModel room = rDao.selectRoom(no);
			
			request.setAttribute("room", room);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/room/room_modify.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		RoomDAO rDao = new RoomDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String code = request.getParameter("code_"+i);
				int codeNo = Integer.parseInt(request.getParameter("code_no_"+i));
				int cnt = Integer.parseInt(request.getParameter("cnt_"+i));
				
				System.out.println("name : "+name);
				
				RoomModel room = new RoomModel();
				room.setName(name);
				room.setCode(code);
				room.setCodeNo(codeNo);
				room.setCnt(cnt);

				
				int result = rDao.insertRoom(room);
				
				
				System.out.println("result : "+result);
				total = total+result;
			}
			
			
			System.out.println("total : "+total);
			
			String ret = "";
			
			if(total==size) {
				ret = "true";
			} else {
				ret = "false";
			}
			
			PrintWriter out = response.getWriter();
			out.print(ret);
		}
		else if("update".equals(mode)) {

			int no = Integer.parseInt(request.getParameter("no"));
			String code = request.getParameter("code");
			int codeNo = Integer.parseInt(request.getParameter("code_no"));
			String name = request.getParameter("name");
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			
			RoomModel room = new RoomModel();
			room.setNo(no);
			room.setCode(code);
			room.setCodeNo(codeNo);
			room.setName(name);
			room.setCnt(cnt);

			rDao.updateRoom(room);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = rDao.deleteRoom(no);
		}
	}

}
