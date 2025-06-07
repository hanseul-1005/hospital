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
import windy.hospital.dao.HospitalDAO;
import windy.hospital.model.EquipmentModel;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.RegionModel;

/**
 * Servlet implementation class Hospital
 */
@WebServlet(name = "hospital", urlPatterns = { "/hospital.windy" })
public class Hospital extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hospital() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		HospitalDAO hDao = new HospitalDAO();
		
		if(menu == null) menu = "patient";
		
		request.setAttribute("menu", menu);
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) hDao.selectListHospital(name);
			
			request.setAttribute("name", name);
			request.setAttribute("listHospital", listHospital);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/hospital/hospital_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/hospital/hospital_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			HospitalModel hospital = hDao.selectHospital(no);
			
			request.setAttribute("hospital", hospital);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/hospital/hospital_modify.jsp");
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
		
		HospitalDAO hDao = new HospitalDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String tel = request.getParameter("tel_"+i);
				int cnt = Integer.parseInt(request.getParameter("cnt_"+i));
				String note = request.getParameter("note_"+i);

				HospitalModel hospital = new HospitalModel();
				hospital.setName(name);
				hospital.setTel(tel);
				hospital.setRoomCnt(cnt);
				hospital.setNote(note);
				
				int result = hDao.insertHospital(hospital);
				
				
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

			long no = Long.parseLong(request.getParameter("no"));
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			String note = request.getParameter("note");
			
			HospitalModel hospital = new HospitalModel();
			hospital.setNo(no);
			hospital.setName(name);
			hospital.setTel(tel);
			hospital.setRoomCnt(cnt);
			hospital.setNote(note);

			hDao.updateHospital(hospital);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = hDao.deleteHospital(no);
		}
	}

}
