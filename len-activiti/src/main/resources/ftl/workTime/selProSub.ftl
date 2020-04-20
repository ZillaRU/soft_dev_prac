<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>选择项目提交工时信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/lenos/main.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"
            charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>

</head>

<body>

<table id="myProList" class="layui-hide" lay-filter="workTime" ></table>
<script type="text/html" id="showProInfo">
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="detail">
        <i class="layui-icon">&#xe60a;</i>角色列表</a>
</script>
<script type="text/html" id="subWorTime">
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="submit">
        <i class="layui-icon">&#xe642;</i>写入提交</a>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        table.render({
            id: 'myProList',
            elem: '#myProList'
            , url: 'proWorRla'
            , cols: [[
                {
                    field: 'proName',
                    title: '项目名称',
                    width: '20%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'pmName', title: '项目经理', width: '20%'}
                , {field: 'receiveUserName', title: '信息审核人员', width: '20%'}
                , {field: 'viewDetail', title: '查看项目角色信息', width: '20%', toolbar: "#showProInfo"}
                , {field: 'operate', title: '提交工时', width: '20%', toolbar: "#subWorTime"}
            ]]
            , page: true
            , height: 'full-83'
        });

        //监听工具条
        table.on('tool(workTime)', function (obj) {
            var data = obj.data;
            console.log(data);
            if (obj.event === 'detail') {
                detail('查看项目人员角色信息', 'showProRoleDetail?proId=' + data.proId + '&proName=' + data.proName
                    +'&pmName=' + data.pmName, 700, 500);
            }else if(obj.event === 'submit'){
                submit('提交项目工时信息', 'submitWorkTimeInfo?id=' + data.id, 700, 500);
            }
        });

    });

    var laydate = layui.laydate, layer = layui.layer;

    function detail(title, url, w, h) {
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
            id: 'pro_role_detail',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url + '&detail=true'
            // btn:['关闭']
        });
    }

    function submit(title, url, w, h) {
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
            id: 'sub_work_time',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url,
            success: function(layero, index) {
                laydate.render({
                    elem: '#subDate',
                    type: 'datetime'
                });
                laydate.render({
                    elem: '#startTime',
                    type: 'time',
                    format: 'HH:mm:ss'
                    });
                laydate.render({
                    elem: '#endTime',
                    type: 'time',
                    format: 'HH:mm:ss'
                });
            }
        });
    }
</script>
</body>

</html>
