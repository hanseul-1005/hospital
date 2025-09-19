package windy.hospital.model;

public class HospitalModel {
	
	private long no = -1;
	private String name = "";
	private String tel = "";
	private int roomCnt = 0;
	private String note = "";
	private String del = "N";
	
	private int patientCnt1 = 0;
	private int patientCnt2 = 0;
	private int patientCnt3 = 0;
	private int patientCnt4 = 0;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getRoomCnt() {
		return roomCnt;
	}
	public void setRoomCnt(int roomCnt) {
		this.roomCnt = roomCnt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getPatientCnt1() {
		return patientCnt1;
	}
	public void setPatientCnt1(int patientCnt1) {
		this.patientCnt1 = patientCnt1;
	}
	public int getPatientCnt2() {
		return patientCnt2;
	}
	public void setPatientCnt2(int patientCnt2) {
		this.patientCnt2 = patientCnt2;
	}
	public int getPatientCnt3() {
		return patientCnt3;
	}
	public void setPatientCnt3(int patientCnt3) {
		this.patientCnt3 = patientCnt3;
	}
	public int getPatientCnt4() {
		return patientCnt4;
	}
	public void setPatientCnt4(int patientCnt4) {
		this.patientCnt4 = patientCnt4;
	}
	
	

}
