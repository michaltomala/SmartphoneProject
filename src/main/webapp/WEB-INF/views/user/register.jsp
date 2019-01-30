<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <style>
        input, select{
            display: block;
            margin:15px 0;
            width: 100%;
        }
    </style>
    <%--<%@include file="../header.jsp"%>--%>
</head>
<body>

    <form:form method="post"
               action="${formAction}"
               modelAttribute="user"
               cssClass="container col-2 user-register-form" >
        <%--<form:errors path="*"  />--%>
        <form:hidden path="id" />

        <form:input path="login" placeholder="login" cssClass="form-input"/>
        <form:errors path="login" cssClass="alert alert-danger" element="div" />

        <form:password path="password" placeholder="password" cssClass="form-input"/>
        <form:errors path="password" cssClass="alert alert-danger" element="div" />

        <%--Zrobić powtórzenie hasła--%>
        <form:password path="repeatedPassword" placeholder="repeat password" cssClass="form-input"/>
        <form:errors path="repeatedPassword" cssClass="alert alert-danger" element="div" />
        <c:if test="${not empty pwdErr}">
            <div class="alert alert-danger">${pwdErr}</div>
        </c:if>

        <form:input path="email" placeholder="email" cssClass="form-input"/>
        <form:errors path="email" cssClass="alert alert-danger" element="div" />


        <input type="submit"  class="btn btn-success">
    </form:form>


</body>
<%--<%@include file="../footer.jsp"%>--%>
</html>
