<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.EmployeeModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

ArrayList<EmployeeModel> listEmployee = (ArrayList<EmployeeModel>) request.getAttribute("listEmployee");
ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

function goSearch() {
	var name = document.getElementById('search_name').value;
	
	location.href="employee.windy?menu=list&name="+name;
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

function goModify() {

	var no = document.getElementById('no').value;
	var name = document.getElementById('name').value;
	var tel = document.getElementById('tel').value;
	
	var param = "&no="+no+"&name="+name+"&tel="+tel;
		
	$.ajax({
		type: "post", 
		url: "employee.windy?mode=update", 
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
					<span class="span_board_title">파견인원 관리</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							<select style="width: 120px; height: 30px; border-color: #74ADBD; color: #496E73; border-radius: 3px">
								<option value="">전체</option>
								<option value="N">활성화</option>
								<option value="Y">비활성화</option>
							</select>
                            <select style="width: 120px; height: 30px; margin-left: 10px; border-color: #74ADBD; color: #496E73; border-radius: 3px">
								<option value="">전체 부서</option>
								<option value="행정처">행정처</option>
								<option value="의사">의사</option>
                                <option value="간호">간호</option>
                                <option value="임상 병리">임상 병리</option>
							</select>
							<input type="text" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; ">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;">
								<img alt="" src="/images/img_search.png" width="30px" height="30px">							
							</div>
						</div>
						<div style="width: 50%">
                            <button class="btn_basic_150" onclick="javascript: goAdd()">일괄 등록</button>
							<button class="btn_basic_150">단일 등록</button>
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
									<td><%=i+1 %></td>
									<td><%=employee.getName() %></td>
									<td><%=employee.getTel() %>></td>
									<td><%=employee.getBelong() %>></td>
                                    <td><%=employee.getMajor() %></td>
                                    <td><%=employee.getDepartment() %></td>
									<td>
                                        <select style="width: 120px; height: 30px; margin-left: 10px; border-color: #74ADBD; color: #496E73; border-radius: 3px">
                                        	<%for(int j=0; j<listRoom.size(); j++) {
                                        		RoomModel room = listRoom.get(i);
                                        	%>
                                            <option value="<%=room.getNo() %>"><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>
                                            <%} %>
                                        </select>
                                    </td>
                                    <td><%=employee.getOnOff() %></td>
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