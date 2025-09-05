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

import org.json.simple.JSONObject;

import windy.hospital.dao.EmployeeDAO;
import windy.hospital.dao.RegionDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.model.EmployeeModel;
import windy.hospital.model.RegionModel;
import windy.hospital.model.RoomModel;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet(name = "manager", urlPatterns = { "/manager.windy" })
public class Manager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String menu = request.getParameter("menu");
		EmployeeDAO eDao = new EmployeeDAO();
		RoomDAO rDao = new RoomDAO();
		
		
		if(menu == null) menu = "list";
		
		System.out.println("employee menu : "+menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "manager");
		
		if("list".equals(menu)) {

			String del = request.getParameter("del");
			String department = request.getParameter("department");
			String name = request.getParameter("name");
			
			if(del==null) del = "";
			if(department==null) department = "";
			if(name==null) name = "";
			
			EmployeeModel employee = new EmployeeModel();
			employee.setDel(del);
			employee.setDepartment(department);
			employee.setName(name);
			
			ArrayList<EmployeeModel> listEmployee = (ArrayList<EmployeeModel>) eDao.selectListEmployeeForManager(employee);
			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");
			
			request.setAttribute("del", del);
			request.setAttribute("name", name);
			request.setAttribute("department", department);
			request.setAttribute("listEmployee", listEmployee);
			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/manager/manager_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");

			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/manager/manager_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			
			EmployeeModel employee = eDao.selectEmployee(no);
			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");
			
			request.setAttribute("employee", employee);
			request.setAttribute("listRoom", listRoom);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/manager/manager_modify.jsp");
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
		
		EmployeeDAO eDao = new EmployeeDAO();
				
		String mode = request.getParameter("mode");
		
		System.out.println("employee mode : "+mode);
		

		if("add".equals(mode)) {
			
			String name = request.getParameter("name");
			String belong = request.getParameter("belong");
			String tel = request.getParameter("tel");
			String department = request.getParameter("department");
			String major = request.getParameter("major");
			
			EmployeeModel employee = new EmployeeModel();
			employee.setName(name);
			employee.setBelong(belong);
			employee.setTel(tel);
			employee.setDepartment(department);
			employee.setMajor(major);
			employee.setRoomNo(-1);
			employee.setRoomName("");
			employee.setId(tel);
			employee.setPw("emap123");
			employee.setOnOff("근무");
			employee.setRoomNo(0);
			employee.setRoomName("");
			
			long no = eDao.insertEmployee(employee);
			
			eDao.insertManager(no);
		}
		else if("update".equals(mode)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			String belong = request.getParameter("belong");
			String tel = request.getParameter("tel");
			String department = request.getParameter("department");
			String major = request.getParameter("major");
			int roomNo = Integer.parseInt(request.getParameter("room_no"));
			String roomName = request.getParameter("room_name");
			
			EmployeeModel employee = new EmployeeModel();
			employee.setNo(no);
			employee.setBelong(belong);
			employee.setTel(tel);
			employee.setDepartment(department);
			employee.setMajor(major);
			employee.setRoomNo(roomNo);
			employee.setRoomName(roomName);
			employee.setId(tel);
			
			eDao.updateEmployee(employee);
		}
		else if("delete".equals(mode)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			
			int result = eDao.deleteEmployee(no);
		}
		else if("update_department".equals(mode)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			String department = request.getParameter("department");
			
			EmployeeModel employee = new EmployeeModel();
			employee.setNo(no);
			employee.setDepartment(department);
			
			
			int result = eDao.updateEmployeeDepartment(employee);
		}
	}

}
