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
@WebServlet(name = "in_out", urlPatterns = { "/in_out.windy" })
public class InOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InOut() {
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
		MedicineDAO mDao = new MedicineDAO();
		SuppliesDAO sDao = new SuppliesDAO();
		
		if(menu == null) menu = "list";
		
		System.out.println("in_out menu : "+menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "in_out");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			String type = request.getParameter("type");
			
			if(name==null) name = "";
			if(type==null) type = "m";
			
			InOutModel inout = new InOutModel();
			inout.setSuppliesName(name);
			inout.setMedicineName(name);
			inout.setType(type);
			
			ArrayList<InOutModel> listInOut = (ArrayList<InOutModel>) iDao.selectListInOut(inout);

			ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) mDao.selectListMedicine("");
			ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) sDao.selectListSupplies("");
			
			request.setAttribute("name", name);
			request.setAttribute("type", type);
			request.setAttribute("listInOut", listInOut);
			
			request.setAttribute("listMedicine", listMedicine);
			request.setAttribute("listSupplies", listSupplies);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/in_out/in_out_list.jsp");
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
		OrderDAO oDao = new OrderDAO();
				
		String mode = request.getParameter("mode");

		if("add_out".equals(mode)) {
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			String date = request.getParameter("date");
			
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");
			
			InOutModel inout = new InOutModel();
			inout.setSuppliesNo(suppliesNo);
			inout.setMedicineNo(medicineNo);
			inout.setDate(date);
			inout.setAmount(amount);
			inout.setNote(note);
			inout.setClassify("출고");
			
			iDao.insertInOut(inout);
			
			if(0<medicineNo) {
				oDao.updateMedicineAmount(inout);
			} 
			else if(0<suppliesNo) {
				oDao.updateSuppliesAmount(inout);
			}
		}
		else if("add_in".equals(mode)) {
				long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
				long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
				String date = request.getParameter("date");
				
				int amount = Integer.parseInt(request.getParameter("amount"));
				String note = request.getParameter("note");
				
				InOutModel inout = new InOutModel();
				inout.setSuppliesNo(suppliesNo);
				inout.setMedicineNo(medicineNo);
				inout.setDate(date);
				inout.setAmount(amount);
				inout.setNote(note);
				inout.setClassify("입고");
				
				iDao.insertInOut(inout);
				
				if(0<medicineNo) {
					oDao.updateMedicineAmount2(inout);
				} 
				else if(0<suppliesNo) {
					oDao.updateSuppliesAmount2(inout);
				}
			}
		else if("update_out".equals(mode)) {
			long no = Long.parseLong(request.getParameter("no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			String date = request.getParameter("date");
			int exAmount = Integer.parseInt(request.getParameter("ex_amount"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");
			String classify = request.getParameter("classify");
			
			InOutModel inout = new InOutModel();
			inout.setNo(no);
			inout.setDate(date);
			inout.setAmount(amount);
			inout.setNote(note);
			inout.setClassify(classify);
			inout.setMedicineNo(medicineNo);
			inout.setSuppliesNo(suppliesNo);
			
			iDao.updateInOut(inout);

			System.out.println("no : "+no);
			System.out.println("amount : "+amount);
			System.out.println("total : "+(amount+exAmount));
			
			if("입고".equals(classify)) {
				inout.setAmount(exAmount-amount);
			} else {
				inout.setAmount(amount+exAmount);
			}
			
			if(0<medicineNo) {
				
				oDao.updateMedicineAmount(inout);
			} 
			else if(0<suppliesNo) {
				
				oDao.updateSuppliesAmount(inout);
			}
		}
		if("update_in".equals(mode)) {
			long no = Long.parseLong(request.getParameter("no"));
			long medicineNo = Long.parseLong(request.getParameter("medicine_no"));
			long suppliesNo = Long.parseLong(request.getParameter("supplies_no"));
			String date = request.getParameter("date");
			int exAmount = Integer.parseInt(request.getParameter("ex_amount"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");
			String classify = request.getParameter("classify");
			
			InOutModel inout = new InOutModel();
			inout.setNo(no);
			inout.setDate(date);
			inout.setAmount(amount);
			inout.setNote(note);
			inout.setClassify(classify);
			inout.setMedicineNo(medicineNo);
			inout.setSuppliesNo(suppliesNo);
			
			iDao.updateInOut(inout);

			System.out.println("no : "+no);
			System.out.println("amount : "+amount);
			System.out.println("total : "+(amount+exAmount));
			
			if("입고".equals(classify)) {
				inout.setAmount(exAmount-amount);
			} else {
				inout.setAmount(amount+exAmount);
			}
			
			if(0<medicineNo) {
				
				oDao.updateMedicineAmount(inout);
			} 
			else if(0<suppliesNo) {
				
				oDao.updateSuppliesAmount(inout);
			}
		}
	}

}
