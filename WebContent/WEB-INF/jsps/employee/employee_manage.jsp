<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.EmployeeModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

String name = (String) request.getAttribute("name");
String del = (String) request.getAttribute("del");
String department = (String) request.getAttribute("department");

ArrayList<EmployeeModel> listEmployee = (ArrayList<EmployeeModel>) request.getAttribute("listEmployee");
ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function popOpen(num, classPrefix, no, name, tel) {
	var modalBg = $(num);
	var modalPop = $(classPrefix);

	document.getElementById('no').value = no;
	document.getElementById('name').value = name;
	document.getElementById('tel').value = tel;
	
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
	location.href="employee.windy?menu=add";
}

function goMultiAdd() {
	location.href="employee.windy?menu=multi_add";
}

function goSearch() {
	var del = document.getElementById('del').value;
	var department = document.getElementById('department').value;
	var name = document.getElementById('search_name').value;
	
	location.href="employee.windy?menu=list&name="+name+"&del="+del+"&department="+department;
}

function updateRoom(idx, no) {
		
	var room = document.getElementById('room_'+idx).value;

	var roomNo = room.split('_')[0];
	var roomName = room.split('_')[1];
	
	var param = "&no="+no+"&room_no="+roomNo+"&room_name="+roomName;
	
	$.ajax({
		type: "POST",
		url: "employee.windy?mode=update_room", 
		data: param,
		error: ajaxFailed,
		success: function(ret) {

			alert("비활성화되었습니다.");
			location.reload(); 
		}
	});
	
}
function goDelete(no) {
		
	var param = "&no="+no;
	
	$.ajax({
		type: "POST",
		url: "employee.windy?mode=delete", 
		data: param,
		error: ajaxFailed,
		success: function(ret) {

			alert("비활성화되었습니다.");
			location.reload(); 
		}
	});
	
}

function goModify(no) {

	location.href = "employee.windy?menu=modify&no="+no;
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
					<span class="span_board_title">파견인원 관리</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							<select class="select_bx" id="del">
								<option value="" <%if("".equals(del)) { %> selected="selected" <%} %>>전체</option>
								<option value="N" <%if("N".equals(del)) { %> selected="selected" <%} %>>활성화</option>
								<option value="Y" <%if("Y".equals(del)) { %> selected="selected" <%} %>>비활성화</option>
							</select>
                            <select class="select_bx" id="department">
								<option value="" <%if("".equals(department)) { %> selected="selected" <%} %>>전체 부서</option>
								<option value="행정처" <%if("행정처".equals(department)) { %> selected="selected" <%} %>>행정처</option>
								<option value="의사" <%if("의사".equals(department)) { %> selected="selected" <%} %>>의사</option>
                                <option value="간호" <%if("간호".equals(department)) { %> selected="selected" <%} %>>간호</option>
                                <option value="임상 병리" <%if("임상 병리".equals(department)) { %> selected="selected" <%} %>>임상 병리</option>
							</select>
							<input type="text" class="td_add" id="search_name">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;" onclick="javascript: goSearch()">
								<img alt="" src="images/img_search.png" width="30px" height="30px">							
							</div>
						</div>
						<div style="width: 50%">
                            <button class="btn_basic_150" onclick="javascript: goMultiAdd()">일괄 등록</button>
							<button class="btn_basic_150" onclick="javascript: goAdd()">단일 등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="100px">
								<col width="200px">
								<col width="200px">
								<col width="200px">
								<col width="200px">
								<col width="200px">
                                <col width="200px">
                                <col width="200px">
                                <col width="200px">
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>이름</th>
									<th>연락처</th>
									<th>소속</th>
									<th>전공</th>
									<th>부서</th>
                                    <th>지정실</th>
                                    <th>근무/휴무</th>
                                    <th>활성화 여부</th>
								</tr>
							</thead>
							<tbody>
								<%for(int i=0; i<listEmployee.size(); i++) {
									EmployeeModel employee = listEmployee.get(i);
								%>
								<tr>
									<td onclick="javascript: goModify(<%=employee.getNo() %>)"><%=i+1 %></td>
									<td onclick="javascript: goModify(<%=employee.getNo() %>)"><%=employee.getName() %></td>
									<td onclick="javascript: goModify(<%=employee.getNo() %>)"><%=employee.getTel() %></td>
									<td onclick="javascript: goModify(<%=employee.getNo() %>)"><%=employee.getBelong() %></td>
                                    <td onclick="javascript: goModify(<%=employee.getNo() %>)"><%=employee.getMajor() %></td>
                                    <td onclick="javascript: goModify(<%=employee.getNo() %>)"><%=employee.getDepartment() %></td>
									<td>
                                        <select class="select_bx" id="room_<%=i %>" onchange="javascript: updateRoom('<%=i %>', '<%=employee.getNo() %>')">
                                        	<%for(int j=0; j<listRoom.size(); j++) {
                                        		RoomModel room = listRoom.get(j);
                                        	%>
                                            <option value="<%=room.getNo() %>_<%=room.getName() %>" <%if(room.getNo() == employee.getRoomNo()) { %> selected="selected" <%} %>><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>
                                            <%} %>
                                        </select>
                                    </td>
                                    <td onclick="javascript: goModify(<%=employee.getNo() %>)"><%=employee.getOnOff() %></td>
									<td>
                                        <button class="btn_cancel_100">비활성화</button>
                                    </td>
								</tr>
								<%} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>