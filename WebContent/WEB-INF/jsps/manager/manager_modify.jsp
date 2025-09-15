<%@page import="windy.hospital.model.EmployeeModel"%>
<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");
EmployeeModel employee = (EmployeeModel) request.getAttribute("employee");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function addRow() {
	
	var num = document.getElementById('num').value;
	
	var html = "";
	html += "<tr id='tr_"+num+"'>";
	html += "<td>"+(parseInt(num)+1)+"</td>"; 
	html += "<td><input type='text' class='td_add' id='name_"+num+"'></td>";
	html += "<td><input type='text' class='td_add' id='tel_"+num+"'></td>";
	html += "<td><input type='text' class='td_add' id='belong_"+num+"'></td>";
	html += "<td><input type='text' class='td_add' id='major_"+num+"'></td>";
	html += "<td>";
	html += "<select class='td_select' id='room_"+num+"'>";
	<%for(int i=0; i<listRoom.size(); i++) {
	RoomModel room = listRoom.get(i);
	%>
  	html += "<option value='<%=room.getNo() %>'><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>";
  	<%} %>
	html += "</select>";
	html += "</td>";
	html += "<td>";
	html += "<input type='radio' name='on_off_"+num+"' value='근무'>";
	html += "<label for='on'>근무</label>";
	html += "<input type='radio' name='on_off_"+num+"' value='휴무'>";
	html += "<label for='on'>휴무</label>";
	html += "<td>";
	html += "<button type='button' class='btn_red_100' onclick='javascript: deleteRow("+num+")'>행 삭제</button>";
	html += "</td>";
	html += "</tr>";
	
	$("#tb_add").append(html);
	
	document.getElementById('num').value = parseInt(num)+1;
}

function deleteRow(num) {
	$("#tr_"+num).remove();
}

function goModify() {

	var no = <%=employee.getNo() %>;
	var belong = document.getElementById('belong').value;
	var tel = document.getElementById('tel').value;
	var department = document.getElementById('department').value;
	var major = document.getElementById('major').value;
	var room = document.getElementById('room').value;

	var roomNo = room.split('_')[0];
	var roomName = room.split('_')[1];
	
	
	param = "&no="+no+"&belong="+belong+"&tel="+tel+"&department="+department+"&major="+major+"&room_no="+roomNo+"&room_name="+roomName;
	
	$.ajax({
		type: "post", 
		url: "employee.windy?mode=update", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("등록되었습니다.");
			location.href = "employee.windy?menu=list"; 
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
        <jsp:include page="../top_menu.jsp"></jsp:include>
        <div class="wrapper_main_contents" >
			<div style="height: 100%;">
				<jsp:include page="../side_menu.jsp"/>
			</div>
			<div class="wrapper_board_contents">
				<div class="wrapper_board">
					<span class="span_board_title">파견직원 수정</span>
					
					<div style="margin-top: 50px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="200px">
								<col width="200px">
								<col width="200px">
								<col width="200px">
							</colgroup>
							<thead>
								<tr>
									<th>이름</th>
									<td><%=employee.getName() %></td>
									<th>소속</th>
									<td><input type="text" class="td_add" id="belong" value="<%=employee.getBelong() %>"></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>연락처</th>
									<td><input type="text" class="td_add" id="tel" value="<%=employee.getTel() %>"></td>
									<th>부서</th>
									<td>
                                        <select class="select_bx" id="department">
                                            <option value="행정처" <%if("행정처".equals(employee.getDepartment())) { %> selected="selected" <%} %>>행정처</option>
											<option value="의사" <%if("의사".equals(employee.getDepartment())) { %> selected="selected" <%} %>>의사</option>
			                                <option value="간호" <%if("간호".equals(employee.getDepartment())) { %> selected="selected" <%} %>>간호</option>
			                                <option value="임상 병리" <%if("임상 병리".equals(employee.getDepartment())) { %> selected="selected" <%} %>>임상 병리</option>
                                        </select>
                                    </td>
								</tr>
                                <tr>
									<th>전공</th>
									<td><input type="text" class="td_add" id="major" value="<%=employee.getMajor() %>"></td>
									<th>지정실</th>
									<td>
                                        <select class="select_bx" id="room">
                                        	<%for(int i=0; i<listRoom.size(); i++) {
                                        		RoomModel room = listRoom.get(i);
                                        	%>
                                            <option value="<%=room.getNo() %>_<%=room.getName() %>" <%if(room.getNo() == employee.getRoomNo()) { %> selected="selected" <%} %>><%=room.getName() %></option>
                                            <%} %>
                                        </select>
                                    </td>
								</tr>
							</tbody>
						</table>
					</div>
                    <div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left;">
							
						</div>
						<div style="width: 50%; text-align: right; display: flxe;">
							<button class="btn_basic_150" onclick="javascript: goModify()">수정</button>
							<button class="btn_cancel_150" onclick="history.back()">취소</button>
						</div>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>