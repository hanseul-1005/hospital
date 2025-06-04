package windy.hospital.servlet;

import java.io.IOException;
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
import windy.hospital.dao.MedicineDAO;
import windy.hospital.dao.OrderDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.dao.SuppliesDAO;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.InOutModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.OrderModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SuppliesModel;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "chart", urlPatterns = { "/chart.windy" })
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		InOutDAO iDao = new InOutDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<InOutModel> listInOut = (ArrayList<InOutModel>) iDao.selectListInOut();
			
			request.setAttribute("name", name);
			request.setAttribute("listInOut", listInOut);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/order/in_out_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/in_out/in_out_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			InOutModel inOut = iDao.selectInOut(no);
			
			request.setAttribute("inOut", inOut);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/in_out/in_out_modify.jsp");
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
		
		InOutDAO iDao = new InOutDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {
			
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			String date = request.getParameter("date");
			String classify = request.getParameter("classify");
			int amount = Integer.parseInt(request.getParameter("amount"));
			long orderNo = Long.parseLong(request.getParameter("order_no"));

			InOutModel inOut = new InOutModel();
			inOut.setSuppliesNo(suppliesNo);
			inOut.setMedicineNo(medicineNo);
			inOut.setDate(date);
			inOut.setClassify(classify);
			inOut.setAmount(amount);
			inOut.setOrderNo(orderNo);
			
			iDao.insertInOut(inOut);
		}
		else if("update".equals(mode)) {

			long no = Long.parseLong(request.getParameter("no"));
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			String date = request.getParameter("date");
			String classify = request.getParameter("classify");
			int amount = Integer.parseInt(request.getParameter("amount"));
			long orderNo = Long.parseLong(request.getParameter("order_no"));

			InOutModel inOut = new InOutModel();
			inOut.setSuppliesNo(suppliesNo);
			inOut.setMedicineNo(medicineNo);
			inOut.setDate(date);
			inOut.setClassify(classify);
			inOut.setAmount(amount);
			inOut.setOrderNo(orderNo);
			
			iDao.updateInOut(inOut);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = iDao.deleteInOut(no);
		}
	}

}
