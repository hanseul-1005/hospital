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
import windy.hospital.dao.MedicineDAO;
import windy.hospital.dao.OrderDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.dao.SuppliesDAO;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.OrderModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SuppliesModel;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "order", urlPatterns = { "/order.windy" })
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		OrderDAO oDao = new OrderDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<OrderModel> listOrder = (ArrayList<OrderModel>) oDao.selectListOrder(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listOrder", listOrder);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/order/order_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/order/order_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			OrderModel order = oDao.selectOrder(no);
			
			request.setAttribute("order", order);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/order/order_modify.jsp");
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
		
		OrderDAO oDao = new OrderDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {
			
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			String date = request.getParameter("date");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");

			OrderModel order = new OrderModel();
			order.setSuppliesNo(suppliesNo);
			order.setMedicineNo(medicineNo);
			order.setDate(date);
			order.setAmount(amount);
			order.setNote(note);
			
			oDao.insertOrder(order);
		}
		else if("update".equals(mode)) {

			long no = Long.parseLong(request.getParameter("no"));
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			String date = request.getParameter("date");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");

			OrderModel order = new OrderModel();
			order.setNo(no);
			order.setSuppliesNo(suppliesNo);
			order.setMedicineNo(medicineNo);
			order.setDate(date);
			order.setAmount(amount);
			order.setNote(note);
			
			oDao.updateOrder(order);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = oDao.deleteOrder(no);
		}
	}

}
