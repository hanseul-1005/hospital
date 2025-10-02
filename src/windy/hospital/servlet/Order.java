package windy.hospital.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

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
		MedicineDAO mDao = new MedicineDAO();
		SuppliesDAO sDao = new SuppliesDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "order");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<OrderModel> listOrder = (ArrayList<OrderModel>) oDao.selectListOrder(name);

			ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) mDao.selectListMedicine("");
			ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) sDao.selectListSupplies("");
			
			request.setAttribute("name", name);
			request.setAttribute("listOrder", listOrder);

			request.setAttribute("listMedicine", listMedicine);
			request.setAttribute("listSupplies", listSupplies);
			
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
		//doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		OrderDAO oDao = new OrderDAO();
				
		String mode = request.getParameter("mode");
		System.out.println("order mode : "+mode);
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
			String date = request.getParameter("date");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");

			OrderModel order = new OrderModel();
			order.setNo(no);
			order.setDate(date);
			order.setAmount(amount);
			order.setNote(note);
			
			oDao.updateOrder(order);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = oDao.deleteOrder(no);
		}
		
		else if("get_order".equals(mode)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			
			OrderModel order = oDao.selectOrder(no);
			
			String name = order.getMedicineName();
			if(order.getMedicineNo()==-1) {
				name = order.getSuppliesName();
			}
			
			response.setContentType("application/json; charset=utf-8");
		    
		    JSONObject objResult = new JSONObject();
		    objResult.put("result", "true");
		    objResult.put("name", name);
		    objResult.put("date", order.getDate());
		    objResult.put("amount", order.getAmount());
		    objResult.put("note", order.getNote());
		    
		    PrintWriter out = response.getWriter();
		    out.print(objResult.toString());
		    out.flush();
		    out.close();
		    
		    return;  // 이 줄이 반드시 필요!
		}
		
		else if("add_in_out".equals(mode)) {
			long orderNo = Long.parseLong(request.getParameter("no"));
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			String date = request.getParameter("date");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");
			
			InOutModel inout = new InOutModel();
			inout.setOrderNo(orderNo);
			inout.setSuppliesNo(suppliesNo);
			inout.setMedicineNo(medicineNo);
			inout.setDate(date);
			inout.setAmount(amount);
			inout.setNote(note);
			inout.setClassify("입고");
			
			InOutDAO iDao = new InOutDAO();
			iDao.insertInOut(inout);
			
			if(0<medicineNo) {
				oDao.updateMedicineAmount(inout);
			} 
			else if(0<suppliesNo) {
				oDao.updateSuppliesAmount(inout);
			}
		}
	}

}
