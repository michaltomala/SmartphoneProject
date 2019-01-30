<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Brands</title>
</head>
<body>

    <a href="${pageContext.request.contextPath}/admin/dashboard"><-</a>

    <h1>Marki:</h1>
    <a href="${pageContext.request.contextPath}/brand/add">Dodaj</a>

    <ul>
        <c:forEach items="${brands}" var="brand">
            <li>
                <h2><a href = "${pageContext.request.contextPath}/brand/list/${brand.id}">${brand.name}</a></h2>
                <p>
                    <a href="${pageContext.request.contextPath}/brand/form/${brand.id}">Edytuj</a>
                    <a href="${pageContext.request.contextPath}/brand/delete/${brand.id}">Usuń</a>
                </p>
            </li>
        </c:forEach>
    </ul>


</body>
</html>
