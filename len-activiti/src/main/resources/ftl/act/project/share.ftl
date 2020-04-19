<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目基本信息共享</title>
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

<table id="projList" class="layui-hide" lay-filter="projTable"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="detail"><i class="layui-icon">&#xe640;</i>基本信息</a>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            id: 'projList',
            elem: '#projList'
            , url: 'getAllProj'
            , cols: [[
                {checkbox: false}
                , {
                    field: 'projName',
                    title: '项目名称',
                    width: '20%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'projNo', title: '编号', width: '16%', sort: true}
                , {field: 'projCustomer', title: '客户代号', width: '10%'}
                , {field: 'projMainFunc', title: '主要功能', width: '24%'}
                , {field: 'projState', title: '状态', width: '10%', sort: true}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]]
            , page: true
            , height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var proName = $('#proName').val();
                table.reload('projList', {
                    where: {
                        proName: proName
                    }
                });
            },
            reload: function () {
                $('#proName').val('');
                table.reload('projList', {
                    where: {
                        proName: ""
                    }
                });
            }
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
        table.on('tool(projTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                console.log(data.id);
                get_detail_layer('查看项目信息', 'showProjDetail?projId=' + data.id, 900, 600, 'proj_detail_layer');
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
</script>
</body>

</html>
