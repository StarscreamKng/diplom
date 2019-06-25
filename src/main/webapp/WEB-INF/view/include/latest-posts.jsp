<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!-- Widget Area -->
<div class="sidebar-widget-area">
    <h5 class="title">Latest Posts</h5>

    <div class="widget-content">

        <%--@elvariable id="lastPosts" type="java.util.List<org.itstep.domain.entity.Post>"--%>
        <c:forEach items="${lastPosts}" var="post">
            <!-- Single Blog Post -->
            <div class="single-blog-post d-flex align-items-center widget-post">
                <!-- Post Thumbnail -->
                <div class="post-thumbnail">
                    <img src="<spring:url value="/resources/img/blog-img/${post.imageUrl}"/>" alt="">
                </div>
                <!-- Post Content -->
                <div class="post-content">
                    <spring:url value="/" var="catUrl">
                        <spring:param name="category" value="${post.category.id}"/>
                    </spring:url>
                    <a href="${catUrl}" class="post-tag">${post.category.name}</a>
                    <h4><a href="/post/${post.id}" class="post-headline">${post.title}</a></h4>
                    <div class="post-meta">
                        <p><a href="#">${post.published.dayOfMonth} ${post.published.month}</a></p>
                    </div>
                </div>
            </div>
        </c:forEach>

        <%--        <!-- Single Blog Post -->--%>
        <%--        <div class="single-blog-post d-flex align-items-center widget-post">--%>
        <%--            <!-- Post Thumbnail -->--%>
        <%--            <div class="post-thumbnail">--%>
        <%--                <img src="<spring:url value="/resources/img/blog-img/lp2.jpg"/>" alt="">--%>
        <%--            </div>--%>
        <%--            <!-- Post Content -->--%>
        <%--            <div class="post-content">--%>
        <%--                <a href="#" class="post-tag">Lifestyle</a>--%>
        <%--                <h4><a href="#" class="post-headline">A sunday in the park</a></h4>--%>
        <%--                <div class="post-meta">--%>
        <%--                    <p><a href="#">12 March</a></p>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <%--        <!-- Single Blog Post -->--%>
        <%--        <div class="single-blog-post d-flex align-items-center widget-post">--%>
        <%--            <!-- Post Thumbnail -->--%>
        <%--            <div class="post-thumbnail">--%>
        <%--                <img src="<spring:url value="/resources/img/blog-img/lp3.jpg"/>" alt="">--%>
        <%--            </div>--%>
        <%--            <!-- Post Content -->--%>
        <%--            <div class="post-content">--%>
        <%--                <a href="#" class="post-tag">Lifestyle</a>--%>
        <%--                <h4><a href="#" class="post-headline">Party people in the house</a></h4>--%>
        <%--                <div class="post-meta">--%>
        <%--                    <p><a href="#">12 March</a></p>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <%--        <!-- Single Blog Post -->--%>
        <%--        <div class="single-blog-post d-flex align-items-center widget-post">--%>
        <%--            <!-- Post Thumbnail -->--%>
        <%--            <div class="post-thumbnail">--%>
        <%--                <img src="<spring:url value="/resources/img/blog-img/lp4.jpg"/>" alt="">--%>
        <%--            </div>--%>
        <%--            <!-- Post Content -->--%>
        <%--            <div class="post-content">--%>
        <%--                <a href="#" class="post-tag">Lifestyle</a>--%>
        <%--                <h4><a href="#" class="post-headline">A sunday in the park</a></h4>--%>
        <%--                <div class="post-meta">--%>
        <%--                    <p><a href="#">12 March</a></p>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
    </div>
</div>

