<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="analisar">
		<fieldset>
			<legend>Sintomas</legend>
			<c:forEach items="${sintomas}" var="sintoma">
			<label>
				${sintoma.label} <input type="checkbox" value="${sintoma.iri}" name="sintomas"> <br>
			</label>
			</c:forEach>
		</fieldset>
		<fieldset>
			<legend>Situações</legend>
			<c:forEach items="${situacoes}" var="situacao">
			<label>
				${situacao.label} <input type="checkbox" value="${situacao.iri}" name="situacoes"> <br>
			</label>
			</c:forEach>
		</fieldset>
		Tempo com os sintomas: <input name="tempo">
		<br>
		<input type="submit">
	</form>
</body>
</html>