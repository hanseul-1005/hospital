<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
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
			<div class="wrapper_contents" >
				<div class="wrapper_left_contents_6">
					<div class="wrapper_top_tb">
						<table class="tb_patient1">
							<colgroup>
								<col width="33%">
								<col width="34%">
								<col width="33%">
							</colgroup>
							<thead>
								<tr>
									<th>오늘인원</th>
									<th>오늘진료</th>
									<th>오늘후송</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>11</td>
									<td>500</td>
									<td>04</td>
								</tr>
							</tbody>
						</table>
						<table class="tb_patient2">
							<colgroup>
								<col width="50%">
								<col width="50%">
							</colgroup>
							<thead>
								<tr>
									<th>누적 입원</th>
									<th>누적 진료인원</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>38/50</td>
									<td>10,500</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<div>
						<table class="tb_patient3_title">
							<colgroup>
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
							</colgroup>
							<thead>
								<tr>
									<th>음압(S)</th>
									<th colspan="2">관찰(A)</th>
									<th colspan="2">일반(B)</th>
								</tr>
							</thead>
						</table>
						
						<table class="tb_patient3_contents">
							<colgroup>
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
							</colgroup>
							<tbody>
								<%
									for(int i=0; i<20; i++) {
								%>
								<tr>
									<td>김길동 (남, 88세)</td>
									<td>조길동 (남, 51세)</td>
									<td>김철수 (남, 38세)</td>
									<td>김영희 (여, 31세)</td>
									<td>성춘향 (여, 42세)</td>
								</tr>
								<%} %>
								<%-- <%
									for(int i=0; i<15; i++) {
								%>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<%} %> --%>
							</tbody>
						</table>
						
						<table class="tb_patient3_footer">
							<colgroup>
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
								<col width="249.39px">
							</colgroup>
							<thead>
								<tr>
									<td>5</td>
									<td colspan="2">10</td>
									<td colspan="2">10</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="wrapper_right_contents_4" style="">
					<table class="tb_patient4">
						<colgroup>
							<col width="10%">
							<col width="30%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="15%">
							<col width="10%">
						</colgroup>
						<thead>
							<tr>
								<th>No</th>
								<th>병원명</th>
								<th>합계</th>
								<th>긴급</th>
								<th>응급</th>
								<th>비응급</th>
								<th>사망</th>
							</tr>
						</thead>
						<tbody>
							<%
								for(int i=0; i<10; i++) {
							%>
							<tr>
								<td><%=i+1 %></td>
								<td>A병원</td>
								<td>20</td>
								<td>10</td>
								<td>20</td>
								<td>20</td>
								<td>20</td>
							</tr>
							<%} %>
							<%
								for(int i=0; i<4; i++) {
							%>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<%} %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>