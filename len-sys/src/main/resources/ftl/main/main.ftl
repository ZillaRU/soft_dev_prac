<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Achieve It</title>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/plugins/font-awesome/css/font-awesome.min.css" media="all"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/build/css/app.css" media="all"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/build/css/themes/default.css" media="all" id="skin" kit-skin/>
    <style>
        .layui-side-scroll {
            border-right: 3px solid #009688;
        }
    </style>
</head>

<body class="kit-theme">
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">Achieve It</div>
        <div class="layui-logo kit-logo-mobile"></div>
        <div class="layui-hide-xs">
        </div>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="layui-icon">&#xe63f;</i> 皮肤</a>
                </a>
                <dl class="layui-nav-child skin">
                    <dd><a href="javascript:;" data-skin="default" style="color:#393D49;"><i
                                    class="layui-icon">&#xe658;</i> 默认</a></dd>
                    <dd><a href="javascript:;" data-skin="orange" style="color:#ff6700;"><i
                                    class="layui-icon">&#xe658;</i> 橘子橙</a></dd>
                    <dd><a href="javascript:;" data-skin="green" style="color:#00a65a;"><i
                                    class="layui-icon">&#xe658;</i> 春天绿</a></dd>
                    <dd><a href="javascript:;" data-skin="pink" style="color:#FA6086;"><i
                                    class="layui-icon">&#xe658;</i> 少女粉</a></dd>
                    <dd><a href="javascript:;" data-skin="blue.1" style="color:#00c0ef;"><i
                                    class="layui-icon">&#xe658;</i> 天空蓝</a></dd>
                    <dd><a href="javascript:;" data-skin="red" style="color:#dd4b39;"><i class="layui-icon">&#xe658;</i>
                            枫叶红</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <#assign currentUser = Session["currentPrincipal"]>
                    <img src="${re.contextPath}/images/${currentUser.photo}"
                         class="layui-nav-img">${currentUser.username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" kit-target
                           data-options="{url:'/person',icon:'&#xe658;',title:'基本资料',id:'966'}"><span>基本资料</span></a>
                    </dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
        </ul>
    </div>

    <#macro tree data start end>
        <#if (start=="start")>
            <div class="layui-side layui-nav-tree layui-bg-black kit-side">
            <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
        </#if>
        <#list data as child>
            <#if child.children?size gt 0>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i aria-hidden="true"
                                                       class="layui-icon">${child.icon}</i><span> ${child.name}</span></a>
                    <dl class="layui-nav-child">
                        <@tree data=child.children start="" end=""/>
                    </dl>
                </li>
            <#else>
                <dd>
                    <a href="javascript:;" kit-target
                       data-options="{url:'${child.url}',icon:'${child.icon}',title:'${child.name}',id:'${child.num?c}'}">
                        <i class="layui-icon">${child.icon}</i><span> ${child.name}</span></a>
                </dd>
            </#if>
        </#list>
        <#if (end=="end")>
            </ul>
            </div>
            </div>
        </#if>
    </#macro>
    <@tree data=menu start="start" end="end"/>
    <div class="layui-body" <#--style="border:1px solid red;padding-bottom:0;"--> id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;"><i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop">&#xe63e;</i>
            请稍等...
        </div>
    </div>
</div>
<script type="text/javascript">
    var websocket = null;


    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        //连接WebSocket节点
        websocket = new WebSocket("ws://localhost:8081/productWebSocket/001");
    } else {
        alert('Not support websocket')
    }


    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };


    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        setMessageInnerHTML("open");
    }


    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        var currentUser = "${Session['currentPrincipal'].currentRoleList[0].roleName}";
        console.log(currentUser);
        if (currentUser === "pm")
            setMessageInnerHTML(event.data);
    }


    //连接关闭的回调方法
    websocket.onclose = function () {
        // setMessageInnerHTML("close");
    }


    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }


    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        layer.alert(innerHTML, {icon: 6, title: "提示", offset: "auto", skin: "layui-layer-molv"});
    }


    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }


    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
<#--————————————————-->
<#--版权声明：本文为CSDN博主「小目标青年」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。-->
<#--原文链接：https://blog.csdn.net/qq_35387940/article/details/93483678-->
<script src="${re.contextPath}/plugin/layui/layui.js"></script>
<script src="${re.contextPath}/plugin/tools/main.js"></script>
</body>

</html>
