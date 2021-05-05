<%-- 
    Document   : login.jsp
    Created on : Feb 7, 2021, 10:11:33 AM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <!--===============================================================================================-->
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
        <title>Login Page</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <style>
            body {
                background-image: url('images/login_background.jpg') ; 
                background-repeat: no-repeat; 
                background-size: 100% 100%; 
                background-attachment:  fixed;
                width : 300px; 
                margin: auto
            }
        </style>
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100 p-t-10 p-b-10">
                    <form class="login100-form validate-form" action="login">
                        <span class="login100-form-title p-b-20">
                            Login
                        </span>

                        <div>
                            <a href="http://www.facebook.com/dialog/oauth?client_id=786568652256001&redirect_uri=https://localhost:8080/J3.L.P0015/loginFacebook" class="btn-login-with bg1 m-b-10">
                                <i class="fa fa-facebook-official"></i>
                                Login with Facebook
                            </a>
                        </div>

                        <div class="text-center p-t-30 p-b-30">
                            <span class="txt1">
                                Login with email
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email: ex@abc.xyz">
                            <input class="input100" type="text" name="txtEmail" placeholder="Email" >
                            <span class="focus-input100"></span>
                        </div>
                        <c:if test = "${not empty requestScope.ERROR}">
                            <font color="red">
                            ${requestScope.ERROR}
                            </font><br>
                        </c:if>
                        <div class="wrap-input100 validate-input m-b-20" data-validate = "Please enter password">
                            <span class="btn-show-pass">
                                <i class="fa fa fa-eye"></i>
                            </span>
                            <input class="input100" type="password" name="txtPassword" placeholder="Password">
                            <span class="focus-input100"></span>
                        </div>

                        <div class="g-recaptcha" data-sitekey="6LfT-HQaAAAAALBxlizeLk_ysSSjzQQRoGyyVezb"></div>

                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn">
                                Login
                            </button>
                        </div>

                        <div class="flex-col-c p-t-30">
                            <span class="txt2 p-b-10" style="color: #ffffff">
                                Don’t have an account?
                            </span>

                            <a href="signUpPage" class="txt3 bo1 hov1" style="color: pink">
                                Sign up now
                            </a>
                        </div>

                    </form>
                </div>
            </div>
        </div>


        <script src="js/main.js"></script>

    </body>
</html>
