<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String mainMenu = (String) request.getAttribute("main_menu");
String menu = (String) request.getAttribute("menu");

String mMonitor = "", mAdmin="", mPassword="", mCar="", mPatientManage="", mSiteAdd="";
String dotMonitor = "○", dotAdmin="○", dotPassword="○", dotCar="○", dotPatientManage="○", dotSiteAdd="○"; 

// 관리자
String mDb = "", mManager = "", mMonitoring1 = "", mMonitoring2 = "", mMonitoring3 = "", mMonitoring4 = "", mMonitoring5 = "";

String dotDb = "○", dotManager = "○", dotMonitoring1 = "○", dotMonitoring2 = "○", dotMonitoring3 = "○", dotMonitoring4 = "○", dotMonitoring5 = "○";
// 행정처
String mEmployee = "", mPatient = "", mRegion = "", mSite = "", mMedical = "", mEquipment = "", mOrder = "",
	mSupplies = "", mMedicine = "", mRoom="", mHospital="", mInOut = ""; 
String dotEmployee = "○", dotPatient = "○", dotRegion = "○", dotSite = "○", dotMedical = "○", dotEquipment = "○", dotOrder = "○", 
	dotSupplies = "○", dotMedicine = "○", dotRoom="○", dotHospital="○", dotInOut="○";

// 의사
String mPatientMonitoring = "", mChart = "", mManage = "";
String dotPatientMonitoring = "○", dotChart = "○", dotManage = "○";

// 비밀번호 변경
String mPw = "";
String dotPw = "○";

if("employee".equals(mainMenu)) {
	mEmployee = "side_menu_on";
	dotEmployee = "●";
}
else if("patient".equals(mainMenu)) {
	mPatient = "side_menu_on";
	dotPatient = "●";
}
else if("region".equals(mainMenu)) {
	mRegion = "side_menu_on";
	dotRegion = "●";
}
else if("site".equals(mainMenu)) {
	mSite = "side_menu_on";
	dotSite = "●";
}
else if("medical".equals(mainMenu)) {
	mMedical = "side_menu_on";
	dotMedical = "●";
}
else if("equipment".equals(mainMenu)) {
	mEquipment = "side_menu_on";
	dotEquipment = "●";
}
else if("order".equals(mainMenu)) {
	mOrder = "side_menu_on";
	dotOrder = "●";
}
else if("supplies".equals(mainMenu)) {
	mSupplies = "side_menu_on";
	dotSupplies = "●";
}
else if("medicine".equals(mainMenu)) {
	mMedicine = "side_menu_on";
	dotMedicine = "●";
}
else if("room".equals(mainMenu)) {
	mRoom = "side_menu_on";
	dotRoom = "●";
}
else if("hospital".equals(mainMenu)) {
	mHospital = "side_menu_on";
	dotHospital = "●";
}
else if("pw".equals(mainMenu)) {
	mPw = "side_menu_on";
	dotPw = "●";
}
else if("db".equals(mainMenu)) {
	mDb = "side_menu_on";
	dotDb = "●";
}
else if("manager".equals(mainMenu)) {
	mManager = "side_menu_on";
	dotManager = "●";
}
else if("patient_monitoring".equals(mainMenu)) {
	mPatientMonitoring = "side_menu_on";
	dotPatientMonitoring = "●";
}
else if("monitor1".equals(mainMenu)) {
	mMonitoring1 = "side_menu_on";
	dotMonitoring1 = "●";
}
else if("monitor2".equals(mainMenu)) {
	mMonitoring2 = "side_menu_on";
	dotMonitoring2 = "●";
}
else if("monitor3".equals(mainMenu)) {
	mMonitoring3 = "side_menu_on";
	dotMonitoring3 = "●";
}
else if("monitor4".equals(mainMenu)) {
	mMonitoring4 = "side_menu_on";
	dotMonitoring4 = "●";
}
else if("monitor5".equals(mainMenu)) {
	mMonitoring5 = "side_menu_on";
	dotMonitoring5 = "●";
}
else if("chart".equals(mainMenu)) {
	mChart = "side_menu_on";
	dotChart = "●";
}
else if("in_out".equals(mainMenu)) {
	mInOut = "side_menu_on";
	dotInOut = "●";
}
else if("manage".equals(mainMenu)) {
	mManage = "side_menu_on";
	dotManage = "●";
}
System.out.println("mainMenu : "+mainMenu);
String role = (String) session.getAttribute("department");

%>
<link rel="stylesheet" type="text/css" href="css/menu.css">
<div style="width: 300px; height: 100%; background-color: #fff;">
	<!-- 프로필 이미지, 등급 -->
	<div class="wrapper_side_profile">
		<div class="wrapper_img_side_profile">
			<img class="img_side_profile" alt="" src="img/img_profile.png">
		</div>
		<div class="wrapper_span_side">
			<span class="span_side_grade"><%=role %></span>
		</div>
	</div>
	
	<div style="width: 100%;">
	<%if("관리자".equals(role)) {%>
		<div class="wrapper_side_menu <%=mDb %>" onclick="location.href='db.windy?menu=list'">
			<span class="span_side_menu"><%=dotDb %> 야전 병원 운용</span>
		</div>
		<div class="wrapper_side_menu <%=mMonitoring1 %>" onclick="location.href='monitoring.windy?menu=monitor1'">
			<span class="span_side_menu"><%=dotMonitoring1 %>통합 정보</span>
		</div>
		<div class="wrapper_side_menu <%=mMonitoring2 %>" onclick="location.href='monitoring.windy?menu=monitor2'">
			<span class="span_side_menu"><%=dotMonitoring2 %>환자 정보</span>
		</div>
		<div class="wrapper_side_menu <%=mMonitoring3 %>" onclick="location.href='monitoring.windy?menu=monitor3'">
			<span class="span_side_menu"><%=dotMonitoring3 %>병실 정보</span>
		</div>
		<div class="wrapper_side_menu <%=mMonitoring4 %>" onclick="location.href='monitoring.windy?menu=monitor4'">
			<span class="span_side_menu"><%=dotMonitoring4 %>차량 정보</span>
		</div>
		<div class="wrapper_side_menu <%=mMonitoring5 %>" onclick="location.href='monitoring.windy?menu=monitor5'">
			<span class="span_side_menu"><%=dotMonitoring5 %>의료 정보</span>
		</div>
		<div class="wrapper_side_menu <%=mManager %>" onclick="location.href='manager.windy?menu=list'">
			<span class="span_side_menu"><%=dotManager %> 부서장 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mMonitor %>" onclick="location.href='admin.windy?menu=monitor'">
			<span class="span_side_menu"><%=dotMonitor %> 비밀번호 변경</span>
		</div>
	<%} 
	else if("행정처".equals(role)) {%>
		<div class="wrapper_side_menu <%=mEmployee %>" onclick="location.href='employee.windy?menu=list'">
			<span class="span_side_menu"><%=dotEmployee %> 파견 인원 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mPatient %>" onclick="location.href='patient.windy?menu=list'">
			<span class="span_side_menu"><%=dotPatient %> 환자관리</span>
		</div>
		<div class="wrapper_side_menu <%=mRegion %>" onclick="location.href='region.windy?menu=list'">
			<span class="span_side_menu"><%=dotRegion %> 중앙통제실/지역 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mSite %>" onclick="location.href='site.windy?menu=list'">
			<span class="span_side_menu"><%=dotSite %> 현장 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mManage %>" onclick="location.href='employee.windy?menu=manage'">
			<span class="span_side_menu"><%=dotManage %> 의료진 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mEquipment %>" onclick="location.href='equipment.windy?menu=list'">
			<span class="span_side_menu"><%=dotEquipment %> 장비 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mOrder %>" onclick="location.href='order.windy?menu=list'">
			<span class="span_side_menu"><%=dotOrder %> 발주 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mSupplies %>" onclick="location.href='supplies.windy?menu=list'">
			<span class="span_side_menu"><%=dotSupplies %> 용품 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mMedicine %>" onclick="location.href='medicine.windy?menu=list'">
			<span class="span_side_menu"><%=dotMedicine %> 약품 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mInOut %>" onclick="location.href='in_out.windy?menu=list'">
			<span class="span_side_menu"><%=dotInOut %> 약품/비품 입출고 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mRoom %>" onclick="location.href='room.windy?menu=list'">
			<span class="span_side_menu"><%=dotRoom %> 병동 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mHospital %>" onclick="location.href='hospital.windy?menu=list'">
			<span class="span_side_menu"><%=dotHospital %> 거점 관리</span>
		</div>
		<%-- <div class="wrapper_side_menu <%=mPw %>" onclick="location.href='admin.windy?menu=patient'">
			<span class="span_side_menu"><%=dotPw %> 비밀번호 변경</span>
		</div> --%>
	<%}
	else if("의사".equals(role) || "간호사".equals(role) || "임상 병리".equals(role)) {%>
		<div class="wrapper_side_menu <%=mPatientMonitoring %>" onclick="location.href='patient.windy?menu=monitoring'">
			<span class="span_side_menu"><%=dotPatientMonitoring %> 환자 모니터링</span>
		</div>
		<div class="wrapper_side_menu <%=mPatient %>" onclick="location.href='patient.windy?menu=list'">
			<span class="span_side_menu"><%=dotPatient %> 환자 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mManage %>" onclick="location.href='employee.windy?menu=manage'">
			<span class="span_side_menu"><%=dotManage %> 의료진 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mSupplies %>" onclick="location.href='supplies.windy?menu=list'">
			<span class="span_side_menu"><%=dotSupplies %> 용품 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mMedicine %>" onclick="location.href='medicine.windy?menu=list'">
			<span class="span_side_menu"><%=dotMedicine %> 약품 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mSupplies %>" onclick="location.href='in_out.windy?menu=list'">
			<span class="span_side_menu"><%=dotSupplies %> 약품/비품 입출고 관리</span>
		</div>
		<%-- <div class="wrapper_side_menu <%=mRoom %>" onclick="location.href='admin.windy?menu=room'">
			<span class="span_side_menu"><%=dotRoom %> 비밀번호 변경</span>
		</div> --%>
		
	<%} %>
	
	</div>
</div>