<%-- 
    Document   : search.jsp
    Created on : Jan 5, 2021, 5:27:52 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <title>Search Page</title>

        <style>
            body {
                background-image: url('images/background.jpg') ; 
                background-repeat: no-repeat; 
                background-size: 100% 100%; 
                background-attachment:  fixed;
                width : 300px; 
                margin: auto
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center; color: blueviolet">Search Page</h1> 
        <c:if test="${empty sessionScope.REGISTRATION}">
            <a href="loginPage">Login</a>
        </c:if>

        <c:if test="${not empty sessionScope.REGISTRATION}">
            <font color = "red">
            Welcome, ${sessionScope.REGISTRATION.fullname} <a href="logout">Logout</a><br>
            </font>
        </c:if>

        <c:if test = "${not empty requestScope.MESS}">
            <font color="green">
            ${requestScope.MESS}
            </font><br>
        </c:if>
        <form action="search" method="POST">
            Name: <input type="text" name="txtName" value="${param.txtName}" /><br>           
            Category: <select name="txtCategory">
                <option>All</option>
                <c:forEach items="${sessionScope.LISTCATEGORY}" var="category">
                    <option>${category}</option>
                </c:forEach>
            </select> <br>
            Rental Date:<input id="rentDate" type="date" name="txtRentalDate" value="${param.txtRentalDate}" required />
            Return Date: <input id="returnDate" type="date" name="txtReturnDate" value="${param.txtReturnDate}" required /><br>
            <input type="submit" value="Search"/>
        </form>
        <c:if test = "${not empty requestScope.ERROR}">
            <font color="red">
            ${requestScope.ERROR}
            </font><br>
        </c:if>

        <c:url var = "urlRewriting" value = "viewCartPage">
            <c:param name="txtName" value="${param.txtName}"/>
            <c:param name="index" value="${param.index}"/>                                
            <c:param name="txtCategory" value="${param.txtCategory}"/>                                
            <c:param name="txtRentalDate" value="${param.txtRentalDate}"/>                                
            <c:param name="txtReturnDate" value="${param.txtReturnDate}"/>                                
        </c:url>
        <c:if test="${sessionScope.REGISTRATION.role eq false}">
            <a href="${urlRewriting}" style="font-size: 20px">Your Cart
            </a><br>

            <a href="history">History Order</a>
        </c:if>


        <c:set var="result" value = "${requestScope.LISTCAR}"/>
        <c:if test = "${not empty result}">
            <table border="1" style="width: 400; align-content: center" class="table table-dark table-hover" style="width: 300px;" >
                <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">Name</th>
                        <th scope="col">Color</th>
                        <th scope="col">Year</th>
                        <th scope="col">Category</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th> 
                        <th scope="col">Feedback</th>                        
                            <c:if test="${sessionScope.REGISTRATION.role eq false and not empty param.txtRentalDate and not empty param.txtReturnDate}">
                            <th scope="col">Action</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "dto" items="${result}" varStatus="counter">
                        <tr>
                            <th scope = "row">
                                ${counter.count}
                            </th>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.color}
                            </td>
                            <td>                                    
                                ${dto.year}
                            </td>
                            <td>                                    
                                ${dto.category}
                            </td>
                            <td>                                    
                                ${dto.price}
                            </td>
                            <td>                                    
                                ${dto.quantity}
                            </td>
                            <td>                                    
                                <form action="getFeedback" method="POST">
                                    <input type="submit" value="View Feedback" />
                                    <input type="hidden" name="txtCarID" value="${dto.carID}" />
                                </form>
                            </td>
                            <c:if test="${sessionScope.REGISTRATION.role eq false and not empty param.txtRentalDate and not empty param.txtReturnDate}">
                                <td>  
                                    <form action="add" method="POST">
                                        <input type="submit" value="Add to cart" />
                                        <input type="hidden" name="txtCarID" value="${dto.carID}" />
                                        <input type="hidden" name="txtName" value="${param.txtName}" />
                                        <input type="hidden" name="txtCategory" value="${param.txtCategory}" />
                                        <input type="hidden" name="maxQuantity" value="${dto.quantity}" />
                                        <input type="hidden" name="txtRentalDate" value="${param.txtRentalDate}" />
                                        <input type="hidden" name="txtReturnDate" value="${param.txtReturnDate}" />                                        
                                        <input type="hidden" name="index" value="${param.index}" />
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>    
                </tbody>
            </table>           


            <c:forEach begin="1" end="${requestScope.endPage}" var="i">
                <a id="${i}" href="SearchServlet?index=${i}&txtName=${param.txtName}&txtRentalDate=${param.txtRentalDate}&txtReturnDate=${param.txtReturnDate}&txtCategory=${param.txtCategory}">${i}</a>
            </c:forEach>


            <c:forEach begin="1" end="${requestScope.endPageAll}" var="i">
                <a id="${i}" href="GetAllCarServlet?index=${i}">${i}</a>
            </c:forEach>  

        </c:if>
        <c:if test = "${empty requestScope.LISTCAR}">
            <h2 color = "violet"> No record is matched</h2>               
        </c:if>

        <script>
            document.getElementById('${param.index}').style.color = "red";
        </script>

        <input id="checkDateCart" type="hidden" value="${requestScope.DATE_ERROR_CART}" />
        <script>
            window.onload = function confirmToDeleteSession() {
                var notification = document.getElementById("checkDateCart").value;
                if (notification != "") {
                    var result = confirm("Date start rent and date end rent are not synchronized. Do you want to remove your cart before add new car");
                    if (result == true) {
                        var xhttp = new XMLHttpRequest();
                        xhttp.open("POST", "removeCart", true);
                        xhttp.send();
                    }
                }
            }
        </script>


        <script>
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1; //January is 0!
            var yyyy = today.getFullYear();
            if (dd < 10) {
                dd = '0' + dd
            }
            if (mm < 10) {
                mm = '0' + mm
            }
            today = yyyy + '-' + mm + '-' + dd;
            document.getElementById("rentDate").setAttribute("min", today);
            document.getElementById("returnDate").setAttribute("min", today);
        </script>
    </body>
</html>
