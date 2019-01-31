<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Dane użytkownika</title>
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
    <a href="${pageContext.request.contextPath}/"><-Strona Główna</a><br/>


    pokazuje Ci tutaj twoje artykuły i ulubione smartphony

    <%@include file="../footer.jsp"%>

</body>
</html>
