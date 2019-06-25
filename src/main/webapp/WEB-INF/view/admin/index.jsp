<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>Admin</h1>
<p>
    <a href="<spring:url value="/admin/category"/>">Categories</a>
</p>

<p>
    <a href="<spring:url value="/admin/tag"/>">Tags</a>
</p>

<p>
    <a href="<spring:url value="/admin/author"/>">Authors</a>
</p>
<p>
    <a href="<spring:url value="/admin/post"/>">Posts</a>
</p>
<p>
    <a href="<spring:url value="/admin/sendemails"/>">Send Emails</a>
</p>
</body>
</html>
