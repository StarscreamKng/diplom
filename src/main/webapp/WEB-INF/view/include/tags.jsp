<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Widget Area -->
<div class="sidebar-widget-area">
    <h5 class="title">Tags</h5>
    <div class="widget-content">
        <ul class="tags">
            <%--@elvariable id="tags" type="java.util.List<org.itstep.domain.entity.Tag>"--%>
            <c:forEach items="${tags}" var="tag">
                <spring:url var="link" value="/">
                    <spring:param name="tag" value="${tag.id}"/>
                </spring:url>
                <li><a href="${link}">${tag.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
