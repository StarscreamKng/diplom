<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sprint" uri="http://www.springframework.org/tags" %>
<!-- ##### Blog Wrapper Start ##### -->
<div class="blog-wrapper section-padding-100 clearfix">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-9">
                <%--@elvariable id="posts" type="java.util.List<org.itstep.domain.entity.Post>"--%>
                <c:forEach items="${posts}" var="post" varStatus="status">
                    <!-- Single Blog Area -->
                    <div class="single-blog-area blog-style-2 mb-50 wow fadeInUp" data-wow-delay="0.${status.count+1}s"
                         data-wow-duration="1000ms">
                        <div class="row align-items-center">
                            <c:choose>
                                <c:when test="${post.wideImage}">
                                    <c:set var="className" value="col-12"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="className" value="col-12 col-md-6"/>
                                </c:otherwise>
                            </c:choose>

                            <div class="${className}">
                                <div class="single-blog-thumbnail">
                                    <img src="<spring:url value="/resources/img/blog-img/${post.imageUrl}"/>" alt="">
                                    <div class="post-date">
                                        <a href="<spring:url value="/search?date=${post.published.toLocalDate()}"/>">${post.published.dayOfMonth}
                                            <span>${post.published.month}</span></a>
                                    </div>
                                </div>
                            </div>
                            <div class="${className}">
                                <!-- Blog Content -->
                                <div class="single-blog-content">
                                    <div class="line"></div>
                                    <a href="<spring:url value="?category=${post.category.id}"/>"
                                       class="post-tag">${post.category.name}</a>
                                    <h4><a href="/post/${post.id}" class="post-headline">${post.title}</a></h4>
                                    <p>${post.description}</p>
                                    <div class="post-meta">
                                        <p>By <a
                                                href="<spring:url value="?author=${post.author.id}"/>">${post.author.fullName}</a>
                                        </p>
                                        <c:if test="${post.comments.size() > 0 }">
                                            <p>${post.comments.size()} comments</p>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${posts.size()==0}">
                    <h4>Posts not found</h4>
                </c:if>
                <!-- Paging -->

                <c:if test="${pages > 1}">
                    <div>
                        <c:if test="${page > 0}">
                            <spring:url value="${url}" var="previousUrl">
                                <spring:param name="page" value="${page-1}"/>
                                <spring:param name="size" value="5"/>
                            </spring:url>
                            <span class="page" data-wow-delay="0.7s" data-wow-duration="1000ms">
                                <a href="${previousUrl}" class="btn original-btn">Previous</a>
                            </span>
                        </c:if>
                        <c:if test="${page != pages - 1}">
                            <spring:url value="${url}" var="nextUrl">
                                <spring:param name="page" value="${page+1}"/>
                                <spring:param name="size" value="5"/>
                            </spring:url>
                            <span class="page" data-wow-delay="0.7s" data-wow-duration="1000ms">
                                <a href="${nextUrl}" class="btn original-btn">Next</a>
                            </span>
                        </c:if>
                    </div>
                </c:if>
            </div>

            <%@include file="sidebar.jsp" %>

        </div>
    </div>
</div>
<!-- ##### Blog Wrapper End ##### -->