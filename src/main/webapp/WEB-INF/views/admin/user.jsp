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
        <c:forEach items="${users}" var="u">
        <li>${u.id}  ${u.login} ${u.email}
            <a href="${pageContext.request.contextPath}/admin/dashboard/user/${u.id}">edytuj</a>

            <c:if test="${u.id  !=  admin.id}">
                <a href="${pageContext.request.contextPath}/admin/dashboard/confirm/${u.id}">usuń</a>
            </c:if>

            <c:if test="${user.id == u.id}">
                <form:form method="post"
                           action="${formAction}"
                           modelAttribute="user">
                    <form:hidden path="id" />
                    <form:hidden path="password" />

                    <form:input path="login" value="${user.login}" />


                    <form:input path="email" value="${user.email}" />

                    <c:if test="${user.id != 1}">
                        <form:checkbox path="isAdmin" value="${user.isAdmin}" />
                    </c:if>

                    <input type="submit"  value="Zapisz zmiany" class="btn btn-success">

                    <c:if test="${not empty loginErr}">
                        <div class="alert alert-danger">${loginErr}</div>
                    </c:if>
                    <c:if test="${not empty emailErr}">
                        <div class="alert alert-danger">${emailErr}</div>
                    </c:if>

                    <div><form:errors path="login"  /></div>
                    <div><form:errors path="email" /></div>
                </form:form>
            </c:if>
            <c:if test="${not empty confirm and deletingUser.id == u.id}">
                Potwierdź usunięcie tego użytkownika!
                <a href="${pageContext.request.contextPath}/admin/dashboard/delete/${u.id}">Tak</a>
                <a href="${pageContext.request.contextPath}/admin/dashboard/user">Nie</a>
            </c:if>

        </li>
        </c:forEach>
    </ul>

    <%@include file="../footer.jsp"%>

</body>
</html>
