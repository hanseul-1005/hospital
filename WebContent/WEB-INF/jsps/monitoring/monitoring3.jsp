<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.PatientModel"%>
<%@page import="windy.hospital.model.HospitalModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<div class="wrapper_contents" style="display: block; width: 94%; height: 93%; padding-left: 3%; position: absolute;">
				<div style="display: flex; width: 100%; height: 50%;">
					<div class="wrapper_left_contents_5" style="border: 1px solid #74ADBD;">
					
					</div>
					
					<div class="wrapper_right_contents_5">
						<div style="width: 100%; height: 40%;">
							<span class="span_medical_title">병실</span>
							<table class="tb_room">
								<colgroup>
									<col width="25%">
									<col width="25%">
									<col width="25%">
									<col width="25%">
								</colgroup>
								<thead>
									<tr>
										<%for(int i=0; i<listRoom.size(); i++) {
											RoomModel room = listRoom.get(i);
										%>
										<th><%=room.getName() %></th>
										<%} %>
										<th>합계</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<%
										int totalCnt = 0, totalPatientCnt = 0;
										for(int i=0; i<listRoom.size(); i++) {
											RoomModel room = listRoom.get(i);
											
											totalCnt = totalCnt+room.getCnt();
											totalPatientCnt = totalPatientCnt+room.getPatientCnt();
											
										%>
										<td><%=room.getPatientCnt() %>/<%=room.getCnt() %></td>
										<%} %>
										<td><%=totalPatientCnt %>/<%=totalCnt %></td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div style="width: 100%; height: 60%;">
							<span class="span_medical_title">청·폐수, 전력</span>
							<table class="tb_room">
								<colgroup>
									<col width="25%">
									<col width="25%">
									<col width="25%">
									<col width="25%">
								</colgroup>
								<thead>
									<tr>
										<th>분류</th>
										<th>전체</th>
										<th>사용량</th>
										<th>잔여량</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th>청수</th>
										<td>1,080 L</td>
										<td>1,080 L</td>
										<td>1,080 L</td>
									</tr>
									<tr>
										<th>폐수</th>
										<td>1,080 L</td>
										<td>1,080 L</td>
										<td>1,080 L</td>
									</tr>
									<tr>
										<th>전력</th>
										<td>6,00 kw</td>
										<td>6,00 kw</td>
										<td>6,00 kw</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
				<div class="wrapper_room_table">
					<table class="tb_basic" style="width: 98.5%; height: 100%;">
						<colgroup>
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
							<col width="">
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>S-1</th>
								<th>A-1</th>
								<th>A-2</th>
								<th>A-3</th>
								<th>A-4</th>
								<th>A-5</th>
								<th>A-6</th>
								<th>소계</th>
								<th>B-1</th>
								<th>B-2</th>
								<th>B-3</th>
								<th>B-4</th>
								<th>B-5</th>
								<th>B-6</th>
								<th>B-7</th>
								<th>B-8</th>
								<th>소계</th>
								<th>화장실</th>
								<th>발전기</th>
								<th>물탱크</th>
								<th>간호사실</th>
								<th>소계</th>
								<th>합계</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>청수(L)</th>
								<%
								String valColor = "";
								for(int i=0; i<23; i++) {
									double randomValue = Math.random();
									
									int val = (int)(randomValue*100)+1;
									
									valColor = "div_value_green";
									if(val < 80) {
										valColor = "div_value_red";
									}
								%>
								<td>
									<div class="wrapper_room_td">
										<div class="wrapper_room_td_value">
											<span><%=val %></span>
										</div>
										<div class="<%=valColor %>"></div>
									</div>
								</td>
								<%} %>
							</tr>
							<tr>
								<th>폐수(L)</th>
								<%
								for(int i=0; i<23; i++) {
									double randomValue = Math.random();
									
									int val = (int)(randomValue*100)+1;
									
									valColor = "div_value_green";
									if(val < 80) {
										valColor = "div_value_red";
									}
								%>
								<td>
									<div class="wrapper_room_td">
										<div class="wrapper_room_td_value">
											<span><%=val %></span>
										</div>
										<div class="<%=valColor %>"></div>
									</div>
								</td>
								<%} %>
							</tr>
							<tr>
								<th>전력(kw)</th>
								<%
								for(int i=0; i<23; i++) {
									double randomValue = Math.random();
									
									int val = (int)(randomValue*100)+1;
									
									valColor = "div_value_green";
									if(val < 40) {
										valColor = "div_value_red";
									}
								%>
								<td>
									<div class="wrapper_room_td">
										<div class="wrapper_room_td_value">
											<span><%=val %></span>
										</div>
										<div class="<%=valColor %>"></div>
									</div>
								</td>
								<%} %>
							</tr>
							<tr>
								<th>온도(℃)</th>
								<%
								for(int i=0; i<23; i++) {
									double randomValue = Math.random();
									
									int val = (int)(randomValue*100)+1;
									
									valColor = "div_value_green";
									if(val < 20) {
										valColor = "div_value_red";
									}
								%>
								<td>
									<div class="wrapper_room_td">
										<div class="wrapper_room_td_value" style="height: 100%">
											<span><%=val %></span>
										</div>
									</div>
								</td>
								<%} %>
							</tr>
							<tr>
								<th>습도(%)</th>
								<%
								for(int i=0; i<23; i++) {
									double randomValue = Math.random();
									
									int val = (int)(randomValue*100)+1;
								%>
								<td>
									<div class="wrapper_room_td">
										<div class="wrapper_room_td_value" style="height: 100%">
											<span><%=val %></span>
										</div>
									</div>
								</td>
								<%} %>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		
		</div>
		
	</div>
</body>
</html>