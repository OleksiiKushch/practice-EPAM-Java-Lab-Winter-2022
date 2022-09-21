<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"
        integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/js/bootstrap.bundle.min.js"
        integrity="sha512-9GacT4119eY3AcosfWtHMsT5JyZudrexyEVzTBWV3viP/YfB9e2pEy3N7WXL3SV6ASXpTU0vzzSxsbfsuUH4sQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.7.8"></script>

    <!-- custom styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/my.tooltip.css">

    <!-- custom scripts -->
    <script src="${pageContext.request.contextPath}/resources/scripts/put-to-cart.js"></script>
</head>

<c:set var="categories" value="${requestScope[ShopLiterals.PRODUCT_CATEGORIES]}"/>
<c:set var="manufacturers" value="${requestScope[ShopLiterals.PRODUCT_MANUFACTURERS]}"/>
<c:set var="products" value="${requestScope[ShopLiterals.PRODUCTS]}"/>

<c:set var="product_name_pattern" value="${requestScope[ShopLiterals.PRODUCT_FILTER_DATA].namePattern}"/>
<c:set var="product_min_price" value="${requestScope[ShopLiterals.PRODUCT_FILTER_DATA].minPrice}"/>
<c:set var="product_max_price" value="${requestScope[ShopLiterals.PRODUCT_FILTER_DATA].maxPrice}"/>
<c:set var="product_manufacture_ids" value="${requestScope[ShopLiterals.PRODUCT_FILTER_DATA].manufactureIds}"/>
<c:set var="product_category_ids" value="${requestScope[ShopLiterals.PRODUCT_FILTER_DATA].categoryIds}"/>

<c:set var="sorting_parameter" value="${requestScope[ShopLiterals.SORTING_DATA].parameter}"/>
<c:set var="sorting_ordering" value="${requestScope[ShopLiterals.SORTING_DATA].ordering}"/>

<c:set var="number_of_products" value="${requestScope[ShopLiterals.NUMBER_OF_PRODUCTS]}"/>

<c:set var="page_size" value="${requestScope[ShopLiterals.PAGE_PAGINATION_DATA].pageSize}"/>
<c:set var="page_number" value="${requestScope[ShopLiterals.PAGE_PAGINATION_DATA].pageNumber}"/>
<c:set var="number_of_pages" value="${requestScope[ShopLiterals.PAGE_PAGINATION_DATA].numberOfPages}"/>
<c:set var="min_possible_page_number" value="${requestScope[ShopLiterals.PAGE_PAGINATION_DATA].minPossiblePageNumber}"/>
<c:set var="max_possible_page_number" value="${requestScope[ShopLiterals.PAGE_PAGINATION_DATA].maxPossiblePageNumber}"/>

<body>
    <jsp:include page="/WEB-INF/view/jsp/component/header.jsp" />

    <div class="album py-2 bg-light h-95">
        <div class="container">
            <h1 class="my-3">Product catalog page:</h1>
            <hr>
            <div class="row">
                <div class="card col-sm-8">
                    <p class="mt-2">Filtration panel:</p>
                    <form action="productCatalog" method="get">
                        <div class="row">
                            <aside class="col-sm-6">
                                <div class="card border-dark">
                                    <article class="card-group-item">
                                        <header class="card-header"><h6 class="title">Filtering</h6></header>
                                        <div class="filter-content">
                                            <div class="list-group list-group-flush">
                                                <div class="mt-2 mb-2 mx-3">
                                                    <label for="filterByName" class="form-label">Single filter by name:</label>
                                                    <input type="text" class="form-control" id="filterByName" name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}"
                                                        placeholder="I’m looking for" value="${product_name_pattern}"/>
                                                </div>
                                            </div>  <!-- list-group .// -->
                                        </div>
                                        <header class="card-header border-top"><h6 class="title">Range prices</h6></header>
                                        <div class="filter-content">
                                            <div class="card-body">
                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <label for="inputMinPrice">Min Price</label>
                                                        <input type="number" step="0.01" class="form-control" id="inputMinPrice" name="${ShopLiterals.FILTER_MIN_PRICE}"
                                                            placeholder="$0.00" value="${product_min_price}">
                                                    </div>
                                                    <div class="form-group col-md-6 text-right">
                                                        <label for="inputMaxPrice">Max Price</label>
                                                        <input type="number" step="0.01" class="form-control" id="inputMaxPrice" name="${ShopLiterals.FILTER_MAX_PRICE}"
                                                            placeholder="$1,000.00" value="${product_max_price}">
                                                    </div>
                                                </div>
                                            </div> <!-- card-body.// -->
                                        </div>
                                    </article> <!-- card-group-item.// -->
                                </div> <!-- card.// -->
                            </aside> <!-- col.// -->
                            <aside class="col-sm-6">
                                <div class="card border-dark">
                                    <article class="card-group-item">
                                        <header class="card-header border-top"><h6 class="title">Filtering by category</h6></header>
                                        <div class="filter-content">
                                            <div class="card-body">
                                                <c:forEach var="category" items="${categories}">
                                                    <div class="custom-control custom-checkbox">
                                                        <span class="float-right badge badge-light round">${category.count}</span>
                                                        <input type="checkbox" class="custom-control-input" id="checkFilterCategory${category.id}"
                                                            name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${category.id}" ${product_category_ids.contains(category.id) ? 'checked' : ''}>
                                                        <label class="custom-control-label" for="checkFilterCategory${category.id}">${category.name}</label>
                                                    </div> <!-- form-check.// -->
                                                </c:forEach>
                                            </div> <!-- card-body.// -->
                                        </div>
                                        <header class="card-header border-top"><h6 class="title">Filtering by manufacturer</h6></header>
                                        <div class="filter-content">
                                            <div class="card-body">
                                                <c:forEach var="manufacturer" items="${manufacturers}">
                                                    <div class="custom-control custom-checkbox">
                                                        <span class="float-right badge badge-light round">${manufacturer.count}</span>
                                                        <input type="checkbox" class="custom-control-input" id="checkFilterManufacturer${manufacturer.id}"
                                                            name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturer.id}" ${product_manufacture_ids.contains(manufacturer.id) ? 'checked' : ''}>
                                                        <label class="custom-control-label" for="checkFilterManufacturer${manufacturer.id}">${manufacturer.name}</label>
                                                    </div> <!-- form-check.// -->
                                                </c:forEach>
                                            </div> <!-- card-body.// -->
                                        </div>
                                    </article> <!-- card-group-item.// -->
                                </div> <!-- card.// -->
                            </aside> <!-- col.// -->
                            <button type="submit" class="btn btn-primary mb-3 mt-2 ml-3">Submit all</button>
                        </div>
                    </form>
                </div>
                <aside class="col-sm-4">
                    <p>Sorted panel:</p>
                    <div class="card border-dark">
                        <article class="card-group-item">
                            <header class="card-header">
                                <h6 class="title">Sorting</h6>
                            </header>
                            <div class="filter-content">
                                <div class="card-body">
                                    <div class="row mx-1">
                                        <label>Sort by name: </label>
                                        <form action="productCatalog" method="get">
                                            <input name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}" value="${product_name_pattern}" type="hidden">
                                            <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                                            <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                                            <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                                <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturerId}" value="on" type="hidden">
                                            </c:forEach>
                                            <c:forEach var="categoryId" items="${product_category_ids}">
                                                <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${categoryId}" value="on" type="hidden">
                                            </c:forEach>
                                            <input name="${ShopLiterals.PAGE_SIZE}" value="${page_size}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.SORT_PARAMETER}" value="${ShopLiterals.NAME}" type="hidden">
                                            <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${ShopLiterals.SORT_ASCENT}" type="hidden">
                                            <button type="submit" class="btn btn-outline-primary btn-sm ml-2">ascent</button>
                                        </form>
                                        <form action="productCatalog" method="get">
                                        <input name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}" value="${product_name_pattern}" type="hidden">
                                            <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                                            <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                                            <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                                <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturerId}" value="on" type="hidden">
                                            </c:forEach>
                                            <c:forEach var="categoryId" items="${product_category_ids}">
                                                <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${categoryId}" value="on" type="hidden">
                                            </c:forEach>
                                            <input name="${ShopLiterals.PAGE_SIZE}" value="${page_size}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.SORT_PARAMETER}" value="${ShopLiterals.NAME}" type="hidden">
                                            <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${ShopLiterals.SORT_DESCENT}" type="hidden">
                                            <button type="submit" class=" btn btn-outline-warning btn-sm ml-2">descent</button>
                                        </form>
                                    </div>
                                    <div class="row mx-1">
                                        <label>Sort by price: </label>
                                        <form action="productCatalog" method="get">
                                            <input name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}" value="${product_name_pattern}" type="hidden">
                                            <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                                            <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                                            <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                                <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturerId}" value="on" type="hidden">
                                            </c:forEach>
                                            <c:forEach var="categoryId" items="${product_category_ids}">
                                                <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${categoryId}" value="on" type="hidden">
                                            </c:forEach>
                                            <input name="${ShopLiterals.PAGE_SIZE}" value="${page_size}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.SORT_PARAMETER}" value="${ShopLiterals.PRICE}" type="hidden">
                                            <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${ShopLiterals.SORT_ASCENT}" type="hidden">
                                            <button type="submit" class=" btn btn-outline-primary btn-sm ml-2">ascent</button>
                                        </form>
                                        <form action="productCatalog" method="get">
                                            <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                                            <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                                            <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                                <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturerId}" value="on" type="hidden">
                                            </c:forEach>
                                            <c:forEach var="categoryId" items="${product_category_ids}">
                                                <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${categoryId}" value="on" type="hidden">
                                            </c:forEach>
                                            <input name="${ShopLiterals.PAGE_SIZE}" value="${page_size}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number}" type="hidden">
                                            <input name="${ShopLiterals.SORT_PARAMETER}" value="${ShopLiterals.PRICE}" type="hidden">
                                            <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${ShopLiterals.SORT_DESCENT}" type="hidden">
                                            <button type="submit" class=" btn btn-outline-warning btn-sm ml-2">descent</button>
                                        </form>
                                    </div>
                                </div> <!-- card-body.// -->
                            </div>
                        </article> <!-- card-group-item.// -->
                    </div> <!-- card.// -->
                </aside> <!-- col.// -->
            </div>
            <hr>

            <nav class="navbar">
                <ul class="pagination justify-content-end">
                    <li class="page-item">
                        <form action="productCatalog" method="get">
                            <input name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}" value="${product_name_pattern}" type="hidden">
                            <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                            <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                            <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}manufacturerId" value="on" type="hidden">
                            </c:forEach>
                            <c:forEach var="categoryId" items="${product_category_ids}">
                                <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}categoryId" value="on" type="hidden">
                            </c:forEach>
                            <input name="${ShopLiterals.SORT_PARAMETER}" value="${sorting_parameter}" type="hidden">
                            <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${sorting_ordering}" type="hidden">
                            <label for="setPageSize" class="form-label">Set size page:</label>
                            <select class="form-select" name="${ShopLiterals.PAGE_SIZE}" id="setPageSize">
                                <option value="4" ${4 == page_size ? 'selected' : ''}>4</option>
                                <option value="8" ${8 == page_size ? 'selected' : ''}>8</option>
                                <option value="16" ${16 == page_size ? 'selected' : ''}>16</option>
                                <option value="${number_of_products}" ${number_of_products == page_size ? 'selected' : ''}>All (${number_of_products})</option>
                            </select>
                            <button type="submit" class="btn btn-primary btn-sm">Go</button>
                        </form>
                    </li>
                </ul>
                <ul class="pagination justify-content-end">
                    <li class="page-item">
                        <form action="productCatalog" method="get">
                            <input name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}" value="${product_name_pattern}" type="hidden">
                            <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                            <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                            <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturerId}" value="on" type="hidden">
                            </c:forEach>
                            <c:forEach var="categoryId" items="${product_category_ids}">
                                <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${categoryId}" value="on" type="hidden">
                            </c:forEach>
                            <input name="${ShopLiterals.SORT_PARAMETER}" value="${sorting_parameter}" type="hidden">
                            <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${sorting_ordering}" type="hidden">
                            <input name="${ShopLiterals.PAGE_SIZE}" value="${page_size}" type="hidden">
                            <label for="selectPageNumber" class="form-label">select number of page</label>
                            <select class="form-select" name="${ShopLiterals.PAGE_NUMBER}" id="selectPageNumber">
                                <c:forEach begin="1" end="${number_of_pages}" var="page">
                                    <option value="${page}" ${page == page_number ? 'selected' : ''}>${page}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-primary btn-sm">Go</button>
                        </form>
                    </li>
                </ul>
                <ul class="pagination justify-content-end">
                    <c:choose>
                        <c:when test="${page_number - 1 > 0}">
                            <li class="page-item">
                                <form action="productCatalog" method="get">
                                    <input name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}" value="${product_name_pattern}" type="hidden">
                                    <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                                    <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                                    <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                        <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturerId}" value="on" type="hidden">
                                    </c:forEach>
                                    <c:forEach var="categoryId" items="${product_category_ids}">
                                        <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${categoryId}" value="on" type="hidden">
                                    </c:forEach>
                                    <input name="${ShopLiterals.SORT_PARAMETER}" value="${sorting_parameter}" type="hidden">
                                    <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${sorting_ordering}" type="hidden">
                                    <input name="${ShopLiterals.PAGE_SIZE}" value="${page_size}" type="hidden">
                                    <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number - 1}" type="hidden">
                                    <button type="submit" class="page-link">previous</button>
                                </form>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">previous</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="page" begin="${min_possible_page_number}" end="${max_possible_page_number}">
                        <c:choose>
                            <c:when test="${page_number == page}">
                                <li class="page-item disabled">
                                    <a class="page-link">page ${page} of ${number_of_pages}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="#">${page}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${page_number + 1 <= number_of_pages}">
                            <li class="page-item">
                                <form action="productCatalog" method="get">
                                    <input name="${ShopLiterals.FILTER_PRODUCT_NAME_PATTERN}" value="${product_name_pattern}" type="hidden">
                                    <input name="${ShopLiterals.FILTER_MIN_PRICE}" value="${product_min_price}" type="hidden">
                                    <input name="${ShopLiterals.FILTER_MAX_PRICE}" value="${product_max_price}" type="hidden">
                                    <c:forEach var="manufacturerId" items="${product_manufacture_ids}">
                                        <input name="${ShopLiterals.FILTER_MANUFACTURER_PARAMETER_PREFIX}${manufacturerId}" value="on" type="hidden">
                                    </c:forEach>
                                    <c:forEach var="categoryId" items="${product_category_ids}">
                                        <input name="${ShopLiterals.FILTER_CATEGORY_PARAMETER_PREFIX}${categoryId}" value="on" type="hidden">
                                    </c:forEach>
                                    <input name="${ShopLiterals.SORT_PARAMETER}" value="${sorting_parameter}" type="hidden">
                                    <input name="${ShopLiterals.SORT_ORDERING_PARAMETER}" value="${sorting_ordering}" type="hidden">
                                    <input name="${ShopLiterals.PAGE_SIZE}" value="${page_size}" type="hidden">
                                    <input name="${ShopLiterals.PAGE_NUMBER}" value="${page_number + 1}" type="hidden">
                                    <button type="submit" class="page-link">next</button>
                                </form>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">next</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>

            <hr>
            <div class="row my-3" id="container">
                <c:forEach var="product" items="${products}">
                    <div class="col-md-3">
                        <div class="card mb-3 box-shadow">
                            <img class="card-img-top" src="displayProductPicture?imageId=${product.id}">
                            <div class="card-body">
                                <h6 class="card-title">${product.name}</h6>
                                <p>by (manufacturer):<br><a href="#">${product.manufacturer.name}</a></p>
                                <p class="card-text">Price: ${product.price}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-success putToCartButton" data-id="${product.id}"
                                            data-toggle="modal" data-target="#pubToCartModal">Buy</button>
                                        <button type="button" class="btn btn-sm btn-outline-primary">See details</button>
                                    </div>
                                    <button type="button" class="btn btn-link my-tooltip">
                                        <small class="text-muted">Available
                                            <span class="my-tooltip-text">Available immediately</span></small></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${products == null || products.isEmpty()}">
               <h3 class="text-center">No products found!</h3>
            </c:if>
        </div>
    </div>

    <div class="modal fade" id="pubToCartModal" tabindex="-1" role="dialog" aria-labelledby="pubToCartModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="pubToCartModalCenterTitle">Form</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <%-- Here a help message (form) on add product to cart --%>
                    <h6 id="modalBodyPubToCartForm"></h6>
                </div>
                <div class="modal-footer">
                    <form class="was-validated" method="get">
                        <input id="putToCartFormProductId" name="${ShopLiterals.PRODUCT_ID}" value="" type="hidden">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <button type="button" onclick="putToCart()" data-dismiss="modal"
                                class="input-group-text mb-3 btn btn-outline-success">Put to cart</button>
                            </div>
                            <label for="putToCartFormInputAmount"></label>
                            <input type="number" min="1" step="1" class="form-control"
                                   id="putToCartFormInputAmount" name="${ShopLiterals.AMOUNT}" value="" required>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/WEB-INF/view/jsp/component/footer.jsp" />
</body>

<script src="${pageContext.request.contextPath}/resources/scripts/put-to-cart.modal.js"></script>

</html>