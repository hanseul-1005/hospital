<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String mainMenu = (String) request.getAttribute("main_menu");
String menu = (String) request.getAttribute("menu");

String mMonitor = "", mAdmin="", mPassword="", mCar="", mPatientManage="", mSiteAdd="";
String dotMonitor = "○", dotAdmin="○", dotPassword="○", dotCar="○", dotPatientManage="○", dotSiteAdd="○"; 

// 관리자
String mDb = "";

String dotDb = "○";
// 행정처
String mEmployee = "", mPatient = "", mRegion = "", mSite = "", mMedical = "", mEquipment = "", mOrder = "",
	mSupplies = "", mMedicine = "", mRoom="", mHospital=""; 
String dotEmployee = "○", dotPatient = "○", dotRegion = "○", dotSite = "○", dotMedical = "○", dotEquipment = "○", dotOrder = "○", 
	dotSupplies = "○", dotMedicine = "○", dotRoom="○", dotHospital="○";

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



String role = (String) session.getAttribute("role");
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
		<div class="wrapper_side_menu <%=mMonitor %>" onclick="location.href='admin.windy?menu=monitor'">
			<span class="span_side_menu"><%=dotMonitor %> 모니터링</span>
		</div>
		<div class="wrapper_side_menu <%=mMonitor %>" onclick="location.href='admin.windy?menu=monitor'">
			<span class="span_side_menu"><%=dotMonitor %> 부서장 관리</span>
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
		<div class="wrapper_side_menu <%=mMedical %>" onclick="location.href='admin.windy?menu=patient'">
			<span class="span_side_menu"><%=dotMedical %> 의료진 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mEquipment %>" onclick="location.href='equipment.windy?menu=list'">
			<span class="span_side_menu"><%=dotEquipment %> 장비 관리</span>
		</div>
		<%-- <div class="wrapper_side_menu <%=mOrder %>" onclick="location.href='admin.windy?menu=patient'">
			<span class="span_side_menu"><%=dotOrder %> 발주 관리</span>
		</div> --%>
		<div class="wrapper_side_menu <%=mSupplies %>" onclick="location.href='supplies.windy?menu=list'">
			<span class="span_side_menu"><%=dotSupplies %> 용품 관리</span>
		</div>
		<%-- <div class="wrapper_side_menu <%=mSupplies %>" onclick="location.href='supplies.windy?menu=list'">
			<span class="span_side_menu"><%=dotSupplies %> 용품 입출고 관리</span>
		</div> --%>
		<div class="wrapper_side_menu <%=mMedicine %>" onclick="location.href='medicine.windy?menu=list'">
			<span class="span_side_menu"><%=dotMedicine %> 약품 관리</span>
		</div>
		<%-- <div class="wrapper_side_menu <%=mMedicine %>" onclick="location.href='medicine.windy?menu=list'">
			<span class="span_side_menu"><%=dotMedicine %> 약품 입출고 관리</span>
		</div> --%>
		<div class="wrapper_side_menu <%=mRoom %>" onclick="location.href='room.windy?menu=list'">
			<span class="span_side_menu"><%=dotRoom %> 병동 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mHospital %>" onclick="location.href='hospital.windy?menu=list'">
			<span class="span_side_menu"><%=dotHospital %> 거점 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mPw %>" onclick="location.href='admin.windy?menu=patient'">
			<span class="span_side_menu"><%=dotPw %> 비밀번호 변경</span>
		</div>
	<%}
	else if("의사".equals(role) || "간호사".equals(role) || "임상병리사".equals(role)) {%>
		<div class="wrapper_side_menu <%=mRoom %>" onclick="location.href='admin.windy?menu=room'">
			<span class="span_side_menu"><%=dotRoom %> 환자 모니터링</span>
		</div>
		<div class="wrapper_side_menu <%=mPatient %>" onclick="location.href='patient.windy?menu=list'">
			<span class="span_side_menu"><%=dotPatient %> 환자 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mRoom %>" onclick="location.href='admin.windy?menu=room'">
			<span class="span_side_menu"><%=dotRoom %> 의료진 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mSupplies %>" onclick="location.href='supplies.windy?menu=list'">
			<span class="span_side_menu"><%=dotSupplies %> 용품 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mSupplies %>" onclick="location.href='supplies.windy?menu=list'">
			<span class="span_side_menu"><%=dotSupplies %> 용품 입출고 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mMedicine %>" onclick="location.href='medicine.windy?menu=list'">
			<span class="span_side_menu"><%=dotMedicine %> 약품 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mMedicine %>" onclick="location.href='medicine.windy?menu=list'">
			<span class="span_side_menu"><%=dotMedicine %> 약품 입출고 관리</span>
		</div>
		<div class="wrapper_side_menu <%=mRoom %>" onclick="location.href='admin.windy?menu=room'">
			<span class="span_side_menu"><%=dotRoom %> 비밀번호 변경</span>
		</div>
		
	<%} %>
	
	</div>
</div>