<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<c:set var="logged_user" value="${sessionScope[ShopLiterals.LOGGED_USER]}" />

<body>
    <jsp:include page="/WEB-INF/view/jsp/component/header.jsp" />

    <div class="container">
        <h1 class="my-3">My profile page:</h1>
        <hr>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5"
                            width="150px" height="150px" style="object-fit: cover;"
                            src="displayUserAvatar?imageId=${logged_user.id}">
                        <span class="font-weight-bold">${logged_user.firstName}</span>
                        <span class="text-black-50">${logged_user.email}</span>
                    </div>
                </div>
                <div class="col-md-5 border">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Name (first name):</label>
                                <input type="text" class="form-control" value="${logged_user.firstName}" placeholder="first name">
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Surname (last name):</label>
                                <input type="text" class="form-control" value="${logged_user.lastName}" placeholder="last name">
                            </div>
                        </div>
                        <div class="mt-4 col-md-12">
                            <label class="labels">User role:</label>
                            <input type="text" class="form-control" value="${logged_user.role.name}" placeholder="user role" disabled>
                        </div>
                        <div class="mt-4 text-center">
                            <button class="btn btn-primary profile-button" type="button">Save Profile</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/view/jsp/component/footer.jsp" />
</body>

</html>