<!-- ##### Sidebar Area ##### -->
<div class="col-12 col-md-4 col-lg-3">
    <div class="post-sidebar-area">

        <!-- Widget Area -->
        <div class="sidebar-widget-area">
            <form action="<spring:url value="/search"/>" class="search-form">
                <input type="search" name="query" id="searchForm" placeholder="Search">
                <input type="submit" value="submit">
            </form>
        </div>


        <!-- Widget Area -->
        <div class="sidebar-widget-area">
            <h5 class="title">Advertisement</h5>
            <a href="#"><img src="<spring:url value="/resources/img/bg-img/add.gif"/>" alt=""></a>
        </div>

        <%@include file="latest-posts.jsp"%>
        <%@include file="tags.jsp"%>

    </div>
</div>
