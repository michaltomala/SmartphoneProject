<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <%-- wiem że nie można tak robic - w przyszłości bedzie to zmienione !!! --%>
    <style>
        input, select{
            display: block;
            margin:15px 0;
            width: 100%;
        }
    </style>
</head>
<body>

    <a href="${pageContext.request.contextPath}/"><-Strona domowa</a>

    <form:form method="post"
               action="${formAction}"
               modelAttribute="user"
               cssClass="container col-2" >
        <%--<form:errors path="*"  />--%>


        <form:input path="login" placeholder="login" cssClass="form-input"/>
        <form:errors path="login" cssClass="alert alert-danger" element="div" />

        <form:password path="password" placeholder="password" cssClass="form-input"/>
        <form:errors path="password" cssClass="alert alert-danger" element="div" />

        <c:if test="${not empty errDB}">
            <div class="alert alert-danger">${errDB}</div>
        </c:if>

        <input type="submit" value="Zaloguj" class="btn btn-success">
    </form:form>



</body>
</html>

