<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Calendar calendar = Calendar.getInstance();
Date day = calendar.getTime();

SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY a HH:mm", Locale.KOREAN);

String mainMenuName = (String) request.getAttribute("menu_name");
String subMenuName = (String) request.getAttribute("sub_name");

String menu = (String) request.getParameter("menu");
String mainMenu = (String) request.getAttribute("main_menu");
String subMenu = (String) request.getAttribute("sub_menu");

/* if("region".equals(mainMenu)) {
	mainMenuName = "거점관리";
	if("list".equals(menu)) subMenuName = "거점 목록";
	else if("add".equals(menu)) subMenuName = "거점 등록";
} */

%>
<link rel="stylesheet" type="text/css" href="css/menu.css">
<div class="wrapper_top_container" >
	<div class="wrapper_logo">
		<div>
			<img alt="" src="img/img_logo.png" style="height: 70%; margin: auto 0;">
		</div>
		
		<span class="span_top_menu" ><%=mainMenuName %>&nbsp;&nbsp;>&nbsp;&nbsp;<%=subMenuName %></span>
	</div>
	<div style="background-color: #fff; height: 100%; width: 55%;">
	
	</div>
	<div class="wrapper_date">
		<span class="span_top_date"><%=sdf.format(day) %></span>
		<button class="btn_logout" type="button" onclick="location.href='login.windy'">로그아웃</button>
	</div>
</div>