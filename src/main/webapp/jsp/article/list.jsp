<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>

	<h2>게시글 목록</h2>
	
	 <a href="http://localhost:8080/AM_JSP_25_04/article/list" target="_blank">리스트</a>
	 <a href="http://localhost:8080/AM_JSP_25_04/article/detail" target="_blank">상세보기</a>
	  

	<ul>
		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<li><%=articleRow.get("id")%>번, <%=articleRow.get("regDate")%>,
			<%=articleRow.get("title")%>,<%=articleRow.get("body")%>
		</li>
		<%
		}
		%>
	</ul>

	
</body>
</html>