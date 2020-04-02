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
<table id="projWorkerList" class="layui-hide" lay-filter="proj" ></table>
<script type="text/html" id="barDemo">
    <#--    <@shiro.hasPermission name="user:select">-->
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <#--    </@shiro.hasPermission>-->
    <#--    shiro-->
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="projFuncs">项目功能</a>
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
            id: 'projWorkerList',
            elem: '#projWorkerList'
            , url: 'showPMprojctList'
            , cols: [[
                {checkbox: false}
                , {
                    field: 'proName',
                    title: '项目名称',
                    width: '10%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'distrWorker', title: '分配人员', width: '10%'}
                , {field: 'proStatus', title: '项目状态', width: '10%'}
                , {field: 'testLeaderName', title: '测试负责人', width: '10%'}
                , {field: 'devWorkerName', title: '测试人员', width: '10%'}
                , {field: 'configManagerName', title: '配置管理人员', width: '12%'}
                , {field: 'qaManagerName', title: 'QA管理人员', width: '12%'}
                , {field: 'epgLeaderName', title: 'epg人员', width: '10%'}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]],
            height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var projworkername = $('#projworkername').val();
                console.info(proworkerjname);
                table.reload('projWorkerList', {
                    where: {
                        projName: projname
                    }
                });
            },
            reload: function () {
                $('#projworkername').val('');
                table.reload('projworkerList', {
                    where: {
                        projworkerName: null
                    }
                });
            },
            detail: function () {
                var checkStatus = table.checkStatus('projworkerList')
                    , data = checkStatus.data;
                if (data.length != 1) {
                    layer.msg('请选择一行查看,已选[' + data.length + ']行', {icon: 5});
                    return false;
                }
                detail('查看项目信息', 'showProjWorkerDetail?projId=' + data[0].id, 1100, 600);
            },
            projFuncs: function () {
                var checkStatus = table.checkStatus('projworkerList')
                    , data = checkStatus.data;
                if (data.length != 1) {
                    layer.msg('请选择一行操作,已选[' + data.length + ']行', {icon: 5});
                    return false;
                }
                setProjFuncs('项目功能设置', 'projFunc?projId=' + data[0].id, 1100, 600);
            }
        };

        //监听工具条
        table.on('tool(proj)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                console.log(data.id);
                detail('查看项目信息', 'showProjDetail?projId=' + data.id, 1100, 600);
            } else if(obj.event === 'projFuncs') {
                console.log(data.id);
                detail('项目功能设置', 'projFunc?projId=' + data.id, 1100, 600);
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

    function setProjFuncs(title, url, w, h) {
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
            id: 'proj-funcs',
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
