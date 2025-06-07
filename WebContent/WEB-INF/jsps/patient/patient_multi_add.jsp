<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

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
	html += "<td><input type='text' class='td_add' id='name_"+num+"'></td>";
	html += "<td>";
	html += "<input type='radio' name='gender_"+num+"' value='남성' checked='checked'>";
	html += "<label for='on'>남성</label>";
	html += "<input type='radio' name='gender_"+num+"' value='여성'>";
	html += "<label for='on'>여성</label>";
	html += "</td>";
	html += "<td><input type='text' class='td_add' id='birth_"+num+"'></td>";
	html += "<td><input type='text' class='td_add' id='tel_"+num+"'></td>";
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
	var size = 0;
	
	var param = "";
	
	for(var i=0; i<num; i++) {

		if(document.getElementById('name_'+i)) {

			var name = document.getElementById('name_'+i).value;
			var gender = $('input[name=gender_'+i+']:checked').val();
			var birth = document.getElementById('birth_'+i).value;
			var tel = document.getElementById('tel_'+i).value;
			
			param += "&name_"+size+"="+name+"&gender_"+size+"="+gender+"&birth_"+size+"="+birth+"&tel_"+size+"="+tel;
			
			size = size+1;
		}
	}
	
	
	param += "&size="+size;
	
	var test = "";
	
	$.ajax({
		type: "post", 
		url: "patient.windy?mode=add", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("등록되었습니다.");
			location.href = "patient.windy?menu=list"; 
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
					<span class="span_board_title">환자 일괄 등록</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
								
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
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
							</colgroup>
							<thead>
								<tr>
									<th>환자명</th>
									<th>성별</th>
									<th>주민번호앞자리</th>
									<th>연락처</th>
                                    <th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" class="td_add" id="name_0"></td>
                                    <td>
                                        <input type="radio" name="gender_0" value="남성" checked="checked"><label for="on">남성</label>
                                        <input type="radio" name="gender_0" value="여성"><label for="on">여성</label>
                                    </td>
									<td><input type="text" class="td_add" id="birth_0"></td>
									<td><input type="text" class="td_add" id="tel_0"></td>
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
</body>
</html>