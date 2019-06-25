<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="single-blog-wrapper section-padding-0-100">

    <!-- Single Blog Area  -->
    <div class="single-blog-area blog-style-2 mb-50">
        <div class="single-blog-thumbnail">
            <img src="<%--@elvariable id="model" type="org.itstep.domain.entity.Post"--%>
            <spring:url value="/resources/img/bg-img/${model.imageUrl}"/>" alt="">
            <div class="post-tag-content">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="post-date">
                                <a href="#">${model.published.dayOfMonth} <span>${model.published.month}</span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <!-- ##### Post Content Area ##### -->
            <div class="col-12 col-lg-9">
                <!-- Single Blog Area  -->
                <div class="single-blog-area blog-style-2 mb-50">
                    <!-- Blog Content -->
                    <div class="single-blog-content">
                        <div class="line"></div>
                        <a href="#" class="post-tag">${model.tags.get(0).name}</a>
                        <h4><a href="#" class="post-headline mb-0">${model.title}</a></h4>
                        <div class="post-meta mb-50">
                            <p>By <a href="#">${model.author.fullName}</a></p>
                            <c:if test="${model.comments != null && model.comments.size() > 0}">
                                <p>${model.comments.size()} comments</p>
                            </c:if>
                        </div>
                        <p>${model.text}</p>
                    </div>
                </div>

                <!-- About Author -->
                <div class="blog-post-author mt-100 d-flex">
                    <div class="author-thumbnail">
                        <img src="<spring:url value="/resources${model.author.avatarUrl}"/>" alt="">
                    </div>
                    <div class="author-info">
                        <div class="line"></div>
                        <span class="author-role">Author</span>
                        <h4><a href="#" class="author-name">${model.author.fullName}</a></h4>
                        <p>${model.author.about}</p>
                    </div>
                </div>

                <!-- Comment Area Start -->
                <c:if test="${model.comments != null && model.comments.size() > 0}">
                    <div class="comment_area clearfix mt-70">
                        <h5 class="title">Comments</h5>

                        <ol>
                            <c:forEach var="comment" items="${model.comments}">
                                <!-- Single Comment Area -->
                                <li class="single_comment_area">
                                    <!-- Comment Content -->
                                    <div class="comment-content d-flex">
                                        <!-- Comment Author -->
<%--                                        <div class="comment-author">--%>
<%--                                            <img src="<spring:url value="/resources/img/bg-img/b7.jpg"/>" alt="author">--%>
<%--                                        </div>--%>
                                        <!-- Comment Meta -->
                                        <div class="comment-meta">
                                            <a href="#"
                                               class="post-date">${comment.commentDate.dayOfMonth} ${comment.commentDate.month}</a>
                                            <p><a href="#" class="post-author">${comment.name}</a></p>
                                            <p>${comment.comment}</p>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </c:if>

                <div class="post-a-comment-area mt-70">
                    <h5>Leave a reply</h5>
                    <!-- Reply Form -->
                    <spring:url value="/post/${model.id}/comment" var="url"/>
                    <%--@elvariable id="comment" type="org.itstep.domain.entity.Comment"--%>
                    <form:form modelAttribute="comment" action="${url}" method="post">
                        <div class="row">
                            <div class="col-12 col-md-6">
                                <div class="group">
                                    <form:input path="name" required="required" />
<%--                                    <input type="text" name="name" id="name" required>--%>
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Name</label>
                                </div>
                            </div>
                            <div class="col-12 col-md-6">
                                <div class="group">
                                    <form:input type="email" path="email" id="email" required="required"/>
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Email</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="group">
                                    <form:input type="text" path="subject" id="subject" required="required"/>
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Subject</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="group">
                                    <form:textarea path="comment" id="message" required="required"/>
                                    <span class="highlight"></span>
                                    <span class="bar"></span>
                                    <label>Comment</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn original-btn">Reply</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
            <%@include file="sidebar.jsp" %>
        </div>
    </div>
</div>