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

import windy.hospital.dao.RegionDAO;
import windy.hospital.model.RegionModel;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet(name = "region", urlPatterns = { "/region.windy" })
public class Region extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Region() {
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
		
		if(menu == null) menu = "list";
		
		System.out.println("region menu : "+menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "region");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<RegionModel> listRegion = (ArrayList<RegionModel>) rDao.selectListRegion(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listRegion", listRegion);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/region/region_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/region/region_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			RegionModel region = rDao.selectRegion(no);
			
			request.setAttribute("region", region);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/region/region_add.jsp");
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
