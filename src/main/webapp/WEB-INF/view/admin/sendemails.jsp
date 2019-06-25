<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Emails</title>
</head>
<body>
<h1>Send Emails</h1>
<form method="post" action="<spring:url value="/admin/sendemails"/>">
    <div class="input">
        <label>
            Subject:
            <input type="text" name="subject">
        </label>
    </div>
    <div class="input">
        <label>
            Message:
            <textarea name="message"></textarea>
        </label>
    </div>
    <input type="submit" value="Отправить"/>
</form>
<script src="//cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
<script>
    CKEDITOR.replace('message');
</script>
</body>
</html>
