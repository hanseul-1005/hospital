package windy.hospital.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 행정처
 * 
 * 
 */

@WebServlet(name = "administration", urlPatterns = { "/administration.windy" })
public class Administration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		
		//
		if("employee_list".equals(menu)) {
			
			String type = request.getParameter("type");
			String department = request.getParameter("department");
			String name = request.getParameter("name");
			
			if(type == null) type = "1";
			if(department == null) department = "all";
			if(name == null) name = "";
			
			request.setAttribute("type", type);
			request.setAttribute("department", department);
			request.setAttribute("name", name);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/admin/monitor.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
