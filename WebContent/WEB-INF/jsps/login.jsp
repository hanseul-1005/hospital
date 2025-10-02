<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript">
$(document).ready(function() {
    $("#id").keyup(function(event) {
        if (event.which === 13) {
        	login();
        }
    });
    $("#passwd").keyup(function(event) {
        if (event.which === 13) {
        	login();
        }
    });
    $("#submit").click(function() {
        alert('clicked!');
    })
});
function login() {
	var id = document.getElementById('id').value;
	var pw = document.getElementById('passwd').value;
	
	console.log('id : '+id);
	console.log('pw : '+pw);
	
	var param = "&id="+id+"&passwd="+pw;
	
	$.ajax({
		type: "POST",
		url: "login.windy?mode=login", 
		data: param,
		error: ajaxFailed,
		success: function(ret) {
			console.log('ret : '+ret);
			console.log('result : '+ret.result);
			if(ret.result=='true') {
				if(ret.roll=='관리자') {
					location.href='db.windy?menu=list';
				}
				else if(ret.roll=='행정처') {
					location.href='employee.windy?menu=list';
				}
				else {
					location.href='patient.windy?menu=monitoring';
				}
			} else {
				alert('아이디 혹은 비밀번호가 일치하지않습니다.\n다시 시도해주세요.');
			}
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
		<div class="wrapper_login_contents">
			<div>
				<div class="wrapper_login_logo">
					<img class="img_login_logo" alt="" src="img/img_logo.png" >
				</div>
				
				<div class="wrapper_login" >
					<input class="input_login_info" type="text" id="id" placeholder="아이디" >
					<input class="input_login_info" type="password" id="passwd" placeholder="비밀번호">
					
					<button class="btn_login" type="button" onclick="javascript: login()">로그인</button>
					
					<div class="wrapper_pw_find">
						<span class="span_pw_find">비밀번호 찾기</span>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>