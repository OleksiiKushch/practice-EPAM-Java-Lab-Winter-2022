<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>
<%@ page import="com.epam.task15.localization.storage_strategy.LocalizationStorageStrategy" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="current_locale" value="<%= ((LocalizationStorageStrategy) request.getAttribute(ShopLiterals.LOCALIZATION_STORAGE_STRATEGY)).getLocale(request) %>" />
<c:set var="all_locales" value="${requestScope[ShopLiterals.LOCALES]}" />
<c:set var="localization_storage_strategy" value="${requestScope[ShopLiterals.LOCALIZATION_STORAGE_STRATEGY]}" />

<c:set var="logged_user" value="${sessionScope[ShopLiterals.LOGGED_USER]}" />

<c:set var="servlet_name" value="${requestScope[ShopLiterals.SERVLET_NAME]}" />

<%-- set the locale --%>
<fmt:setLocale value="${current_locale}"/>
<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="i18n.messages"/>

<header class="text-dark bg-dark">
    <nav class="navbar navbar-expand-md navbar-dark">
        <a class="navbar-brand" href="#">BookShop</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item" id="home_li">
                    <a class="nav-link" href="${pageContext.request.contextPath}/main"><fmt:message key="header.home"/></a>
                </li>
                <li class="nav-item" id="product_catalog_li">
                    <a class="nav-link" href="${pageContext.request.contextPath}/productCatalog"><fmt:message key="header.product.catalog"/></a>
                </li>
            </ul>

            <ul class="navbar-nav mr-auto">
                <form action="${servlet_name}" method="get" class="mb-0">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button type="submit" class="btn btn-info">
                                <i class="fa fa-language" aria-hidden="true"></i>
                            </button>
                        </div>
                        <select id="changeLocale" name="${ShopLiterals.LOCALE}" class="form-control">
                            <c:forEach var="locale" items="${all_locales}">
                                <option value="${locale.key}" ${locale.key.equals(current_locale) ? 'selected' : ''}>${locale.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </ul>

            <ul class="nav navbar-nav">
                <a class="btn btn-outline-success mx-1 my-sm-0" href="${pageContext.request.contextPath}/my_cart"><fmt:message key="header.my.cart"/></a>

                <c:if test="${logged_user != null}">
                    <a class="btn btn-outline-primary mx-1 my-sm-0" href="${pageContext.request.contextPath}/myProfile"><fmt:message key="header.my.profile"/></a>
                    <a class="logoutLink btn btn-outline-warning mx-1 my-sm-0" data-toggle="modal" data-target="#logoutModal" href=""><fmt:message key="header.log.out"/></a>

                    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="logoutModalCenterTitle"><fmt:message key="header.warning"/></h5>
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
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="header.no"/></button>
                                        <button type="submit" class="btn btn-primary"><fmt:message key="header.yes"/></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${logged_user == null}">
                    <a class="btn btn-outline-warning mx-1 my-sm-0" href="${pageContext.request.contextPath}/registration"><fmt:message key="header.registration"/></a>
                    <a class="btn btn-outline-primary mx-1 my-sm-0" href="${pageContext.request.contextPath}/login"><fmt:message key="header.login"/></a>
                </c:if>
            </ul>

        </div>
    </nav>
</header>

<script src="${pageContext.request.contextPath}/resources/scripts/logout.alert.js"></script>