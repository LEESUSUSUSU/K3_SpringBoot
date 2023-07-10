<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String data = (String)request.getAttribute("data");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Model</title>
</head>
<body>
	<h1>Model</h1>
	<h2>${data}</h2>
	
</body>
</html>