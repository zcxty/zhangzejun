<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/4/6
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String Path = request.getContextPath();
   String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + Path + "/";
%>
 
<html>
<head>
    <title>User列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="<%=basePath%>resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>resources/css/user-list.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>resources/js/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    基于SSM框架的管理系统：简单实现增、删、改、查。
                </h1>
            </div>
        </div>
    </div>
 
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>论文列表 —— 显示所有论文</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
    
    <div class="col-md-4 column form-inline">
	    <div class="form-group">
	    <form action="<%=basePath%>user/searchUser" method="post">
	    <label class="sr-only" for="exampleInputEmail3">用户名</label>
	    <input type="text" class="form-control username" name="username"  id="username" placeholder="username">
	    </div>
	    <button type="submit" class="search btn btn-default">查找</button>
	    </form>
	    <a class="btn btn-primary" href="<%=basePath%>user/toAddUser">新增</a>
    </div>
        <div class="col-md-8 column">
         
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>用户编号</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>ip</th>
                    <th>注册时间</th>
                    <th>登录时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.ip}</td>
                        <td>${user.createTime}</td>
                         <td>${user.loginTime}</td>
                        <td>
                            <a href="<%=basePath%>user/toUpdateUser?id=${user.id}">更改</a> |
                            <a href="<%=basePath%>user/del/${user.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <p class="pagehtml" id="pagehtml">${page}</p>
        </div>
    </div>
</div>
<%-- <script type="text/javascript">
$(".search").click(function(){	
	
 var name=$(".username").val();
 if($.trim(name)!=null){
	 window.location.href="<%=basePath%>user/allUser?username="+name;
 }else{
	 window.location.href="<%=basePath%>user/allUser";
 }
	
})


</script> --%>

</body>
</html>