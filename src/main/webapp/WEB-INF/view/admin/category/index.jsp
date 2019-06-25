<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Categories</title>
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

<h1>Categories</h1>

<p>
    <a href="<spring:url value="/admin"/>">Admin panel</a>
</p>
<%--@elvariable id="model" type="org.itstep.domain.entity.Category"--%>
<form:form modelAttribute="model">
    <div class="group">
        <form:label path="name"> Name: </form:label>
        <div class="input"><form:input path="name"/> <span class="error"><form:errors path="name"/></span></div>
    </div>
    <input type="submit"/>
</form:form>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
    </tr>
    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
