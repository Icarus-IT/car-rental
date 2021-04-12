<%-- 
    Document   : signup
    Created on : Mar 14, 2021, 4:40:16 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create New Account</h1>

        <form action="CreateAccount" method="POST">

            Email* <input type="text" name="txtUsername" value="${param.txtUsername}" /> <br/>
            <c:if test="${not empty requestScope.USERNAMEERROR}">
                <font color="red">
                ${requestScope.USERNAMEERROR}
                </font><br/>
            </c:if>
            
            Password* <input type="password" name="txtPassword" value="" /> <br/>
            <c:if test="${not empty requestScope.PASSWORDERROR}">
                <font color="red">
                ${requestScope.PASSWORDERROR}
                </font><br/>
            </c:if>


            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty requestScope.CONFIRMERROR}">
                <font color="red">
                ${requestScope.CONFIRMERROR}
                </font><br/>
            </c:if>

            Phone* <input type="text" name="txtPhone" value="${param.txtPhone}" />  <br/>
            <c:if test="${not empty requestScope.PHONEERROR}">
                <font color="red">
                ${requestScope.PHONEERROR}
                </font><br/>   
            </c:if>

            Name* <input type="text" name="txtName" value="${param.txtName}" />  <br/>
            <c:if test="${not empty requestScope.NAMEERROR}">
                <font color="red">
                ${requestScope.NAMEERROR}
                </font><br/>   
            </c:if>
                
            Address* <input type="text" name="txtAddress" value="${param.txtAddress}" /> <br/>
            <c:if test="${not empty requestScope.ADDRESSERROR}">
                <font color="red">
                ${requestScope.ADDRESSERROR}
                </font><br/>   
            </c:if>
                
                
            <input type="submit" value="Create New Account" name="btnAction" />    
            <input type="reset" value="Reset" />

        </form> <br/>
    <c:if test="${not empty errors.usernameIsExisted}">
        <font color="red">
        ${requestScope.USERNAMEERROR}
        </font>
    </c:if>
  <br/>  <a href="try">Click here to return home page</a>
</body>
</html>
