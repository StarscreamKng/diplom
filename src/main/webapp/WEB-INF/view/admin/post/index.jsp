<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Post</title>
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

        table {
            width: 800px;
            margin: 10px auto;
        }

        form {
            margin: 50px auto;
            display: block;
            width: 800px;
        }

        [type=submit] {
            /*float: right;*/
        }

        label {
            vertical-align: top;
        }

        h1 {
            text-align: center;
        }

    </style>
</head>
<body>

<h1>Posts</h1>

<p>
    <a href="<spring:url value="/admin"/>">Admin panel</a>
</p>
<%--@elvariable id="model" type="org.itstep.domain.dto.PostDto"--%>
<form:form modelAttribute="model" enctype="multipart/form-data">
    <div class="group">
        <label for="authorId"> Author: </label>
        <div class="input">
                <%--@elvariable id="authors" type="java.util.List<org.itstep.domain.entity.Author>"--%>
            <form:select items="${authors}" itemLabel="name" itemValue="id" path="authorId">
                <form:option value="--"/>
            </form:select>
            <span class="error"><form:errors path="authorId"/></span>
        </div>
    </div>
    <div class="group">
        <form:label path="title"> Title: </form:label>
        <div class="input"><form:input path="title"/> <span class="error"><form:errors path="title"/></span></div>
    </div>
    <div class="group">
        <form:label path="text" type="text"> Text: </form:label>
        <div class="input">
            <form:textarea path="text"/> <span class="error"><form:errors path="text"/></span>
        </div>
    </div>
    <div class="group">
        <label for="image"> Image: </label>
        <div class="input">
            <input type="file" name="image" id="image"/>
            <form:checkbox path="wideImage"/>
            <span>Is image wide?</span>
        </div>
    </div>

    <div class="group">
        <label for="categoryId"> Category: </label>
        <div class="input">
                <%--@elvariable id="categories" type="java.util.List<org.itstep.domain.entity.Category>"--%>
            <form:select path="categoryId" items="${categories}" itemLabel="name" itemValue="id">
                <form:option value="--"/>
            </form:select>
            <span class="error"><form:errors path="categoryId"/></span>
        </div>
    </div>
    <div class="group">
        <label id="tagsId"> Tags: </label>
        <div class="input">
                <%--@elvariable id="tags" type="java.util.List<org.itstep.domain.entity.Tag>"--%>
            <form:checkboxes items="${tags}" itemLabel="name" itemValue="id" path="tagsId"/>
            <span class="error"><form:errors path="tagsId"/></span>
        </div>
    </div>
    <input type="submit" value="Сохранить пост"/>
</form:form>
<table border="1">
    <tr>
        <th>id</th>
        <th>author</th>
        <th>published</th>
        <th>title</th>
        <%--<th>text</th>--%>
        <th>imageUrl</th>
        <th>category</th>
        <th>tags</th>
    </tr>
    <%--@elvariable id="posts" type="java.util.List<org.itstep.domain.entity.Post>"--%>
    <c:forEach items="${posts}" var="post">
        <tr>
            <td>${post.id}</td>
            <td>${post.author.fullName}</td>
            <td>${post.published}</td>
            <td>${post.title}</td>
                <%--<td>${post.text}</td>--%>
            <td>${post.imageUrl}</td>
            <td>${post.category.name}</td>
            <td>
                <c:forEach items="${post.tags}" var="tag">
                    ${tag.name}
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    <script src="//cdn.ckeditor.com/4.11.1/standard/ckeditor.js"></script>
    <script>
        CKEDITOR.replace('text');
    </script>
</table>
</body>
</html>
