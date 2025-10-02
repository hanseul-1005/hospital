<%@page import="windy.hospital.model.HospitalModel"%>
<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.PatientModel"%>
<%@page import="windy.hospital.model.RegionModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

ArrayList<PatientModel> listPatient = (ArrayList<PatientModel>) request.getAttribute("listPatient");

String roomName = "";

if(listPatient.size()>0) {
	roomName = listPatient.get(0).getRoomName();
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function goChart(no) {
	location.href = "chart.windy?menu=chart&no="+no;
}

function ajaxFailed(xmlRequest)	{
	alert(xmlRequest.status+"\n\r"+xmlRequest.statusText+"\n\r"+xmlRequest.responseText);
}
</script>
<title>Insert title here</title>
</head>
<body>
    <div>
        <jsp:include page="../top_menu.jsp"></jsp:include>
        <div class="wrapper_main_contents" >
			<div style="height: 100%;">
				<jsp:include page="../side_menu.jsp"/>
			</div>
			<div class="wrapper_board_contents">
				<div class="wrapper_board">
					<span class="span_board_title">환자 모니터링<%=roomName %></span>
					
					<div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="200px">
								<col width="200px">
								<col width="200px">
								<col width="200px">
							</colgroup>
							<thead>
								<tr>
									<th colspan="4">입원 환자 (<%=listPatient.size() %>명)</th>
								</tr>
							</thead>
							<tbody>
								<%for(int i=0; i<listPatient.size(); i+=4) {
									PatientModel patient = listPatient.get(i);
								%>
								<tr >
									<td <%if(0<patient.getNo1()) { %> onclick="javascript: goChart(<%=patient.getNo1() %>)" <%} %>><%=patient.getName1() %></td>
									<td <%if(0<patient.getNo2()) { %> onclick="javascript: goChart(<%=patient.getNo2() %>)" <%} %>><%=patient.getName2() %></td>
									<td <%if(0<patient.getNo3()) { %> onclick="javascript: goChart(<%=patient.getNo3() %>)" <%} %>><%=patient.getName3() %></td>
									<td <%if(0<patient.getNo4()) { %> onclick="javascript: goChart(<%=patient.getNo4() %>)" <%} %>><%=patient.getName4() %></td>
								</tr>
								<%}  %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>