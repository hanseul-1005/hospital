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
@WebServlet(name = "employee", urlPatterns = { "/employee.windy" })
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee() {
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
		request.setAttribute("main_menu", "employee");
		
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
			
			ArrayList<EmployeeModel> listEmployee = (ArrayList<EmployeeModel>) eDao.selectListEmployee(employee);
			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");
			
			request.setAttribute("del", del);
			request.setAttribute("name", name);
			request.setAttribute("department", department);
			request.setAttribute("listEmployee", listEmployee);
			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");

			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("multi_add".equals(menu)) {

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");

			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_multi_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			
			EmployeeModel employee = eDao.selectEmployee(no);
			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");
			
			request.setAttribute("employee", employee);
			request.setAttribute("listRoom", listRoom);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_modify.jsp");
			dispatcher.forward(request, response);
		}
		else if("manage".equals(menu)) {
			
			String onDepartment = request.getParameter("on_department");
			String onName = request.getParameter("on_name");
			String offDepartment = request.getParameter("off_department");
			String offName = request.getParameter("off_name");
			
			if(onDepartment == null) onDepartment = "";
			if(onName == null) onName = "";
			if(offDepartment == null) offDepartment = "";
			if(offName == null) offName = "";
			
			EmployeeModel emp = new EmployeeModel();
			emp.setDepartment(onDepartment);
			emp.setName(onName);
			emp.setOnOff("근무");
			System.out.println("onDepartment : "+onDepartment);
			System.out.println("onName : "+onName);
			ArrayList<EmployeeModel> listEmpOn = (ArrayList<EmployeeModel>) eDao.selectListEmployeeForManage(emp);
			
			emp.setDepartment(offDepartment);
			emp.setName(offName);
			emp.setOnOff("휴무");
			
			ArrayList<EmployeeModel> listEmpOff = (ArrayList<EmployeeModel>) eDao.selectListEmployeeForManage(emp);

			request.setAttribute("main_menu", "manage");
			
			request.setAttribute("listEmpOn", listEmpOn);
			request.setAttribute("listEmpOff", listEmpOff);
			request.setAttribute("onDepartment", onDepartment);
			request.setAttribute("onName", onName);
			request.setAttribute("offDepartment", offDepartment);
			request.setAttribute("offName", offName);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_manage.jsp");
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
			int roomNo = Integer.parseInt(request.getParameter("room_no"));
			String roomName = request.getParameter("room_name");
			
			EmployeeModel employee = new EmployeeModel();
			employee.setName(name);
			employee.setBelong(belong);
			employee.setTel(tel);
			employee.setDepartment(department);
			employee.setMajor(major);
			employee.setRoomNo(roomNo);
			employee.setRoomName(roomName);
			employee.setId(tel);
			employee.setPw("emap123");
			employee.setOnOff("근무");
			
			eDao.insertEmployee(employee);
		}
		else if("delete".equals(mode)) {
			long no = Long.parseLong(request.getParameter("no"));
			
			eDao.deleteEmployee(no);
		}
		else if("multi_add".equals(mode)) {
			
			int size = Integer.parseInt(request.getParameter("size"));
			String department = request.getParameter("department");

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String tel = request.getParameter("tel_"+i);
				String belong = request.getParameter("belong_"+i);
				String major = request.getParameter("major_"+i);
				int roomNo = Integer.parseInt(request.getParameter("room_no_"+i));
				String roomName = request.getParameter("room_name_"+i);
				String onOff = request.getParameter("on_off_"+i);
				
				EmployeeModel employee = new EmployeeModel();
				employee.setName(name);
				employee.setTel(tel);
				employee.setBelong(belong);
				employee.setMajor(major);
				employee.setRoomNo(roomNo);
				employee.setRoomName(roomName);
				employee.setOnOff(onOff);
				employee.setDepartment(department);
				employee.setId(tel);
				employee.setPw("emap123");

				
				int result = eDao.insertEmployee(employee);
				
				
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
			
			/*
			 * JSONObject objResult = new JSONObject();
			 * response.setContentType("text/json; charset=utf-8"); objResult.put("ret",
			 * ret); System.out.println("ret : "+ret);
			 * 
			 * PrintWriter out = response.getWriter(); out.print(objResult);
			 */
			
			PrintWriter out = response.getWriter();
			out.print(ret);
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
		else if("update_room".equals(mode)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			int roomNo = Integer.parseInt(request.getParameter("room_no"));
			String roomName = request.getParameter("room_name");
			
			EmployeeModel employee = new EmployeeModel();
			employee.setNo(no);
			employee.setRoomNo(roomNo);
			employee.setRoomName(roomName);
			
			
			int result = eDao.updateEmployeeRoom(employee);
		}
		else if("manage_off".equals(mode)) {
			
			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				long no = Long.parseLong(request.getParameter("no_"+i));
				
				int result = eDao.updateEmployeeOnOff(no, "휴무");
				
			}
			
			
			System.out.println("total : "+total);
			
			String ret = "";
			
			if(total==size) {
				ret = "true";
			} else {
				ret = "false";
			}
			
			/*
			 * JSONObject objResult = new JSONObject();
			 * response.setContentType("text/json; charset=utf-8"); objResult.put("ret",
			 * ret); System.out.println("ret : "+ret);
			 * 
			 * PrintWriter out = response.getWriter(); out.print(objResult);
			 */
			
			PrintWriter out = response.getWriter();
			out.print(ret);
		}
		else if("manage_on".equals(mode)) {
			
			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				long no = Long.parseLong(request.getParameter("no_"+i));
				
				int result = eDao.updateEmployeeOnOff(no, "근무");
				
			}
			
			
			System.out.println("total : "+total);
			
			String ret = "";
			
			if(total==size) {
				ret = "true";
			} else {
				ret = "false";
			}
			
			/*
			 * JSONObject objResult = new JSONObject();
			 * response.setContentType("text/json; charset=utf-8"); objResult.put("ret",
			 * ret); System.out.println("ret : "+ret);
			 * 
			 * PrintWriter out = response.getWriter(); out.print(objResult);
			 */
			
			PrintWriter out = response.getWriter();
			out.print(ret);
		}
	}

}
