<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.epam.task11.constant.ShopLiterals" %>
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
    <jsp:include page="/WEB-INF/view/jsp/component/header.jsp" />

    <p>Admin page</p>

    <jsp:include page="/WEB-INF/view/jsp/component/footer.jsp" />
</body>
</html>