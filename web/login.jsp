<%-- 
    Document   : login
    Created on : Mar 13, 2021, 4:22:02 PM
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
            h1{
                text-align: center;
                color: red;
            }
            .logins{
                color: red;
            }
            a{
                color: red;
            }

        </style>
        <title>Car rental</title>
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>
    <body>
        <h1>Login Page</h1>
        <form class="logins" action="login" method="POST" onsubmit="return submitForm()"> 
            <c:set var="invalid" value="${requestScope.INVALID}"/>
            username: <input type="email" name="txtUsername" value="" /> <br/>

            password: <input type="password" name="txtPassword" value="" /> <br/>

            <c:if test="${not empty invalid}">
                <font color ="red">
                ${invalid}

                </font>
                <br/>

            </c:if>
            <jsp:useBean id="recaptcha" class="util.Recaptcha"></jsp:useBean>
                <div 
                    class="g-recaptcha" data-sitekey="${recaptcha.siteKey}"data-callback='onSubmit' data-action='submit'
                ></div>

            <div id="errMsg" style="display: none" class="my-1 text-danger" ><font color="red">Invalid recaptcha, try again!</font></div>

            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="reset" />
        </form>
        <c:if test="${sessionScope.USERNAME eq null && sessionScope.SEARCHRESULT eq null}">
            <a href="signup">Create new account</a> <br/>
        </c:if>
    </body>
    <script>
        let captchaResponse = "";
        function onSubmit(token) {
            if (token.length > 0) {
                document.getElementById("errMsg").style.display = "none";
                captchaResponse = token;
            }
        }
        function submitForm() {
            if (captchaResponse == "") {
                document.getElementById("errMsg").style.display = "block";
                return false;
            }
            return true;
        }

    </script>
</html>
