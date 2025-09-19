package windy.hospital.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import windy.hospital.dao.DBDAO;
import windy.hospital.dao.EquipmentDAO;
import windy.hospital.dao.MedicineDAO;
import windy.hospital.dao.MonitoringDAO;
import windy.hospital.dao.RegionDAO;
import windy.hospital.dao.SiteDAO;
import windy.hospital.dao.SuppliesDAO;
import windy.hospital.model.AmbulanceModel;
import windy.hospital.model.DBModel;
import windy.hospital.model.EmployeeModel;
import windy.hospital.model.EquipmentModel;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.PatientModel;
import windy.hospital.model.RegionModel;
import windy.hospital.model.RoomModel;
import windy.hospital.model.SiteModel;
import windy.hospital.model.SuppliesModel;
import windy.hospital.model.VolunteerModel;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet(name = "monitoring", urlPatterns = { "/monitoring.windy" })
public class Monitoring extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monitoring() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String menu = request.getParameter("menu");
		RegionDAO rDao = new RegionDAO();
		SiteDAO sDao = new SiteDAO();
		MonitoringDAO mDao = new MonitoringDAO();
		EquipmentDAO eDao = new EquipmentDAO();
		SuppliesDAO suDao = new SuppliesDAO();
		MedicineDAO meDao = new MedicineDAO();
		
		if(menu == null) menu = "list";
		
		System.out.println("region menu : "+menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", menu);
		
		if("monitor1".equals(menu)) {
			// 누적 진료
			int totalDiagnosis = mDao.selectTotalDiagnosis();
			// 누적 사망
			int totalDeath = mDao.selectTotalDeath();
			
			// 오늘 진료
			int todayDiagnosis = mDao.selectTodayDiagnosis();
			// 오늘 입원
			int todayAdmission = mDao.selectTodayAdmission();
			// 오늘 후송 
			int todayEvacuation = mDao.selectTodayEvacuation();
			// 오늘 사망
			int todayDeath = mDao.selectTodayDeath();
	
			ArrayList<RegionModel> listRegion = (ArrayList<RegionModel>) rDao.selectListRegion("");
			ArrayList<SiteModel> listSite = (ArrayList<SiteModel>) sDao.selectListSite("");
			ArrayList<AmbulanceModel> listAmbulance = (ArrayList<AmbulanceModel>) mDao.selectListAmbulance();
			ArrayList<VolunteerModel> listVolunteer = (ArrayList<VolunteerModel>) mDao.selectListVolunteer();

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) mDao.selectListRoomCnt();
			
			ArrayList<PatientModel> listHospital = (ArrayList<PatientModel>) mDao.selectListHospital();
			
			request.setAttribute("totalDiagnosis", totalDiagnosis);
			request.setAttribute("totalDeath", totalDeath);
			
			request.setAttribute("todayDiagnosis", todayDiagnosis);
			request.setAttribute("todayAdmission", todayAdmission);
			request.setAttribute("todayEvacuation", todayEvacuation);
			request.setAttribute("todayDeath", todayDeath);
			
			request.setAttribute("listRegion", listRegion);
			request.setAttribute("listSite", listSite);
			request.setAttribute("listAmbulance", listAmbulance);
			request.setAttribute("listVolunteer", listVolunteer);
			
			request.setAttribute("listRoom", listRoom);
			
			request.setAttribute("listHospital", listHospital);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring1.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor2".equals(menu)) {

			// 누적 진료
			int totalDiagnosis = mDao.selectTotalDiagnosis();
			// 누적 입원
			int totalAdmission = mDao.selectTotalAdmission();
			
			// 오늘 입원
			int todayAdmission = mDao.selectTodayAdmission();
			// 오늘 진료
			int todayDiagnosis = mDao.selectTodayDiagnosis();
			// 오늘 후송 
			int todayEvacuation = mDao.selectTodayEvacuation();
			
			ArrayList<PatientModel> listPatientS = (ArrayList<PatientModel>) mDao.selectListPatient("음압");
			ArrayList<PatientModel> listPatientA = (ArrayList<PatientModel>) mDao.selectListPatient2("관찰");
			ArrayList<PatientModel> listPatientB = (ArrayList<PatientModel>) mDao.selectListPatient2("일반");
			
			ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) mDao.selectListHospitalReason();
			
			request.setAttribute("totalDiagnosis", totalDiagnosis);
			request.setAttribute("totalAdmission", totalAdmission);
			request.setAttribute("todayAdmission", todayAdmission);
			request.setAttribute("todayDiagnosis", todayDiagnosis);
			request.setAttribute("todayEvacuation", todayEvacuation);
			
			request.setAttribute("listPatientS", listPatientS);
			request.setAttribute("listPatientA", listPatientA);
			request.setAttribute("listPatientB", listPatientB);
			
			request.setAttribute("listHospital", listHospital);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring2.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor3".equals(menu)) {

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) mDao.selectListRoomCnt();
			
			request.setAttribute("listRoom", listRoom);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring3.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor4".equals(menu)) {

			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring4.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitor5".equals(menu)) {

			
			ArrayList<EmployeeModel> listDoctor = (ArrayList<EmployeeModel>) mDao.selectListEmployee("의사");
			ArrayList<EmployeeModel> listNurseOn = (ArrayList<EmployeeModel>) mDao.selectListEmployeeForNurse("간호사", "근무");
			ArrayList<EmployeeModel> listNurseOff = (ArrayList<EmployeeModel>) mDao.selectListEmployeeForNurse("간호사", "휴무");
			ArrayList<EmployeeModel> listOffice = (ArrayList<EmployeeModel>) mDao.selectListEmployee("행정처");

			ArrayList<EquipmentModel> listEquipment = (ArrayList<EquipmentModel>) eDao.selectListEquipment("");
			ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) suDao.selectListSupplies("");
			ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) meDao.selectListMedicine("");
						

			request.setAttribute("listDoctor", listDoctor);
			request.setAttribute("listNurseOn", listNurseOn);
			request.setAttribute("listNurseOff", listNurseOff);
			request.setAttribute("listOffice", listOffice);
			request.setAttribute("listEquipment", listEquipment);
			request.setAttribute("listSupplies", listSupplies); 
			request.setAttribute("listMedicine", listMedicine);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/monitoring/monitoring5.jsp");
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
		
		DBDAO dDao = new DBDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {

			String code = request.getParameter("code");
			String title = request.getParameter("title");
			String detail = request.getParameter("detail");
			String startDate = request.getParameter("start_date");
			String endDate = request.getParameter("end_date");
			String name = request.getParameter("name");
			
			DBModel db = new DBModel();
			db.setCode(code);
			db.setTitle(title);
			db.setDetail(detail);
			db.setStartDate(startDate);
			db.setEndDate(endDate);
			db.setName(name);

			dDao.insertDB(db);
		}
		else if("update".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			String detail = request.getParameter("detail");
			String startDate = request.getParameter("start_date");
			String endDate = request.getParameter("end_date");
			
			DBModel db = new DBModel();
			db.setNo(no);
			db.setDetail(detail);
			db.setStartDate(startDate);
			db.setEndDate(endDate);

			dDao.updateDB(db);
		}
		else if("delete".equals(mode)) {
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			int result = dDao.deleteDB(no);
		}
	}

}
