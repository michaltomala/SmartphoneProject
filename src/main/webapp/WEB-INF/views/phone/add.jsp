<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>AddPhone</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <style>
        input, select{
            display: block;
            margin:15px 0;
            width: 100%;
    </style>
</head>
<body>

    <form:form method="post"
               action="${formAction}"
               modelAttribute="phone"
               cssClass="container col-2" >
        <%--<form:errors path="*"  />--%>
        <form:hidden path="id" />

        <form:input path="name" placeholder="nazwa" cssClass="form-input"/>
        <form:errors path="name" cssClass="alert alert-danger" element="div" />

        <form:input path="price" placeholder="cena"  cssClass="form-input"/>
        <form:errors path="price" cssClass="alert alert-danger" element="div"/>

        <form:textarea path="description" cols="12" rows="3" placeholder="opis"  cssClass="form-input"/>
        <form:errors path="description" cssClass="alert alert-danger" element="div"/>

        <form:select path="brand"  cssClass="form-input">
            <form:option value="0" disabled="true" selected="true">Wybierz markę</form:option>
            <form:options  items="${brandsList}"
                           itemValue="id"
                           itemLabel="name" />
        </form:select>
        <form:errors path="brand" cssClass="alert alert-danger" element="div"/>

            <%--<form:options  items="${category}"--%>
                           <%--itemValue="id" />--%>

        <input type="submit"  class="btn btn-success">
    </form:form>

</body>
</html>
