<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Transtornos Inferidos</h2>
	<table border="1">
		<c:forEach items="${transtornos}" var="t">
			<tr>
				<td>${t.label}</td>
			</tr>
		</c:forEach>
	</table>
	<h2>Comorbidades Inferidas</h2>
	<table border="1">
		<c:forEach items="${comorbidades}" var="c">
			<tr>

				<td>${c.label}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>