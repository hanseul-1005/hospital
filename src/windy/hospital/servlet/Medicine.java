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
import windy.hospital.dao.MedicineDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.dao.SuppliesDAO;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.InOutModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SuppliesModel;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "medicine", urlPatterns = { "/medicine.windy" })
public class Medicine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Medicine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		MedicineDAO mDao = new MedicineDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "medicine");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) mDao.selectListMedicine(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listMedicine", listMedicine);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/medicine/medicine_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/medicine/medicine_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			MedicineModel medicine = mDao.selectMedicine(no);
			
			request.setAttribute("medicine", medicine);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/medicine/medicine_modify.jsp");
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
		
		MedicineDAO mDao = new MedicineDAO();
		InOutDAO iDao = new InOutDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			long medicineNo = -1;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String date = request.getParameter("date_"+i);
				int amount = Integer.parseInt(request.getParameter("amount_"+i));
				String note = request.getParameter("note_"+i);
				
				MedicineModel medicine = new MedicineModel();
				medicine.setName(name);
				medicine.setDate(date);
				medicine.setAmount(amount);
				medicine.setNote(note);

				
				medicineNo = mDao.insertMedicine(medicine);
				
				InOutModel inout = new InOutModel();
				inout.setMedicineNo(medicineNo);
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
			int amount = Integer.parseInt(request.getParameter("amount"));
			String note = request.getParameter("note");
			
			MedicineModel medicine = new MedicineModel();
			medicine.setNo(no);
			medicine.setName(name);
			medicine.setDate(date);
			medicine.setAmount(amount);
			medicine.setNote(note);

			mDao.updateMedicine(medicine);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = mDao.deleteMedicine(no);
		}
	}

}
