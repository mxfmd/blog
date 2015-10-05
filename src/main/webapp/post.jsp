<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp">
    <c:param name="title" value="Blog - ${post.title}"/>
</c:import>

<body>

<div class="container">

    <c:import url="accountbar.jsp"/>

    <c:choose>
        <c:when test="${not empty post}">

            <div class="row">
                <div class="col-md-12">
                    <h2>${post.title}</h2>

                    <c:out value="${js}"/>
                    Posted <fmt:formatDate type="both" dateStyle="short" value="${post.date}"/>
                    <i>By ${post.author}</i><br/>

                    <hr>

                    <p><c:out value="${post.body}"/></p>

                    <p>
                        <em>Filled under:</em>

                        <c:forEach var="tag" items="${post.tags}">
                            <a href="/tag/${tag}">${tag}</a>
                        </c:forEach>
                    </p>

                    <a name="comments"></a>

                    <h4>Comments:</h4>
                </div>
            </div>
            <div class="row" id="comments">
                <c:if test="${not empty post.comments}">


                    <c:forEach var="comment" items="${post.comments}">
                        <div class="col-md-8 col-md-offset-1">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <p>${comment.body}</p>
                                </div>
                                <div class="panel-footer">
                                    <p>Author: <i>${comment.author}</i></p>

                                    <c:if test="${not empty comment.email}">
                                        <p>Email: <i>${comment.email}</i></p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </c:if>
            </div>

            <div class="row">
                <form class="col-md-8 col-md-offset-1" id="newCommentForm" role="form"
                      action="<c:url value="/post/newcomment"></c:url>" method="post">

                        <%-- If JS disabled --%>
                    <noscript>
                        <input type="hidden" name="isJSEnabled" value="false">
                    </noscript>

                    <c:if test="${not empty error_message}">
                        <div class="alert alert-danger" role="alert">
                            <p>${error_message}</p>
                        </div>
                        ${error_message = ""}
                    </c:if>

                    <input type="hidden" name="permalink" value="${post.permalink}">

                    <div class="form-group">
                        <label for="inputName">Name (required):</label>
                        <input type="text" class="form-control" id="inputName" name="name">
                    </div>

                    <div class="form-group">
                        <label for="inputEmail">Email (optional):</label>
                        <input type="email" class="form-control" id="inputEmail" name="email">
                    </div>

                    <div class="form-group">
                        <label for="inputCommentBody">Comment:</label>
                        <textarea id="inputCommentBody" class="form-control" rows="4" name="commentBody"></textarea>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-md-12 text-center">
                    <h3>Requested post could not be found</h3>
                </div>
            </div>
        </c:otherwise>

    </c:choose>

</div>

</body>
</html>
