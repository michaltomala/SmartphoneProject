<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%@include file="../docHeader.jsp"%>
    <%@include file="../header.jsp"%>

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

        <input type="submit"  class="btn btn-success">
    </form:form>


    <%@include file="../footer.jsp"%>

