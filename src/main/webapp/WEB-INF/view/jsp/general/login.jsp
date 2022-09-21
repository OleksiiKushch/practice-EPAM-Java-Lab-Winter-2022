<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>
<%@ page import="com.epam.task11.validation.RegexPattern" %>
<%@ page import="com.epam.task15.localization.storage_strategy.LocalizationStorageStrategy" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- set the locale --%>
<fmt:setLocale value="<%= ((LocalizationStorageStrategy) request.getAttribute(ShopLiterals.LOCALIZATION_STORAGE_STRATEGY)).getLocale(request) %>"/>
<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="i18n.messages"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BookShop</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/scripts/show.hide.password.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/validator.general-form.js"></script>
</head>

<c:set var="saved_email" value="${requestScope[ShopLiterals.LOGIN_DATA].email}"/>

<c:set var="error_messages" value="${requestScope[ShopLiterals.ERRORS]}"/>

<body>
    <jsp:include page="/WEB-INF/view/jsp/component/header.jsp" />

    <div class="container">
        <h1 class="my-3"><fmt:message key="login.title.page"/></h1>
        <div class="card bg-light">
            <div class="card-body">
                <form name="loginForm" class="was-validated" action="login" method="post" > <!-- onsubmit="return validationLoginForm()" -->
                    <div class="mb-3">
                        <label for="inputEmail" class="form-label"><fmt:message key="login.title.input.email.address"/></label>
                        <input type="email" class="form-control" id="inputEmail" placeholder='<fmt:message key="login.placeholder.input.email.address"/>'
                               name="${ShopLiterals.EMAIL}" <c:if test="${saved_email != null}"> value="${saved_email}" </c:if> required>
                    </div>
                    <div class="mb-3">
                        <label for="inputPassword" class="form-label"><fmt:message key="login.title.input.password"/></label>
                        <div class="input-group" id="showHidePassword">
                            <input type="password" class="form-control" id="inputPassword" placeholder='<fmt:message key="login.placeholder.input.password"/>'
                                   name="${ShopLiterals.PASSWORD}" minlength="${RegexPattern.MIN_LENGTH_PASSWORD}" maxlength="${RegexPattern.MAX_LENGTH_PASSWORD}" required>
                            <span class="input-group-append">
                            <button type="button" class="btn btn-secondary">
                                <i class="fa fa-eye-slash" aria-hidden="true"></i>
                            </button>
                        </span>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary mb-3 "><fmt:message key="login.sing.in"/></button>

                    <div class="mb-1">
                        <label for="forgotPassword" class="form-label"><fmt:message key="login.forgot.password"/></label>
                        <a href="#" class="fw-bold text-body" id="forgotPassword"><u><fmt:message key="login.here"/></u></a>
                    </div>
                    <div class="mb-1">
                        <label for="noAccount" class="form-label"><fmt:message key="login.no.account"/></label>
                        <a href="${pageContext.request.contextPath}/registration" class="fw-bold text-body" id="noAccount"><u><fmt:message key="login.here"/></u></a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- error message container -->
    <div class="container mt-2 mb-5">
        <div class="card">
            <div class="card-body">
                <%@ include file="/WEB-INF/view/jsp/component/error_messages.jsp" %>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/view/jsp/component/footer.jsp" />
</body>
</html>