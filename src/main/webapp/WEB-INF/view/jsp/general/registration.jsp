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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- custom styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/upload.image.css">

    <!-- custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/scripts/validator.general-form.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/reload-captcha.js"></script>
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
                <form name="registrationForm" class="was-validated" action="registration" method="post" enctype="multipart/form-data" onsubmit="" > <!-- return validationRegistrationForm() -->
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
                    <div class="row py-4">
                        <div class="col-lg-8 mx-auto">
                            <!-- Upload image (avatar) input -->
                            <p class="text-center">Set your avatar, upload your desired image.</p>
                            <div class="input-group mb-3 px-2 py-2 border border-success rounded-pill">
                                <input id="upload" type="file" name="${ShopLiterals.AVATAR_IMAGE}" onchange="readURL(this);" class="form-control border-0">
                                <label id="upload-label" for="upload" class="font-weight-light text-muted">Choose file</label>
                                <div id="inputImage" class="input-group-append">
                                    <label for="upload" class="btn btn-outline-primary m-0 rounded-pill px-4"><i class="fa fa-cloud-upload mr-2 text-muted"></i><small class="text-uppercase font-weight-bold text-muted">Choose file</small></label>
                                    <button type="button" class="btn btn-outline-warning m-0 ml-1 rounded-pill px-4"><small class="text-uppercase font-weight-bold text-muted">Clean</small></button>
                                </div>
                            </div>
                            <!-- Uploaded image (avatar) area -->
                            <p class="text-center">The image (avatar) uploaded will be rendered inside the box below.</p>
                            <div class="image-area mt-4">
                                <img id="imageResult" src="#" alt="" class="img-fluid rounded shadow-sm mx-auto d-block">
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <div class="row">
                            <div class="col-sm-4">
                                <span id="captchaContainer">
                                    <captchaProvider:get />
                                </span>
                                <br>
                                <button type="button" onclick="reloadCaptcha()" class="btn btn-link">reload</button>
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
<!-- custom scripts -->
<script src="${pageContext.request.contextPath}/resources/scripts/show.uploaded.image.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/show.uploaded.image.name.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/clean.uploaded.image.js"></script>
</html>