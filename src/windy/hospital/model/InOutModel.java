package windy.hospital.model;

public class InOutModel {
	private long no = -1;
	private long suppliesNo = -1;
	private long medicineNo = -1;
	private String date = "";
	private String classify = "";
	private int amount = 0;
	private long orderNo = -1;
	
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
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
