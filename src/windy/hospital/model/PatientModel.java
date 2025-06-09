package windy.hospital.model;

import java.util.ArrayList;
import java.util.List;

public class PatientModel {
	private long no = -1;
	private String code = "";
	private String name = "";
	private String birth = "";
	private int age = 0;
	private String gender = "";
	private String blood = "";
	private String tel = "";
	private String addr = "";
	private String addrDetail = "";
	private int roomNo = -1;
	private String roomName = "";
	private String guardianName = "";
	private String guardianTel = "";
	private String guardianRelation = "";
	private int state = 0;
	private long hospitalNo = -1;
	private String evacuationReason = "";
	private String admissionDate = "";
	private String diagnosisDate = "";
	private String evacuationDate = "";
	private String returnDate = "";
	private String dischargeDate = "";
	private String deathDate = "";
	private long pcrGroupNo = -1;
	
	private List<PatientModel> listPatientS = new ArrayList<PatientModel>();
	private List<PatientModel> listPatientA = new ArrayList<PatientModel>();
	private List<PatientModel> listPatientB = new ArrayList<PatientModel>();
	private List<PatientModel> listMonitor = new ArrayList<PatientModel>();
	
	private String name1 ="";
	private String name2 = "";
	private String name3 = "";
	private String name4 = "";
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getGuardianTel() {
		return guardianTel;
	}
	public void setGuardianTel(String guardianTel) {
		this.guardianTel = guardianTel;
	}
	public String getGuardianRelation() {
		return guardianRelation;
	}
	public void setGuardianRelation(String guardianRelation) {
		this.guardianRelation = guardianRelation;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getHospitalNo() {
		return hospitalNo;
	}
	public void setHospitalNo(long hospitalNo) {
		this.hospitalNo = hospitalNo;
	}
	public String getEvacuationReason() {
		return evacuationReason;
	}
	public void setEvacuationReason(String evacuationReason) {
		this.evacuationReason = evacuationReason;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getDiagnosisDate() {
		return diagnosisDate;
	}
	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	public String getEvacuationDate() {
		return evacuationDate;
	}
	public void setEvacuationDate(String evacuationDate) {
		this.evacuationDate = evacuationDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public long getPcrGroupNo() {
		return pcrGroupNo;
	}
	public void setPcrGroupNo(long pcrGroupNo) {
		this.pcrGroupNo = pcrGroupNo;
	}
	public List<PatientModel> getListPatientS() {
		return listPatientS;
	}
	public void setListPatientS(List<PatientModel> listPatientS) {
		this.listPatientS = listPatientS;
	}
	public List<PatientModel> getListPatientA() {
		return listPatientA;
	}
	public void setListPatientA(List<PatientModel> listPatientA) {
		this.listPatientA = listPatientA;
	}
	public List<PatientModel> getListPatientB() {
		return listPatientB;
	}
	public void setListPatientB(List<PatientModel> listPatientB) {
		this.listPatientB = listPatientB;
	}
	public List<PatientModel> getListMonitor() {
		return listMonitor;
	}
	public void setListMonitor(List<PatientModel> listMonitor) {
		this.listMonitor = listMonitor;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	
	
	
	
}
