<%-- 
    Document   : viewCart
    Created on : Mar 21, 2021, 9:15:44 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                background-repeat: no-repeat;
                background-size: 100%;
                background-image: url(https://64.media.tumblr.com/95e3e9372a403583e51c11ad0820d7f5/tumblr_noxjmqx7zR1uvgn7eo1_500.gifv);
            }
            body{
                background-repeat: no-repeat;
                background-size: 100%;
                background-image: url(https://64.media.tumblr.com/95e3e9372a403583e51c11ad0820d7f5/tumblr_noxjmqx7zR1uvgn7eo1_500.gifv);
            }
            table{
                color: white;
                font-size:30px;
                text-align: center;
            }
            h1{
                text-align: center;
                color: red;
            }
            a{
                color: red;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>

        <c:if test="${sessionScope.USERNAME ne null}">
            <font color="red">
            Welcome ${sessionScope.USERNAME} <br/>
            </font>
            <form action="logout">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </c:if>
        <br/>
        <h1>View Cart</h1>
        <c:url var="urlRewriting" value="searchpage">
            <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
            <c:param name="cboCategory" value="${param.cboCategory}"/>
            <c:param name="cboYear" value="${param.cboYear}"/>
            <c:param name="txtPage" value="${param.txtPage}"/>
        </c:url>
        <a href="${urlRewriting}">Click here to shopping continue</a>
        <c:set var="result" value="${sessionScope.CUSTCART}"/>

        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>CarID</th>
                        <th>Car Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Price</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <form action="RemoveCar">
                    <tbody>
                        <c:set var="total" value="${0}"></c:set>
                        <c:forEach var="itemKey" items="${result}" varStatus="counter">
                            <tr>

                                <td>
                                    ${itemKey.key.carid}
                                </td>
                                <td>
                                    ${itemKey.key.carname}
                                </td>
                                <td>
                                    ${itemKey.value}
                                </td>

                                <td>
                                    ${itemKey.key.price}
                                </td>
                                <td>
                                    ${itemKey.key.price*itemKey.value}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${itemKey.key.carid}" />
                                </td>
                                <c:set var="total" value="${total=total+itemKey.key.price*itemKey.value}"></c:set>
                                </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5">
                                Total of Bill: ${total}
                            </td>
                            <td>
                                <input type="submit" name="btAction" value="Remove Selected Item" />
                            </td>
                        </tr>

                    </tbody>
                </form>
                <form action="checkout">
                    <tr>
                        <td colspan="1">
                            <input type="submit" name="btAction" value="Check out" />
                        </td>
                    </tr>
                </form>
            </table>

        </c:if>
        <br/>
        <c:if test="${sessionScope.OUTOFSTOCKERROR != null}">
            ${sessionScope.OUTOFSTOCKERROR}
        </c:if>

        <c:if test="${requestScope.SUCCESSCHECKOUT eq null}">
            <c:if test="${empty result}">
                <h2>Cart is empty</h2> <br/>
            </c:if>
        </c:if>
        <c:if test="${requestScope.SUCCESSCHECKOUT ne null}">
            ${requestScope.SUCCESSCHECKOUT }
        </c:if>


    </body>
</html>
