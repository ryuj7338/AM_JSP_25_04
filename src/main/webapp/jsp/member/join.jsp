<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<h2>회원가입</h2>

	<a href="../home/main">메인으로 이동</a>

	<form action="doJoin" method="post">
		<div>
			아이디 : <input autocomplete="off" type="text" placeholder="아이디 입력" name="loginId" />
		</div>
		<div>
			비밀번호 : <input autocomplete="off" type="password" placeholder="비밀번호 입력" name="loginPw" />
		</div>
		<div>
			비밀번호 확인 : <input autocomplete="off" type="password" placeholder="비밀번호 확인 입력" name="loginPwConfirm" />
		</div>
		<div>
			이름 : <input autocomplete="off" type="text" name="name" />
		</div>
		<button type="submit">가입</button>
	</form>

</body>
</html>