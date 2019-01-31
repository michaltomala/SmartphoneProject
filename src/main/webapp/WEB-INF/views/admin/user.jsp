<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Users</title>
    <style>
        footer{
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>

    <%@include file="../header.jsp"%>

    <a href="${pageContext.request.contextPath}/admin/dashboard"><-Panel Administratora</a>

    <h1>Users:</h1>

    <a href="${pageContext.request.contextPath}/user/add">Dodaj użytkownika</a>

    <ul>
        <c:forEach items="${users}" var="user">
        <li>${user.id}  ${user.login} ${user.email} edytuj usuń</li>
        </c:forEach>
    </ul>

    <%@include file="../footer.jsp"%>

</body>
</html>
