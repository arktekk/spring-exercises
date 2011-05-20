<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>The Castle</title>
</head>

<body bgcolor="lightblue">

	<div align="center">
		<h2>The Castle</h2>
		<img
			src="http://upload.wikimedia.org/wikipedia/commons/thumb/3/35/BurgForchtenstein2.jpg/800px-BurgForchtenstein2.jpg"
			width="200" height="200" />

		<p>You stand before a majestic castle with large iron doors. You
			can see a small keyhole in the door.</p>

		<c:if test="${numberOfKeys == 1}">
			<p>The key that you found before looks like it can fit the door.</p>
		</c:if>

		<br /> <a href="${flowExecutionUrl}&_eventId=goSouth">Go South</a> <br />
		<a href="${flowExecutionUrl}&_eventId=enterCastle">Enter Castle</a>

	</div>

</body>
</html>