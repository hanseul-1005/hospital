<%@page import="windy.hospital.model.HospitalModel"%>
<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="windy.hospital.model.PatientModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	PatientModel patient = (PatientModel) request.getAttribute("patient");
	ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");
	ArrayList<HospitalModel> listHospital = (ArrayList<HospitalModel>) request.getAttribute("listHospital");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">
function reg_Formatter() {

    var Social_Security_Number = document.getElementById('birth').value; //주민번호를 받아온다.
    var Social_Year = Social_Security_Number.substring(0,2); // 년도를 의미하는 2개로 자른다.



    let today = new Date();             //Windows.time을 가져온다.

    let year = today.getFullYear();     // 받아온 time에서 Year만 가져온다.



    var The_20th_Century = 19;          // 20세기 사람인지 구별하기 위한 변수

    var The_21th_Century = 20;          // 21세기 사람인지 구별하기 위한 변수

    var Before_Age = year - parseInt(Social_Year,10);  // 받아온 년도 2개를 기준으로 Windows.time 년도를 계산한다.

    // 예를들면, 21세기 태어난 사람들은 2020년 -2000년을 하면, 무조건 2000이상의 값이 나온다.

    // 2000이하인 경우 20세기 사람이므로, 주민번호 앞자리 2개와 19를 붙힌다.

    // 2000이상인 경우 21세기 사람이므로, 주민번호 앞자리 2개와 20를 붙힌다.

    if(Before_Age < 2000){

        //20세기 사람

        var After_Age =The_20th_Century + Social_Year;

        var Age = year - parseInt(After_Age,10)+1;

        return Age;

    }

    else{

        //21세기 사람

        var After_Age = The_21th_Century + Social_Year;

        var Age = year - parseInt(After_Age,10)+1;

        return Age;

    }
}


function goModify() {

	var no = '<%=patient.getNo() %>';
	var code = '<%=patient.getCode() %>';
	var name = document.getElementById('name').value;
	var birth = document.getElementById('birth').value;
	var gender = $('input[name=gender]:checked').val();
	var blood = document.getElementById('blood').value;
	var tel = document.getElementById('tel').value;
	var addr = document.getElementById('addr').value;
	var addrDetail = document.getElementById('addr_detail').value;
	var roomNo = document.getElementById('room').value;
	var guardianName = document.getElementById('guardian_name').value;
	var guardianTel = document.getElementById('guardian_tel').value;
	var guardianRelation = document.getElementById('guardian_relation').value;
	var state = $('input[name=state]:checked').val();
	var hospitalNo = document.getElementById('hospital').value;
	var evacuationReason = document.getElementById('evacuation_reason').value;
	
	var age = reg_Formatter();
	
	var param = "&no="+no+"&code="+code+"&name="+name+"&birth="+birth+"&gender="+gender+"&blood="+blood+"&tel="+tel+"&addr="+addr+"&addr_detail="+addrDetail+"&room_no="+roomNo+"&age="+age
		+"&guardian_name="+guardianName+"&guardian_tel="+guardianTel+"&guardian_relation="+guardianRelation+"&state="+state+"&hospital_no="+hospitalNo+"&evacuation_reason="+evacuationReason;
	
	$.ajax({
		type: "post", 
		url: "patient.windy?mode=update", 
		data: param,
		async: false, 
		dataType: 'text', 
		error: ajaxFailed,
		success: function(data, textStatus) {
			
			alert("수정되었습니다.");
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
        <jsp:include page="../top_menu.jsp"></jsp:include>
        <div class="wrapper_main_contents" >
			<div style="height: 100%;">
				<jsp:include page="../side_menu.jsp"/>
			</div>
			<div class="wrapper_board_contents">
				<div class="wrapper_board">
					<span class="span_board_title">환자 수정</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
								
						</div>
						<div style="width: 50%">
                            <button class="btn_basic_150" onclick="javascript: addRow()">행 추가</button>
							<button class="btn_basic_150" onclick="javascript: goModify()">수정</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<input type="hidden" id="num" value="1"/>
						<input type="hidden" id="patient_state" value="<%=patient.getState() %>"/>
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
		                        <th>이름</th>
		                        <td><input type="text" class="input_text" id="name" value="<%=patient.getName() %>"></td>
							</tr>
							<tr>
								<th>주민등록번호 앞자리</th>
								<td><input type="text" class="input_text" id="birth" value="<%=patient.getBirth() %>"></td>
		                        <th>성별</th>
								<td>
									<input type="radio" name="gender" value="남성" <%if("남성".equals(patient.getGender())) { %> checked="checked" <%} %>>
									<label for="on">남성</label>
									<input type="radio" name="gender" value="여성" <%if("여성".equals(patient.getGender())) { %> checked="checked" <%} %>>
									<label for="on">여성</label>
								</td>
							</tr>
							<tr>
								<th>혈액형</th>
								<td><input type="text" class="input_text" id="blood" value="<%=patient.getBlood() %>"></td>
		                        <th>연락처</th>
								<td><input type="text" class="input_text" id="tel" value="<%=patient.getTel() %>"></td>
							</tr>
							<tr>
								<th>주소</th>
		                        <td colspan="3">
		                        	<input type="text" class="input_text" id="addr" style="height: 40px; width: 97%; margin-top: 5px" value="<%=patient.getAddr() %>"><br/>
		                        	<input type="text" class="input_text" id="addr_detail" style="height: 40px; width: 97%; margin-top: 5px; margin-bottom: 5px;" value="<%=patient.getAddrDetail() %>">
		                        </td>
							</tr>
							<tr>
								<th>병실</th>
								<td>
									<select class="popup_select" id="room">
										<%for(int i=0; i<listRoom.size(); i++) {
											RoomModel room = listRoom.get(i);
										%>
										<option value="<%=room.getNo() %>" <%if(room.getNo() == patient.getRoomNo()) { %> selected="selected" <%} %>><%=room.getName() %>(<%=room.getCode() %><%=room.getCodeNo() %>)</option>
										<%} %>
									</select>
								</td>
		                        <th>보호자명</th>
								<td><input type="text" class="input_text" id="guardian_name" value="<%=patient.getGuardianName() %>"></td>
							</tr>
							<tr>
								<th>보호자 연락처</th>
								<td><input type="text" class="input_text" id="guardian_tel" value="<%=patient.getGuardianTel() %>"></td>
		                        <th>보호자 관계</th>
								<td><input type="text" class="input_text" id="guardian_relation" value="<%=patient.getGuardianRelation() %>"></td>
							</tr>
							<tr>
								<th>상태</th>
		                        <td colspan="3">
									<div style="width: 100%; text-align: left;">
										<input type="radio" name="state" value="1" <%if(patient.getState() == 1) {%> checked="checked" <%} %>>
										<label for="on">입원</label>
										<input type="radio" name="state" value="2" <%if(patient.getState() == 2) {%> checked="checked" <%} %>>
										<label for="on">후송</label>
										<select class="popup_select_2020" id="hospital">
											<%for(int l=0; l<listHospital.size(); l++) {
												HospitalModel hospital = listHospital.get(l);
											%>
											<option value="<%=hospital.getNo() %>" <%if(patient.getHospitalNo() == hospital.getNo()) { %> selected="selected" <%} %>><%=hospital.getName() %></option>
											<%} %>
										</select>
										<select class="popup_select_2020" id="evacuation_reason">
											<option>비응급</option>
										</select>
										<input type="radio" name="state" id="state3" value="3" <%if(patient.getState() == 3) {%> checked="checked" <%} %>>
										<label for="on">귀가</label>
										<input type="radio" name="state" id="state4" value="4" <%if(patient.getState() == 4) {%> checked="checked" <%} %>>
										<label for="on">퇴원</label>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>