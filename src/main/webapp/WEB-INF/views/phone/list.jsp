<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Smartphones</title>
</head>
<body>

    <c:if test="${not empty user and user.isAdmin == true}">
        <a href="${pageContext.request.contextPath}/admin/dashboard"><-</a>
    </c:if>

    <h1>Smartphones:</h1>

    <c:if test="${not empty user and user.isAdmin == true}">
    <a href="${pageContext.request.contextPath}/admin/phone/add">Dodaj</a>
    </c:if>

    <ul>
        <c:forEach items="${phones}" var="phone">
            <li>
                <h2><a href="${pageContext.request.contextPath}/brand/list/${brand.id}">${phone.brand.name}</a> <a href=${pageContext.request.contextPath}/phone/list/${phone.id}>${phone.name}</a></h2>
                <h4>${phone.price}</h4>
                <p>And the description</p>
                <p>
                    <c:if test="${not empty user and user.isAdmin == true}">
                    <a href="${pageContext.request.contextPath}/admin/phone/form/${phone.id}">Edytuj</a>
                    <a href="${pageContext.request.contextPath}/admin/phone/delete/${phone.id}">Usuń</a>
                    </c:if>
                </p>
            </li>
        </c:forEach>
    </ul>



</body>
</html>
