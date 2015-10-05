<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="header.jsp">
    <c:param name="title" value="Log In"></c:param>
</c:import>

<body>

<div class="container">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <p>Need to create an account? <a href="<c:url value="/signup"></c:url>">Sign Up</a></p>
        </div>
    </div>

    <div class="row">
        <form role="form" class="col-md-4 col-md-offset-4" method="POST">
            <h2>Log In</h2>

            <%-- Error message --%>
            <c:if test="${not empty error_message}">
                <div class="alert alert-danger" role="alert">
                    <p>${requestScope.error_message}</p>
                </div>
            </c:if>

            <div class="form-group">
                <label for="inputUsername"></label>
                <input type="text" class="form-control" id="inputUsername" name="username"
                       placeholder="Enter username">
            </div>
            <div class="form-group">
                <label for="inputPassword"></label>
                <input type="password" class="form-control" id="inputPassword" name="password"
                       placeholder="Enter password">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Log In</button>
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
