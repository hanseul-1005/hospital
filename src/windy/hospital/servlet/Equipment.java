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

import windy.hospital.dao.EquipmentDAO;
import windy.hospital.dao.RegionDAO;
import windy.hospital.dao.SiteDAO;
import windy.hospital.model.EquipmentModel;
import windy.hospital.model.RegionModel;
import windy.hospital.model.SiteModel;

/**
 * Servlet implementation class Equipment
 */
@WebServlet(name = "equipment", urlPatterns = { "/equipment.windy" })
public class Equipment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Equipment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		EquipmentDAO eDao = new EquipmentDAO();
		
		if(menu == null) menu = "patient";
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "equipment");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<EquipmentModel> listEquipment = (ArrayList<EquipmentModel>) eDao.selectListEquipment(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listEquipment", listEquipment);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/equipment/equipment_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/equipment/equipment_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			EquipmentModel equipment = eDao.selectEquipment(no);
			
			request.setAttribute("equipment", equipment);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/equipment/equipment_modify.jsp");
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
		
		EquipmentDAO eDao = new EquipmentDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String date = request.getParameter("date_"+i);
				String as = request.getParameter("as_"+i);
				String note = request.getParameter("note_"+i);
				
				EquipmentModel equipment = new EquipmentModel();
				equipment.setName(name);
				equipment.setDate(date);
				equipment.setAs(as);
				equipment.setNote(note);

				
				int result = eDao.insertEquipment(equipment);
				
				
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
			String date = request.getParameter("date");
			String as = request.getParameter("as");
			String note = request.getParameter("note");
			
			EquipmentModel equipment = new EquipmentModel();
			equipment.setNo(no);
			equipment.setName(name);
			equipment.setDate(date);
			equipment.setAs(as);
			equipment.setNote(note);

			eDao.updateEquipment(equipment);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = eDao.deleteEquipment(no);
		}
	}

}
