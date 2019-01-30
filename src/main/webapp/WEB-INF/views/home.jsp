<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Home</title>
</head>
<body>

    <c:if test="${not empty user}">
        Zalogowany
    </c:if>


    <c:if test="${not empty user and user.isAdmin == true}">
        <a href="/admin/dashboard">Panel administracyjny z warunku</a>
    </c:if>

</body>
</html>
