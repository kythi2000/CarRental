<%-- 
    Document   : verifyCode.jsp
    Created on : Mar 7, 2021, 4:33:08 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <style>
            body {
                background-image: url('images/verify.jpg') ; 
                background-repeat: no-repeat; 
                background-size: 100% 100%; 
                background-attachment:  fixed;
                width : 300px; 
                margin: auto
            }
        </style>
        <title>Verify Code Page</title>
    </head>
    <body>
        <h1 style="text-align: center; color: pink">Verify Account</h1>
        <h3 style="text-align: center; color: greenyellow">A verify code is sent to ${param.txtEmail}, enter your code:</h3>
        <form action="verify" method="POST">
            <div class="mb-3">
                <label class="form-label"></label>
                <input type="number" class="form-control"  name="txtCode" value="${param.txtCode}" placeholder="Code" required max="9999">
            </div>
            <c:if test = "${not empty requestScope.ERROR}">
                <font color="red">
                ${requestScope.ERROR}
                </font><br>
            </c:if>
            <input type="submit" value="Submit"/>
            <input type="hidden" name="txtEmail" value="${param.txtEmail}" />
        </form>
    </body>
</html>
