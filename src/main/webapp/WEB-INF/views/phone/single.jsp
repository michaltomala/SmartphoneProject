<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Single Phone</title>
</head>
<body>

    <h1>${phone.brand.name} ${phone.name}</h1>
    <h2>${phone.price}</h2>
    <p>${phone.description}</p>

</body>
</html>
