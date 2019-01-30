<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

    <ul><h1>Panel Administracyjny</h1>
        <li><h2><a href="${pageContext.request.contextPath}/admin/dashboard/brand">Brands</a></h2></li>
        <li><h2><a href="${pageContext.request.contextPath}/admin/dashboard/phone">Phones</a></h2></li>
        <li><h2><a href="${pageContext.request.contextPath}/admin/dashboard/user">Users</a></h2></li>
    </ul>
</body>
</html>
