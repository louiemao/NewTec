<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme() + "://"--%>
            <%--+ request.getServerName() + ":" + request.getServerPort()--%>
            <%--+ path + "/";--%>
<%--%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%--<base href="<%=basePath%>"/>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <script type="text/javascript" src="/static/js/jquery-1.9.1.min.js"></script>
    <title>userInfo</title>
</head>
<body>
用户信息 用户名：${userInfo.username} 密码：${userInfo.password} 邮箱： ${userInfo.email} 用户id：${userInfo.id}
注册时间： <fmt:formatDate value="${userInfo.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
角色：[<c:forEach items="${ userInfo.roles}" var="role">
    ${role.name } &nbsp; 权限[
    <c:forEach items="${ role.permissions}" var="authority">
        ${authority.name }
    </c:forEach> ]
</c:forEach>]

<br/>
ajax显示全部用户信息：
<div id="show_all_user"></div>
</body>
<script type="text/javascript">
    $.ajax({
        type: "get",
        url: "/user/showInfos",
        dataType: "json",
        success: function (data) {
//            $("#show_all_user").append("<p>"+data+"</p>");
            $(data).each(
                    function (i, user) {
                        var p = "<p>用户名:" + user.username + "    邮箱:"
                                + user.email + "    注册时间:"
                                + getLocalTime(user.registerTime) + "    id:"
                                + user.id + "</p>";
                        $("#show_all_user").append(p);
                    });
        },
        async: true
    });
    function getLocalTime(nS) {
        return new Date(nS).toLocaleString();
    }
</script>
</html>