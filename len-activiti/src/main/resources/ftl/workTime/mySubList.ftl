<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的工时提交记录</title>
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
<div class="lenos-search">
    <div class="select">
        项目名称:
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="proName" autocomplete="off">
        </div>
        <div class="layui-inline">
            <select id="chooseStatus" name="chooseStatus">
                <option value="">全部</option>
                <option value="processing">处理中</option>
                <option value="refused">已驳回</option>
                <option value="accepted">已通过</option>
            </select>
        </div>
        <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>
        </button>
        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"
                data-type="reload">
            <i class="layui-icon">ဂ</i>
        </button>
    </div>
</div>

<table id="mySubWorkTimeList" lay-filter="workTimeInfo"></table>
<script type="text/html" id="opraWorkTimeInfo">
    {{# if (d.infoStatus == "处理中") { }}
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="update">
        <i class="layui-icon">&#xe642;</i>修改</a>
    {{# } else if (d.infoStatus == "已通过") { }}
    <a class="layui-btn layui-btn-disabled layui-btn-sm">已通过</a>
    {{# } else { }}
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm " lay-event="update">
        <i class="layui-icon">&#xe642;</i>修改</a>
    {{# } }}
</script>

<script type="text/html" id="showStatus">
    {{# if (d.infoStatus == "处理中") { }}
    <span style="color:#fabc24">处理中</span>
    {{# } else if (d.infoStatus == "已通过") { }}
    <span style="color:#7cc84a">已通过</span>
    {{# } else { }}
    <span style="color:#c82f38">已驳回</span>
    {{# } }}
</script>
<script type="text/html" id="worTimInfDet">
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="detail">
        <i class="layui-icon">&#xe60a;</i>查看</a>
</script>
<script>
    layui.use(['table', 'laytpl'], function () {
        var table = layui.table;
        var laytpl = layui.laytpl;
        //方法级渲染
        table.render({
            id: 'mySubWorkTimeList',
            elem: '#mySubWorkTimeList'
            , url: 'mySubWorkTimeInfoList'
            , cols: [[
                {
                    field: 'proName',
                    title: '项目名称',
                    width: '20%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'submitDate', title: '工时日期', width: '15%', sort: true}
                , {field: 'receiveUserName', title: '审核人', width: '20%'}
                , {field: 'infoStatus', title: '审核状态', width: '15%', templet: '#showStatus'}
                , {field: 'detail', title: '查看详情', width: '15%', toolbar: "#worTimInfDet"}
                , {field: 'operate', title: '操作', width: '15%', templet: '#opraWorkTimeInfo'}
            ]]
            , page: true
            , height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var proName = $('#proName').val();
                var chooseStatus = $('#chooseStatus').val();
                console.log(proName);
                console.log(chooseStatus);
                table.reload('mySubWorkTimeList', {
                    where: {
                        proName: proName,
                        infoStatus: chooseStatus
                    }
                });
            },
            reload: function () {
                $('#proName').val('');
                $('#chooseStatus').val('');
                table.reload('mySubWorkTimeList', {
                    where: {
                        proName: "",
                        infoStatus: ""
                    }
                });
                select();
            }
        };

        $('.select .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.reload .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //监听工具条
        table.on('tool(workTimeInfo)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                detail('工时信息详情', 'showMySubWorkInfo?id=' + data.id, 700, 500);
            } else if (obj.event === 'update') {
                update('修改工时信息', 'updateMySubWorkInfo?id=' + data.id, 700, 500);
            }
        });
    });

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
            id: 'my_sub_work_info',
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

    function update(title, url, w, h) {
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
            id: 'my_sub_work_info',
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
