<%@page import="windy.hospital.model.DBModel"%>
<%@page import="windy.hospital.model.RegionModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

ArrayList<DBModel> listDB = (ArrayList<DBModel>) request.getAttribute("listDB");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">

function popOpen(num, no, title, detail, startDate, endDate) {
    var modalBg = $('.modal-bg' + num);
	var modalPop = $('.modal_900_600-wrap' + num);

	document.getElementById('modi_no').value = no;
	document.getElementById('modi_title').innerHTML = title;
	document.getElementById('modi_detail').value = detail;
	document.getElementById('modi_start_date').value = startDate;
	document.getElementById('modi_end_date').value = endDate;
	
    modalBg.show();
	modalPop.show();
}

function popClose(num) {
	var modalBg = $('.modal-bg' + num);
    var modalPop = $('.modal_900_600-wrap' + num);
    
	modalBg.hide();
    modalPop.hide();
}

function goSearch() {
	var name = document.getElementById('search_name').value;
	
	location.href="region.windy?menu=list&name="+name;
}

function goDelete(no) {
		
	var param = "&no="+no;
	
	$.ajax({
		type: "POST",
		url: "region.windy?mode=delete", 
		data: param,
		error: ajaxFailed,
		success: function(ret) {

			alert("비활성화되었습니다.");
			location.reload(); 
		}
	});
	
}

function goAdd() {

	var strCode = document.getElementById('code').value;

	var code = strCode.split('_')[0];
	var title = strCode.split('_')[1];
	var detail = document.getElementById('detail').value;
	var startDate = document.getElementById('start_date').value;
	var endDate = document.getElementById('end_date').value;
	
	var param = "&code="+code+"&title="+title+"&detail="+detail
	+"&start_date="+startDate+"&end_date="+endDate;
		
	$.ajax({
		type: "post", 
		url: "db.windy?mode=add", 
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

function goModify() {

	var no = document.getElementById('modi_no').value;
	var detail = document.getElementById('modi_detail').value;
	var startDate = document.getElementById('modi_start_date').value;
	var endDate = document.getElementById('modi_end_date').value;
	
	var param = "&no="+no+"&code="+code+"&detail="+detail+"&start_date="+startDate+"&end_date="+endDate;
		
	$.ajax({
		type: "post", 
		url: "db.windy?mode=update", 
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
					<span class="span_board_title">야전 병원 운용</span>
					<div class="wrapper_board_btn">
						<div style="width: 100%">
							<button class="btn_basic_150" onclick="javascript:popOpen('1', '', '', '', '', '');">야전 병원 운용</button>
							<button class="btn_basic_150">야전 병원 종료</button>
						</div>
					</div>
					<div style="margin-top: 30px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="100px">
								<col width="200px">
								<col width="400px">
								<col width="200px">
								<col width="200px">
								<col width="399px">
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>재난 구분</th>
									<th>재난명</th>
									<th>시작일자</th>
									<th>종료일자</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%for(int i=0; i<listDB.size(); i++) {
									DBModel model = listDB.get(i);
								%>
								<tr>
									<td onclick="javascript:popOpen('2', '<%=model.getNo() %>', '<%=model.getTitle() %>', '<%=model.getDetail() %>', '<%=model.getStartDate() %>', '<%=model.getEndDate() %>');"><%=i+1 %></td>
									<td onclick="javascript:popOpen('2', '<%=model.getNo() %>', '<%=model.getTitle() %>', '<%=model.getDetail() %>', '<%=model.getStartDate() %>', '<%=model.getEndDate() %>');"><%=model.getTitle() %></td>
									<td onclick="javascript:popOpen('2', '<%=model.getNo() %>', '<%=model.getTitle() %>', '<%=model.getDetail() %>', '<%=model.getStartDate() %>', '<%=model.getEndDate() %>');"><%=model.getDetail() %></td>
									<td onclick="javascript:popOpen('2', '<%=model.getNo() %>', '<%=model.getTitle() %>', '<%=model.getDetail() %>', '<%=model.getStartDate() %>', '<%=model.getEndDate() %>');"><%=model.getStartDate() %></td>
									<td onclick="javascript:popOpen('2', '<%=model.getNo() %>', '<%=model.getTitle() %>', '<%=model.getDetail() %>', '<%=model.getStartDate() %>', '<%=model.getEndDate() %>');"><%=model.getEndDate() %></td>
									<td>
										<button class="btn_basic_100">세팅</button>
										<button class="btn_basic_100">복원</button>
									</td>
								</tr>
								<%} %>
							</tbody>
						</table>
						
						<div style="margin-top: 30px;">
							<!-- 페이징처리 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal-bg1" onClick="javascript:popClose('1');"></div>
	<div class="modal_900_600-wrap1">
		<div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">야전 병원 운용 등록</span>
				</div>
				<div class="wrapper_popup_top_right">
					<img alt="" src="images/img_close.png" width="39px" height="39px" onClick="javascript:popClose('1');">
				</div>
			</div>
	
			<div class="wrapper_popup" style="margin-top: 30px;">
				<table class="popup_tb">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
						<th>재난구분</th>
						<td>
							<select class="popup_select" id="code">
								<option value="T_감염병">감염병</option>
								<option value="N_자연재해">자연재해</option>
								<option value="R_화학방사능">화학방사능</option>
								<option value="F_화재">화재</option>
								<option value="E_기타">기타</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>재난상세</th>
						<td><input type="text" class="input_text" id="detail"></td>
					</tr>
					<tr>
						<th>시작일자</th>
						<td><input type="date" class="input_text" id="start_date"></td>
					</tr>
					<tr>
						<th>종료일자</th>
						<td><input type="date" class="input_text" id="end_date"></td>
					</tr>
					<tr>
						<th></th>
						<td><button class="btn_basic_150_50" onclick="javascript: goAdd()">등록</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="modal-bg2" onClick="javascript:popClose('2');"></div>
	<div class="modal_900_600-wrap2">
		<div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">야전 병원 운용 수정</span>
				</div>
				<div class="wrapper_popup_top_right">
					<img alt="" src="images/img_close.png" width="39px" height="39px" onClick="javascript:popClose('2');">
				</div>
			</div>
	
			<div class="wrapper_popup" style="margin-top: 30px;">
				<table class="popup_tb">
					<colgroup>
						<col width="30%">
						<col width="70%">
					</colgroup>
					<tr>
						<th>재난구분</th>
						<td>
							<input type="hidden" id="modi_no" value="" />
							<input type="hidden" id="modi_code" value="" />
							<label for="on" id="modi_title"></label>
						</td>
					</tr>
					<tr>
						<th>재난상세</th>
						<td><input type="text" class="input_text" id="modi_detail"></td>
					</tr>
					<tr>
						<th>시작일자</th>
						<td><input type="date" class="input_text" id="modi_start_date"></td>
					</tr>
					<tr>
						<th>종료일자</th>
						<td><input type="date" class="input_text" id="modi_end_date"></td>
					</tr>
					<tr>
						<th></th>
						<td><button class="btn_basic_150_50" onclick="javascript: goModify()">수정</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>