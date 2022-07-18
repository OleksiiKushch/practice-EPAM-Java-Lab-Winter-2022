<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:choose>
    <c:when test="${not empty error_messages}">
        <c:forEach var="message" items="${error_messages}">
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Error message: </strong>
                ${message}
            </div>
        </c:forEach>
    </c:when>
</c:choose>
