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

function goAdd() {

	var num = document.getElementById('num').value;
	var department = document.getElementById('department').value;
	var size = 0;
	
	var param = "&department="+department;
	
	for(var i=0; i<num; i++) {

		if(document.getElementById('name_'+i)) {

			var name = document.getElementById('name_'+i).value;
			var tel = document.getElementById('tel_'+i).value;
			var belong = document.getElementById('belong_'+i).value;
			var major = document.getElementById('major_'+i).value;
			var room = document.getElementById('room_'+i).value;
			
			var roomNo = room.split('_')[0];
			var roomName = room.split('_')[1];
			
			var onOff = $('input[name=on_off_'+i+']:checked').val();
			
			param += "&name_"+size+"="+name+"&tel_"+size+"="+tel+"&belong_"+size+"="+belong
				+"&major_"+size+"="+major+"&room_no_"+size+"="+roomNo+"&room_name_"+size+"="+roomName+"&on_off_"+size+"="+onOff;
			
			size = size+1;
			
			console.log("onOff : "+onOff);
		}
	}
	
	
	param += "&size="+size;
	
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
        <div class="wrapper_main_contents" >
			<div style="height: 100%;">
				<jsp:include page="../side_menu.jsp"/>
			</div>
			<div class="wrapper_board_contents">
				<div class="wrapper_board">
					<span class="span_board_title">파견직원 일괄 등록</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
                            <select id="department" style="width: 120px; height: 30px; border-color: #74ADBD; color: #496E73; border-radius: 3px">
								<option>행정처</option>
								<option>의사</option>
                                <option>간호</option>
                                <option>임상 병리</option>
							</select>
							<!-- <button class="btn_basic_150" style="margin-left: 10px;">엑셀 다운로드</button>
                            <button class="btn_basic_150" style="margin-left: 10px;" onClick="javascript:popOpen('.modal-bg1', '.modal_700_200-wrap1');">엑셀 업로드</button> -->
						</div>
						<div style="width: 50%">
                            <button class="btn_basic_150" onclick="javascript: addRow()">행 추가</button>
							<button class="btn_basic_150" onclick="javascript: goAdd()">일괄 등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<input type="hidden" id="num" value="1"/>
						<table class="tb_basic tb_management" id="tb_add">
							<colgroup>
								<col width="100px">
								<col width="150px">
								<col width="200px">
								<col width="200px">
								<col width="300px">
								<col width="200px">
                                <col width="200px">
                                <col width="150px">
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>이름</th>
									<th>연락처</th>
									<th>소속</th>
									<th>전공</th>
                                    <th>지정실</th>
                                    <th>근무/휴무</th>
                                    <th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><input type="text" class="td_add" id="name_0"></td>
									<td><input type="text" class="td_add" id="tel_0"></td>
									<td><input type="text" class="td_add" id="belong_0"></td>
									<td><input type="text" class="td_add" id="major_0"></td>
									<td>
                                        <select class="td_select" id="room_0">
                                        	<%for(int i=0; i<listRoom.size(); i++) {
                                        		RoomModel room = listRoom.get(i);
                                        	%>
                                            <option value="<%=room.getNo() %>_<%=room.getName() %>"><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>
                                            <%} %>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="radio" name="on_off_0" id="on" value="근무">
                                        <label for="on">근무</label>
                                        <input type="radio" name="on_off_0" id="off" value="휴무">
                                        <label for="on">휴무</label>
                                    </td>
									<td>
                                        <button class="btn_red_100">행 삭제</button>
                                    </td>
								</tr>
							</tbody>
						</table>
						
					</div>
				</div>
			</div>
		</div>
    </div>

	<div class="modal-bg1" onClick="javascript:popClose('.modal-bg1', '.modal_700_200-wrap1');"></div>
	<div class="modal_700_200-wrap1">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top" style="height: 20%;">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title_20">엑셀 업로드</span>
				</div>
			</div>
			<div class="wrapper_popup" style="margin-top: 15px;">
				<div class="filebox">
					파일명 : 
					<input class="upload-name" value="첨부파일" placeholder="첨부파일">
					<label for="file">파일찾기</label> 
					<input type="file" id="file">

					<script>
						$("#file").on('change', function() {
						  var fileName = $("#file").val().split('\\').pop(); // 경로 말고 파일명만 가져오기
						  $(".upload-name").val(fileName);
						});
					  </script>
				</div>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_100" onClick="javascript:popClose('.modal-bg1', '.modal_700_200-wrap1');">확인</button>
                    <button class="btn_cancel_100" onClick="javascript:popClose('.modal-bg1', '.modal_700_200-wrap1');">취소</button>
                </div>
            </div>
		</div>
    </div>
</body>
</html>