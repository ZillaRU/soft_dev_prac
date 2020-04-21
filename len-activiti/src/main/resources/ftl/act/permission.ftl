<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限分配及管理</title>
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
<table id="perManList" class="layui-hide" lay-filter="permiManTable" ></table>
<script type="text/html" id="docPer">
    {{# if (d.permiDoc == "no") { }}
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="addDoc">
        <i class="layui-icon">&#xe654;</i>开通</a>
    {{# } else { }}
    <a class="layui-btn layui-btn-primary layui-bg-orange layui-btn-sm " lay-event="cancelDoc">
        <i class="layui-icon">&#xe640;</i>取消</a>
    {{# } }}
</script>
<script type="text/html" id="gitPer">
    {{# if (d.permiGit == "no") { }}
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="addGit">
        <i class="layui-icon">&#xe654;</i>开通</a>
    {{# } else { }}
    <a class="layui-btn layui-btn-primary layui-bg-orange layui-btn-sm " lay-event="cancelGit">
        <i class="layui-icon">&#xe640;</i>取消</a>
    {{# } }}
</script>
<script type="text/html" id="wokTiPer">
    {{# if (d.permiWokTi == "no") { }}
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="addWokTi">
        <i class="layui-icon">&#xe654;</i>开通</a>
    {{# } else { }}
    <a class="layui-btn layui-btn-primary layui-bg-orange layui-btn-sm " lay-event="cancelWokTi">
        <i class="layui-icon">&#xe640;</i>取消</a>
    {{# } }}
</script>
<script type="text/html" id="inEmailPer">
    {{# if (d.permiInEmail == "no") { }}
    <a class="layui-btn layui-btn-primary layui-bg-green layui-btn-sm" lay-event="addEmail">
        <i class="layui-icon">&#xe654;</i>开通</a>
    {{# } else { }}
    <a class="layui-btn layui-btn-primary layui-bg-orange layui-btn-sm " lay-event="cancelEmail">
        <i class="layui-icon">&#xe640;</i>取消</a>
    {{# } }}
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">
        <i class="layui-icon">&#xe640;</i>删除</a>
</script>
<script>
    var use = layui.use('table', function () {
        var table = layui.table;
        table.render({
            id: 'perManList',
            elem: '#perManList'
            , url: 'perManList'
            , cols: [[
                {
                    field: 'proName',
                    title: '项目名称',
                    width: '15%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'userName', title: '项目人员名称', width: '15%'}
                , {field: 'proRoleName', title: '项目人员角色', width: '24%'}
                , {field: 'permiDoc', title: '文件访问权限', width: '12%',
                    templet: '#docPer'}
                , {field: 'permiGit', title: 'git访问权限', width: '12%',
                    templet: '#gitPer'}
                , {field: 'permiInEmail', title: '邮件列表', width: '10%',
                    templet: '#inEmailPer'}
                , {field: 'operation', title: '删除人员', width: '12%', toolbar: "#barDemo"}
            ]]
            , page :true
            , height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var proName = $('#proName').val();
                table.reload('perManList', {
                    where: {
                        proName: proName
                    }
                });
            },
            reload: function () {
                $('#proName').val('');
                table.reload('perManList', {
                    where: {
                        proName: ""
                    }
                });
                select();
            }
        };

        table.on('tool(permiManTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'addDoc') {
                addDoc(data.id, "permiDoc");
            } else if (obj.event === 'cancelDoc') {
                cancelDoc(data.id, "permiDoc");
            } else if (obj.event === 'addGit') {
                addGit(data.id, "permiGit");
            } else if (obj.event === 'cancelGit') {
                cancelGit(data.id, "permiGit");
            } else if (obj.event === 'addEmail') {
                addEmail(data.id, "permiEmail");
            } else if (obj.event === 'cancelEmail') {
                cancelEmail(data.id, "permiEmail");
            } else if (obj.event === 'delete') {
                layer.confirm('确定删除项目<label style="color: #00AA91;">' + data.proName + '</label>'+'的成员'
                    + '<label style="color: #00AA91;">' + data.userName + '</label>'
                    + ',同时取消他在此项目中的一切权限' + '</label>?', function () {
                    del(data.id);
                });
            }
        });

        $('.select .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.reload .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });

        function addDoc(id, permiDoc) {
            $.ajax({
                url: "addPermission",
                type: "post",
                data: {
                    id: id,
                    permission: permiDoc
                },
                success: function (d) {
                    if(d.msg){
                        layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                        layui.table.reload('perManList');
                    }else{
                        layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                }
            });
        }

        function cancelDoc(id, permiDoc) {
            $.ajax({
                url: "cancelPermission",
                type: "post",
                data: {
                    id: id,
                    permission: permiDoc},
                success: function (d) {
                    if(d.msg){
                        layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                        layui.table.reload('perManList');
                    }else{
                        layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                }
            });
        }

        function addGit(id, permiGit) {
            $.ajax({
                url: "addPermission",
                type: "post",
                data: {
                    id: id,
                    permission: permiGit
                },
                success: function (d) {
                    if(d.msg){
                        layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                        layui.table.reload('perManList');
                    }else{
                        layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                }
            });
        }

        function cancelGit(id, permiGit) {
            $.ajax({
                url: "cancelPermission",
                type: "post",
                data: {
                    id: id,
                    permission: permiGit
                },
                success: function (d) {
                    if(d.msg){
                        layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                        layui.table.reload('perManList');
                    }else{
                        layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                }
            });
        }

        function addEmail(id, permiEmail) {
            $.ajax({
                url: "addPermission",
                type: "post",
                data: {
                    id: id,
                    permission: permiEmail
                },
                success: function (d) {
                    if(d.msg){
                        layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                        layui.table.reload('perManList');
                    }else{
                        layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                }
            });
        }

        function cancelEmail(id, permiEmail) {
            $.ajax({
                url: "cancelPermission",
                type: "post",
                data: {
                    id: id,
                    permission: permiEmail
                },
                success: function (d) {
                    if (d.msg) {
                        layer.msg(d.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                        layui.table.reload('perManList');
                    } else {
                        layer.msg(d.msg, {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                    }
                }
            });
        }


    function del(id){
        $.ajax({
            url: "delWorPermi",
            type: "post",
            data: {id: id},
            success: function (d) {
                if(d.msg){
                    layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                    layui.table.reload('perManList');
                }else{
                    layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                }
            }
        });
    }
</script>
</body>

</html>
