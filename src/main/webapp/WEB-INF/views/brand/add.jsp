<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>addBrand</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <style>
        input, select{
            display: block;
            margin:15px 0;
            width: 100%;
    </style>
</head>
<body>

    <p><a href="${pageContext.request.contextPath}/brand/list/"><-Wróć</a></p>


    <form:form method="post"
               action="${formAction}"
               modelAttribute="brand"
               cssClass="container col-2" >
        <form:hidden path="id" />

        <form:input path="name" placeholder="Nazwa" cssClass="form-input"/>
        <form:errors path="name" cssClass="alert alert-danger" element="div" />

        <input type="submit"  class="btn btn-success">
    </form:form>


</body>
</html>
