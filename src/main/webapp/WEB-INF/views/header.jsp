<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="page-header">
    <center><a class="navbar-brand main-logo">
        Strona o <span>smartphonach</span>
    </a></center>

    <nav class="navbar navbar-expand-lg justify-content-around">


        <ul class="nav nounderline text-uppercase col-6">
            <c:if test="${empty user}">
                <li class="nav-item ml-4" >
                    <a class="nav-link color-header" href="/register">Rejestracja</a>
                </li>
                <li class="nav-item ml-4" >
                    <a class="nav-link color-header" href="/login">Zaloguj się</a>
                </li>
            </c:if>
            <c:if test="${not empty user}">
                <li class="nav-item ml-4" >
                    <a class="nav-link color-header" href="/logout">Wyloguj się</a>
                </li>
                <li class="nav-item ml-4" ><a class="nav-link color-header" href="/settings/${user.id}">Ustawienia</li>

                <li class="nav-item ml-4" >Witaj
                    <a class="nav-link color-header" href="/user/${user.id}"> ${user.login}</a>
                </li>
            </c:if>
        </ul>
        <ul class="nav nounderline text-uppercase col-6">
            <c:if test="${not empty user and user.isAdmin == true}">
                <li class="nav-item ml-4" >
                    <a class="nav-link color-header" href="/admin/dashboard">Panel administracyjny</a>
                </li>
            </c:if>
            <li class="nav-item ml-4">
                <a class="nav-link" href="#about">o aplikacji</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="recipes.html">Przepisy</a>
            </li>
            <li class="nav-item ml-4">
                <a class="nav-link disabled" href="#contact">Kontakt</a>
            </li>
        </ul>
    </nav>
</header>
