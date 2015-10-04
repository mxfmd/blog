<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp">
    <c:param name="title" value="Sign Up"/>
</c:import>

<body>

<div class="container">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <p>Already a user? <a href="<c:url value="/login" />">Log In</a></p>
        </div>
    </div>

    <div class="row">

        <form role="form" class="col-md-4 col-md-offset-4" method="post">
            <h2>Sign Up</h2>

            <div class="form-group">

                <%--Error message--%>
                <c:if test="${not empty error_message}">
                    <div class="alert alert-danger" role="alert">
                        <p>${requestScope.error_message}</p>
                    </div>
                </c:if>

                <label for="inputUsername"></label>
                <input type="text" class="form-control" id="inputUsername" name="username" placeholder="Enter username">
            </div>

            <div class="form-group">

                <label for="inputPassword"></label>
                <input type="password" class="form-control" id="inputPassword" name="password"
                       placeholder="Enter password">
            </div>

            <div class="form-group">

                <label for="inputVerify"></label>
                <input type="password" class="form-control" id="inputVerify" name="verify"
                       placeholder="Enter password again">
            </div>

            <div class="form-group">

                <label for="inputEmail"></label>
                <input type="email" class="form-control" id="inputEmail" name="email"
                       placeholder="Enter email (optional)">
            </div>

            <button type="submit" class="btn btn-primary btn-block">Sign Up</button>

        </form>

    </div>


    <div class="row">
        <hr>
        <div class="col-md-12 text-center">
            <a href="<c:url value="/" />"><c:out value="<- back to blog"/></a>
        </div>
    </div>
</div>

</body>
</html>
