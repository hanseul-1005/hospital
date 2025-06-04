package windy.hospital.model;

public class OrderModel {

	private long no = -1;
	private long suppliesNo = -1;
	private long medicineNo = -1;
	private String date = "";
	private int amount = 0;
	private String note = "";
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getSuppliesNo() {
		return suppliesNo;
	}
	public void setSuppliesNo(long suppliesNo) {
		this.suppliesNo = suppliesNo;
	}
	public long getMedicineNo() {
		return medicineNo;
	}
	public void setMedicineNo(long medicineNo) {
		this.medicineNo = medicineNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
