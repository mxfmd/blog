<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="header.jsp">
    <c:param name="title" value="Blog"/>
</c:import>

<body>

<div class="container">

    <c:import url="accountbar.jsp"/>

    <c:forEach var="post" items="${posts}">
        <div class="row">

            <div class="col-md-12">

                <h2><a href="<c:url value="/post/${post.permalink}"></c:url>"><c:out value="${post.title}"/></a></h2>

                <p>Posted <fmt:formatDate type="both" dateStyle="short" value="${post.date}"></fmt:formatDate> <i>By
                    <c:out
                            value="${post.author}"/></i></p><br/>
                Comments: <a href="<c:url value="/post/${post.permalink}" />">${fn:length(post.comments)}</a>
                <hr>
                <p><c:out value="${post.body}"/></p>

                <p>
                    <em>Filled under:</em>

                    <c:forEach var="tag" items="${post.tags}">
                        <a href="<c:url value="/tag/${tag}" />">${tag}</a>
                    </c:forEach>
                </p>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <hr>
                </div>
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
