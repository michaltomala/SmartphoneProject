<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>ListBrands</title>
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


    <c:if test="${not empty user and user.isAdmin == true}">
        <a href="${pageContext.request.contextPath}/admin/dashboard"><-Panel Administracyjny<br/></a>
    </c:if>

    <a href="${pageContext.request.contextPath}/"><-Strona domowa</a>

    <h1>Marka:</h1>

    <c:if test="${not empty user and user.isAdmin == true}">
        <a href="${pageContext.request.contextPath}/admin/brand/add">Dodaj</a>
    </c:if>

    <ul>
        <c:forEach items="${brands}" var="brand">
            <li>
                <h2><a href = "${pageContext.request.contextPath}/brand/list/${brand.id}">${brand.name}</a></h2>
                <p>
                    <c:if test="${not empty user and user.isAdmin == true}">
                    <a href="${pageContext.request.contextPath}/admin/brand/form/${brand.id}">Edytuj</a>
                    <a href="${pageContext.request.contextPath}/admin/brand/delete/${brand.id}">Usuń</a>
                    </c:if>

                </p>
            </li>
        </c:forEach>
    </ul>

    <%@include file="../footer.jsp"%>

</body>
</html>
