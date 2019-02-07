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


    <h1>Twoje ulubione smartphony: </h1>

    <ul>
        <c:forEach items="${phones}" var="phone">
            <li>
                <h4>
                    <a href="${pageContext.request.contextPath}/brand/list/${brand.id}">${phone.brand.name}</a>
                    <a href=${pageContext.request.contextPath}/phone/list/${phone.id}>${phone.name}</a>
                        ${phone.price} zł
                    <a href="${pageContext.request.contextPath}/phone/list/${phone.id}"">Pokaż cały opis</a>
                    <a href="${pageContext.request.contextPath}/user/phone/delete/${phone.id}">Usuń</a>
                </h4>
            </li>
        </c:forEach>
    </ul>

    <c:if test="${empty phones}">
        <h3>Nie masz jeszcze ulubionych smartphonów</h3>
    </c:if>

    <%@include file="../footer.jsp"%>

</body>
</html>
