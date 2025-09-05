package windy.hospital.model;

public class ChartModel {
	private long no = -1;
	private String classify = "";
	private String date = "";
	private String content = "";
	private String test = "";
	private int testCheck = -1;
	private int testResultCheck = -1;
	private String testResult = "";
	private long employeeNo = -1;
	private String employeeName = "";
	private long patientNo = -1;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public int getTestCheck() {
		return testCheck;
	}
	public void setTestCheck(int testCheck) {
		this.testCheck = testCheck;
	}
	public int getTestResultCheck() {
		return testResultCheck;
	}
	public void setTestResultCheck(int testResultCheck) {
		this.testResultCheck = testResultCheck;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public long getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(long employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public long getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(long patientNo) {
		this.patientNo = patientNo;
	}
	
	
}
