<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Ustawienia użytkownika</title>
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


    login: ${user.login}
    <a href="${pageContext.request.contextPath}/settings/login/${user.id}">zmień</a><br/>
    <c:if test="${not empty login}">
        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="user"
                   cssClass="container col-2 user-register-form" >
        <%--<form:errors path="*"  />--%>
        <form:hidden path="id" />

        <form:input path="login" placeholder="login" cssClass="form-input"/>

        <input type="submit" value="Zapisz zmiany" class="btn btn-success"><br/>

        <c:if test="${not empty pwdErr}">
            <div class="alert alert-danger">${pwdErr}</div>
        </c:if>

        </form:form>
    </c:if>

    password: ..
    <a href="${pageContext.request.contextPath}/settings/password/${user.id}">zmień</a><br/>
    <c:if test="${not empty password}">
        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="user"
                   cssClass="container col-2 user-register-form" >
        <%--<form:errors path="*"  />--%>
        <form:hidden path="id" />

        <form:password path="password" placeholder="password" cssClass="form-input"/>

        <form:password path="repeatedPassword" placeholder="repeat password" cssClass="form-input"/>

        <input type="submit" value="Zapisz zmiany" class="btn btn-success">

        <c:if test="${not empty pwdErr}">
            <div class="alert alert-danger">${pwdErr}</div>
        </c:if>

        </form:form>
    </c:if>

    email:${user.email}
    <a href="${pageContext.request.contextPath}/settings/email/${user.id}">zmień</a><br/>
    <c:if test="${not empty email}">

        <form:form method="post"
                   action="${formAction}"
                   modelAttribute="user"
                   cssClass="container col-2 user-register-form" >
        <%--<form:errors path="*"  />--%>
        <form:hidden path="id" />

        <form:input path="email" placeholder="email" cssClass="form-input"/>

        <input type="submit" value="Zapisz zmiany" class="btn btn-success"><br/>

        <c:if test="${not empty pwdErr}">
            <div class="alert alert-danger">${pwdErr}</div>
        </c:if>

        </form:form>
    </c:if>



    <%@include file="../footer.jsp"%>

</body>
</html>