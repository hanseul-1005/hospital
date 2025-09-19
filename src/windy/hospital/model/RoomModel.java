package windy.hospital.model;

public class RoomModel {

	private int no = -1;
	private String code = "";
	private int codeNo = -1;
	private String name = "";
	private int cnt = 0;
	private String del = "N";
	private int patientCnt = 0;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCodeNo() {
		return codeNo;
	}
	public void setCodeNo(int codeNo) {
		this.codeNo = codeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getPatientCnt() {
		return patientCnt;
	}
	public void setPatientCnt(int patientCnt) {
		this.patientCnt = patientCnt;
	}
	
	
	
}
