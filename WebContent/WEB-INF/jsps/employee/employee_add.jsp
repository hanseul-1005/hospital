<%@page import="windy.hospital.model.EmployeeModel"%>
<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function goAdd() {

	var name = document.getElementById('name').value;
	var belong = document.getElementById('belong').value;
	var tel = document.getElementById('tel').value;
	var department = document.getElementById('department').value;
	var major = document.getElementById('major').value;
	var room = document.getElementById('room').value;

	var roomNo = room.split('_')[0];
	var roomName = room.split('_')[1];
	
	
	param = "&name="+name+"&belong="+belong+"&tel="+tel+"&department="+department+"&major="+major+"&room_no="+roomNo+"&room_name="+roomName;
	
	$.ajax({
		type: "post", 
		url: "employee.windy?mode=add", 
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
									<td><input type="text" class="td_add" id="name"></td>
									<th>소속</th>
									<td><input type="text" class="td_add" id="belong"></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>연락처</th>
									<td><input type="text" class="td_add" id="tel"></td>
									<th>부서</th>
									<td>
                                        <select class="select_bx" id="department">
                                            <option value="행정처">행정처</option>
											<option value="의사">의사</option>
			                                <option value="간호">간호</option>
			                                <option value="임상 병리">임상 병리</option>
                                        </select>
                                    </td>
								</tr>
                                <tr>
									<th>전공</th>
									<td><input type="text" class="td_add" id="major"></td>
									<th>지정실</th>
									<td>
                                        <select class="select_bx" id="room">
                                        	<%for(int i=0; i<listRoom.size(); i++) {
                                        		RoomModel room = listRoom.get(i);
                                        	%>
                                            <option value="<%=room.getNo() %>_<%=room.getName() %>"><%=room.getName() %></option>
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
							<button class="btn_basic_150" onclick="javascript: goAdd()">등록</button>
							<button class="btn_cancel_150" onclick="history.back()">취소</button>
						</div>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>