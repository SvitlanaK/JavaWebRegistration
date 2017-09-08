<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />

</head>

<body>
   <div class="single-product-area">
     		<div class="container">
                 <c:if test="${errors != null}">
                     <div class="row">
                         <div class="col-md-6 col-md-push-3 col-sm-8 col-sm-push-2 errors">
                             <c:forEach var="error" items="${errors}">
     			                 <p>${error.value}</p>
                             </c:forEach>
                         </div>
                     </div>
                 </c:if>
      <br>
    <div class="container">

        <form action="RegistrationServlet" method="post">
            <legend>
                <center>
                    <h2><b>REGISTRATION FORM</b></h2>
                </center>
            </legend><br>

            <div class="form-group">
                <label class="col-md-4 control-label">First Name</label>
                <input type="text" class="form-control" name="firstName" id="txtUserName" placeholder="Enter First Name" required="required">

            </div>
            <div class="form-group">
                <label class="col-md-4 control-label">Last Name</label>
                <input type="text" class="form-control" name="lastName" id="txtUserName" placeholder="Enter Last Name" required="required"> </div>

            <div class="form-group">
                <label class="col-md-4 control-label">Login</label>
                <input type="text" class="form-control" name="login" id="txtUserName" placeholder="Enter Login" required="required"> </div>

            <div class="form-group">
                <label class="col-md-4 control-label">Password</label>
                <input name="password" placeholder="Password" class="form-control" type="password" maxlength="15" required="required">
                <!-- Text input-->

                <div class="form-group">
                    <label class="col-md-4 control-label">Confirm Password</label>
                    <input name="confirmpassword" placeholder="Password" class="form-control" type="password" maxlength="15" required="required"> </div>


                <div class="form-group">
                    <label class="col-md-4 control-label">E-Mail</label>
                    <input name="email" placeholder="E-Mail Address" class="form-control" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required="required"> </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Gender</label>
                    <input type="radio" name="gender" value="male"> Male
                    &nbsp&nbsp&nbsp&nbsp
                    <input type="radio" name="gender" value="female"> Female
                    <button type="submit" value="register" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block"><b>Login</b></button>

        </form>

        </div>
        </div>

    </div>

    </div>
    </div>
    </div>

</body>

</html>