<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Dashboard</title>
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

    <ul><h1>Panel Administracyjny</h1>
        <li><h2><a href="${pageContext.request.contextPath}/brand/list">Brands</a></h2></li>
        <li><h2><a href="${pageContext.request.contextPath}/phone/list">Phones</a></h2></li>

        <li><h2><a href="${pageContext.request.contextPath}/admin/dashboard/user">Users</a></h2></li>
    </ul>

    <%@include file="../footer.jsp"%>

</body>
</html>
