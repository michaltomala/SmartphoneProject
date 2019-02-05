<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Users</title>
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

    <a href="${pageContext.request.contextPath}/admin/dashboard"><-Panel Administratora</a>

    <h1>Users:</h1>


    <ul>
        <c:forEach items="${users}" var="user">
        <li>${user.id}  ${user.login} ${user.email}
            <a href="${pageContext.request.contextPath}/admin/dashboard/user/${user.id}">edytuj</a>
            <a href="${pageContext.request.contextPath}/admin/dashboard/confirm/${user.id}">usuń</a>
            <c:if test="${editingUser.id == user.id}">
                <form:form method="post"
                           action="${formAction}"
                           modelAttribute="editingUser">
                    <form:hidden path="id" />
                    <form:hidden path="password" />

                    <form:input path="login" value="${editingUser.login}" />
                    <form:errors path="login"/>

                    <form:input path="email" value="${editingUser.email}" />
                    <form:errors path="email" />

                    <form:checkbox path="isAdmin" value="${editingUser.isAdmin}" />
                    <input type="submit"  value="Zapisz zmiany" class="btn btn-success">
                </form:form>

            </c:if>
            <c:if test="${not empty confirm and editingUser.id == user.id}">
                Potwierdź usunięcie tego użytkownika!
                <a href="${pageContext.request.contextPath}/admin/dashboard/delete/${user.id}">Tak</a>
                <a href="${pageContext.request.contextPath}/admin/dashboard/user">Nie</a>
            </c:if>

        </li>
        </c:forEach>
    </ul>

    <%@include file="../footer.jsp"%>

</body>
</html>
