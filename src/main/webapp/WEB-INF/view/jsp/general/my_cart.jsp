<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BookShop</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- custom scripts -->
</head>

<c:set var="error_messages" value="${requestScope[ShopLiterals.ERRORS]}"/>

<c:set var="cart" value="${sessionScope[ShopLiterals.CART]}"/>

<body>
    <jsp:include page="/WEB-INF/view/jsp/component/header.jsp" />

    <div class="container-fluid">
        <h1 class="mt-4">My cart page:</h1>
        <table class="table border rounded">
            <thead class="thead-light">
                <tr>
                    <th scope="col"># (id)</th>
                    <th scope="col">img</th>
                    <th scope="col">name</th>
                    <th scope="col">single price</th>
                    <th scope="col">total price</th>
                    <th scope="col">amount</th>
                    <th scope="col">actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="entryCart" items="${cart.getContent()}">
                    <tr>
                        <th class="col-md-1" scope="row">${entryCart.getKey().id}</th>
                        <th class="col-md-1">
                            <img width="50px" height="75px" style="object-fit: cover;"
                                src="displayProductPicture?imageId=${entryCart.getKey().id}">
                        </th>
                        <td class="col-md-4">${entryCart.getKey().name}</td>
                        <td class="col-md-1">${entryCart.getKey().price}</td>
                        <td class="col-md-1">${entryCart.getKey().price.multiply(entryCart.getValue())}</td>
                        <td class="col-md-1">${entryCart.getValue()}</td>
                        <td class="col-md-1">
                            <a href="" class="editLink" data-amount="${entryCart.getValue()}" data-id="${entryCart.getKey().id}" data-toggle="modal" data-target="#editModal">edit</a>
                            &nbsp &nbsp
                            <a href="" class="deleteLink" data-id="${entryCart.getKey().id}" data-toggle="modal" data-target="#deleteModal">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <nav class="navbar navbar-expand-md navbar-dark">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto"><h4 class="float-left">Total sum: ${cart.getTotalSum()}</h4></ul>
                <ul class="nav navbar-nav">
                    <form action="clean_cart" method="get">
                        <button type="submit" class="btn btn-danger">Clean (delete all)</button>
                    </form>
                </ul>
            </div>
        </nav>
        <div>
            <hr><br>
            <form action="checkout" method="post">
                <aside class="col-sm-8 mb-4">
                    <div class="card border-dark">
                        <article class="card-group-item">
                            <header class="card-header"><h6 class="title"><b>(* optional field *)</b> Input delivery address</h6></header>
                            <div class="filter-content">
                                <div class="list-group list-group-flush">
                                    <div class="mt-2 mb-2 mx-3">
                                        <label for="paymentType" class="form-label"></label>
                                        <input type="text" class="form-control mb-3" name="${ShopLiterals.DELIVERY}" placeholder="Your full address here">
                                    </div>
                                </div>  <!-- list-group .// -->
                            </div>
                        </article> <!-- card-group-item.// -->
                    </div> <!-- card.// -->
                </aside> <!-- col.// -->

                <button type="submit" class="btn btn-primary btn-lg p-2 mx-3">Checkout</button>
            </form>
        </div>
    </div>

    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalCenterTitle">Form:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <%-- Here a help message (form) on editing amount of product in the cart --%>
                    <h6 id="modalBodyEditForm"></h6>
                </div>
                <div class="modal-footer">
                    <form class="was-validated" action="edit_cart" method="get">
                        <input id="editCartFormProductId" name="${ShopLiterals.PRODUCT_ID}" value="" type="hidden">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <button type="submit" class="input-group-text mb-3 btn btn-outline-success">Edit</button>
                            </div>
                            <label for="editCartFormInputAmount"></label>
                            <input type="number" min="1" step="1" class="form-control" id="editCartFormInputAmount" name="${ShopLiterals.AMOUNT}" value="" required>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalCenterTitle">Warning</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <%-- Here a help message (warning) on deleting product in the cart --%>
                    <h6 id="modalBodyDeleteForm"></h6>
                </div>
                <div class="modal-footer">
                    <form action="delete_from_cart" method="get">
                        <input id="deleteFromCartProductId" name="${ShopLiterals.PRODUCT_ID}" value="" type="hidden">
                        <button type="button" data-dismiss="modal" class="btn btn-secondary">No</button>
                        <button type="submit" class="btn btn-primary">Yes</button>
                    </form>
                </div>
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

<script src="${pageContext.request.contextPath}/resources/scripts/edit-cart.modal.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/delete-from-cart.modal.js"></script>

</html>