<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
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

    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<%--@elvariable id="model" type="org.itstep.domain.entity.Author"--%>
<form:form method="post" modelAttribute="model" action="register" enctype="multipart/form-data">
    <div class="row">
        <div class="col-12 col-md-6">
            <div class="group">
                <form:input path="name" required="required"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Name</label>
            </div>
        </div>
        <div class="col-12 col-md-6">
            <div class="group">
                <form:input path="surname" required="required"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Surname</label>
            </div>
        </div>
        <div class="col-12 col-md-6">
            <div class="group">
                <form:input path="email" type="email" id="email" required="required"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Email</label>
            </div>
        </div>
        <div class="col-12 col-md-6">
            <div class="group">
                <form:input path="password" type="password" required="required"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Password</label>
            </div>
        </div>
        <div class="col-12 col-md-6">
            <div class="group">
                <form:textarea path="about" id="about"/>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>About</label>
            </div>
        </div>
        <div class="col-12 col-md-6">
            <div class="group">
                <form:input path="avatarUrl" id="avatar" type="file"/>
                <label>Avatar</label>
            </div>
        </div>
        <div class="col-12">
            <button type="submit" class="btn original-btn">Register</button>
        </div>
    </div>
</form:form>
<%@include file="include/footer.jsp"%>
</body>
</html>
