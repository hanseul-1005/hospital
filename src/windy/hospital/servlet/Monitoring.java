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

import windy.hospital.dao.DBDAO;
import windy.hospital.dao.RegionDAO;
import windy.hospital.dao.SiteDAO;
import windy.hospital.model.DBModel;
import windy.hospital.model.RegionModel;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet(name = "monitoring", urlPatterns = { "/monitoring.windy" })
public class Monitoring extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monitoring() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String menu = request.getParameter("menu");
		RegionDAO rDao = new RegionDAO();
		SiteDAO sDao = new SiteDAO();
		
		
		if(menu == null) menu = "list";
		
		System.out.println("region menu : "+menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", menu);
		
		if("monitor1".equals(menu)) {

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring1.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor2".equals(menu)) {

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring2.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor3".equals(menu)) {

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring3.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor4".equals(menu)) {

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring4.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor5".equals(menu)) {

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring5.jsp");
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
		
		DBDAO dDao = new DBDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			String code = request.getParameter("code");
			String title = request.getParameter("title");
			String detail = request.getParameter("detail");
			String startDate = request.getParameter("start_date");
			String endDate = request.getParameter("end_date");
			String name = request.getParameter("name");
			
			DBModel db = new DBModel();
			db.setCode(code);
			db.setTitle(title);
			db.setDetail(detail);
			db.setStartDate(startDate);
			db.setEndDate(endDate);
			db.setName(name);

			dDao.insertDB(db);
		}
		else if("update".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			String detail = request.getParameter("detail");
			String startDate = request.getParameter("start_date");
			String endDate = request.getParameter("end_date");
			
			DBModel db = new DBModel();
			db.setNo(no);
			db.setDetail(detail);
			db.setStartDate(startDate);
			db.setEndDate(endDate);

			dDao.updateDB(db);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = dDao.deleteDB(no);
		}
	}

}
