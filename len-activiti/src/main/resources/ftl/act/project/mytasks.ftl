<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>待处理项目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/lenos/main.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"
            charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
</head>

<body>
<table id="projList" class="layui-hide" lay-filter="proj"></table>
</body>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">基本信息</a>
    <@shiro.hasPermission name="project:approval">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="pass_proj">允许</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="reject_proj">驳回</a>
    </@shiro.hasPermission>
    <@shiro.hasRole name="${conf}">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="conf_complete">配置完成</a>
    </@shiro.hasRole>
    <@shiro.hasRole name="${epg}">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="assign_epg">分配EPG</a>
    </@shiro.hasRole>
    <@shiro.hasRole name="${qa}">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="assign_qa">分配QA</a>
    </@shiro.hasRole>
</script>
<script>
    document.onkeydown = function (e) { // 回车提交表单
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code == 13) {
            $(".select .select-on").click();
        }
    };
    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        table.render({
            id: 'projList',
            elem: '#projList'
            , url: 'myTasks'
            , cols: [[
                {
                    field: 'projName',
                    title: '项目名称',
                    width: '20%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                },
                {field: 'pmName', title: '项目经理', width: '12%'}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]],
            page: true,
            height: 'full-83'
        });

        //监听工具条
        table.on('tool(proj)', function (obj) {
            var data = obj.data;
            console.log(data + "\ndata['urlpath']: " + data['urlpath'] + obj.event);
            if (obj.event === 'detail') {
                get_detail_layer('查看项目信息', 'showProjDetail?projId=' + data['urlpath'], 900, 600, 'proj_detail_layer');
            } else if (obj.event === 'pass_proj') {
                approve_or_reject('确定批准立项吗？', 'chiefCheck', data['urlpath'], true);
            } else if (obj.event === 'reject_proj') {
                approve_or_reject('确定驳回立项吗？', 'chiefCheck', data['urlpath'], false);
            } else if (obj.event === 'assign_qa') {
                get_detail_layer('分配QA', 'qaSetting?projId=' + data['urlpath'], 600, 500, 'qa_layer');
            } else if (obj.event === 'assign_epg') {
                get_detail_layer('分配EPG', 'epgSetting?projId=' + data['urlpath'], 600, 500, 'epg_layer');
            } else if (obj.event === 'conf_complete') {
                confirm_op("通知项目经理：配置已完成？", 'confCheck', data['urlpath']);
            }
        });

    });

    function get_detail_layer(title, url, w, h, layer_id) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            url = "error/404";
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            id: layer_id,
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url
            // btn:['关闭']
        });
    }

    function approve_or_reject(alert_msg, url, projId, flag) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg(alert_msg, {
                time: 5000,//5秒自动关闭
                btn: ['确定', '取消'],
                yes: function (index) {
                    $.ajax({
                        url: url,
                        data: {
                            'projId': projId,
                            'flag': flag
                        },
                        type: "POST",
                        dataType: "json",
                        success: function (data) {
                            var index = parent.layer.getFrameIndex(window.name);
                            window.top.layer.msg(data.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                            parent.layer.close(index);
                            window.parent.location.reload();
                        }, error: function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            window.top.layer.msg('请求失败', {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                            parent.layer.close(index);
                        }
                    });
                    layer.close(index);
                }
            });
        });
    }

    function confirm_op(alert_msg, url, projId) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg(alert_msg, {
                btn: ['确定', '取消'],
                yes: function (index) {
                    $.ajax({
                        url: url,
                        data: {
                            'projId': projId
                        },
                        type: "POST",
                        dataType: "json",
                        success: function (data) {
                            var index = parent.layer.getFrameIndex(window.name);
                            window.top.layer.msg(data.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                            parent.layer.close(index);
                            window.parent.location.reload();
                        }, error: function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            window.top.layer.msg('请求失败', {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                            parent.layer.close(index);
                        }
                    });
                    layer.close(index);
                }
            });
        });
    }
</script>
</html>
