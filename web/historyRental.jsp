<%-- 
    Document   : historyOrder
    Created on : Jan 17, 2021, 2:18:34 PM
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
        <title>History </title>
        <style>
            body {
                background-image: url('images/background_cart.jpg') ; 
                background-repeat: no-repeat; 
                background-size: 100% 100%; 
                background-attachment:  fixed;
                width : 300px; 
                margin: 15px;
            }
        </style>
    </head>
    <body>
        <h1>History</h1>
        <c:if test = "${not empty requestScope.MESS}">
            <font color="blue">
            ${requestScope.MESS}
            </font><br>
        </c:if>
        <a href="getAllCar">Home</a><br>
        Search:<br>
        <form action="history" method="POST">
            Name: <input type="text" name="txtSearchName" value="${param.txtSearchName}"  />  <br>
            Rental Date: <input type="date" name="txtSearchDate" value="${param.txtSearchDate}" />  <br>
            <input type="submit" name="btnAction" value="Search"></input>
        </form>
        <c:set var="result" value = "${sessionScope.HistoryRental}"/>
        <c:if test = "${not empty result}">
            <c:forEach var = "map" items="${result.historyRental}" >
                <table class="table table-success table-striped" border="1" style="width: 400; align-content: center" >
                    <thead>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Name</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Color</th>
                            <th scope="col">Category</th>
                            <th scope="col">Price</th>
                            <th scope="col">Comment</th>
                            <th scope="col">Point</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${map.value}" varStatus="counter">
                            <c:set var="rentalDate" value="${dto.rentalDate}"/>
                            <c:set var="returnDate" value="${dto.returnDate}"/>
                            <c:set var="date" value="${dto.dateOfCreate}"/>
                            <c:set var="total" value="${dto.total}"/>
                            <c:set var="rentalID" value="${dto.rentalID}"/>
                        <form action="rating" method="POST">
                            <tr>
                                <th scope="row">
                                    ${counter.count}
                                </th>
                                <td>
                                    ${dto.carInRental.name}
                                </td>
                                <td>
                                    ${dto.quantity}
                                </td>
                                <td>                                    
                                    ${dto.carInRental.color}
                                </td>
                                <td>                                    
                                    ${dto.carInRental.category}
                                </td>
                                <td>                                    
                                    ${dto.price}
                                </td>
                                <td>
                                    <textarea name="txtComment" rows="5" cols="15">
                                    </textarea>
                                </td>
                                <td>                                    
                                    <input type="number" name="txtPoint" value="" min="0" max="10" required/>
                                    <input type="hidden" name="txtCarID" value="${dto.carInRental.carID}" />
                                    <input type="hidden" name="txtSearchName" value="${param.txtSearchName}"  />  <br>
                                    <input type="hidden" name="txtSearchDate" value="${param.txtSearchDate}"  />  <br>
                                </td>
                                <td>                                    
                                    <input type="submit" value="Rate" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    <tr>
                        <td colspan="5">Total</td>
                        <td>${total}</td>
                    </tr>
                </tbody>
            </table>
            Rental Date ${rentalDate}<br>
            Return Date ${returnDate}<br>
            Date of create ${date}<br>
            <form action="deleteHistory" method="POST">
                <input type="hidden" name="txtRentalID" value="${rentalID}" />
                <input type="submit" value="Delete" name="btnAction"/>
                <input type="hidden" name="txtSearchName" value="${param.txtSearchName}"  />  <br>
                <input type="hidden" name="txtSearchDate" value="${param.txtSearchDate}"  />  <br>
            </form>
            <font style="color: green">--------------------------------------------------------------------</font><br>
        </c:forEach>    
    </c:if>

    <c:if test = "${empty result.historyRental}">
        <h2 color = "blue"> You haven't rented a car yet </h2>  
    </c:if>
</body>
</html>
