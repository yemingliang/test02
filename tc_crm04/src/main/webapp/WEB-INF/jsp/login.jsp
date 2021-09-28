<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/inc/common_head.jsp" %>
    <script>
        jQuery(function ($) {
            $("#loginBtn").click(function () {
                $.ajax({
                    url: "/user/login.do",
                    type: "post",
                    data: {
                        username: $("#username").val(),
                        password: $("#password").val(),
                        autoLogin: $("#autoLogin").prop("checked")
                    },
                    success(data) {
                        if (data.success) {
                            //alert("登录成功！跳转到系统首页！");
                            location = "/workbench/index.html";
                        } else {
                            alert(data.msg);
                        }
                    }
                })
            });
        })
    </script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="/static/image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form action="workbench/index.html" class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input id="username" class="form-control" type="text" placeholder="用户名">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input id="password" class="form-control" type="password" placeholder="密码">
                </div>
                <div class="checkbox" style="position: relative;top: 30px; left: 10px;">
                    <label>
                        <input id="autoLogin" type="checkbox"> 十天内免登录
                    </label>
                </div>
                <button id="loginBtn" type="button" class="btn btn-primary btn-lg btn-block"
                        style="width: 350px; position: relative;top: 45px;">登录
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>