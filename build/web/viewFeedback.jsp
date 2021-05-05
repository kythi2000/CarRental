<%-- 
    Document   : viewFeedback
    Created on : Mar 18, 2021, 11:48:56 AM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
    </head>
    <body>
        <c:if test="${not empty requestScope.LISTRATING}">
            <h1>Quality: ${requestScope.POINT}/10 </h1>
            <c:forEach var="rating" items="${requestScope.LISTRATING}">
                Time: ${rating.dateOfCreate} <br>
                ${rating.email} : ${rating.comment} <br>
                ------------------------------------------------------------------------------<br>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.LISTRATING}">
            No one has rated
        </c:if>
    </body>
</html>
