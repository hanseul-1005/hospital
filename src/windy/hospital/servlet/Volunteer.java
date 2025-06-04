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

import windy.hospital.dao.AmbulanceDAO;
import windy.hospital.dao.HospitalDAO;
import windy.hospital.dao.InOutDAO;
import windy.hospital.dao.MedicineDAO;
import windy.hospital.dao.OrderDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.dao.SuppliesDAO;
import windy.hospital.dao.VolunteerDAO;
import windy.hospital.model.AmbulanceModel;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.InOutModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.OrderModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SuppliesModel;
import windy.hospital.model.VolunteerModel;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "volunteer", urlPatterns = { "/volunteer.windy" })
public class Volunteer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Volunteer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menu = request.getParameter("menu");
		VolunteerDAO vDao = new VolunteerDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		
		if("list".equals(menu)) {

			ArrayList<VolunteerModel> listVolunteer = (ArrayList<VolunteerModel>) vDao.selectListVolunteer();
			
			request.setAttribute("listVolunteer", listVolunteer);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/volunteer/volunteer_list.jsp");
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
		
		VolunteerDAO vDao = new VolunteerDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {
			
			String belong = request.getParameter("belong");
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			
			VolunteerModel volunteer = new VolunteerModel();
			volunteer.setBelong(belong);
			volunteer.setCnt(cnt);
			
			vDao.insertVolunteer(volunteer);
		}
		else if("delete".equals(mode)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			
			int result = vDao.deleteVolunteer(no);
		}
	}

}
