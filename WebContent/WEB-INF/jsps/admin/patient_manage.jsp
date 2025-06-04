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
			<jsp:include page="../top_menu.jsp"/>
		</div>
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
								<option>음압(S)</option>
								<option>관찰(A)</option>
								<option>일반(B)</option>
							</select>
							<input type="text" style="width: 120px; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; ">
							<div style="width: 30px; height: 30px; margin-left: 10px; background-color: #67AFBE; border-radius: 5px;">
								<img alt="" src="img/img_search.png" width="30px" height="30px">							
							</div>
						</div>
						<div style="width: 50%">
							<button class="btn_basic_150">환자 일괄 등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
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
									<th>환자번호</th>
									<th>환자명</th>
									<th>성별</th>
									<th>생년월일</th>
									<th>상태</th>
								</tr>
							</thead>
							<tbody>
								<%
									for(int i=0; i<5; i++) {
								%>
								<tr>
									<td><%=i+1 %></td>
									<td>FA000<%=i+1 %></td>
									<td>ㅇㅇㅇ</td>
									<td>남</td>
									<td>980101</td>
									<td>
										A거점
									</td>
								</tr>
								<%} %>
								<%
									for(int i=0; i<5; i++) {
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
						
						<div style="margin-top: 30px;">
							<!-- 페이징처리 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>