<%@page import="windy.hospital.model.HospitalModel"%>
<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.PatientModel"%>
<%@page import="windy.hospital.model.RegionModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

ArrayList<PatientModel> listPatient = (ArrayList<PatientModel>) request.getAttribute("listPatient");
ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");
ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) request.getAttribute("listHospital");
String code = (String) request.getAttribute("code");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
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

function goModify(no) {

	location.href = "patient.windy?menu=modify&no="+no;
	
}

function goChart(no) {
	location.href = "chart.windy?menu=chart&no="+no;
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
					<span class="span_board_title">환자 관리</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							<select style="width: 120px; height: 30px; border-color: #74ADBD; color: #496E73; border-radius: 3px">
								<option>전체 병실</option>
								<%for(int i=0; i<listRoom.size(); i++) {
									RoomModel room = listRoom.get(i);
								%>
								<option><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>
								<%} %>
							</select>
							<input type="text" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; " id="name">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;">
								<img alt="" src="images/img_search.png" width="30px" height="30px">							
							</div>
						</div>
						<div style="width: 50%">
                            <button class="btn_basic_150" onClick="javascript:popOpen('.modal-bg3', '.modal_1400_900-wrap1');">PCR 그룹 관리</button>
                            <button class="btn_basic_150" onClick="javascript: goAdd()">환자 등록</button>
							<button class="btn_basic_150" onclick="javascript: goAddAll()">환자 일괄 등록</button>
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
							</colgroup>
							<thead>
								<tr>
									<th>No</th>
									<th>환자명</th>
									<th>환자번호</th>
									<th>성별</th>
									<th>생년월일</th>
									<th>차트 조회</th>
								</tr>
							</thead>
							<tbody>
								<%for(int j=0; j<listPatient.size(); j++) { 
									PatientModel patient = listPatient.get(j);
								%>
								<tr>
									<td onClick="javascript: goModify(<%=patient.getNo() %>)"><%=j+1 %></td>
									<td onClick="javascript: goModify(<%=patient.getNo() %>)"><%=patient.getName() %></td>
									<td onClick="javascript: goModify(<%=patient.getNo() %>)"><%=patient.getCode() %></td>
									<td onClick="javascript: goModify(<%=patient.getNo() %>)"><%=patient.getGender() %></td>
                                    <td onClick="javascript: goModify(<%=patient.getNo() %>)"><%=patient.getBirth() %></td>
									<td>
                                        <button class="btn_basic_100" onclick="javascript: goChart(<%=patient.getNo() %>)">차트 조회</button>
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

	<div class="modal-bg1" onClick="javascript:popClose('.modal-bg1', '.modal_1200_700-wrap1');"></div>
	<div class="modal_1200_700-wrap1">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">환자 등록</span>
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
						<td><input type="text" class="input_text" id="code" value="<%=code %>"></td>
                        <th>이름</th>
                        <td><input type="text" class="input_text" id="name"></td>
					</tr>
					<tr>
						<th>주민등록번호 앞자리</th>
						<td><input type="text" class="input_text" id="birth"></td>
                        <th>성별</th>
						<td>
							<input type="radio" name="gender" value="남성">
							<label for="on">남성</label>
							<input type="radio" name="gender" value="여성">
							<label for="on">여성</label>
						</td>
					</tr>
					<tr>
						<th>혈액형</th>
						<td><input type="text" class="input_text" id="blood"></td>
                        <th>연락처</th>
						<td><input type="text" class="input_text" id="tel"></td>
					</tr>
					<tr>
						<th>주소</th>
                        <td colspan="3">
                        	<input type="text" class="input_text" id="addr" style="height: 40px; width: 97%; margin-top: 5px"><br/>
                        	<input type="text" class="input_text" id="addr_detail" style="height: 40px; width: 97%; margin-top: 5px; margin-bottom: 5px;">
                        </td>
					</tr>
					<tr>
						<th>병실</th>
						<td>
							<select class="popup_select" id="room">
								<%for(int i=0; i<listRoom.size(); i++) {
									RoomModel room = listRoom.get(i);
								%>
								<option value="<%=room.getNo() %>"><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>
								<%} %>
							</select>
						</td>
                        <th>보호자명</th>
						<td><input type="text" class="input_text" id="guardian_name"></td>
					</tr>
					<tr>
						<th>보호자 연락처</th>
						<td><input type="text" class="input_text" id="guardian_tel"></td>
                        <th>보호자 관계</th>
						<td><input type="text" class="input_text" id="guardian_relation"></td>
					</tr>
					<tr>
						<th>상태</th>
                        <td colspan="3">
							<div style="width: 100%; text-align: left;">
								<input type="radio" name="state" id="state1" value="1">
								<label for="on">입원</label>
								<input type="radio" name="state" id="state2" value="2">
								<label for="on">후송</label>
								<select class="popup_select_2020" id="hospital">
									<%for(int l=0; l<listHospital.size(); l++) {
										HospitalModel hospital = listHospital.get(l);
									%>
									<option value="<%=hospital.getNo() %>"><%=hospital.getName() %></option>
									<%} %>
								</select>
								<select class="popup_select_2020">
									<option>비응급</option>
								</select>
								<input type="radio" name="state" id="state3" value="3">
								<label for="on">귀가</label>
								<input type="radio" name="state" id="state4" value="4">
								<label for="on">퇴원</label>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150">등록</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg1', '.modal_1200_700-wrap1');">취소</button>
                </div>
            </div>
		</div>
    </div>

	<div class="modal-bg2" onClick="javascript:popClose('.modal-bg2', '.modal_1200_700-wrap2');"></div>
	<div class="modal_1200_700-wrap2">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">환자 수정</span>
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
						<td id="modi_code"></td>
                        <th>이름</th>
                        <td><input type="text" class="input_text" id="modi_name"></td>
					</tr>
					<tr>
						<th>주민등록번호 앞자리</th>
						<td><input type="text" class="input_text" id="modi_birth"></td>
                        <th>성별</th>
						<td>
							<input type="radio" name="modi_gender" id="male">
							<label for="on">남성</label>
							<input type="radio" name="modi_gender" id="female">
							<label for="on">여성</label>
						</td>
					</tr>
					<tr>
						<th>혈액형</th>
						<td><input type="text" class="input_text" id="modi_blood"></td>
                        <th>연락처</th>
						<td><input type="text" class="input_text" id="modi_tel"></td>
					</tr>
					<tr>
						<th>주소</th>
                        <td colspan="3"></td>
					</tr>
					<tr>
						<th>병실</th>
						<td>
							<select class="popup_select" id="modi_room">
								<%for(int i=0; i<listRoom.size(); i++) {
									RoomModel room = listRoom.get(i);
								%>
								<option value="<%=room.getNo() %>"><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>
								<%} %>
							</select>
						</td>
                        <th>보호자명</th>
						<td><input type="text" class="input_text" id="modi_guardian_name"></td>
					</tr>
					<tr>
						<th>보호자 연락처</th>
						<td><input type="text" class="input_text" id="modi_guardian_tel"></td>
                        <th>보호자 관계</th>
						<td><input type="text" class="input_text" id="modi_guardian_relation"></td>
					</tr>
					<tr>
						<th>상태</th>
                        <td colspan="3">
							<div style="width: 100%; text-align: left;">
								<input type="radio" name="modi_state" id="state1">
								<label for="on">입원</label>
								<input type="radio" name="modi_state" id="state2">
								<label for="on">후송</label>
								<select class="popup_select_2020" id="modi_hospital">
									<%for(int l=0; l<listHospital.size(); l++) {
										HospitalModel hospital = listHospital.get(l);
									%>
									<option value="<%=hospital.getNo() %>"><%=hospital.getName() %></option>
									<%} %>
								</select>
								<select class="popup_select_2020">
									<option>비응급</option>
								</select>
								<input type="radio" name="modi_state" id="state3">
								<label for="on">귀가</label>
								<input type="radio" name="modi_state" id="state4">
								<label for="on">퇴원</label>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="wrapper_popup_btn">
                <div style="width: 80%">
                    <button class="btn_basic_150">수정</button>
                    <button class="btn_cancel_150" onClick="javascript:popClose('.modal-bg2', '.modal_1200_700-wrap2');">취소</button>
                </div>
            </div>
		</div>
    </div>

	<div class="modal-bg3" onClick="javascript:popClose('.modal-bg3', '.modal_1400_900-wrap1');"></div>
	<div class="modal_1400_900-wrap1">
        <div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">그룹 관리</span>
				</div>
			</div>
			<div class="wrapper_popup_top">
				<div style="width: 90%; margin: auto; display: flex;">
					<div style="width: 30%; text-align: left; display: flex;">
						<table class="popup_tb_border">
							<colgroup>
								<col width="50%">
								<col width="50%">
							</colgroup>
							<tr>
								<th>그룹명</th>
								<td>그룹3</td>
							</tr>
						</table>
					</div>
					<div style="width: 70%; display: flex; justify-content: right; align-items: center;">
						<input type="text" class="top_input" style="width: 200px;"/>
						<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;">
							<img alt="" src="/images/img_search.png" width="30px" height="30px">							
						</div>
					</div>
				</div>
			</div>
			<div class="wrapper_popup_top">
				<div style="width: 90%; margin: auto; display: flex;">
					<div style="width: 30%; text-align: left; display: flex; flex-direction: column;">
						<div style="text-align: center;">
							<button class="btn_basic_150">그룹 추가</button>
							<button class="btn_cancel_150">그룹 삭제</button>
						</div>
						<table class="popup_tb_border" style="margin-top: 5%;">
							<colgroup>
								<col width="100%">
							</colgroup>
							<tr>
								<th>그룹 목록</th>
							</tr>
							<tr>
								<td>그룹1</td>
							</tr>
							<tr>
								<td>그룹2</td>
							</tr>
							<tr>
								<td></td>
							</tr>
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
					<div style="width: 68%; display: flex; justify-content: right; align-items: center; margin-left: 2%;">
						<div style="width: 40%;">
							<table class="popup_tb_border">
								<colgroup>
									<col width="100%">
								</colgroup>
								<tr>
									<th>그룹 포함</th>
								</tr>
								<tr>
									<td>김길동(남, 52세, 환자번호)</td>
								</tr>
								<tr>
									<td>이길동(남, 52세)</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td></td>
								</tr>
							</table>
						</div>
						<div style="width: 20%;">
							<div style="display: flex; width: 100%; flex-direction: column; justify-content: center; align-items: center;">
								<button class="btn_basic_100" style="margin-bottom: 20%;">◀ 포함</button>
								<button class="btn_cancel_100">미포함 ▶</button>
							</div>
						</div>
						<div style="width: 40%;">
							<table class="popup_tb_border">
								<colgroup>
									<col width="100%">
								</colgroup>
								<tr>
									<th>그룹 미포함</th>
								</tr>
								<tr>
									<td>성춘향(여, 37세, 환자번호)</td>
								</tr>
								<tr>
									<td>최길동(남, 28세)</td>
								</tr>
								<tr>
									<td>강몽룡(남, 62세)</td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>