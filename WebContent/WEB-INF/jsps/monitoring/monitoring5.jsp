<%@page import="windy.hospital.model.MedicineModel"%>
<%@page import="windy.hospital.model.SuppliesModel"%>
<%@page import="windy.hospital.model.EquipmentModel"%>
<%@page import="windy.hospital.model.EmployeeModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
ArrayList<EmployeeModel> listDoctor = (ArrayList<EmployeeModel>) request.getAttribute("listDoctor");
ArrayList<EmployeeModel> listNurseOn = (ArrayList<EmployeeModel>) request.getAttribute("listNurseOn");
ArrayList<EmployeeModel> listNurseOff = (ArrayList<EmployeeModel>) request.getAttribute("listNurseOff");
ArrayList<EmployeeModel> listOffice = (ArrayList<EmployeeModel>) request.getAttribute("listOffice");

ArrayList<EquipmentModel> listEquipment = (ArrayList<EquipmentModel>) request.getAttribute("listEquipment");
ArrayList<SuppliesModel> listSupplies = (ArrayList<SuppliesModel>) request.getAttribute("listSupplies");
ArrayList<MedicineModel> listMedicine = (ArrayList<MedicineModel>) request.getAttribute("listMedicine");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="js/jquery-3.7.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<jsp:include page="../top_menu.jsp"></jsp:include>
		</div>
		<div style="display: flex;">
			<div>
				<jsp:include page="../side_menu.jsp"></jsp:include>
			</div>
			<div class="wrapper_contents">
				<div class="wrapper_contents_5">
					<span class="span_medical_title">의료진</span>
					<!-- 의사 목록 -->
					<div>
						<table class="tb_docter_title">
							<colgroup>
								<col width="100px">
								<col width="150px">
								<col width="238.48px">
								<col width="240.53px">
								<col width="100px">
								<col width="100px">
							</colgroup>
							<thead>
								<tr>
									<th>의사</th>
									<th>이름</th>
									<th>소속</th>
									<th>전공</th>
									<th>ON/OFF</th>
									<th>지정실</th>
								</tr>
							</thead>
						</table>
						
						<table class="tb_docter_contents">
							<colgroup>
								<col width="100px">
								<col width="150px">
								<col width="238.48px">
								<col width="240.53px">
								<col width="100px">
								<col width="100px">
							</colgroup>
							<tbody>
								<%
									for(int i=0; i<listDoctor.size(); i++) {
										EmployeeModel doctor = listDoctor.get(i);
								%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=doctor.getName() %></td>
									<td><%=doctor.getBelong() %></td>
									<td><%=doctor.getMajor() %></td>
									<td><%=doctor.getOnOff() %></td>
									<td><%=doctor.getRoomName() %></td>
								</tr>
								<%} %>
								<%
									if(listDoctor.size()<6) {
										for(int j=0; j<6-listDoctor.size(); j++) {
								%>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<%	} 
								}%>
							</tbody>
						</table>	
						
					</div>
					
					<!-- 간호사 목록 -->
					<div class="wrapper_contents_nurse">
						<div class="wrapper_tb_nurse">
							<table class="tb_nurse_title">
								<colgroup>
									<col width="100px">
									<col width="117.33px">
									<col width="129.86px">
									<col width="114.94px">
								</colgroup>
								<thead>
									<tr>
										<th>간호</th>
										<th>이름</th>
										<th>근무여부</th>
										<th>지정실</th>
									</tr>
								</thead>
							</table>
							
							<table class="tb_nurse_contents">
								<colgroup>
									<col width="100px">
									<col width="117.33px">
									<col width="129.86px">
									<col width="114.94px">
								</colgroup>
								<tbody>
									<%
									for(int i=0; i<listNurseOn.size(); i++) {
										EmployeeModel nurse = listNurseOn.get(i);
									%>
									<tr>
										<td><%=i+1 %></td>
										<td><%=nurse.getName() %></td>
										<td><%=nurse.getOnOff() %></td>
										<td><%=nurse.getRoomName() %></td>
									</tr>
									<%} %>
									<%
										if(listDoctor.size()<6) {
											for(int j=0; j<6-listDoctor.size(); j++) {
									%>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<%	} 
									}%>
								</tbody>
							</table>
						</div>
						<div class="wrapper_tb_nurse2">
							<table class="tb_nurse_title">
								<colgroup>
									<col width="100px">
									<col width="117.33px">
									<col width="129.86px">
									<col width="114.94px">
								</colgroup>
								<thead>
									<tr>
										<th>간호</th>
										<th>이름</th>
										<th>근무여부</th>
										<th>지정실</th>
									</tr>
								</thead>
							</table>
							
							<table class="tb_nurse_contents">
								<colgroup>
									<col width="100px">
									<col width="117.33px">
									<col width="129.86px">
									<col width="114.94px">
								</colgroup>
								<tbody>
									<%
									for(int i=0; i<listNurseOff.size(); i++) {
										EmployeeModel nurse = listNurseOff.get(i);
									%>
									<tr>
										<td><%=i+1 %></td>
										<td><%=nurse.getName() %></td>
										<td><%=nurse.getOnOff() %></td>
										<td><%=nurse.getRoomName() %></td>
									</tr>
									<%} %>
									<%
										if(listNurseOff.size()<6) {
											for(int j=0; j<6-listNurseOff.size(); j++) {
									%>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<%	} 
									}%>
								</tbody>
							</table>
						</div>
						
					</div>
					
					<!-- 행정 목록 -->
					<div>
						<table class="tb_docter_title">
							<colgroup>
								<col width="100px">
								<col width="231.41px">
								<col width="328.72px">
								<col width="134.92px">
								<col width="133.98px">
							</colgroup>
							<thead>
								<tr>
									<th>행정</th>
									<th>이름</th>
									<th>소속</th>
									<th colspan="2">연락처</th>
								</tr>
							</thead>
						</table>
						
						<table class="tb_docter_contents">
							<colgroup>
								<col width="100px">
								<col width="231.41px">
								<col width="328.72px">
								<col width="134.92px">
								<col width="133.98px">
							</colgroup>
							<tbody>
								<%
								for(int i=0; i<listOffice.size(); i++) {
									EmployeeModel office = listOffice.get(i);
								%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=office.getName() %></td>
									<td><%=office.getBelong() %></td>
									<td colspan="2"><%=office.getTel() %></td>
								</tr>
								<%} %>
								<%
									if(listOffice.size()<6) {
										for(int j=0; j<6-listOffice.size(); j++) {
								%>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td colspan="2"></td>
								</tr>
									<%	} 
								}%>
							</tbody>
						</table>	
						
					</div>
				</div>
				<div class="wrapper_contents_5">
					<span class="span_medical_title">의료 장비·용품</span>
					<!-- 의료 장비 용품 목록 -->
					<div>
						<table class="tb_docter_title">
							<colgroup>
								<col width="100px">
								<col width="278.69px">
								<col width="167.22px">
								<col width="185.8px">
								<col width="197.3px">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>장비명</th>
									<th>입고일</th>
									<th>A/S</th>
									<th>비고</th>
								</tr>
							</thead>
						</table>
						
						<table class="tb_docter_contents">
							<colgroup>
								<col width="100px">
								<col width="278.69px">
								<col width="167.22px">
								<col width="185.8px">
								<col width="197.3px">
							</colgroup>
							<tbody>
								<%
								for(int i=0; i<listEquipment.size(); i++) {
									EquipmentModel equip = listEquipment.get(i);
								%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=equip.getName() %></td>
									<td><%=equip.getDate() %></td>
									<td><%=equip.getAs() %></td>
									<td><%=equip.getNote() %></td>
								</tr>
								<%} %>
								<%
									if(listEquipment.size()<6) {
										for(int j=0; j<6-listEquipment.size(); j++) {
								%>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
									<%	} 
								}%>
							</tbody>
						</table>	
						
					</div>
					<!-- 의료용품 목록 -->
					<div>
						<table class="tb_docter_title">
							<colgroup>
								<col width="100px">
								<col width="234.48px">
								<col width="197.22px">
								<col width="100px">
								<col width="100px">
								<col width="197.3px">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>용품명</th>
									<th>입고일</th>
									<th>입고개수</th>
									<th>잔여량</th>
									<th>비고</th>
								</tr>
							</thead>
						</table>
						
						<table class="tb_docter_contents">
							<colgroup>
								<col width="100px">
								<col width="234.48px">
								<col width="197.22px">
								<col width="100px">
								<col width="100px">
								<col width="197.3px">
							</colgroup>
							<tbody>
								<%
								for(int i=0; i<listMedicine.size(); i++) {
									MedicineModel med = listMedicine.get(i);
								%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=med.getName() %></td>
									<td><%=med.getDate() %></td>
									<td></td>
									<td><%=med.getAmount() %></td>
									<td><%=med.getNote() %></td>
								</tr>
								<%} %>
								<%
									if(listMedicine.size()<6) {
										for(int j=0; j<6-listMedicine.size(); j++) {
								%>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
									<%	} 
								}%>
							</tbody>
						</table>	
						
					</div>
					<!-- 비품 목록 -->
					<div>
						<table class="tb_docter_title">
							<colgroup>
								<col width="100px">
								<col width="234.48px">
								<col width="197.22px">
								<col width="100px">
								<col width="100px">
								<col width="197.3px">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>비품명</th>
									<th>입고일</th>
									<th>입고개수</th>
									<th>잔여량</th>
									<th>비고</th>
								</tr>
							</thead>
						</table>
						
						<table class="tb_docter_contents">
							<colgroup>
								<col width="100px">
								<col width="234.48px">
								<col width="197.22px">
								<col width="100px">
								<col width="100px">
								<col width="197.3px">
							</colgroup>
							<tbody>
								<%
								for(int i=0; i<listSupplies.size(); i++) {
									SuppliesModel sup = listSupplies.get(i);
								%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=sup.getName() %></td>
									<td><%=sup.getDate() %></td>
									<td></td>
									<td><%=sup.getAmount() %></td>
									<td><%=sup.getNote() %></td>
								</tr>
								<%} %>
								<%
									if(listSupplies.size()<6) {
										for(int j=0; j<6-listSupplies.size(); j++) {
								%>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
									<%	} 
								}%>
							</tbody>
						</table>	
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>