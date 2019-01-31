<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<header class="page-header">--%>
    <%--<a class="nav-item nav-link  navbar-brand main-logo">--%>
    <%--Strona o <span>smartphonach</span>--%>
    <%--</a>--%>

    <%--<nav class="navbar navbar-expand-lg justify-content-around">--%>


        <%--<ul class="nav nounderline text-uppercase col-6">--%>
            <%--<c:if test="${empty user}">--%>
                <%--<li class="nav-item ml-4" >--%>
                    <%--<a class="nav-link color-header" href="/register">Rejestracja</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item ml-4" >--%>
                    <%--<a class="nav-link color-header" href="/login">Zaloguj się</a>--%>
                <%--</li>--%>
            <%--</c:if>--%>
            <%--<c:if test="${not empty user}">--%>
                <%--<li class="nav-item ml-4" >--%>
                    <%--<a class="nav-link color-header" href="/logout">Wyloguj się</a>--%>
                <%--</li>--%>
                <%--<li class="nav-item ml-4" ><a class="nav-link color-header" href="/settings/${user.id}">Ustawienia</li>--%>

                <%--<li class="nav-item ml-4" >Witaj--%>
                    <%--<a class="nav-link color-header" href="/user/${user.id}"> ${user.login}</a>--%>
                <%--</li>--%>
            <%--</c:if>--%>
        <%--</ul>--%>
        <%--<ul class="nav nounderline text-uppercase col-6">--%>
            <%--<c:if test="${not empty user and user.isAdmin == true}">--%>
                <%--<li class="nav-item ml-4" >--%>
                    <%--<a class="nav-link color-header" href="/admin/dashboard">Panel administracyjny</a>--%>
                <%--</li>--%>
            <%--</c:if>--%>


            <%--<li class="nav-item ml-4">--%>
                <%--<a class="nav-link disabled" href="/brand/list">Marki</a>--%>
            <%--</li>--%>

            <%--<li class="nav-item ml-4">--%>
                <%--<a class="nav-link disabled" href="/phone/list">Telefony</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</nav>--%>
<%--</header>--%>

<header>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    <div class="container">
        <div class="row">
            <center><div class="col">
                Strona o smartphonach<br/><br/>
            </div></center>
        </div>
        <div class="row">
            <%-- Tak wiem że tak się nie robi (style inline) !!--%>
            <div class="col" style="float:left;">
                <a class="nav-link disabled" href="/brand/list">Marki</a>
                <a class="nav-link disabled" href="/phone/list">Telefony</a>
            </div>
            <%-- Tak wiem że tak się nie robi (style inline) !!--%>
            <div class="col" style="float:right; ">
                <c:if test="${not empty user and user.isAdmin == true}">
                    <a class="nav-link color-header" href="/admin/dashboard">Panel administracyjny</a>
                </c:if>
                <c:if test="${empty user}">
                    <a class="nav-link color-header" href="/login">Zaloguj się</a>
                    <a class="nav-link color-header" href="/register">Rejestracja</a>
                </c:if>
                <c:if test="${not empty user}">
                    Witaj
                    <a class="nav-link color-header" href="/user/${user.id}"> ${user.login}</a>
                    <a class="nav-link color-header" href="/settings/${user.id}">Ustawienia</a>
                    <a class="nav-link color-header" href="/logout">Wyloguj się</a>
                </c:if>
            </div>
        </div>
    </div>

<br/><br/>
================================================================ kiedyś tu będzie dobrze ostylowany header =======================================================
</header>