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
import windy.hospital.dao.InOutDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.dao.SuppliesDAO;
import windy.hospital.model.EquipmentModel;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.InOutModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SuppliesModel;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "supplies", urlPatterns = { "/supplies.windy" })
public class Supplies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Supplies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		SuppliesDAO sDao = new SuppliesDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "supplies");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) sDao.selectListSupplies(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listSupplies", listSupplies);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/supplies/supplies_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/supplies/supplies_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			SuppliesModel supplies = sDao.selectSupplies(no);
			
			request.setAttribute("supplies", supplies);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/supplies/supplies_modify.jsp");
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
		
		SuppliesDAO sDao = new SuppliesDAO();
		InOutDAO iDao = new InOutDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String date = request.getParameter("date_"+i);
				int amount = Integer.parseInt(request.getParameter("amount_"+i));
				String note = request.getParameter("note_"+i);
				
				SuppliesModel supplies = new SuppliesModel();
				supplies.setName(name);
				supplies.setDate(date);
				supplies.setAmount(amount);
				supplies.setNote(note);

				
				long suppliesNo = sDao.insertSupplies(supplies);

				InOutModel inout = new InOutModel();
				inout.setSuppliesNo(suppliesNo);
				inout.setClassify("입고");
				inout.setAmount(amount);
				inout.setNote("최초 등록");
				
				iDao.insertInOut(inout);
				
				total = total+1;
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

			long no = Long.parseLong(request.getParameter("no"));
			String name = request.getParameter("name");
			String date = request.getParameter("date");
			String tel = request.getParameter("tel");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");
			
			SuppliesModel supplies = new SuppliesModel();
			supplies.setNo(no);
			supplies.setName(name);
			supplies.setDate(date);
			supplies.setTel(tel);
			supplies.setAmount(amount);
			supplies.setNote(note);

			sDao.updateSupplies(supplies);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = sDao.deleteSupplies(no);
		}
	}

}
