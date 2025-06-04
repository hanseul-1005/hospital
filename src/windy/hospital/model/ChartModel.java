package windy.hospital.model;

public class ChartModel {
	private long chartNo = -1;
	private String chartClassify = "";
	private String chartDate = "";
	private String chartContent = "";
	private String chartTest = "";
	private int chartTestResultCheck = -1;
	private String chartTestResult = "";
	private long employeeNo = -1;
	private String employeeName = "";
	private long patientNo = -1;
	
	public long getChartNo() {
		return chartNo;
	}
	public void setChartNo(long chartNo) {
		this.chartNo = chartNo;
	}
	public String getChartClassify() {
		return chartClassify;
	}
	public void setChartClassify(String chartClassify) {
		this.chartClassify = chartClassify;
	}
	public String getChartDate() {
		return chartDate;
	}
	public void setChartDate(String chartDate) {
		this.chartDate = chartDate;
	}
	public String getChartContent() {
		return chartContent;
	}
	public void setChartContent(String chartContent) {
		this.chartContent = chartContent;
	}
	public String getChartTest() {
		return chartTest;
	}
	public void setChartTest(String chartTest) {
		this.chartTest = chartTest;
	}
	public int getChartTestResultCheck() {
		return chartTestResultCheck;
	}
	public void setChartTestResultCheck(int chartTestResultCheck) {
		this.chartTestResultCheck = chartTestResultCheck;
	}
	public String getChartTestResult() {
		return chartTestResult;
	}
	public void setChartTestResult(String chartTestResult) {
		this.chartTestResult = chartTestResult;
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
