<%@page import="windy.hospital.model.SuppliesModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) request.getAttribute("listSupplies");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function popOpen(num, classPrefix, no, name, date, amount, note) {
	var modalBg = $(num);
	var modalPop = $(classPrefix);

	document.getElementById('no').value = no;
	document.getElementById('name').value = name;
	document.getElementById('date').value = date;
	document.getElementById('amount').value = amount;
	document.getElementById('note').value = note;
	
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
	location.href="supplies.windy?menu=add";
}

function goSearch() {
	var name = document.getElementById('search_name').value;
	
	location.href="supplies.windy?menu=list&name="+name;
}

function goDelete(no) {
		
	var param = "&no="+no;
	
	$.ajax({
		type: "POST",
		url: "supplies.windy?mode=delete", 
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
	var date = document.getElementById('date').value;
	var amount = document.getElementById('amount').value;
	var note = document.getElementById('note').value;
	
	var param = "&no="+no+"&name="+name+"&date="+date+"&amount="+amount+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "supplies.windy?mode=update", 
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
					<span class="span_board_title">용품 관리</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							<input type="text" id="search_name" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; padding-left: 5px; padding-right: 5px; ">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;" onclick="javascript: goSearch()">
								<img alt="" src="images/img_search.png" width="30px" height="30px">							
							</div>
						</div>
						<div style="width: 50%">
							<button class="btn_basic_150" onclick="javascript: goAdd()">등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>용품명</th>
									<th>입고일</th>
									<th>잔여량</th>
									<th>비활성화</th>
								</tr>
							</thead>
							<tbody>
								<%for(int i=0; i<listSupplies.size(); i++) {
									SuppliesModel model = listSupplies.get(i);
								%>
								<tr>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', 
										<%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getDate() %>', '<%=model.getAmount()%>', '<%=model.getNote() %>');"><%=i+1 %></td>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', 
										<%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getDate() %>', '<%=model.getAmount()%>', '<%=model.getNote() %>');"><%=model.getName() %></td>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', 
										<%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getDate() %>', '<%=model.getAmount()%>', '<%=model.getNote() %>');"><%=model.getDate() %></td>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', 
										<%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getDate() %>', '<%=model.getAmount()%>', '<%=model.getNote() %>');"><%=model.getAmount() %></td>
									<td>
                                        <button type="button" class="btn_cancel_100" onclick="javascript: goDelete(<%=model.getNo() %>)">비활성화</button>
                                    </td>
								</tr>
								<%} %>
							</tbody>
						</table>
						<!-- 
						<div style="margin-top: 30px;">
							페이징처리
						</div> -->
					</div>
				</div>
			</div>
		</div>
    </div>
	<div class="modal-bg1" onClick="javascript:popClose('.modal-bg1', '.modal_700_420-wrap1');"></div>
	<div class="modal_700_420-wrap1" style="height: 450px;">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">용품 수정</span>
				</div>
			</div>
            
			<div class="wrapper_popup" style="margin-top: 10px;">
				<input type="hidden" id="no">
				<table class="popup_tb_border">
					<colgroup>
						<col width="50%">
						<col width="50%">
					</colgroup>
                    <tr>
						<th>용품명</th>
						<td><input type="text" id="name" class="input_text"></td>
					</tr>
					<tr>
						<th>입고일</th>
						<td><input type="text" id="date" class="input_text"></td>
					</tr>
					<tr>
						<th>잔여량</th>
						<td><input type="text" id="amount" class="input_text"></td>
					</tr>
					<tr>
						<th>비고</th>
						<td><input type="text" id="note" class="input_text"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: goModify()">수정</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg1', '.modal_700_420-wrap1');">취소</button>
                </div>
            </div>
		</div>
    </div>
</body>
</html>