<%@page import="windy.hospital.model.HospitalModel"%>
<%@page import="windy.hospital.model.PatientModel"%>
<%@page import="windy.hospital.model.RoomModel"%>
<%@page import="windy.hospital.model.VolunteerModel"%>
<%@page import="windy.hospital.model.AmbulanceModel"%>
<%@page import="windy.hospital.model.SiteModel"%>
<%@page import="windy.hospital.model.RegionModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
// 누적 진료
int totalDiagnosis = (int) request.getAttribute("totalDiagnosis");
// 누적 사망
int totalDeath = (int) request.getAttribute("totalDeath");
//오늘 진료
int todayDiagnosis = (int) request.getAttribute("todayDiagnosis");
// 오늘 입원
int todayAdmission = (int) request.getAttribute("todayAdmission");
// 오늘 후송 
int todayEvacuation =(int) request.getAttribute("todayEvacuation");
// 오늘 사망
int todayDeath = (int) request.getAttribute("todayDeath");

ArrayList<RegionModel> listRegion = (ArrayList<RegionModel>) request.getAttribute("listRegion");
ArrayList<SiteModel> listSite = (ArrayList<SiteModel>) request.getAttribute("listSite");
ArrayList<AmbulanceModel> listAmbulance = (ArrayList<AmbulanceModel>) request.getAttribute("listAmbulance");
ArrayList<VolunteerModel> listVolunteer = (ArrayList<VolunteerModel>) request.getAttribute("listVolunteer");

ArrayList<RoomModel> listRoom = (ArrayList<RoomModel>) request.getAttribute("listRoom");

ArrayList<PatientModel> listHospital = (ArrayList<PatientModel>) request.getAttribute("listHospital");

int cntDoctor = (int) request.getAttribute("cntDoctor");
int cntNurse = (int) request.getAttribute("cntNurse");
int cntAdmin = (int) request.getAttribute("cntAdmin");

int totalRoomCnt = 0;
int totalRoomPatientCnt = 0;
for(int i=0; i<listRoom.size(); i++) {
	totalRoomCnt = totalRoomCnt+listRoom.get(i).getCnt();
	totalRoomPatientCnt = totalRoomPatientCnt+listRoom.get(i).getPatientCnt();
}

int totalHospital = 0;
for(int i=0; i<listHospital.size(); i++) {
	totalHospital = totalHospital+listHospital.get(i).getCnt();

}

int cntAmb = 0;
for(int i=0; i<listAmbulance.size(); i++) {
	cntAmb = cntAmb+listAmbulance.get(i).getCnt();
}
int cntVol = 0;
for(int i=0; i<listVolunteer.size(); i++) {
	cntVol = cntVol+listVolunteer.get(i).getCnt();
}
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/jquery-3.7.1.min.js"></script>
<script type ="text/javascript" >
function popOpen(num) {
    var modalBg = $('.modal-bg' + num);
	var modalPop = $('.modal_900_600-wrap' + num);

    modalBg.show();
	modalPop.show();
}

function popClose(num) {
	var modalBg = $('.modal-bg' + num);
    var modalPop = $('.modal_900_600-wrap' + num);
    
	modalBg.hide();
    modalPop.hide();
}

function addRow(type) {
	var num = 0;
	
	if(type=='amb') {
		num = document.getElementById('ambulance_num').value;
	} else {
		num = document.getElementById('volunteer_num').value;
	}
	
	var html = "";
	if(type=='amb') {
		html += "<tr id='tr_ambulance_"+num+"'>";
		html += "<td><input type='text' class='input_text' id='amb_belong_"+num+"' style='height: 30px; width: 90%;'></td>";
		html += "<td><input type='text' class='input_text' id='amb_cnt_"+num+"' style='height: 30px; width: 70%;'> 명</td>";
		html += "<td>";
		html += "<button type='button' class='btn_red_100' onclick='javascript: deleteRow(\"amb\", "+num+")'>행 삭제</button>";
		html += "</td>";
		html += "</tr>";

		$("#tb_ambulance").append(html);
		document.getElementById('ambulance_num').value = parseInt(num)+1;
	} else {
		html += "<tr id='tr_volunteer_"+num+"'>";
		html += "<td><input type='text' class='input_text' id='vol_belong_"+num+"' style='height: 30px; width: 90%;'></td>";
		html += "<td><input type='text' class='input_text' id='vol_cnt_"+num+"' style='height: 30px; width: 70%;'> 명</td>";
		html += "<td>";
		html += "<button type='button' class='btn_red_100' onclick='javascript: deleteRow(\"vol\", "+num+")'>행 삭제</button>";
		html += "</td>";
		html += "</tr>";

		$("#tb_volunteer").append(html);
		document.getElementById('volunteer_num').value = parseInt(num)+1;
	}

}

function deleteRow(type, num) {
	if(type=='amb') {
		$("#tr_ambulance_"+num).remove();
	} else {
		$("#tr_volunteer_"+num).remove();
	}
}

function goAdd(type) {

	if(type=='amb') {
		var num = document.getElementById('ambulance_num').value;
		var size = 0;
		
		var param = "";
		
		for(var i=0; i<num; i++) {

			if(document.getElementById('amb_belong_'+i).value != "" && document.getElementById('amb_cnt_'+i).value != "") {

				var belong = document.getElementById('amb_belong_'+i).value;
				var cnt = document.getElementById('amb_cnt_'+i).value;
				
				param += "&amb_belong_"+size+"="+belong+"&amb_cnt_"+size+"="+cnt;
				
				size = size+1;
				
			}
		}

		param += "&size="+size;
		
		$.ajax({
			type: "post", 
			url: "monitoring.windy?mode=amb_add", 
			data: param,
			async: false, 
			dataType: 'text', 
			error: ajaxFailed,
			success: function(data, textStatus) {
				
				alert("등록되었습니다.");
				location.reload(); 
			}
		});  
	} else {

		var num = document.getElementById('volunteer_num').value;
		var size = 0;
		
		var param = "";
		
		for(var i=0; i<num; i++) {

			if(document.getElementById('vol_belong_'+i).value != "" && document.getElementById('vol_cnt_'+i).value != "") {

				var belong = document.getElementById('vol_belong_'+i).value;
				var cnt = document.getElementById('vol_cnt_'+i).value;
				
				param += "&vol_belong_"+size+"="+belong+"&vol_cnt_"+size+"="+cnt;
				
				size = size+1;
				
			}
		}

		param += "&size="+size;
		
		$.ajax({
			type: "post", 
			url: "monitoring.windy?mode=vol_add", 
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
	
}

function ajaxFailed(xmlRequest)	{
	alert(xmlRequest.status+"\n\r"+xmlRequest.statusText+"\n\r"+xmlRequest.responseText);
}


function side() {
	var sideType = document.getElementById('side_type').value;
	
	if(sideType=='on') {
		document.getElementById('side_type').value = 'off';
		document.getElementById('side_menu').style.display = 'none';
	} else {
		document.getElementById('side_type').value = 'on';
		document.getElementById('side_menu').style.display = '';
	}
}

</script>
<title>Insert title here</title>
</head>
<body>
    <div>
        <div>
			<jsp:include page="../top_menu.jsp"></jsp:include>
		</div>
		
        <div class="div_contents" style="display: flex;">
            <div style="width: 15%; height: 100%; z-index: 1000;" id="side_menu">
			<jsp:include page="../side_menu.jsp"></jsp:include>
            </div>
            <div style="width: 94%; height: 93%; padding-left: 3%; position: absolute;" >
	            <div style="height: 43%; width: 100%; display: flex;">
	                <div class="content1_left_contents">
						
						<img alt="" src="images/map.png" style="width: 100%; height: 100%;">
	                </div>
	
	                <div class="content1_center_contents">
	                    <div style="height: 30%; width: 100%;">
	                        <table class="tb_css center_tb_1">
	                            <colgroup>
	                                <col width="33%">
	                                <col width="33%">
	                                <col width="33%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th>입원</th>
										<th>누적진료</th>
										<th>누적사망</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><%=totalRoomPatientCnt %>/<%=totalRoomCnt %></td>
										<td><%=totalDiagnosis %></td>
										<td><%=totalDeath %></td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	
	                    <div style="height: 20%; width: 100%;">
	                        <table class="tb_css center_tb_2">
	                            <colgroup>
	                                <col width="25%">
	                                <col width="25%">
	                                <col width="25%">
	                                <col width="25%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th>오늘입원</th>
										<th>오늘진료</th>
										<th>오늘후송</th>
	                                    <th>오늘사망</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><%=todayAdmission %></td>
										<td><%=todayDiagnosis %></td>
										<td><%=todayEvacuation %></td>
	                                    <td><%=todayDeath %></td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	
	                    <div style="height: 50%; width: 100%;">
	                        <table class="tb_css center_tb_3">
	                            <colgroup>
	                                <col width="16%">
	                                <col width="16%">
	                                <col width="16%">
	                                <col width="16%">
	                                <col width="16%">
	                                <col width="20%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th colspan="5">거점병원</th>
										<th>누적후송</th>
									</tr>
								</thead>
								<tbody>
									<tr>
	                                    <%
	                                    if(listHospital.size()<6) {
	                                    	for(int i=0; i<listHospital.size(); i++) {%>
	                                    <td><%=listHospital.get(i).getHospitalName() %></td>
	                                    <%	}
	                                    } else { 
											for(int i=0; i<5; i++) {%>
	                                    <td><%=listHospital.get(i).getHospitalName() %></td>
	                                    <%	}
										}%>
	                                    <%for(int i=0; i<5-listHospital.size(); i++) { %>
	                                    <td></td>
	                                    <%} %>
	                                    <td rowspan="2"><%=totalHospital %></td>
									</tr>
	                                <tr>
										<%for(int i=0; i<listHospital.size(); i++) {
											PatientModel patient = listHospital.get(i);
										%>
	                                    <td style="height: 70px;"><%=patient.getCnt() %></td>
	                                    <%} %>
	                                    <%for(int i=0; i<5-listHospital.size(); i++) {%>
	                                    <td></td>
	                                    <%} %>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	                </div>
	
	                <div class="content1_right_contents">
                    	<%
                    	int roomCnt = 0;
                    	for(int i=0; i<listRoom.size(); i++) {
                    		RoomModel room = listRoom.get(i);
                    		roomCnt = roomCnt+1;
                    	%>
	                    <div style="height: 33.3%; width: 100%;">
	                        <table class="tb_css right_tb_<%=i+1%>">
	                            <colgroup>
	                                <col width="100%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th><%=room.getName() %></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><%=room.getPatientCnt() %>/<%=room.getCnt() %></td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	                    <%} %>
	                    
	                    <%if(listRoom.size()<3) {
	                    	for(int i=0; i<3-listRoom.size(); i++) {
	                    		roomCnt = roomCnt+1;
	                    %>
	                    <div style="height: 33.3%; width: 100%;">
	                        <table class="tb_css right_tb_<%=roomCnt %>">
	                            <colgroup>
	                                <col width="100%">
	                            </colgroup>
	                            <thead>
									<tr>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td></td>
									</tr>
								</tbody>
	                        </table>
	                    </div>
	                    <%	} 
	                    }%>
	                </div>
	            </div>
	
	            <div style="height: 7%; width: 100%; display: flex; align-items: center;">
	                <span style="margin-left: 20px; font-size: 30px; font-weight: bold; color: #496E73;"></span>
	            </div>
	
	            <div style="height: 43%; width: 100%; display: flex;">
	                <div class="content2_left_contents">
	                    <table class="tb_css tb2_left">
	                        <colgroup>
	                            <col width="50%">
	                            <col width="50%">
	                        </colgroup>
	                        <thead>
	                            <tr>
	                                <th colspan="2">중앙통제실/지역</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        	<%if(listRegion.size() > 8) {
	                        		for(int i=0; i<8; i++) {
		                        		RegionModel region = listRegion.get(i);
	                        	%>
	                        	<tr>
	                                <td><%=region.getName() %></td>
	                                <td><%=region.getTel() %></td>
	                            </tr>
	                        	<%
	                        		}
                        		} else { 
                        			for(int i=0; i<listRegion.size(); i++) {
		                        		RegionModel region = listRegion.get(i);
                        		%>
                        		<tr>
	                                <td><%=region.getName() %></td>
	                                <td><%=region.getTel() %></td>
	                            </tr>
	                        	<%	} %>
	                        	  <%for(int i=0; i<8-listRegion.size(); i++) {%>
	                            <tr>
	                            	<td></td>
	                            	<td></td>
	                            </tr>
	                            <%} %>
	                            <%} %>
	                           
	                        </tbody>
	                    </table>
	                </div>
	
	                <div class="content2_center_contents">
	                    <table class="tb_css tb2_center">
	                        <colgroup>
	                            <col width="10%">
	                            <col width="20%">
	                            <col width="20%">
	                            <col width="25%">
	                            <col width="25%">
	                        </colgroup>
	                        <thead>
	                            <tr>
	                                <th>NO</th>
	                                <th>책임자</th>
	                                <th>이름</th>
	                                <th>연락처</th>
	                                <th>소속</th>
	                            </tr>
	                            <%
	                            if(listSite.size()>8) {
	                            
	                            %>
	                            <%for(int i=0; i<8; i++) {
	                            	SiteModel site = listSite.get(i);
	                            %>
	                            <tr>
	                                <td><%=i+1 %></td>
	                                <td><%=site.getName() %></td>
	                                <td><%=site.getPersonName() %></td>
	                                <td><%=site.getTel() %></td>
	                                <td><%=site.getBelong() %></td>
	                            </tr>
	                            <%	} 
	                            } else {
	                            	for(int i=0; i<listSite.size(); i++) {
		                            	SiteModel site = listSite.get(i);
	                            %>
	                            <tr>
	                                <td><%=i+1 %></td>
	                                <td><%=site.getName() %></td>
	                                <td><%=site.getPersonName() %></td>
	                                <td><%=site.getTel() %></td>
	                                <td><%=site.getBelong() %></td>
	                            </tr>
	                            <%} %>
	                            <%for(int i=0; i<8-listSite.size(); i++) {%>
	                            <tr>
	                            	<td></td>
	                            	<td></td>
	                            	<td></td>
	                            	<td></td>
	                            	<td></td>
	                            </tr>
	                            <%} 
	                            }%>
	                        </thead>
	                    </table>
	                </div>
	
	                <div class="content2_right_contents">
	                    <div style="width: 100%; height: 80%;">
	                        <table class="tb_css tb2_right">
	                            <colgroup>
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                                <col width="">
	                            </colgroup>
	                            <thead>
	                                <tr>
	                                    <th>분류</th>
	                                    <th>전체</th>
	                                    <th>사용량</th>
	                                    <th>잔여량</th>
	                                    <th>S소계</th>
	                                    <th>A소계</th>
	                                    <th>B소계</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <tr>
	                                    <th>청수</th>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                </tr>
	                                <tr>
	                                    <th>폐수</th>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                    <td>0L</td>
	                                </tr>
	                                <tr>
	                                    <th>전력</th>
	                                    <td>0kw</td>
	                                    <td>0kw</td>
	                                    <td>0kw</td>
	                                    <td>0kw</td>
	                                    <td>0kw</td>
	                                    <td>0kw</td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </div>
	
	                    <div style="width: 100%; height: 20%; display: flex;">
	                        <table class="tb_css_under">
	                            <colgroup>
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                                <col widtd="">
	                            </colgroup>
	                            <tbody>
	                                <tr>
	                                    <td>의사:<%=cntDoctor %></td>
	                                    <td>간호사:<%=cntNurse %></td>
	                                    <td>행정:<%=cntAdmin %></td>
	                                    <td onclick="javascript:popOpen('1')">구급대:<%=cntAmb %></td>
	                                    <td onclick="javascript:popOpen('2')">봉사:<%=cntVol %></td>
	                                    <td>합계:<%=cntDoctor+cntNurse+cntAdmin+cntAmb+cntVol %></td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
	            </div>
	
	            <div style="height: 7%; width: 100%; display: flex; justify-content: center; align-items: center;">
	                <div style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center; background-color: #E2EBED; padding-left: 50px; margin-top: 20px;">
	                    <table class="tb_css_footer">
	                        <colgroup>
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                            <col width="">
	                        </colgroup>
	                        <tbody>
	                            <tr>
	                            	<%
	                            	for(int i=0; i<listHospital.size(); i++) {
	                            	%>
	                                <td><%=listHospital.get(i).getHospitalName() %>:<%=listHospital.get(i).getCnt() %>/<%=listHospital.get(i).getHospitalCnt() %></td>
	                                <%} %>
	                            </tr>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
            </div>
        </div>
    </div>
    
	<input type="hidden" id="ambulance_num" value="<%=listAmbulance.size()+1%>"/>
	<input type="hidden" id="volunteer_num" value="<%=listVolunteer.size()+1%>"/>
	<div class="modal-bg1" onClick="javascript:popClose('1');"></div>
	<div class="modal_900_600-wrap1">
		<div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">구급대 관리</span>
				</div>
				<div class="wrapper_popup_top_right">
					<img alt="" src="images/img_close.png" width="39px" height="39px" onClick="javascript:popClose('1');">
				</div>
			</div>
			<div style="text-align: right; width: 80%; margin-left: 10%;">
				<button class="btn_basic_150" onclick="javascript: addRow('amb')">행 추가</button>
				<button class="btn_basic_150" onclick="javascript: goAdd('amb')">일괄 등록</button>
			</div>
			<div class="wrapper_popup" style="margin-top: 30px;">
				<table class="tb_css" id="tb_ambulance">
					<colgroup>
						<col width="50%">
						<col width="25%">
						<col winth="25%">
					</colgroup>
					<thead>
						<tr height="40">
							<th>소속</th>
							<th>인원</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<%if(listAmbulance.size() == 0) {%>
					<tr height="40" id="tr_ambulance_0">
						<td><input type="text" class="input_text" id="amb_belong_0" style="height: 30px; width: 90%;" ></td>
						<td><input type="text" class="input_text" id="amb_cnt_0" style="height: 30px; width: 70%;"> 명</td>
						<td>
                            
						</td>
					</tr>
					<%} else { 
						for(int i=0; i<listAmbulance.size(); i++) {
							AmbulanceModel ambulance = listAmbulance.get(i);
					%>
					<tr height="40" id="tr_ambulance_<%=i %>">
						<td><input type="text" class="input_text" style="height: 30px; width: 90%;" id="amb_belong_<%=i %>" value="<%=ambulance.getBelong() %>"></td>
						<td><input type="text" class="input_text" style="height: 30px; width: 70%;" id="amb_cnt_<%=i %>" value="<%=ambulance.getCnt() %>"> 명</td>
						<td>
						<%if(i>0) { %>
                            <button class="btn_red_100" onclick="javascript: deleteRow('amb', <%=i %>)">행 삭제</button>
                        <%} %>
						</td>
					</tr>
					<%	}
					} %>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div class="modal-bg2" onClick="javascript:popClose('2');"></div>
	<div class="modal_900_600-wrap2">
		<div class="wrapper_popup_contents">
			<div class="wrapper_popup_top">
				<div class="wrapper_popup_top_center">
					<span class="span_popup_title">봉사대 관리</span>
				</div>
				<div class="wrapper_popup_top_right">
					<img alt="" src="images/img_close.png" width="39px" height="39px" onClick="javascript:popClose('2');">
				</div>
			</div>
			<div style="text-align: right; width: 80%; margin-left: 10%;">
				<button class="btn_basic_150" onclick="javascript: addRow('vol')">행 추가</button>
				<button class="btn_basic_150" onclick="javascript: goAdd('vol')">일괄 등록</button>
			</div>
			<div class="wrapper_popup" style="margin-top: 30px;">
				<table class="tb_css" id="tb_volunteer">
					<colgroup>
						<col width="50%">
						<col width="25%">
						<col winth="25%">
					</colgroup>
					<tr height="40">
						<th>소속</th>
						<th>인원</th>
						<th></th>
					</tr>
					<%if(listVolunteer.size() == 0) {%>
					<tr height="40">
						<td><input type="text" class="input_text" style="height: 30px; width: 90%;" id="vol_belong_0"></td>
						<td><input type="text" class="input_text" style="height: 30px; width: 70%;" id="vol_cnt_0"> 명</td>
						<td>
                            
						</td>
					</tr>
					<%} else { 
						for(int i=0; i<listVolunteer.size(); i++) {
							VolunteerModel volunteer = listVolunteer.get(i);
					%>
					<tr height="40">
						<td><input type="text" class="input_text" style="height: 30px; width: 90%;" id="vol_belong_<%=i %>" value="<%=volunteer.getBelong() %>"></td>
						<td><input type="text" class="input_text" style="height: 30px; width: 70%;" id="vol_cnt_<%=i %>" value="<%=volunteer.getCnt() %>"> 명</td>
						<td>
						<%if(i>0) { %>
                            <button class="btn_red_100">행 삭제</button>
                      	<%} %>
						</td>
					</tr>
					<%}
					} %>
				</table>
			</div>
		</div>
	</div>

</body>
</html>