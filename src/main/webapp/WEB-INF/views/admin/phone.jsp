<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Phones</title>
</head>
<body>

    <c:if test="${not empty user and user.isAdmin == true}">
        <a href="${pageContext.request.contextPath}/admin/dashboard"><-</a>
    </c:if>

    <h1>Smartphones:</h1>

    <a href="${pageContext.request.contextPath}/phone/add">Dodaj</a>

    <ul>
        <c:forEach items="${phones}" var="phone">
            <li>
                <h2><a href="${pageContext.request.contextPath}/brand/list/${brand.id}">${phone.brand.name}</a> <a href=${pageContext.request.contextPath}/phone/list/${phone.id}>${phone.name}</a></h2>
                <h4>${phone.price}</h4>
                <p>And the description</p>
                <p>
                    <a href="${pageContext.request.contextPath}/phone/form/${phone.id}">Edytuj</a>
                    <a href="${pageContext.request.contextPath}/phone/delete/${phone.id}">Usuń</a>
                </p>
            </li>
        </c:forEach>
    </ul>


</body>
</html>
