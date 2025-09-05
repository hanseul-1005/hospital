package windy.hospital.servlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import windy.hospital.dao.RestDAO;
import windy.hospital.model.ChartModel;
import windy.hospital.model.EmployeeModel;
import windy.hospital.model.MedicineModel;
import windy.hospital.model.PatientModel;


/**
 * Servlet implementation class Rest
 */
@WebServlet(description = "Rest", urlPatterns = { "/rest.windy" })
public class Rest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		RestDAO rDao = new RestDAO();
		

		String cmd = request.getParameter("cmd");
		System.out.println("rest cmd : "+cmd);
		
		if("login".equals(cmd)) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			System.out.println("id : "+id);
			System.out.println("pw : "+pw);
			EmployeeModel employee = new EmployeeModel();
			employee.setId(id);
			employee.setPw(pw);
			
			employee = rDao.login(employee);
			
			JSONObject json = new JSONObject();
			if(employee.isCheck()) {
				json.put("result", String.valueOf(employee.isCheck()));
				json.put("no", employee.getNo());
				json.put("name", employee.getName());
				json.put("belong", employee.getBelong());
				json.put("department", employee.getDepartment());
				json.put("room_no", employee.getRoomNo()+"");
				
				String message = "";
				if(!"간호".equals(employee.getDepartment()) && !"의사".equals(employee.getDepartment())) {
					message = "의료진 전용 어플리케이션입니다.";
				} else {
					if(employee.getRoomNo()==-1) {
						message = "정해진 지정실이 없습니다.\n부서장에게 문의해주세요.";
					}
				}
				
				json.put("message", message);
			
			}else{
				json.put("result", "false");
				json.put("message", "아이디와 비밀번호를 확인해주세요.");
			}
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
		else if("patient_monitoring".equals(cmd)) {
			
			int roomNo = Integer.parseInt(request.getParameter("room_no"));
			
			ArrayList<PatientModel> listPatient = (ArrayList<PatientModel>) rDao.selectListPatient(roomNo);
			
			JSONArray jArr = new JSONArray();
			for(int i=0; i<listPatient.size(); i++) {
				JSONObject jObj = new JSONObject();
				
				jObj.put("no", listPatient.get(i).getNo()+"");
				jObj.put("name", listPatient.get(i).getName()+"("+listPatient.get(i).getGender()+", "+listPatient.get(i).getAge()+"세, "+listPatient.get(i).getCode()+")");
				
				jArr.add(jObj);
			}
			
			JSONObject json = new JSONObject();
			
			String result = "false";
			if(0<jArr.size()) {
				result = "true";
			}
			System.out.println("result : "+result);
			response.setContentType("text/json; charset=utf-8");
			json.put("listPatient", jArr);
			json.put("result", result);

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
		else if("patient_list".equals(cmd)) {
			
			int roomNo = Integer.parseInt(request.getParameter("room_no"));
			
			ArrayList<PatientModel> listPatient = (ArrayList<PatientModel>) rDao.selectListPatient(roomNo);
			
			JSONArray jArr = new JSONArray();
			for(int i=0; i<listPatient.size(); i++) {
				JSONObject jObj = new JSONObject();
				
				jObj.put("no", listPatient.get(i).getNo()+"");
				jObj.put("name", listPatient.get(i).getName()+"");
				jObj.put("code", listPatient.get(i).getCode()+"");
				jObj.put("gender", listPatient.get(i).getGender()+"");
				
				jArr.add(jObj);
			}
			
			JSONObject json = new JSONObject();
			
			String result = "false";
			if(0<jArr.size()) {
				result = "true";
			}
			System.out.println("result : "+result);
			response.setContentType("text/json; charset=utf-8");
			json.put("listPatient", jArr);
			json.put("result", result);

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
		else if("chart_info".equals(cmd)) {
			long no = Long.parseLong(request.getParameter("patient_no"));
			
			ArrayList<ChartModel> listChart = (ArrayList<ChartModel>) rDao.selectListChart(no);
			
			JSONArray jArr = new JSONArray();
			for(int i=0; i<listChart.size(); i++) {
				ChartModel chart = listChart.get(i);
				
				JSONObject jObj = new JSONObject();
				
				jObj.put("date", chart.getDate()+"");
				jObj.put("name", chart.getEmployeeName()+"");
				
				String content = "";
				
				if("임상병리".equals(chart.getClassify())) {
					content = chart.getTest()+" 검사 결과 : "+chart.getTestResult()+"\n"
							+"소견 : "+chart.getContent();
				} else {
					content = chart.getContent();
				}
				
				jObj.put("content", content);
				
				jArr.add(jObj);
			}
			
			JSONObject json = new JSONObject();
			
			String result = "false";
			if(0<jArr.size()) {
				result = "true";
			}
			System.out.println("result : "+result);
			response.setContentType("text/json; charset=utf-8");
			json.put("listPatient", jArr);
			json.put("result", result);

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
		else if("add_chart".equals(cmd)) {
			long employeeNo = Long.parseLong(request.getParameter("employee_no"));
			String employeName = request.getParameter("employee_name");
			long patientNo = Long.parseLong(request.getParameter("patient_no"));
			String department = request.getParameter("department");
			String date = request.getParameter("date");
			String content = request.getParameter("content");
			//int testCheck = Integer.parseInt(request.getParameter("test_check"));
			String test = request.getParameter("test");
			//int testResultCheck = Integer.parseInt(request.getParameter("test_result_check"));
			String testResult = request.getParameter("rest_result");
						
			ChartModel chart = new ChartModel();
			chart.setEmployeeNo(employeeNo);
			chart.setEmployeeName(employeName);
			chart.setPatientNo(patientNo);
			chart.setClassify(department);
			chart.setDate(date);
			chart.setContent(content);
			chart.setTest("");
			chart.setTestCheck(0);
			chart.setTestResult("");
			chart.setTestResultCheck(0);
			
			int result = rDao.insertChart(chart);

			JSONObject json = new JSONObject();
			
			if(result == -1) {
				json.put("result", "false");
			} else {
				json.put("result", "true");
			}

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
		else if("medicine_list".equals(cmd)) {

			String searchText = request.getParameter("search_text");
			
			ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) rDao.selectListMedicine(searchText);
			
			JSONArray jArr = new JSONArray();
			for(int i=0; i<listMedicine.size(); i++) {
				JSONObject jObj = new JSONObject();
				
				jObj.put("no", String.valueOf(i+1));
				jObj.put("name", listMedicine.get(i).getName()+"");
				jObj.put("count", listMedicine.get(i).getAmount()+"");
				
				jArr.add(jObj);
			}
			
			JSONObject json = new JSONObject();
			
			String result = "false";
			if(0<jArr.size()) {
				result = "true";
			}
			System.out.println("result : "+result);
			response.setContentType("text/json; charset=utf-8");
			json.put("listMedicine", jArr);
			json.put("result", result);

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		}
	}

}
