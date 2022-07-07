<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>
<%@ page import="com.epam.task11.validation.RegexPattern" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="captchaProvider" uri="http://com.epam/task11/tag/CaptchaProvider" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BookShop</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/script/validator.general.form.js"></script>
</head>

<c:set var="saved_email" value="${requestScope[ShopLiterals.REGISTRATION_DATA].email}"/>
<c:set var="saved_first_name" value="${requestScope[ShopLiterals.REGISTRATION_DATA].firstName}"/>
<c:set var="saved_last_name" value="${requestScope[ShopLiterals.REGISTRATION_DATA].lastName}"/>

<c:set var="error_messages" value="${requestScope[ShopLiterals.ERRORS]}"/>

<body>
    <jsp:include page="/WEB-INF/view/jsp/component/header.jsp" />

    <div class="container">
        <h1 class="my-3">Registration page:</h1>
        <div class="card bg-light">
            <div class="card-body">
                <form name="registrationForm" class="was-validated" action="registration" method="post" onsubmit="return validationRegistrationForm()" >
                    <div class="mb-3">
                        <label for="inputEmail" class="form-label">Email address:</label>
                        <input type="email" class="form-control" id="inputEmail" placeholder="email address"
                               name="${ShopLiterals.EMAIL}" <c:if test="${saved_email != null}"> value="${saved_email}" </c:if> required>
                    </div>
                    <div class="mb-3">
                        <label for="inputFirstName" class="form-label">First name:</label>
                        <input type="text" class="form-control" id="inputFirstName" placeholder="first name"
                               name="${ShopLiterals.FIRST_NAME}" maxlength="${RegexPattern.MAX_LENGTH_SHORT_NAME}"
                               <c:if test="${saved_first_name != null}"> value="${saved_first_name}" </c:if> required>
                    </div>
                    <div class="mb-3">
                        <label for="inputLastName" class="form-label">Last name:</label>
                        <input type="text" class="form-control" id="inputLastName" placeholder="last name"
                               name="${ShopLiterals.LAST_NAME}" maxlength="${RegexPattern.MAX_LENGTH_SHORT_NAME}"
                               <c:if test="${saved_last_name != null}"> value="${saved_last_name}" </c:if> required>
                    </div>
                    <div class="mb-3">
                        <label for="inputPassword" class="form-label">Password:</label>
                        <input type="password" class="form-control" id="inputPassword" placeholder="password"
                               name="${ShopLiterals.PASSWORD}" minlength="${RegexPattern.MIN_LENGTH_PASSWORD}" maxlength="${RegexPattern.MAX_LENGTH_PASSWORD}" required>
                    </div>
                    <div class="mb-3">
                        <label for="inputConfirmationPassword" class="form-label">Confirmation password:</label>
                        <input type="password" class="form-control" id="inputConfirmationPassword" placeholder="confirmation password"
                               name="${ShopLiterals.CONFIRMATION_PASSWORD}" minlength="${RegexPattern.MIN_LENGTH_PASSWORD}" maxlength="${RegexPattern.MAX_LENGTH_PASSWORD}" required>
                    </div>
                    <div class="mb-3">
                        <div class="row">
                            <div class="col-sm-4">
                                <captchaProvider:get />
                                <br>
                                <button type="button" class="btn btn-link">reload</button>
                            </div>
                            <div class="col-sm-8">
                                <label for="inputCaptcha" class="form-label">Enter the code from the image here:</label>
                                <input type="text" class="form-control" id="inputCaptcha" placeholder="captcha code"
                                       name="${ShopLiterals.CAPTCHA}" required>
                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary mb-3 ">Registration</button>

                    <div class="mb-1">
                        <label for="noAccount" class="form-label">Have account? Log in:</label>
                        <a href="login.html" class="fw-bold text-body" id="noAccount"><u>here</u></a>
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