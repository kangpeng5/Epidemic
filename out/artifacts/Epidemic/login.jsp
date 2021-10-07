<%--
  Created by IntelliJ IDEA.
  User: pk
  Date: 2020/5/19
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>登陆界面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
</head>
<body style="background: #10aeb5">
    <div class="modal-dialog" style="margin-top: 10%">
        <form action="${pageContext.request.contextPath}/user/login" method="post" class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title text-center">登录</h4>
                </div>
                <div class="modal-body" id="modal-body">
                    <div class="form-group" style="margin-left: 20px;margin-right: 20px">
                        <input type="text" name="account" class="form-control" placeholder="请输入账号" autocomplete="off">
                    </div>
                    <div class="form-group" style="margin-left: 20px;margin-right: 20px">
                        <input type="password" name="password" class="form-control" placeholder="请输入密码" autocomplete="off">
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group" style="margin-left: 20px;margin-right: 20px">
                        <button type="submit" class="btn btn-primary form-control">登录</button>
                    </div>
                </div>
                <!-- 登录失败消息提示 -->
                <c:if test="${not empty msg}">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <div class="alert alert-danger alter-dismissable">
                                <button type="button" class="close" data-dismiss="alert">
                                    <span>×</span>
                                </button>
                                ${msg}
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</body>
</html>
