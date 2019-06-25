<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Authors</title>
    <meta charset="utf-8"/>
    <style>
        .error {
            color: red;
        }
        .group {
            display: block;
            margin-bottom: 8px;
        }
        .group label {
            display: inline-block;
            width: 75px;
        }

        .group .input {
            display: inline-block;
        }

        form {
            display: block;
            width: 300px;
        }

        [type=submit] {
            float: right;
        }


    </style>
</head>
<body>
<h1>Authors</h1>
<p>
    <a href="<spring:url value="/admin"/>">Admin panel</a>
</p>
<%--@elvariable id="model" type="org.itstep.domain.entity.Author"--%>
<form:form modelAttribute="model" enctype="multipart/form-data">
    <div class="group">
        <form:label path="name"> Name: </form:label>
        <div class="input"><form:input path="name"/> <span class="error"><form:errors path="name"/></span></div>
    </div>
    <div class="group">
        <form:label path="surname"> Surname: </form:label>
        <div class="input"><form:input path="surname"/> <span class="error"><form:errors path="surname"/></span></div>
    </div>
    <div class="group">
        <form:label path="email" type="email"> Email: </form:label>
        <div class="input"><form:input path="email"/> <span class="error"><form:errors path="email"/></span></div>
    </div>
    <div class="group">
        <form:label path="about" type="text"> About: </form:label>
        <div class="input"><form:input path="about"/> <span class="error"><form:errors path="about"/></span></div>
    </div>
    <div class="group">
        <form:label path="avatarUrl"> AvatarURL: </form:label>
        <div class="input" type="data"><form:input path="avatarUrl" type="file"/> <span class="error"><form:errors path="avatarUrl"/></span></div>
    </div>
    <input type="submit">
</form:form>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>about</th>
        <th>avatarUrl</th>
    </tr>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td>${author.id}</td>
            <td>${author.name}</td>
            <td>${author.surname}</td>
            <td>${author.email}</td>
            <td>${author.about}</td>
            <td>${author.avatarUrl}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
