package windy.hospital.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import windy.hospital.dao.DBDAO;
import windy.hospital.dao.EmployeeDAO;
import windy.hospital.model.DBModel;
import windy.hospital.model.EmployeeModel;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = { "/login.windy" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("here");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		request.setCharacterEncoding("UTF-8");

		String mode = request.getParameter("mode");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		HttpSession session = request.getSession();

		System.out.println("mode : "+mode);
		
		DBDAO dDao = new DBDAO();

		if ("login".equals(mode)) {
			
			EmployeeDAO eDao = new EmployeeDAO();
			
			EmployeeModel emp = new EmployeeModel(); 
			emp.setId(id);
			emp.setPw(passwd);
			int result = eDao.login(emp);
			
			if(result==1) {
				DBModel db = dDao.selectDB();
				
				System.out.println("login department : "+emp.getDepartment());
				if("admin".equals(id)) {
					
					session.setAttribute("id", id);
					session.setAttribute("pw", passwd);		
					session.setAttribute("role", "관리자");
					session.setAttribute("no", emp.getNo());
					session.setAttribute("name", emp.getName());
					session.setAttribute("department", emp.getDepartment());
					session.setMaxInactiveInterval(60 * 60 * 10);
					session.setAttribute("db_name", db.getName());

					JSONObject objResult = new JSONObject();
					response.setContentType("text/json; charset=utf-8");
					objResult.put("result", "true");	
					
					
					PrintWriter out = response.getWriter();					
					out.print(objResult);
				} else {
					session.setAttribute("id", id);
					session.setAttribute("pw", passwd);	
					session.setAttribute("role", emp.getDepartment());		
					session.setAttribute("no", emp.getNo());
					session.setAttribute("name", emp.getName());
					session.setAttribute("department", emp.getDepartment());
					session.setMaxInactiveInterval(60 * 60 * 10);
					session.setAttribute("db_name", db.getName());
					

					JSONObject objResult = new JSONObject();
					response.setContentType("text/json; charset=utf-8");
					objResult.put("result", "true");	
					
					
					PrintWriter out = response.getWriter();					
					out.print(objResult);
				}
			} else {
				JSONObject objResult = new JSONObject();
				response.setContentType("text/json; charset=utf-8");
				objResult.put("result", "false");
				
				PrintWriter out = response.getWriter();					
				out.print(objResult);
				
			}
	
		}
	}

}
