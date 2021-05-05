<%-- 
    Document   : viewCart
    Created on : Jan 14, 2021, 2:18:44 PM
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
        <title>Cart</title>
        <style>
            body {
                background-image: url('images/background_cart.jpg') ; 
                background-repeat: no-repeat; 
                background-size: 100% 100%; 
                background-attachment:  fixed;
                width : 300px; 
                margin: auto
            }
        </style>
        <script type="text/javascript">

            function Confirm() {
                var result = confirm("Are you sure <3 <3 <3 ?");
                if (result) {
                    return true;
                } else {
                    return false;
                }
            }

        </script>
    </head>
    <body>
        <h1 style="text-align: center; color: blueviolet">Cart</h1> 
        <h2>${sessionScope.Cart.customerName}</h2>      

        <c:set var="cart" value="${sessionScope.Cart.cart.values()}" />
        <c:if test="${not empty cart}" >   
            Rental Date: ${sessionScope.Cart.rentalDate} <br>
            Return Date: ${sessionScope.Cart.returnDate}   
            <form action="actionCart" method="POST">
                <table class="table" border="1">
                    <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Name</th>
                            <th scope="col">Type</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Price</th>
                            <th scope="col">Delete</th>
                            <th scope="col">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart}" var = "dto" varStatus = "counter" >
                            <tr>
                                <th scope = "row">${counter.count}</th>
                                <td>${dto.name}</td>
                                <td>${dto.category}</td>
                                <td>
                                    <input type="number" name="txtQuantity" value="${dto.quantity}" min="1" max="${dto.maxQuantity}" required/> 
                                    <input type="hidden" name="txtCarID" value="${dto.carID}" />
                                </td>
                                <td>${dto.price}</td>
                                <td>
                                    <input type="checkbox" name="chkRemove" value="${dto.carID}" />
                                </td>   

                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4">Total</td>
                            <td>${sessionScope.Cart.total}</td>
                            <td>
                                <button type="submit" value="Remove" name="btnAction" onclick="return Confirm()" >Remove</button>
                                <input type="hidden" name="txtName" value="${param.txtName}" />
                                <input type="hidden" name="txtCategory" value="${param.txtCategory}" />
                                <input type="hidden" name="maxQuantity" value="${dto.quantity}" />
                                <input type="hidden" name="txtRentalDate" value="${param.txtRentalDate}" />
                                <input type="hidden" name="txtReturnDate" value="${param.txtReturnDate}" />                                        
                                <input type="hidden" name="index" value="${param.index}" />
                            </td>
                            <td>
                                <button type="submit" value="Update" name="btnAction" >Update</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input type="submit" name="btnAction" value="Rent" onclick="return Confirm()"/>
            </form>
        </c:if>

        <c:url var = "urlRewriting" value = "SearchServlet">
            <c:param name="txtName" value="${param.txtName}"/>
            <c:param name="index" value="${param.index}"/>                                
            <c:param name="txtCategory" value="${param.txtCategory}"/>                                
            <c:param name="txtRentalDate" value="${param.txtRentalDate}"/>                                
            <c:param name="txtReturnDate" value="${param.txtReturnDate}"/>                                
        </c:url>
        <a href="${urlRewriting}">Shopping continue</a>
        <font style="color: red">${requestScope.ERROR}</font>
    </body>
</html>
