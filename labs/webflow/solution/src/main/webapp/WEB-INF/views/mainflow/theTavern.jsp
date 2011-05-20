<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>The Tavern</title>
</head>

<body bgcolor="lightblue">

	<div align="center">
		<h2>The Tavern</h2>
		<img
			src="http://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Dickies_Tavern.jpg/643px-Dickies_Tavern.jpg"
			width="200" height="200" />

		<p>Following a path in the forest you find an abandoned building
			that appears to be an old tavern. Its very spooky and stuff.</p>

		<c:if test="${numberOfKeys == null}">
		<p>You find a strange looking key under an old barrel.</p>
		</c:if>

		<br /> <a href="${flowExecutionUrl}&_eventId=goNorth">Go North</a> <br />
		<a href="${flowExecutionUrl}&_eventId=goWest">Go West</a>

	</div>

</body>
</html>