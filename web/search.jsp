<%-- 
    Document   : search
    Created on : Mar 13, 2021, 9:22:52 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .paging{
                text-align: center;
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
            .searchs{
                color: red;
                font-size: 25px;
            }
            .toLogin{
                font-size: 30px;
            }
        </style>
        <title>Search Page</title>
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
        <h1>Search Page</h1>
        <c:if test="${sessionScope.USERNAME eq null}">
            <a class="toLogin" href="try">Login</a> <br/>
        </c:if>
        <c:if test="${sessionScope.USERNAME eq false}">
            <c:url var="urlRewriting" value="viewyourcart">
                <c:param name="txtSearchValue" value="${param.txtSearchValue}"/>
                <c:param name="cboCategory" value="${param.cboCategory}"/>
                <c:param name="cboYear" value="${param.cboYear}"/>
                <c:param name="txtPage" value="${param.txtPage}"/>

            </c:url>
            <a href="${urlRewriting}">View Cart</a>
        </c:if>

        <form class="searchs" action="search">
            Search Name <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />  
            Category:
            <select name="cboCategory">
                <option  value="All">All</option>
                <c:forEach var="dto" items="${sessionScope.CATEGORYLIST}">
                    <c:if test="${dto eq param.cboCategory}">
                        <option selected="selected" value="${dto}">${dto}</option>
                    </c:if>
                    <c:if test="${dto ne param.cboCategory}">
                        <option  value="${dto}">${dto}</option>
                    </c:if>
                </c:forEach>
            </select>  
            Year:
            <select name="cboYear">
                <option  value="All">All</option>
                <c:forEach var="dto" items="${sessionScope.YEARLIST}">
                    <c:if test="${dto eq param.cboYear}">
                        <option selected="selected" value="${dto}">${dto}</option>
                    </c:if>
                    <c:if test="${dto ne param.cboYear}">
                        <option  value="${dto}">${dto}</option>
                    </c:if>
                </c:forEach>
            </select>  <br/>
            <input type="hidden" name="txtPage" value="1" />
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>



        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:set var="categoryValue" value="${param.cboCategory}"/>
        <c:set var="yearValue" value="${param.cboYear}"/>
        <c:set var="result" value="${sessionScope.SEARCHRESULT}"/>
        <c:if test="${not empty result}">
            <table border="1" width="100%">
                <thead>
                    <tr>
                        <th>Car name</th>
                        <th>Color</th>
                        <th>Year</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Image</th>
                            <c:if test="${sessionScope.ROLE eq false}">
                            <th> Add to cart</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}">
                    <form action="Update">
                        <tr>
                            <td>
                                ${dto.getCarname()}
<!--                                    <input type="hidden" name="txtUserName" value="${dto.carname}" />-->
                            </td>
                            <td>
                                ${dto.getColor()}
<!--                                    <input type="hidden" name="txtPassword" value="${dto.color}" />-->
                            </td>
                            <td>
                                ${dto.getYear()}
<!--                                    <input type="hidden" name="txtSearchValue" value="${param.txtSearchValue}" />-->
                            </td>
                            <td>
                                ${dto.getCategory()}
                            </td>
                            <td>
                                ${dto.getPrice()}
                            </td>
                            <td>
                                ${dto.getQuantiy()}
                            </td>
                            <td>
                                <img src="${dto.image}" style="width:200px; height: 150px;"><br/>
                            </td>
                            <c:if test="${sessionScope.ROLE eq false}">
                                <td>
                                    <c:url var="urlRewriting" value="AddtoCart">
                                        <c:param name="btAction" value="Addtocart"/>
                                        <c:param name="carid" value="${dto.getCarid()}"/>
                                        <c:param name="carname" value="${dto.getCarname()}"/>
                                        <c:param name="price" value="${dto.getPrice()}"/>

                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                        <c:param name="lastcategoryValue" value="${categoryValue}"/>
                                        <c:param name="lastyearValue" value="${yearValue}"/>
                                        <c:param name="txtPage" value="${param.txtPage}"/>

                                    </c:url>
                                    <a href="${urlRewriting}">Select</a>
                                </td>
                            </c:if>

                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>
        <div class="paging">
            <form action="paging">
                <c:if test="${param.txtPage!=1}">
                    <input type="submit" value="BACK" name="btAction" />
                </c:if>

                <input type="text" name="txtPage" value="${param.txtPage}" readonly="readonly" />
                <c:if test="${param.txtPage<sessionScope.PAGEMAX}">
                    <input type="submit" value="NEXT" name="btAction" />
                </c:if>

                <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                <input type="hidden" name="lastcboCategory" value="${param.cboCategory}" />
                <input type="hidden" name="lastcboYear" value="${param.cboYear}" />

            </form>
        </div>


    </c:if>
    <c:if test="${ param.cboCategory ne null}">
        <c:if test="${empty result}">
            <h2> No record is matched</h2>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.USERNAME eq null && sessionScope.SEARCHRESULT eq null}">
        <a href="signup">Create new account</a> <br/>
    </c:if>
</body>
</html>
