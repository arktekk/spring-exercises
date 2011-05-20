<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body bgcolor="orange">

<div align="center">
<h1>Login to the Auction site</h1>

<form action="<c:url value='/j_spring_security_check'/>" method="post">
    User name:   <input type="text"     name="j_username" size="20"> <br>  
    Password:    <input type="password" name="j_password" size="20"> <br>  
                 <input type="Submit"   value="Login">
</form>
<img src="http://upload.wikimedia.org/wikipedia/commons/3/38/Japanese_stop_sign.svg"/>
</div>

</body>
</html>
