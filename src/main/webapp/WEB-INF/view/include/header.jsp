<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Original - Lifestyle Blog Template</title>
    <!-- Favicon -->
    <link rel="icon" href="<spring:url value="/resources/img/core-img/favicon.ico"/>"/>

    <!-- Style CSS -->
    <link rel="stylesheet" href="<spring:url value="/resources/css/style.css"/>"/>
</head>

<body>
<!-- Preloader -->
<div id="preloader">
    <div class="preload-content">
        <div id="original-load"></div>
    </div>
</div>

<!-- Subscribe Modal -->
<div class="subscribe-newsletter-area">
    <div class="modal fade" id="subsModal" tabindex="-1" role="dialog" aria-labelledby="subsModal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <div class="modal-body">
                    <h5 class="title">Subscribe to my newsletter</h5>
                    <form action="<spring:url value="/subscribe"/>" class="newsletterForm" method="post">
                        <input type="email" name="email" id="subscribesForm2" placeholder="Your e-mail here">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn original-btn">Subscribe</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ##### Header Area Start ##### -->
<header class="header-area">

    <!-- Top Header Area -->
    <div class="top-header">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
            </div>
        </div>
    </div>

    <!-- Logo Area -->
    <div class="logo-area text-center">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <a href="<spring:url value="/"/>" class="original-logo">
                        <img src="<spring:url value="/resources/img/core-img/logo.png"/>" alt=""></a>
                </div>
            </div>
        </div>
    </div>

    <!-- Nav Area -->
    <div class="original-nav-area" id="stickyNav">
        <div class="classy-nav-container breakpoint-off">
            <div class="container">
                <!-- Classy Menu -->
                <nav class="classy-navbar justify-content-between">

                    <!-- Subscribe btn -->
                    <div class="subscribe-btn">
                        <a href="#" class="btn subscribe-btn" data-toggle="modal" data-target="#subsModal">Subscribe</a>
                    </div>

                    <!-- Navbar Toggler -->
                    <div class="classy-navbar-toggler">
                        <span class="navbarToggler"><span></span><span></span><span></span></span>
                    </div>

                    <!-- Menu -->
                    <div class="classy-menu" id="originalNav">
                        <!-- close btn -->
                        <div class="classycloseIcon">
                            <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                        </div>

                        <!-- Nav Start -->
                        <div class="classynav">
                            <ul>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <li><a href="<spring:url value="/admin"/>">Admin panel</a></li>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_AUTHOR')">
                                    <li><a href="<spring:url value="/control"/>">Control panel</a></li>
                                </sec:authorize>
                                <sec:authorize access="!isAuthenticated()">
                                    <li><a href="<spring:url value="/register"/>">Register</a></li>
                                    <li><a href="<spring:url value="/login"/>">Login</a></li>
                                </sec:authorize>
                                <sec:authorize access="isAuthenticated()">
                                    <li><a href="<spring:url value="/logout"/>">Logout</a></li>
                                </sec:authorize>
                                <li><a href="<spring:url value="/"/>">Home</a></li>
                                <li><a href="/">New Post</a></li>
                                <li><a href="#">Pages</a>
                                    <ul class="dropdown">
                                        <li><a href="../index.jsp">Home</a></li>
                                        <li><a href="../about-us.jsp">About Us</a></li>
                                        <li><a href="../single-post.jsp">Single Post</a></li>
                                        <li><a href="../contact.jsp">Contact</a></li>
                                        <li><a href="../coming-soon.jsp">Coming Soon</a></li>
                                    </ul>
                                </li>
                                <li><a href="#">Category</a>
                                    <ul class="dropdown">
                                        <%--@elvariable id="categories" type="java.util.List<org.itstep.domain.entity.Category>"--%>
                                        <c:forEach items="${categories}" var="category">
                                            <li><a href="<spring:url value="?category=${category.id}"/>">${category.name}</a></li>
                                        </c:forEach>
                                    </ul>
                                </li>
                                <li><a href="../about-us.jsp">About Us</a></li>
                                <li><a href="../contact.jsp">Contact</a></li>
                            </ul>

                            <!-- Search Form  -->
                            <div id="search-wrapper">
                                <form action="<spring:url value="/search"/>" method="get">
                                    <input type="text"  id="search" name="query" placeholder="Search something...">
                                    <div id="close-icon"></div>
                                    <input class="d-none" type="submit" value="">
                                </form>
                            </div>
                        </div>
                        <!-- Nav End -->
                    </div>
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ##### Header Area End ##### -->
