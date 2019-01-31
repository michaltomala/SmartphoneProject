<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Single Brand</title>
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


    <c:if test="${not empty brand}">
        <p><a href="${pageContext.request.contextPath}/brand/list"><-Marki</a></p>
    </c:if>

    <c:if test="${not empty phone}">
        <p><a href="${pageContext.request.contextPath}/phone/list"><-Smartphony</a></p>
    </c:if>

    <h1>Smartphony ${brand.name}</h1>

    <c:if test="${not empty user and user.isAdmin == true}">
        <a href="/admin/brand/addphone/${brand.id}">Dodaj smartphone marki ${brand.name}</a>
    </c:if>

    <ul>
        <c:forEach items="${phones}" var="phone">
            <li>
                <h2>${phone.brand.name} <a href = "${pageContext.request.contextPath}/phone/list/${phone.id}">${phone.name}</a></h2>
                <h4>${phone.price}zł</h4>
                <p>
                    <c:if test="${not empty user and user.isAdmin == true}">
                    <a href="${pageContext.request.contextPath}/admin/phone/form/${phone.id}">Edytuj</a>
                    <a href="${pageContext.request.contextPath}/admin/phone/delete/${phone.id}">Usuń</a>
                    </c:if>
                </p>
            </li>
        </c:forEach>
    </ul>

    <%@include file="../footer.jsp"%>


</body>
</html>
