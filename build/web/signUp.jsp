<%-- 
    Document   : signUp
    Created on : Feb 7, 2021, 9:32:56 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <title>Sign Up</title>
    </head>
    <body style="margin: 15px">
        <h1 style="text-align: center; color: greenyellow">Create New Account</h1>
        <form action="signUp" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERROR}"/>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Email*</span>
                </div>
                <input type="email" name="txtEmail" value="${param.txtEmail}" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required minlength="6" maxlength="30">(6-30 chars)
            </div>
            <c:if test = "${not empty errors.usernameIsExistErr}">
                <font color="red">
                ${errors.usernameIsExistErr}
                </font><br>
            </c:if>


            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Password*</span>
                </div>
                <input type="password" name="txtPassword" value="" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required minlength="6" maxlength="20">(6-20 chars)
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Confirm*</span>
                </div>
                <input type="password" name="txtConfirm" value="" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required>(6-20 chars)
            </div>
            <c:if test = "${not empty errors.confirmErr}">
                <font color="red">
                ${errors.confirmErr}
                </font><br>
            </c:if>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Full name*</span>
                </div>
                <input type="text" name="txtFullname" value="${param.txtFullname}" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required minlength="6" maxlength="30">(6-30 chars)
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Phone*</span>
                </div>
                <input type="number" name="txtPhone" value="${param.txtPhone}" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required minlength="9" maxlength="11">(9-11 numbers)
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Address*</span>
                </div>
                <input type="text" name="txtAddress" value="${param.txtAddress}" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" required minlength="6" maxlength="50">(6-50 chars)
            </div>

            <button type="submit" class="btn btn-outline-primary" value="Sign Up" name="btnAction">Sign Up</button>
            <button type="reset" class="btn btn-dark">Reset</button>
        </form>
    </body>
</html>
