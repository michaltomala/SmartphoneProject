<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Single Phone</title>
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


        <p><a href="${pageContext.request.contextPath}/brand/list/${phone.brand.id}"><-${phone.brand.name}</a></p>

        <p><a href="${pageContext.request.contextPath}/phone/list/"><-Smartphony</a></p>


<h1>${phone.brand.name} ${phone.name}</h1>
    <h2>${phone.price}</h2>
    <p>${phone.description}</p>
    <c:if test="${not empty user and user.isAdmin == true}">
        <a href="${pageContext.request.contextPath}/admin/phone/form/${phone.id}">Edytuj</a>
        <a href="${pageContext.request.contextPath}/admin/phone/delete/${phone.id}">Usuń</a>
    </c:if>

    <%@include file="../footer.jsp"%>

</body>
</html>
