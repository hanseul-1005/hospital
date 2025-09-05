<%@page import="windy.hospital.model.MedicineModel"%>
<%@page import="windy.hospital.model.ChartModel"%>
<%@page import="windy.hospital.model.HospitalModel"%>
<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.PatientModel"%>
<%@page import="windy.hospital.model.RegionModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

PatientModel patient = (PatientModel) request.getAttribute("patient");
ArrayList<ChartModel> listChart = (ArrayList<ChartModel>) request.getAttribute("listChart");
ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) request.getAttribute("listMedicine");

String role = (String) session.getAttribute("role");

String modalType = ".modal_1200_700-wrap2";
if("임상병리".equals(role)) {
	modalType = ".modal_1200_700-wrap1";
} else {
	modalType = ".modal_1200_700-wrap2";
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">
function popOpen(num, classPrefix, idx) {

	var modalBg = $(num);
	var modalPop = $(classPrefix);

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
	location.href="patient.windy?menu=add";
}

function goAddAll() {
	location.href="patient.windy?menu=multi_add";
}

function goSearch() {
	var name = document.getElementById('search_name').value;
	
	location.href="region.windy?menu=list&name="+name;
}

function addChart(type) {
	
	var no = '<%=patient.getNo() %>';
	var date = "";
	var content = "";
	var test = "";
	var testCheck = "";
	var testResult = "";
	var testResultCheck = "";
	
	if(type = "c") {
		date = document.getElementById("c_date").value;
		content = document.getElementById("c_content").value;
		test = document.getElementById("c_test").value;
		testResult =  $(":input:radio[name=c_test_result]:checked").val();
		
	} else if(type == "d") {
		date = document.getElementById("d_date").value;
		content = document.getElementById("d_content").value;
		
	} 
		
	var param = "&no="+no+"&date="+date+"&content="+content+"&test="+test+"&test_result="+testResult+"&test_check=0&test_result_check=0";
	
	$.ajax({
		type: "POST",
		url: "chart.windy?mode=add", 
		data: param,
		error: ajaxFailed,
		success: function(ret) {

			alert("등록되었습니다.");
			location.reload(); 
		}
	});
	
}

function goModify(no) {

	location.href = "patient.windy?menu=modify&no="+no;
	
}

function goChart(no) {
	location.href = "patient.windy?menu=chart&no="+no;
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
					<span class="span_board_title">차트 관리</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;"></div>
						<div style="width: 50%">
							<button class="btn_basic_150" onClick="javascript:popOpen('.modal-bg2', '<%=modalType %>');">차트 등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="200px">
								<col width="200px">
								<col width="200px">
								<col width="200px">
							</colgroup>
							<thead>
								<tr>
									<th>환자번호</th>
									<td><%=patient.getCode() %></td>
									<th>이름(나이, 성별)</th>
									<td><%=patient.getName() %>(<%=patient.getAge() %>세, <%=patient.getGender() %>)</td>
								</tr>
							</thead>
						</table>
					</div>
                    <div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex; align-items: center;">
							<input type="checkbox" id="hide" style="width: 30px; height: 30px; accent-color: #74ADBD;">
                            <label for="hide" style="color: #496E73; font-weight: bold;">간호사 소견 숨기기</label>
						</div>
						<div style="width: 50%; display: flex; justify-content: right;">
                            <input type="text" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; margin-right: 10px; padding-left: 5px; padding-right: 5px; ">
                            ~
                            <input type="text" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; ">
							<input type="text" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; ">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;">
								<img alt="" src="images/img_search.png" width="30px" height="30px">							
							</div>
						</div>
					</div>
                    <div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="200px">
								<col width="200px">
								<col width="200px">
								<col width="200px">
							</colgroup>
							<tbody>
							<%for(int i=0; i<listChart.size(); i++) {
								ChartModel chart = listChart.get(i);	%>
                                <tr>
									<th>날짜</th>
									<td><%=chart.getDate() %></td>
									<th>작성자</th>
									<td><%=chart.getEmployeeName() %></td>
								</tr>
								<tr height="">
									<th>소견</th>
									<td colspan="3"><%=chart.getContent() %></td>
								</tr>
							<%} %>            
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
    </div>

	<div class="modal-bg1" onClick="javascript:popClose('.modal-bg1', '<%=modalType %>');"></div>
	<div class="modal_1200_700-wrap1">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">차트 등록(임상병리사)</span>
				</div>
			</div>
            
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border">
					<colgroup>
						<col width="25%">
						<col width="25%">
                        <col width="25%">
						<col width="25%">
					</colgroup>
                    <tr>
						<th>환자번호</th>
						<td><%=patient.getCode() %>></td>
                        <th>이름(나이, 성별)</th>
                        <td><%=patient.getName() %>(<%=patient.getAge() %>세, <%=patient.getGender() %>)</td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border popup_tb_th100">
					<colgroup>
						<col width="25%">
						<col width="25%">
                        <col width="25%">
						<col width="25%">
					</colgroup>
                    <tr>
						<th>날짜</th>
						<td><input type="text" class="input_text" id="c_date"></td>
                        <th>
							<select class="popup_select" style="font-weight: bold; text-align: center;" id="c_test">
								<option value="PCR검사">PCR검사</option>
							</select>
						</th>
                        <td>
							<input type="radio" name="c_test_result" value="양성">
							<label for="on">양성</label>
							<input type="radio" name="c_test_result" value="음성">
							<label for="on">음성</label>
						</td>
					</tr>
					<tr>
						<th>검사결과</th>
						<td colspan="3"><input type="text" class="input_text" id="c_content"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: addChart('c')">등록</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg1', '<%=modalType %>');">취소</button>
                </div>
            </div>
		</div>
    </div>

	<div class="modal-bg2" onClick="javascript:popClose('.modal-bg2', '<%=modalType %>');"></div>
	<div class="modal_1200_700-wrap2">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">차트 등록(의사 및 간호사용)</span>
				</div>
			</div>
            
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border">
					<colgroup>
						<col width="25%">
						<col width="25%">
                        <col width="25%">
						<col width="25%">
					</colgroup>
                    <tr>
						<th>환자번호</th>
						<td><%=patient.getCode() %></td>
                        <th>이름(나이, 성별)</th>
                        <td><%=patient.getName() %>(<%=patient.getAge() %>세, <%=patient.getGender() %>)</td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border popup_tb_th100">
					<colgroup>
						<col width="25%">
						<col width="25%">
                        <col width="25%">
						<col width="25%">
					</colgroup>
                    <tr>
						<th>날짜</th>
						<td colspan="3">
							<div style="display: flex; justify-content: space-between; width: 100%;">
								<div style="width: 33%;">
									<input type="text" class="input_text" id="d_date">
								</div>
								<div style="width: 33%;">
									<button class="btn_basic_150" onClick="javascript:popOpen('.modal-bg3', '.modal_1200_700-wrap3');">약품 재고 조회</button>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>검사결과</th>
						<td colspan="3"><input type="text" class="input_text" id="d_content"></td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150" onclick="javascript: addChart('d')">등록</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg2', '<%=modalType %>');">취소</button>
                </div>
            </div>
		</div>
    </div>

	<div class="modal-bg3" onClick="javascript:popClose('.modal-bg3', '.modal_1200_700-wrap3');"></div>
	<div class="modal_1200_700-wrap3">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">약품 재고 조회</span>
				</div>
			</div>
			<div class="wrapper_popup_top" style="height: 5%;">
				<div style="width: 80%; margin: auto;">
					<div style="width: 50%; text-align: left; display: flex;">
						<input type="text" class="top_input" id="search_medicine" />
						<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;">
							<img alt="" src="images/img_search.png" width="30px" height="30px">							
						</div>
					</div>
				</div>
			</div>
			<div class="wrapper_popup" style="margin-top: 10px;">
				<table class="popup_tb_border popup_tb_th100">
					<colgroup>
						<col width="20%">
						<col width="40%">
                        <col width="40%">
					</colgroup>
                    <tr>
						<th>no</th>
						<th>약품명</th>
						<th>잔여량</th>
					</tr>
					<%
					for(int j=0; j<listMedicine.size(); j++) {
						MedicineModel medicine = listMedicine.get(j);
					%>
					<tr>
						<td><%=j+1 %></td>
						<td><%=medicine.getName() %></td>
						<td><%=medicine.getAmount() %></td>
					</tr>
					<%} %>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg3', '.modal_1200_700-wrap3');">닫기</button>
                </div>
            </div>
		</div>
    </div>
</html>