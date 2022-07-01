<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BookShop</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
    <jsp:include page="/WEB-INF/view/jsp/component/header.jsp" />

    <div class="card text-center bg-light w-75 my-5 mx-auto">
       <div class="card-body">
           <h4 class="alert-heading">Main page</h4>
           <hr><hr>
           <p class="card-title">Hello world! This is a bookstore.</p>
           <hr>
           <p class="card-text">A simple e-marketplace where you can buy your favorite book.</p>
       </div>
    </div>

    <jsp:include page="/WEB-INF/view/jsp/component/footer.jsp" />
</body>
</html>