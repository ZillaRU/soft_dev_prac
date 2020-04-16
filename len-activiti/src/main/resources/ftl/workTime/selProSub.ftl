<#-- Created by IntelliJ IDEA.
 User: Administrator
 Date: 2017/12/6
 Time: 14:00
 To change this template use File | Settings | File Templates.
 项目人员信息显示-->
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

<div class="lenos-search">
    <div class="select">
        项目名称:
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="proName" autocomplete="off">
        </div>
        <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>
        </button>
        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"
                data-type="reload">
            <i class="layui-icon">ဂ</i>
        </button>
    </div>
</div>
<table id="myProList" class="layui-hide" lay-filter="workTime" ></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-xs" lay-event="detail">查看</a>
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
                    width: '10%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'proStatus', title: '项目状态', width: '10%'}
                , {field: 'devLeader', title: '开发负责人', width: '10%'}
                , {field: 'dev', title: '开发小组', width: '15%'}
                , {field: 'testLeader', title: '测试负责人', width: '10%'}
                , {field: 'test', title: '测试小组', width: '15%'}
                , {field: 'confMan', title: '配置管理人员', width: '12%'}
                , {field: 'qa', title: 'QA管理人员', width: '12%'}
                , {field: 'epg', title: 'epg人员', width: '10%'}
                , {field: 'operate', title: '操作', width: '8%', toolbar: "#barDemo"}
            ]]
            , page: true
            , height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var proName = $('#proName').val();
                console.info(proName);
                table.reload('projWorkerList', {
                    where: {
                        proName: proName
                    }
                });
            },
            reload: function () {
                $('#proName').val('');
                table.reload('projworkerList', {
                    where: {
                        proName: ""
                    }
                });
            },
            detail: function () {
                var checkStatus = table.checkStatus('projworkerList')
                    , data = checkStatus.data;
                console.log(data);
                detail('查看项目信息', 'showProDetail?proId=' + data[0].id, 900, 600);
            },
        };

        $('.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.select .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.refresh .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //监听工具条
        table.on('tool(act)', function (obj) {
            var data = obj.data;
            console.log(data)
            if (obj.event === 'detail') {
                detail('查看项目人员信息', 'showProDetail?proId=' + data.proId, 700, 500);
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
            id: 'proj-detail',
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
</script>
</body>

</html>
