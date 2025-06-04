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
					<span class="span_board_title">현장 등록</span>
					<div class="wrapper_board_btn">
						<div style="width: 50%; text-align: left; display: flex;">
							
						</div>
						<div style="width: 50%">
							<button class="btn_basic_100">행추가</button>
							<button class="btn_basic_100">등록</button>
						</div>
					</div>
					<div style="margin-top: 10px;">
						<table class="tb_basic tb_management">
							<colgroup>
								<col width="10%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="20%">
								<col width="10%">
							</colgroup>
							<thead>
								<tr>
									<th rowspan="2">번호</th>
									<th>책임자</th>
									<th>이름</th>
									<th>연락처</th>
									<th>소속</th>
									<th rowspan="2"></th>
								</tr>
								<tr>
									<th colspan="4">비고</th>
								</tr>
							</thead>
							<tbody>
								<%
									for(int i=0; i<5; i++) {
								%>
								<tr>
									<td rowspan="2"><%=i+1 %></td>
									<td>
										<input type="text" style="width: 91%; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; margin-left: 0.6%; margin-right: 0.6%">
									</td>
									<td>
										<input type="text" style="width: 91%; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; margin-left: 0.6%; margin-right: 0.6%">
									</td>
									<td>
										<input type="text" style="width: 91%; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; margin-left: 0.6%; margin-right: 0.6%">
									</td>
									<td>
										<input type="text" style="width: 91%; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; margin-left: 0.6%; margin-right: 0.6%">
									</td>
									<td rowspan="2">
										<button class="btn_red_100">행삭제</button>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<input type="text" style="width: 98%; height: 26px; border: 0.5px solid #74ADBD; border-radius: 3px; margin-left: 10px; padding-left: 5px; padding-right: 5px; margin-left: 0.5%; margin-right: 0.5%">
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
</body>
</html>