<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		html += "<td><input type='text' class='td_add' id='name_"+num+"'></td>";
		html += "<td>";
		html += "<select class='select_bx' onclick='javascript: setCnt("+num+")' id='code_"+num+"'>";
		html += "<option value='S'>S</option>";
		html += "<option value='A'>A</option>";
		html += "<option value='B'>B</option>";
		html += "</select>"
		html += "</td>";
		html += "<td>";
		html += "<select class='select_bx' id='code_no_"+num+"'>";
		for(var i=1; i<9; i++) {
			html += "<option value='"+i+"'>"+i+"</option>";
		}
		html += "<option value='A'>A</option>";
		html += "<option value='B'>B</option>";
		html += "</td>";
		html += "<td><input type='text' class='td_add' id='cnt_"+num+"' value='1'></td>";
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

//날짜 포맷 yyyy-mm-dd
function inputDateFormat(obj) {
 // 사용자 입력값은 모두 숫자만 받는다.(나머지는 ""처리)
 let val = obj.value.replace(/\D/g, "");
 let leng = val.length;

 // 출력할 결과 변수
 let result = '';

 if (leng == 0) {
     result = '';
 } else if (leng < 5) {
     result = val;
 } else if (leng == 5 || leng == 6) {
     result = val.substring(0,4) + '-' + val.substring(4,6);
 } else {
     result = val.substring(0,4) + '-' + val.substring(4,6) + '-' + val.substring(6,8);
 }
 obj.value = result;
}

function setCnt(num) {
	var code = document.getElementById('code_'+num).value;
	
	if(code == "S") {
		document.getElementById('cnt_'+num).value = 1;
	}
	else if(code == "A") {
		document.getElementById('cnt_'+num).value = 6;
	}
	else if(code == "B") {
		document.getElementById('cnt_'+num).value = 8;
	}
}

function goAdd() {

	var num = document.getElementById('num').value;
	var size = 0;
	
	var param = "";
	
	for(var i=0; i<num; i++) {

		if(document.getElementById('name_'+i)) {

			var name = document.getElementById('name_'+i).value;
			var code = document.getElementById('code_'+i).value;
			var codeNo = document.getElementById('code_no_'+i).value;
			var cnt = document.getElementById('cnt_'+i).value;
			
			param += "&name_"+size+"="+name+"&code_"+size+"="+code+"&code_no_"+size+"="+codeNo+"&cnt_"+size+"="+cnt;
			
			size = size+1;
		}
	}
	
	
	param += "&size="+size;
	
	var test = "";
	
	$.ajax({
		type: "post", 
		url: "room.windy?mode=add", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("등록되었습니다.");
			location.href = "room.windy?menu=list"; 
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
					<span class="span_board_title">병실 등록</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							
						</div>
						<div style="width: 50%">
                            <button type="button" class="btn_basic_150" onclick="javascript: addRow()">행추가</button>
							<button type="button" class="btn_basic_150" onclick="javascript: goAdd()">등록</button>
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
							</colgroup>
							<thead>
								<tr>
									<th>병동명</th>
									<th>코드</th>
									<th>코드번호</th>
									<th>수용인원</th>
									<th>행삭제</th>
								</tr>
							</thead>
							<tbody>
								<tr id="tr_0">
									<td><input type="text" class="td_add" id="name_0"></td>
									<td>
                                        <select class="select_bx" onclick="javascript: setCnt(0)" id="code_0">
											<option value="S">S</option>
											<option value="A">A</option>
											<option value="B">B</option>
										</select>
									</td>
									<td>
                                        <select class="select_bx" id="code_no_0">
											<%for(int i=1; i<9; i++) {%>
											<option value="<%=i %>"><%=i %></option>
											<%} %>
										</select>
									</td>
									<td><input type="text" class="td_add" id="cnt_0" value="1"></td>
									<td>
                                        <button type="button" class="btn_red_100" onclick="javascript: deleteRow(0)">행 삭제</button>
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