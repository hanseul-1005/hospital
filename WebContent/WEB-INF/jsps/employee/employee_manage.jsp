<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.EmployeeModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

String onDepartment = (String) request.getAttribute("onDepartment");
String onName = (String) request.getAttribute("onName");
String offDepartment = (String) request.getAttribute("offDepartment");
String offName = (String) request.getAttribute("offName");

ArrayList<EmployeeModel> listEmpOn = (ArrayList<EmployeeModel>) request.getAttribute("listEmpOn");
ArrayList<EmployeeModel> listEmpOff = (ArrayList<EmployeeModel>) request.getAttribute("listEmpOff");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function goSearch() {
	var onDepartment = document.getElementById('on_department').value;
	var onName = document.getElementById('on_name').value;
	var offDepartment = document.getElementById('off_department').value;
	var offName = document.getElementById('off_name').value;
	
	location.href = 'employee.windy?menu=manage&on_department='+onDepartment+'&on_name='+onName+'&off_department='+offDepartment+'&off_name='+offName;
}

function setOff() {

	var num = <%=listEmpOn.size() %>;
	var size = 0;
	
	var param = "";
	
	for(var i=0; i<num; i++) {

		if($('#on_chk_'+i).is(':checked')) {

			var no = document.getElementById('on_no_'+i).value;
			
			param += "&no_"+size+"="+no;
			
			size = size+1;
			
		}
	}

	param += "&size="+size;
	console.log('param : '+param);
	
	$.ajax({
		type: "post", 
		url: "employee.windy?mode=manage_off", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("수정되었습니다.");
			location.reload(); 
		}
	});  
	
}

function setOn() {

	var num = <%=listEmpOff.size() %>;
	var size = 0;
	
	var param = "";
	
	for(var i=0; i<num; i++) {

		if($('#off_chk_'+i).is(':checked')) {

			var no = document.getElementById('off_no_'+i).value;
			
			param += "&no_"+size+"="+no;
			
			size = size+1;
			
		}
	}

	param += "&size="+size;
	console.log('param : '+param);
	
	$.ajax({
		type: "post", 
		url: "employee.windy?mode=manage_on", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("수정되었습니다.");
			location.reload(); 
		}
	});  
	
}

function ajaxFailed(xmlRequest)	{
	alert(xmlRequest.status+"\n\r"+xmlRequest.statusText+"\n\r"+xmlRequest.responseText);
}
</script>
<title>Insert title here</title>
</head>
<body>
    <div>
        <div class="wrapper_main_contents" >
			<div style="height: 100%;">
				<jsp:include page="../side_menu.jsp"/>
			</div>
			<div class="wrapper_board_contents">
				<div class="wrapper_board">
					<span class="span_board_title">의료진 관리</span>
					<div style="width: 100%; display: flex; margin-top: 50px; height: 90%;">
						<div style="width: 40%;">
							<span class="span_board_title">근무 목록</span>
							<div class="wrapper_board_btn">
								<div style="width: 100%; text-align: left; display: flex;">
									<select style="width: 150px; height: 40px; border-color: #74ADBD; color: #496E73; border-radius: 3px" id="on_department">
										<option value="" <%if("".equals(onDepartment)) {%> selected="selected" <%} %>>전체부서</option>
										<option value="의사" <%if("의사".equals(onDepartment)) {%> selected="selected" <%} %>>의사</option>
										<option value="간호사" <%if("간호사".equals(onDepartment)) {%> selected="selected" <%} %>>간호사</option>
									</select>
									<input type="text" style="width: 150px; height: 36px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; " id="on_name" value="<%=onName %>"> 
									<div style="width: 40px; height: 40px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;" onclick="javascript: goSearch()">
										<img alt="" src="images/img_search.png" width="40px" height="40px">							
									</div>
								</div>
							</div>
							<div style="margin-top: 10px;">
								<table class="tb_basic tb_management">
									<colgroup>
										<col width="100px">
										<col width="200px">
										<col width="200px">
									</colgroup>
									<thead>
										<tr>
											<th></th>
											<th>이름</th>
											<th>부서</th>
										</tr>
									</thead>
									<tbody>
										<%for(int i=0; i<listEmpOn.size(); i++) {
											EmployeeModel emp = listEmpOn.get(i);
										%>
										<tr>
											<td>
												<input type="checkbox" style="transform: scale(1.5); width: 15px; height: 15px;" id="on_chk_<%=i %>">
												<input type="hidden" id="on_no_<%=i %>" value="<%=emp.getNo() %>"/>
											</td>
											<td><%=emp.getName() %></td>
											<td><%=emp.getDepartment() %></td>
										</tr>
										<%} %>
									</tbody>
								</table>
							</div>
						</div>
						<div style="width: 20%; display: flex;">
							<div style="display: flex; width: 100%; flex-direction: column; justify-content: center; align-items: center;">
								<button class="btn_basic_100" style="margin-bottom: 20%; width: 200px; height: 50px;" onclick="javascript: setOn()">◀ 근무</button>
								<button class="btn_cancel_100" style="width: 200px; height: 50px;" onclick="javascript: setOff()">휴무 ▶</button>
							</div>
						</div>
						<div style="width: 40%;">
							<span class="span_board_title">휴무 목록</span>
							<div class="wrapper_board_btn">
								<div style="width: 100%; text-align: left; display: flex;">
									<select style="width: 150px; height: 40px; border-color: #74ADBD; color: #496E73; border-radius: 3px" id="off_department">
										<option value="" <%if("".equals(offDepartment)) {%> selected="selected" <%} %>>전체부서</option>
										<option value="의사" <%if("의사".equals(offDepartment)) {%> selected="selected" <%} %>>의사</option>
										<option value="간호사" <%if("간호사".equals(offDepartment)) {%> selected="selected" <%} %>>간호사</option>
									</select>
									<input type="text" style="width: 150px; height: 36px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; " id="off_name" value="<%=offName %>">
									<div style="width: 40px; height: 40px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;" onclick="javascript: goSearch()">
										<img alt="" src="images/img_search.png" width="40px" height="40px">							
									</div>
								</div>
							</div>
							<div style="margin-top: 10px;">
								<table class="tb_basic tb_management">
									<colgroup>
										<col width="100px">
										<col width="200px">
										<col width="200px">
									</colgroup>
									<thead>
										<tr>
											<th></th>
											<th>이름</th>
											<th>부서</th>
										</tr>
									</thead>
									<tbody>
										<%for(int i=0; i<listEmpOff.size(); i++) {
											EmployeeModel emp = listEmpOff.get(i);
										%>
										<tr>
											<td>
												<input type="checkbox" style="transform: scale(1.5); width: 15px; height: 15px;" id="off_chk_<%=i %>">
												<input type="hidden" id="off_no_<%=i %>" value="<%=emp.getNo() %>"/>
											</td>
											<td><%=emp.getName() %></td>
											<td><%=emp.getDepartment() %></td>
										</tr>
										<%} %>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>