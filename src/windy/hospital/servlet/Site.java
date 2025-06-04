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

import windy.hospital.dao.RegionDAO;
import windy.hospital.dao.SiteDAO;
import windy.hospital.model.RegionModel;
import windy.hospital.model.SiteModel;

/**
 * Servlet implementation class Site
 */
@WebServlet(name = "site", urlPatterns = { "/site.windy" })
public class Site extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Site() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		SiteDAO sDao = new SiteDAO();
		
		if(menu == null) menu = "patient";
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "site");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<SiteModel> listSite = (ArrayList<SiteModel>) sDao.selectListSite(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listSite", listSite);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/site/site_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/site/site_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			SiteModel site = sDao.selectSite(no);
			
			request.setAttribute("site", site);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/site/site_modify.jsp");
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
		
		SiteDAO sDao = new SiteDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String personName = request.getParameter("person_name_"+i);
				String belong = request.getParameter("belong_"+i);
				String tel = request.getParameter("tel_"+i);
				String note = request.getParameter("note_"+i);
				
				SiteModel site = new SiteModel();
				site.setName(name);
				site.setPersonName(personName);
				site.setBelong(belong);
				site.setTel(tel);
				site.setNote(note);

				
				int result = sDao.insertSite(site);
				
				
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
			String personName = request.getParameter("person_name");
			String belong = request.getParameter("belong");
			String tel = request.getParameter("tel");
			String note = request.getParameter("note");
			
			SiteModel site = new SiteModel();
			site.setNo(no);
			site.setName(name);
			site.setPersonName(personName);
			site.setBelong(belong);
			site.setTel(tel);
			site.setNote(note);

			sDao.updateSite(site);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = sDao.deleteSite(no);
		}
	}

}
