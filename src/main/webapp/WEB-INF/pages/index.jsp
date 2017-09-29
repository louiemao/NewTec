<%--
  Created by IntelliJ IDEA.
  User: george
  Date: 15-7-1
  Time: 下午1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <base href="/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <title>我的工作台</title>
    <link href="/static/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/loader.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .nav, .pagination, .carousel, .panel-title a {
            cursor: pointer;
        }

        .center-modal {
            top: 30%;
        }

    </style>

</head>
<body style="padding-bottom: 70px;margin-top:0px;" ng-app="newapp" ng-controller="newappctl">

<nav class="navbar navbar-default navbar-inverse" role="navigation">
    <div class="navbar-header">
        <%--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myCollapse">--%>
        <%--<span class="sr-only">Toggle navigation</span>--%>
        <%--<span class="icon-bar"></span>--%>
        <%--<span class="icon-bar"></span>--%>
        <%--<span class="icon-bar"></span>--%>
        <%--</button>--%>
        <a class="navbar-brand" href="/">演示网站</a>
    </div>
    <script id="recursion" type="text/ng-template">
        <li ng-repeat="item in navbars" class="{{item.children&&item.children.length>0?'dropdown-submenu':''}}">
            <a href="{{item.link}}">{{item.label}}</a>
            <ul ng-if="item.children&&item.children.length>0" class="dropdown-menu" ng-include="'recursion'"
                ng-init="navbars=item.children"></ul>
        </li>
    </script>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <li ng-repeat="item in navbars" class="dropdown" ng-class="{active:isActive(item.label)}">
                <a href="{{item.link}}" class="dropdown-toggle" data-toggle="dropdown">{{item.label}} <b
                        ng-if="item.children&&item.children.length>0" class="caret"></b></a>
                <ul ng-if="item.children&&item.children.length>0" class="dropdown-menu menu-top"
                    ng-include="'recursion'" ng-init="navbars=item.children"></ul>
            </li>
        </ul>
        <form class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" placeholder="Search" ng-disabled="false">
            </div>
            <button type="submit" class="btn  btn-sm">搜索</button>
        </form>
        <ul class="nav navbar-nav navbar-right  " style="margin-right: 20px;">

            <li not-authenticated><a ng-click="open('sm')"> 登陆 </a></li>
            <li authenticated class="dropdown">
                <a class="dropdown-toggle" bs-dropdown="dropdown" data-toggle="dropdown">
                    <principal property="username"/>
                    <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">设置</a></li>
                    <li class="divider"></li>
                    <li><a ng-click="logout()">登出</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <%--<div class="collapse navbar-collapse " id="myCollapse">--%>
    <%--<ul class="nav navbar-nav">--%>

    <%--<li class="active"><a href="/servers">我的服务器</a></li>--%>

    <%--<li><a href="/customers">我的客户</a></li>--%>

    <%--<li><a href="/usermanage">用户管理</a></li>--%>

    <%--<li><a href="/trainsview">火车</a></li>--%>

    <%--<li class="dropdown">--%>
    <%--<a class="dropdown-toggle" bs-dropdown="dropdown" data-toggle="dropdown">常用链接 <b class="caret"></b></a>--%>
    <%--<ul class="dropdown-menu">--%>
    <%--<li><a href="https://clonegoogle.com">Google镜像</a></li>--%>
    <%--<li><a href="https://www.baidu.com">度娘</a></li>--%>
    <%--<li><a href="http://www.oschina.net">开源中国</a></li>--%>
    <%--<li class="divider"></li>--%>
    <%--<li><a href="/servers/add">添加服务器</a></li>--%>
    <%--<li class="divider"></li>--%>
    <%--<li><a href="#">One more separated link</a></li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--<form class="navbar-form navbar-left">--%>
    <%--<div class="form-group">--%>
    <%--<input type="text" placeholder="Search" ng-disabled="false">--%>
    <%--</div>--%>
    <%--<button type="submit" class="btn  btn-sm">搜索</button>--%>
    <%--</form>--%>
    <%--<ul class="nav navbar-nav navbar-right  " style="margin-right: 20px;">--%>

    <%--&lt;%&ndash;<li  ng-if= "!islogin"><a ng-click="open('sm')"> 登陆 </a></li>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<li ng-if="islogin" class="dropdown">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<a class="dropdown-toggle" bs-dropdown="dropdown" data-toggle="dropdown">个人信息 <b class="caret"></b></a>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<ul class="dropdown-menu">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<li><a href="#">设置</a></li>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<li class="divider"></li>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<li><a href="logout()">登出</a></li>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
    <%--<li not-authenticated><a ng-click="open('sm')"> 登陆 </a></li>--%>
    <%--<li authenticated class="dropdown">--%>
    <%--<a class="dropdown-toggle" bs-dropdown="dropdown" data-toggle="dropdown"><principal property="username" /> <b class="caret"></b></a>--%>
    <%--<ul class="dropdown-menu">--%>
    <%--<li><a href="#">设置</a></li>--%>
    <%--<li class="divider"></li>--%>
    <%--<li><a ng-click="logout()">登出</a></li>--%>
    <%--</ul>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</div>--%>
</nav>

<div class="container">
    <div ng-view>

    </div>


</div>
<footer>
    <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
        <h4>
            <p style="text-align: center;">版权所有 SWS-CSSC 备案号:沪B11111</p>
        </h4>
    </nav>
</footer>

<script src="/static/js/angular/angular.js"></script>
<script src="/static/js/angular-resource/angular-resource.js"></script>
<script src="/static/js/angular-route/angular-route.min.js"></script>
<%--<script src="/static/js/angular-mocks/angular-mocks.js"></script>--%>
<script src="/static/js/angular-shiro/angular-shiro.min.js"></script>
<%--<script src="/static/js/angular-strap/angular-strap.min.js"></script>
<script src="/static/js/angular-strap/angular-strap.tpl.min.js"></script>--%>

<script src="/static/js/angular-bootstrap/ui-bootstrap.min.js"></script>
<script src="/static/js/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>

<script src="/static/js/jquery-1.9.1.min.js"></script>
<script src="/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>

<script src="/static/templates/module1/server/server.js"></script>
<script src="/static/templates/module1/customer/customer.js"></script>
<script src="/static/templates/module1/user/user.js"></script>
<script src="/static/templates/security/usermanage/user.js"></script>
<script src="/static/templates/module1/train/Train.js"></script>
<script src="/static/templates/module1/train/TrainService.js"></script>
<script src="/static/templates/module1/train/TrainController.js"></script>
<script src="/static/templates/module1/train/TrainAddController.js"></script>
<script src="/static/templates/module1/train/TrainUpdateController.js"></script>
<script src="/static/templates/module1/train/OffSetFilter.js"></script>
<script src="/static/templates/module1/app.js"></script>
</body>
</html>
