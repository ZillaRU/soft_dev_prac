<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目人员显示</title>
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
<div class="add">
    <button class="layui-btn layui-btn-normal" data-type="add">
        <i class="layui-icon">&#xe608;</i>新增
    </button>
</div>
<table id="proWorList" class="layui-hide" lay-filter="worInfoTable" ></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete"><i class="layui-icon">&#xe640;</i>删除</a>
    <a class="layui-btn layui-bg-green layui-btn-sm" lay-event="update"><i class="layui-icon">&#xe642;</i>修改</a>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            id: 'proWorList',
            elem: '#proWorList'
            , url: 'worInfo'
            , cols: [[
                {
                    field: 'proName',
                    title: '项目名称',
                    width: '15%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'userName', title: '项目人员名称', width: '15%'}
                , {field: 'userPhone', title: '项目人员手机', width: '15%'}
                , {field: 'userEmail', title: '项目人员邮箱', width: '20%'}
                , {field: 'proRoleName', title: '项目人员角色', width: '15%'}
                , {field: 'operation', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]]
            , page: false
            , height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var proName = $('#proName').val();
                table.reload('proWorList', {
                    where: {
                        proName: proName
                    }
                });
            },
            reload: function () {
                $('#proName').val('');
                table.reload('proWorList', {
                    where: {
                        proName: ""
                    }
                });
            },
            add: function () {
                add('添加项目人员', 'showAddProWor', 700, 450);
            }
        };

        table.on('tool(worInfoTable)', function (obj)
        {
            var data = obj.data;
            if(obj.event==='delete'){
                layer.confirm('确定删除项目<label style="color: #00AA91;">' + data.proName + '</label>' + '的<label style="color: #00AA91;">' + data.userName + '</label>?', function(){
                    del(data.id);
                });
                }else if(obj.event==='update'){
                update('修改项目人员信息', 'showUpdProWor?id='+data.id, 700, 450);
            }
        });

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
        $('.add .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        function del(id) {
            $.ajax({
                url: "delWor",
                type: "post",
                data: {id: id},
                success: function (d) {
                    if(d.msg){
                        layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                        layui.table.reload('proWorList');
                    }else{
                        layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                }
            });
        }
    });

    function add(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        };
        if (url == null || url == '') {
            url = "/error/404";
        };
        if (w == null || w == '') {
            w = ($(window).width() * 0.7);
        };
        if (h == null || h == '') {
            h = ($(window).height() - 30);
        };
        layer.open({
            id: 'pro_wor_add',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url
        });
    }
    function update(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        };
        if (url == null || url == '') {
            url = "/error/404";
        };
        if (w == null || w == '') {
            w = ($(window).width() * 0.7);
        };
        if (h == null || h == '') {
            h = ($(window).height() - 30);
        };
        layer.open({
            id: 'pro_wor_upd',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url
        });
    }
</script>
</body>

</html>
