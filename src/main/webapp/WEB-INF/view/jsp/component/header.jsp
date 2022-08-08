<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="logged_user" value="${sessionScope[ShopLiterals.LOGGED_USER]}" />

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

            <c:if test="${logged_user != null}">
                <ul class="nav navbar-nav">
                    <a class="btn btn-outline-primary mx-1 my-sm-0" href="${pageContext.request.contextPath}/myProfile">My profile</a>
                    <a class="logoutLink btn btn-outline-warning my-sm-0" data-toggle="modal" data-target="#logoutModal" href="">Log out</a>
                </ul>

                <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="logoutModalCenterTitle">Warning!</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <%-- Here a help message (warning) on logout --%>
                                <h6 id="modalBodyWarningMessageLogout"></h6>
                            </div>
                            <div class="modal-footer">
                                <form id="logoutForm" action="logout" method="get">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                    <button type="submit" class="btn btn-primary">Yes</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${logged_user == null}">
                <ul class="nav navbar-nav">
                    <a class="btn btn-outline-warning mx-1 my-sm-0" href="${pageContext.request.contextPath}/registration">Registration</a>
                    <a class="btn btn-outline-primary my-sm-0" href="${pageContext.request.contextPath}/login">Log in</a>
                </ul>
            </c:if>

        </div>
    </nav>
</header>

<script src="${pageContext.request.contextPath}/resources/scripts/logout.alert.js"></script>