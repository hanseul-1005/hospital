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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">
function popOpen(num, classPrefix, idx) {

	var modalBg = $(num);
	var modalPop = $(classPrefix);

	modalBg.show();
	modalPop.show();
}

function popClose(num, classPrefix) {
	var modalBg = $(num);
	var modalPop = $(classPrefix);

	modalBg.hide();
	modalPop.hide();
}

function goAdd() {
	location.href="patient.windy?menu=add";
}

function goAddAll() {
	location.href="patient.windy?menu=multi_add";
}

function goSearch() {
	var name = document.getElementById('search_name').value;
	
	location.href="region.windy?menu=list&name="+name;
}

function goDelete(no) {
		
	var param = "&no="+no;
	
	$.ajax({
		type: "POST",
		url: "region.windy?mode=delete", 
		data: param,
		error: ajaxFailed,
		success: function(ret) {

			alert("비활성화되었습니다.");
			location.reload(); 
		}
	});
	
}

function goModify(no) {

	location.href = "patient.windy?menu=modify&no="+no;
	
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
								<tr>
									<td><%=patient.getName1() %></td>
									<td><%=patient.getName2() %></td>
									<td><%=patient.getName3() %></td>
									<td><%=patient.getName4() %></td>
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