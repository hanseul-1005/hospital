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
									for(int i=0; i<20; i++) {
								%>
								<tr>
									<td><%=i+1 %></td>
									<td>홍길동</td>
									<td>전남대학교병원</td>
									<td>정형외과</td>
									<td>ON</td>
									<td>A</td>
								</tr>
								<%} %>
								<%
									for(int i=0; i<15; i++) {
								%>
								<tr>
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
										<th>ON/OFF</th>
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
										for(int i=0; i<20; i++) {
									%>
									<tr>
										<td><%=i+1 %></td>
										<td>김간호</td>
										<td>ON</td>
										<td>A</td>
									</tr>
									<%} %>
									<%
										for(int i=0; i<15; i++) {
									%>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<%} %>
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
										<th>ON/OFF</th>
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
										for(int i=0; i<20; i++) {
									%>
									<tr>
										<td><%=i+1 %></td>
										<td>김간호</td>
										<td>ON</td>
										<td>A</td>
									</tr>
									<%} %>
									<%
										for(int i=0; i<15; i++) {
									%>
									<tr>
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
									<th>ON/OFF</th>
									<th>지정실</th>
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
									for(int i=0; i<20; i++) {
								%>
								<tr>
									<td><%=i+1 %></td>
									<td>홍길동</td>
									<td>전남대학교병원</td>
									<td>ON</td>
									<td>A</td>
								</tr>
								<%} %>
								<%
									for(int i=0; i<15; i++) {
								%>
								<tr>
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
									for(int i=0; i<20; i++) {
								%>
								<tr>
									<td><%=i+1 %></td>
									<td>X-ray</td>
									<td>2024.10.01</td>
									<td>010-1111-1111</td>
									<td></td>
								</tr>
								<%} %>
								<%
									for(int i=0; i<15; i++) {
								%>
								<tr>
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
									for(int i=0; i<20; i++) {
								%>
								<tr>
									<td><%=i+1 %></td>
									<td>주사기</td>
									<td>2024.10.11</td>
									<td>10</td>
									<td>10</td>
									<td></td>
								</tr>
								<%} %>
								<%
									for(int i=0; i<15; i++) {
								%>
								<tr>
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
									for(int i=0; i<20; i++) {
								%>
								<tr>
									<td><%=i+1 %></td>
									<td>주사기</td>
									<td>2024.10.11</td>
									<td>10</td>
									<td>10</td>
									<td></td>
								</tr>
								<%} %>
								<%
									for(int i=0; i<15; i++) {
								%>
								<tr>
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
	</div>
</body>
</html>