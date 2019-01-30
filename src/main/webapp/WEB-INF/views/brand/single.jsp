<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Single Brand</title>
</head>
<body>

    <h1>${brand.name}</h1>
    <a href="${pageContext.request.contextPath}/phone/add">Dodaj smartphone marki ${brand.name}</a>

    <ul>
        <c:forEach items="${phones}" var="phone">
            <li>
                <h2>${phone.name}</h2>
                <h4>${phone.price}</h4>
                <p>
                    <a href="${pageContext.request.contextPath}/phone/form/${phone.id}">Edytuj</a>
                    <a href="${pageContext.request.contextPath}/phone/delete/${phone.id}">Usuń</a>
                </p>
            </li>
        </c:forEach>
    </ul>

</body>
</html>
