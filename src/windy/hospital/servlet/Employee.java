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
		
		if(menu == null) menu = "list";
		
		System.out.println("region menu : "+menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "region");
		
		if("list".equals(menu)) {

			RoomDAO rDao = new RoomDAO();
			
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
			
			request.setAttribute("name", name);
			request.setAttribute("listEmployee", listEmployee);
			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RoomDAO rDao = new RoomDAO();

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");

			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			
			EmployeeModel employee = eDao.selectEmployee(no);
			
			request.setAttribute("employee", employee);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/employee/employee_add.jsp");
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
		
		RegionDAO rDao = new RegionDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {
			
			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String tel = request.getParameter("tel_"+i);
				
				System.out.println("name_"+i+"="+name);
				System.out.println("tel_"+i+"="+tel);
				
				RegionModel region = new RegionModel();
				region.setName(name);
				region.setTel(tel);

				
				int result = rDao.insertRegion(region);
				
				
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
			
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");
			
			RegionModel region = new RegionModel();
			region.setNo(no);
			region.setName(name);
			region.setTel(tel);

			rDao.updateRegion(region);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = rDao.deleteRegion(no);
		}
	}

}
