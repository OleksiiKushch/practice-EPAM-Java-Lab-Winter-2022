<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header class="text-dark bg-dark">
    <nav class="navbar navbar-expand-md navbar-dark">
        <a class="navbar-brand" href="#">BookShop</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item" id="home_li">
                    <a class="nav-link" href="${pageContext.request.contextPath}/main">Home</a>
                </li>
                <li class="nav-item" id="product_catalog_li">
                    <a class="nav-link" href="product_catalog.html">Product catalog</a>
                </li>
            </ul>
            <a class="btn btn-outline-warning mx-1 my-sm-0" href="${pageContext.request.contextPath}/registration">Registration</a>
            <a class="btn btn-outline-primary my-sm-0" href="login.html">Log in</a>
        </div>
    </nav>
</header>