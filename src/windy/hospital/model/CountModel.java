package windy.hospital.model;

public class CountModel {
	// 입원, 누적진료, 누적사망
	private int enterCnt = 0;
	private int totalDiaCnt = 0;
	private int totalDeathCnt = 0;
	
	// 오늘입원, 오늘진료, 오늘후송, 오늘사망
	private int todayEnterCnt = 0;
	private int todayDiaCnt = 0;
	private int todayEvaCnt = 0;
	private int todayDeathCnt = 0;
	
	// 음압합계, 관찰합계, 일반합계
	private int sCnt = 0;
	private int aCnt = 0;
	private int bCnt = 0;
	
	// 전체합계, 사용량합계, 잔여량합계, S병실합계, A합계, B합계
	private float genTotalCnt = 0;
	private float genUseCnt = 0;
	private float genRemainCnt = 0;
	private float genSCnt = 0;
	private float getACnt = 0;
	private float genBCnt = 0;
	public int getEnterCnt() {
		return enterCnt;
	}
	public void setEnterCnt(int enterCnt) {
		this.enterCnt = enterCnt;
	}
	public int getTotalDiaCnt() {
		return totalDiaCnt;
	}
	public void setTotalDiaCnt(int totalDiaCnt) {
		this.totalDiaCnt = totalDiaCnt;
	}
	public int getTotalDeathCnt() {
		return totalDeathCnt;
	}
	public void setTotalDeathCnt(int totalDeathCnt) {
		this.totalDeathCnt = totalDeathCnt;
	}
	public int getTodayEnterCnt() {
		return todayEnterCnt;
	}
	public void setTodayEnterCnt(int todayEnterCnt) {
		this.todayEnterCnt = todayEnterCnt;
	}
	public int getTodayDiaCnt() {
		return todayDiaCnt;
	}
	public void setTodayDiaCnt(int todayDiaCnt) {
		this.todayDiaCnt = todayDiaCnt;
	}
	public int getTodayEvaCnt() {
		return todayEvaCnt;
	}
	public void setTodayEvaCnt(int todayEvaCnt) {
		this.todayEvaCnt = todayEvaCnt;
	}
	public int getTodayDeathCnt() {
		return todayDeathCnt;
	}
	public void setTodayDeathCnt(int todayDeathCnt) {
		this.todayDeathCnt = todayDeathCnt;
	}
	public int getsCnt() {
		return sCnt;
	}
	public void setsCnt(int sCnt) {
		this.sCnt = sCnt;
	}
	public int getaCnt() {
		return aCnt;
	}
	public void setaCnt(int aCnt) {
		this.aCnt = aCnt;
	}
	public int getbCnt() {
		return bCnt;
	}
	public void setbCnt(int bCnt) {
		this.bCnt = bCnt;
	}
	public float getGenTotalCnt() {
		return genTotalCnt;
	}
	public void setGenTotalCnt(float genTotalCnt) {
		this.genTotalCnt = genTotalCnt;
	}
	public float getGenUseCnt() {
		return genUseCnt;
	}
	public void setGenUseCnt(float genUseCnt) {
		this.genUseCnt = genUseCnt;
	}
	public float getGenRemainCnt() {
		return genRemainCnt;
	}
	public void setGenRemainCnt(float genRemainCnt) {
		this.genRemainCnt = genRemainCnt;
	}
	public float getGenSCnt() {
		return genSCnt;
	}
	public void setGenSCnt(float genSCnt) {
		this.genSCnt = genSCnt;
	}
	public float getGetACnt() {
		return getACnt;
	}
	public void setGetACnt(float getACnt) {
		this.getACnt = getACnt;
	}
	public float getGenBCnt() {
		return genBCnt;
	}
	public void setGenBCnt(float genBCnt) {
		this.genBCnt = genBCnt;
	}
	
	
}
