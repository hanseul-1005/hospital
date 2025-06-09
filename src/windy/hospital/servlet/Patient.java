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

import org.json.simple.JSONObject;

import windy.hospital.dao.HospitalDAO;
import windy.hospital.dao.PatientDAO;
import windy.hospital.dao.RegionDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.PatientModel;
import windy.hospital.model.RegionModel;
import windy.hospital.model.RoomModel;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet(name = "patient", urlPatterns = { "/patient.windy" })
public class Patient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Patient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String menu = request.getParameter("menu");
		PatientDAO pDao = new PatientDAO();
		RoomDAO rDao = new RoomDAO();
		HospitalDAO hDao = new HospitalDAO();
		
		
		if(menu == null) menu = "list";
		
		System.out.println("patient menu : "+menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("main_menu", "patient");
		
		if("list".equals(menu)) {

			String name = request.getParameter("name");
			
			if(name==null) name = "";
			
			ArrayList<PatientModel> listPatient = (ArrayList<PatientModel>) pDao.selectListPatient();
			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");
			ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) hDao.selectListHospital(""); 
					
			String code = (String) pDao.selectPatientCode();
			
			if(code.length()>5) {
				
				code = "AA"+(Integer.parseInt(code.substring(2, 5))+1);
			} else {
				code = "AA0001";
			}
			
			
			request.setAttribute("name", name);
			request.setAttribute("code", code);
			request.setAttribute("listPatient", listPatient);
			request.setAttribute("listRoom", listRoom);
			request.setAttribute("listHospital", listHospital);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/patient/patient_list.jsp");
			dispatcher.forward(request, response);
		}
		else if("multi_add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/patient/patient_multi_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("add".equals(menu)) {

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");
			ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) hDao.selectListHospital(""); 

			request.setAttribute("listRoom", listRoom);
			request.setAttribute("listHospital", listHospital);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/patient/patient_add.jsp");
			dispatcher.forward(request, response);
			
		}
		else if("modify".equals(menu)) {
			
			long no = Long.parseLong(request.getParameter("no"));

			ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) rDao.selectListRoom("");
			ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) hDao.selectListHospital(""); 
			PatientModel patient = pDao.selectPatient(no);
			
			request.setAttribute("patient", patient);
			request.setAttribute("listRoom", listRoom);
			request.setAttribute("listHospital", listHospital);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/patient/patient_modify.jsp");
			dispatcher.forward(request, response);
		}
		else if("monitoring".equals(menu)) {
			
			
			ArrayList<PatientModel> listPatient = (ArrayList<PatientModel>) pDao.selectListPatientForMonitoring();
			
			request.setAttribute("listPatient", listPatient);
			request.setAttribute("main_menu", "patient_monitoring");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/patient/patient_monitoring.jsp");
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
		
		PatientDAO pDao = new PatientDAO();
				
		String mode = request.getParameter("mode");
		
		if("multi_add".equals(mode)) {
			
			int size = Integer.parseInt(request.getParameter("size"));

			System.out.println("size="+size);
			
			int total = 0;
			
			for(int i=0; i<size; i++) {

				String name = request.getParameter("name_"+i);
				String gender = request.getParameter("gender_"+i);
				String birth = request.getParameter("birth_"+i);
				String tel = request.getParameter("tel_"+i);
				int age = Integer.parseInt(request.getParameter("age_"+i));

				PatientModel patient = new PatientModel();
				patient.setName(name);
				patient.setGender(gender);
				patient.setBirth(birth);
				patient.setTel(tel);
				patient.setAge(age);
				
				int result = pDao.insertPatientMulti(patient);
				
				
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
		else if("add".equals(mode)) {
			
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			String blood = request.getParameter("blood");
			String tel = request.getParameter("tel");
			String addr = request.getParameter("addr_detail");
			String addrDetail = request.getParameter("addr_detail");
			int roomNo = Integer.parseInt(request.getParameter("room_no"));
			String guardianName = request.getParameter("guardian_name");
			String guardianTel = request.getParameter("guardian_tel");
			String guardianRelation= request.getParameter("guardian_relation");
			int state = Integer.parseInt(request.getParameter("state"));
			long hosipitalNo = Long.parseLong(request.getParameter("hospital_no"));
			String evacuationReason = request.getParameter("evacuation_reason");
			
			PatientModel patient = new PatientModel();
			patient.setCode(code);
			patient.setName(name);
			patient.setBirth(birth);
			patient.setAge(age);
			patient.setGender(gender);
			patient.setBlood(blood);
			patient.setTel(tel);
			patient.setAddr(addr);
			patient.setAddrDetail(addrDetail);
			patient.setRoomNo(roomNo);
			patient.setGuardianName(guardianName);
			patient.setGuardianTel(guardianTel);
			patient.setGuardianRelation(guardianRelation);
			patient.setState(state);
			patient.setHospitalNo(hosipitalNo);
			patient.setEvacuationReason(evacuationReason);
			

			pDao.insertPatient(patient);
		}
		else if("update".equals(mode)) {

			long no = Long.parseLong(request.getParameter("no"));
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			String blood = request.getParameter("blood");
			String tel = request.getParameter("tel");
			String addr = request.getParameter("addr_detail");
			String addrDetail = request.getParameter("addr_detail");
			int roomNo = Integer.parseInt(request.getParameter("room_no"));
			String guardianName = request.getParameter("guardian_name");
			String guardianTel = request.getParameter("guardian_tel");
			String guardianRelation= request.getParameter("guardian_relation");
			int state = Integer.parseInt(request.getParameter("state"));
			long hosipitalNo = Long.parseLong(request.getParameter("hospital_no"));
			String evacuationReason = request.getParameter("evacuation_reason");
			
			PatientModel patient = new PatientModel();
			patient.setNo(no);
			patient.setName(name);
			patient.setBirth(birth);
			patient.setAge(age);
			patient.setGender(gender);
			patient.setBlood(blood);
			patient.setTel(tel);
			patient.setAddr(addr);
			patient.setAddrDetail(addrDetail);
			patient.setRoomNo(roomNo);
			patient.setGuardianName(guardianName);
			patient.setGuardianTel(guardianTel);
			patient.setGuardianRelation(guardianRelation);
			patient.setState(state);
			patient.setHospitalNo(hosipitalNo);
			patient.setEvacuationReason(evacuationReason);
			System.out.println("no : "+no);
			pDao.updatePatient(patient);
		}
		else if("delete".equals(mode)) {
			
			long no = Long.parseLong(request.getParameter("no"));
			
			int result = pDao.deletePatient(no);
		}
	}

}
