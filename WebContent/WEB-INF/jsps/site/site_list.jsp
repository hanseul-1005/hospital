<%@page import="windy.hospital.model.SiteModel"%>
<%@page import="windy.hospital.model.RegionModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

ArrayList<SiteModel> listSite = (ArrayList<SiteModel>) request.getAttribute("listSite");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function popOpen(num, classPrefix, no, name, personName, tel, belong, note) {
	var modalBg = $(num);
	var modalPop = $(classPrefix);

	document.getElementById('no').value = no;
	document.getElementById('name').value = name;
	document.getElementById('person_name').value = personName;
	document.getElementById('tel').value = tel;
	document.getElementById('belong').value = belong;
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
	location.href="site.windy?menu=add";
}

function goSearch() {
	var name = document.getElementById('search_name').value;
	
	location.href="site.windy?menu=list&name="+name;
}

function goDelete(no) {
		
	var param = "&no="+no;
	
	$.ajax({
		type: "POST",
		url: "site.windy?mode=delete", 
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
	var personName = document.getElementById('person_name').value;
	var tel = document.getElementById('tel').value;
	var belong = document.getElementById('belong').value;
	var note = document.getElementById('note').value;
	
	var param = "&no="+no+"&name="+name+"&person_name="+personName+"&tel="+tel+"&belong="+belong+"&note="+note;
		
	$.ajax({
		type: "post", 
		url: "site.windy?mode=update", 
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
        <jsp:include page="../top_menu.jsp"></jsp:include>
        <div class="wrapper_main_contents" >
			<div style="height: 100%;">
				<jsp:include page="../side_menu.jsp"/>
			</div>
			<div class="wrapper_board_contents">
				<div class="wrapper_board">
					<span class="span_board_title">현장 관리</span>
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
								<col width="100px">
								<col width="200px">
								<col width="150px">
								<col width="200px">
                                <col width="300px">
                                <col width="200px">
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>책임자</th>
									<th>이름</th>
                                    <th>연락처</th>
                                    <th>소속</th>
									<th>비활성화</th>
								</tr>
							</thead>
							<tbody>
								<%for(int i=0; i<listSite.size(); i++) {
									SiteModel model = listSite.get(i);
								%>
								<tr>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', <%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getPersonName() %>', '<%=model.getTel() %>', '<%=model.getBelong() %>', '<%=model.getNote() %>');"><%=i+1 %></td>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', <%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getPersonName() %>', '<%=model.getTel() %>', '<%=model.getBelong() %>', '<%=model.getNote() %>');"><%=model.getName() %></td>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', <%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getPersonName() %>', '<%=model.getTel() %>', '<%=model.getBelong() %>', '<%=model.getNote() %>');"><%=model.getPersonName() %></td>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', <%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getPersonName() %>', '<%=model.getTel() %>', '<%=model.getBelong() %>', '<%=model.getNote() %>');"><%=model.getBelong() %></td>
									<td onClick="javascript: popOpen('.modal-bg1', '.modal_700_420-wrap1', <%=model.getNo() %>, '<%=model.getName() %>', '<%=model.getPersonName() %>', '<%=model.getTel() %>', '<%=model.getBelong() %>', '<%=model.getNote() %>');"><%=model.getTel() %></td>
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
	<div class="modal_700_420-wrap1" style="height: 550px;">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">현장 수정</span>
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
						<th>책임자</th>
						<td><input type="text" id="name" class="input_text"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" id="person_name" class="input_text"></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="text" id="tel" class="input_text"></td>
					</tr>
					<tr>
						<th>소속</th>
						<td><input type="text" id="belong" class="input_text"></td>
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