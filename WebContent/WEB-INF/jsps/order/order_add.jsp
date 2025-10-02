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
	html += "<td><input type='text' class='td_add' id='date_"+num+"' onkeydown='javascript: inputDateFormat(this)'></td>";
	html += "<td><input type='text' class='td_add' id='amount_"+num+"'></td>";
	html += "<td><input type='text' class='td_add' id='note_"+num+"'></td>";
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

function goAdd() {

	var num = document.getElementById('num').value;
	var size = 0;
	
	var param = "";
	
	for(var i=0; i<num; i++) {

		if(document.getElementById('name_'+i)) {

			var name = document.getElementById('name_'+i).value;
			var date = document.getElementById('date_'+i).value;
			var amount = document.getElementById('amount_'+i).value;
			var note = document.getElementById('note_'+i).value;
			
			param += "&name_"+size+"="+name+"&date_"+size+"="+date+"&amount_"+size+"="+amount+"&note_"+size+"="+note;
			
			size = size+1;
		}
	}
	
	
	param += "&size="+size;
	
	var test = "";
	
	$.ajax({
		type: "post", 
		url: "medicine.windy?mode=add", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("등록되었습니다.");
			location.href = "medicine.windy?menu=list"; 
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
					<span class="span_board_title">약품 등록</span>
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
									<th>약품</th>
									<th>입고일</th>
									<th>잔여량</th>
									<th>비고</th>
									<th>행삭제</th>
								</tr>
							</thead>
							<tbody>
								<tr id="tr_0">
									<td><input type="text" class="td_add" id="name_0"></td>
									<td><input type="text" class="td_add" id="date_0" onkeydown="javascript: inputDateFormat(this)"></td>
									<td><input type="text" class="td_add" id="amount_0"></td>
									<td><input type="text" class="td_add" id="note_0"></td>
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