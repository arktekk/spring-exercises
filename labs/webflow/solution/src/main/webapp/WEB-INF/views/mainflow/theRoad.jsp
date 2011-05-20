<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>The Road</title>
</head>

<body bgcolor="lightblue">

	<div align="center">
		<h2>The Road</h2>
		<img
			src="http://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Middle_Age-road.JPG/800px-Middle_Age-road.JPG"
			width="200" height="200" />

		<p>You see a winding road before you. To the distant north you can
			see a castle on a hill. Around the road is a forest with many small
			paths.</p>

		<br /> <a href="${flowExecutionUrl}&_eventId=goNorth">Go North</a> <br />
		<a href="${flowExecutionUrl}&_eventId=goEast">Go East</a> <br /> <a
			href="${flowExecutionUrl}&_eventId=goWest">Go West</a>

	</div>

</body>
</html>