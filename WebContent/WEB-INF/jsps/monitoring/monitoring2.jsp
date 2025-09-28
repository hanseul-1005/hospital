<%@page import="windy.hospital.model.HospitalModel"%>
<%@page import="windy.hospital.model.PatientModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

// 누적 진료
int totalDiagnosis = (int) request.getAttribute("totalDiagnosis");
// 누적 입원
int totalAdmission = (int) request.getAttribute("totalAdmission");
// 오늘 입원
int todayAdmission = (int) request.getAttribute("todayAdmission");
// 오늘 진료
int todayDiagnosis = (int) request.getAttribute("todayDiagnosis");
// 오늘 후송 
int todayEvacuation = (int) request.getAttribute("todayEvacuation");

ArrayList<PatientModel> listPatientS = (ArrayList<PatientModel>) request.getAttribute("listPatientS");
ArrayList<PatientModel> listPatientA = (ArrayList<PatientModel>) request.getAttribute("listPatientA");
ArrayList<PatientModel> listPatientB = (ArrayList<PatientModel>) request.getAttribute("listPatientB");

ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) request.getAttribute("listHospital");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/jquery-3.7.1.min.js"></script>
<script>

function side() {
	var sideType = document.getElementById('side_type').value;
	
	if(sideType=='on') {
		document.getElementById('side_type').value = 'off';
		document.getElementById('side_menu').style.display = 'none';
	} else {
		document.getElementById('side_type').value = 'on';
		document.getElementById('side_menu').style.display = '';
	}
}

</script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="../top_menu.jsp"></jsp:include>
		</div>
		<div style="display: flex;">
			<div style="width: 15%; height: 94vh; z-index: 1000;" id="side_menu">
				<jsp:include page="../side_menu.jsp"></jsp:include>
			</div>
			<div class="wrapper_contents" style="width: 94%; height: 93%; padding-left: 3%; position: absolute;">
				<div class="wrapper_left_contents_6">
					<div class="wrapper_top_tb">
						<table class="tb_patient1">
							<colgroup>
								<col width="33%">
								<col width="34%">
								<col width="33%">
							</colgroup>
							<thead>
								<tr>
									<th>오늘입원</th>
									<th>오늘진료</th>
									<th>오늘후송</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%=todayAdmission %></td>
									<td><%=todayDiagnosis %></td>
									<td><%=todayEvacuation %></td>
								</tr>
							</tbody>
						</table>
						<table class="tb_patient2">
							<colgroup>
								<col width="50%">
								<col width="50%">
							</colgroup>
							<thead>
								<tr>
									<th>누적 입원</th>
									<th>누적 진료인원</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%=totalAdmission %></td>
									<td><%=totalDiagnosis %></td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div class="wrapper_top_tb">
						<div style="width: 200px;">
							<table class="tb_patient5_title">
								<colgroup>
									<col width="199">
								</colgroup>
								<thead>
									<tr>
										<th>음압(S)</th>
									</tr>
								</thead>
							</table>
						
							<table class="tb_patient5_contents">
								<colgroup>
									<col width="199">
								</colgroup>
								<tbody>
									<%
									for(int i=0; i<listPatientS.size(); i++) {
										PatientModel patient = listPatientS.get(i);
									%>
									<tr>
										<td><%=patient.getName() %>(<%=patient.getGender() %>, <%=patient.getAge() %>세)</td>
									</tr>
									<%} %>
									<%
										for(int i=0; i<14-listPatientS.size(); i++) {
									%>
									<tr>
										<td></td>
									</tr>
									<%} %>
									<tr>
										<td>5</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="width: 400px;">
							<table class="tb_patient5_title">
								<colgroup>
									<col width="199">
									<col width="199">
								</colgroup>
								<thead>
									<tr>
										<th colspan="2">관찰(A)</th>
									</tr>
								</thead>
							</table>
						
							<table class="tb_patient5_contents">
								<colgroup>
									<col width="199">
									<col width="199">
								</colgroup>
								<tbody>
									<%
									for(int i=0; i<listPatientA.size(); i++) {
										PatientModel patient = listPatientA.get(i);
									%>
									<tr>
										<td><%=patient.getName() %></td>
										<td><%=patient.getName2() %></td>
									</tr>
									<%} %>
									<%
										for(int i=0; i<14-listPatientA.size(); i++) {
									%>
									<tr>
										<td></td>
										<td></td>
									</tr>
									<%} %>
									<tr>
										<td>5</td>
										<td>5</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="width: 400px;">
							<table class="tb_patient5_title">
								<colgroup>
									<col width="199">
									<col width="199">
								</colgroup>
								<thead>
									<tr>
										<th colspan="2">일반(B)</th>
									</tr>
								</thead>
							</table>
						
							<table class="tb_patient5_contents">
								<colgroup>
									<col width="199">
									<col width="199">
								</colgroup>
								<tbody>
									<%
									for(int i=0; i<listPatientB.size(); i++) {
										PatientModel patient = listPatientB.get(i);
									%>
									<tr>
										<td><%=patient.getName() %></td>
										<td><%=patient.getName2() %></td>
									</tr>
									<%} %>
									<%
										for(int i=0; i<14-listPatientB.size(); i++) {
									%>
									<tr>
										<td></td>
										<td></td>
									</tr>
									<%} %>
									<tr>
										<td>5</td>
										<td colspan="2">10</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="wrapper_right_contents_4" style="">
					<table class="tb_patient4">
						<colgroup>
							<col width="10%">
							<col width="30%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="15%">
							<col width="10%">
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>병원명</th>
								<th>합계</th>
								<th>긴급</th>
								<th>응급</th>
								<th>비응급</th>
								<th>사망</th>
							</tr>
						</thead>
						<tbody>
							<%
								for(int i=0; i<listHospital.size(); i++) {
									HospitalModel hospital = listHospital.get(i);
							%>
							<tr>
								<td><%=i+1 %></td>
								<td><%=hospital.getName() %></td>
								<td><%=hospital.getPatientCnt1()+hospital.getPatientCnt2()+hospital.getPatientCnt3() %></td>
								<td><%=hospital.getPatientCnt1() %></td>
								<td><%=hospital.getPatientCnt2() %></td>
								<td><%=hospital.getPatientCnt3() %></td>
								<td><%=hospital.getPatientCnt4() %></td>
							</tr>
							<%} %>
							<%
								for(int i=0; i<17-listHospital.size(); i++) {
							%>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<%} %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>