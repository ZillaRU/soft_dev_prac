<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目归档管理</title>
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
<table id="archiveList" class="layui-hide" lay-filter="proj"></table>
<script type="text/html" id="barDemo">
    <#--    <@shiro.hasPermission name="user:select">-->
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="proArchive">归档审核</a>
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
            id: 'archiveList',
            elem: '#archiveList'
            , url: 'showArchiveList'
            , cols: [[
                {checkbox: false}
                , {
                    field: 'projName',
                    title: '项目名称',
                    width: '20%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'pmName', title: '项目经理姓名', width: '20%'}
                , {field: 'projState', title: '状态', width: '16%'}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]]
            , page: false,
            height: 'full-83'
        });
        //监听工具条
        table.on('tool(proj)', function (obj) {
            var data = obj.data;
            if (obj.event === 'proArchive') {
                console.log(data.id);
                get_archive_layer('审核项目归档', 'showProArchive?projId=' + data.id, 900, 600, 'proj_archive_layer');
            }
        });
    });

    function get_archive_layer(title, url, w, h, layer_id) {
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
</script>
</body>

</html>