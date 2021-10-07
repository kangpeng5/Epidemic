<%--
  Created by IntelliJ IDEA.
  User: p
  Date: 2020/5/19
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>疫情数据统计系统后台管理</title>
    <jsp:include page="template/bootstrap_common.jsp"></jsp:include>
</head>
<body>
    <div class="container">
        <!-- 头部的表示 -->
        <jsp:include page="template/top.jsp"></jsp:include>
        <div class="row">
            <!-- 左边菜单 -->
            <div class="col-md-3">
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div>
            <!-- 右边内容窗口 -->
            <div class="col-md-9">
                <ul class="breadcrumb">
                    <li>
                        <a href="${pageContext.request.contextPath}/main.jsp">主页</a>
                    </li>
                    <li>后台主页</li>
                </ul>
                欢迎使用疫情信息统计系统！
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</body>
</html>
