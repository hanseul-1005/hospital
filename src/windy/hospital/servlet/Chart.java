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

import windy.hospital.dao.ChartDAO;
import windy.hospital.dao.HospitalDAO;
import windy.hospital.dao.InOutDAO;
import windy.hospital.dao.MedicineDAO;
import windy.hospital.dao.OrderDAO;
import windy.hospital.dao.PatientDAO;
import windy.hospital.dao.RoomDAO;
import windy.hospital.dao.SuppliesDAO;
import windy.hospital.model.ChartModel;
import windy.hospital.model.HospitalModel;
import windy.hospital.model.InOutModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.OrderModel;
import windy.hospital.model.PatientModel;
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
		ChartDAO cDao = new ChartDAO();
		PatientDAO pDao = new PatientDAO();
		
		if(menu == null) menu = "list";
		
		request.setAttribute("menu", menu);
		
		if("chart".equals(menu)) {

			long no = Long.parseLong(request.getParameter("no"));
			
			PatientModel patient = pDao.selectPatient(no);
			ArrayList<ChartModel> listChart = (ArrayList<ChartModel>) cDao.selectListChart(no);
			
			MedicineDAO mDao = new MedicineDAO();
			ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) mDao.selectListMedicine("");
					
			request.setAttribute("no", no);
			request.setAttribute("patient", patient);
			request.setAttribute("listChart", listChart);
			request.setAttribute("listMedicine", listMedicine);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/patient/patient_chart.jsp");
			dispatcher.forward(request, response);
		}
		else if("add".equals(menu)) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/chart/chart_add.jsp");
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
		
		ChartDAO cDao = new ChartDAO();
				
		String mode = request.getParameter("mode");
		
		if("add".equals(mode)) {
			long employeeNo = (long) session.getAttribute("no");
			String employeName = (String) session.getAttribute("name");
			long patientNo = Long.parseLong(request.getParameter("no"));
			String department = (String) session.getAttribute("department");
			String date = request.getParameter("date");
			String content = request.getParameter("content");
			int testCheck = Integer.parseInt(request.getParameter("test_check"));
			String test = request.getParameter("test");
			int testResultCheck = Integer.parseInt(request.getParameter("test_result_check"));
			String testResult = request.getParameter("rest_result");
						
			ChartModel chart = new ChartModel();
			chart.setEmployeeNo(employeeNo);
			chart.setEmployeeName(employeName);
			chart.setPatientNo(patientNo);
			chart.setClassify(department);
			chart.setDate(date);
			chart.setContent(content);
			chart.setTest(test);
			chart.setTestCheck(testCheck);
			chart.setTestResult(testResult);
			chart.setTestResultCheck(testResultCheck);
			
			int result = cDao.insertChart(chart);
		}
	}

}
