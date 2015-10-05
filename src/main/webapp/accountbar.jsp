<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="accountbar">
    <div class="col-md-12">
        <p class="text align-left">
            <c:choose>
                <c:when test="${not empty username}">
                    Welcome, <c:out value="${username}"/>! <a href="<c:url value="/logout" />">Log
                    Out</a> | <a
                        href="<c:url value="/newpost" />">New Post</a>
                </c:when>
                <c:otherwise>
                    Have something to say? <a href="<c:url value="/login" />">Log In</a> | <a
                        href="<c:url value="/signup" />">Sign Up</a>
                </c:otherwise>
            </c:choose>
        </p>
    </div>
</div>

<div class="row" id="title">
    <div class="col-md-12">
        <div class="page-header">
            <h1><a href="<c:url value="/" />">My Blog</a></h1>
        </div>
    </div>
</div>