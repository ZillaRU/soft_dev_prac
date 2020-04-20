<#-- Created by IntelliJ IDEA.
 User: Administrator
 Date: 2017/12/6
 Time: 14:00
 To change this template use File | Settings | File Templates.
 用户管理-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>风险列表</title>
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
    <#--    <div class="select">-->
    <#--        风险名称：-->
    <#--        <div class="layui-inline">-->
    <#--            <input class="layui-input" height="20px" id="projname" autocomplete="off">-->
    <#--        </div>-->
    <#--        <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>-->
    <#--        </button>-->
    <#--        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"-->
    <#--                data-type="reload">-->
    <#--            <i class="layui-icon">ဂ</i>-->
    <#--        </button>-->
    <#--    </div>-->
    参与的项目的所有风险列表
</div>
<table id="riskList" class="layui-hide" lay-filter="risk"></table>
<script type="text/html" id="barDemo">

    <#--    <@shiro.hasPermission name="user:select">-->
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    {{# if((!d.hcreator?' ':d.hcreator) == '${user.id}'){  }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
    {{# } else{
    for(var member in d.hmember){
    if(d.hmember[member] == '${user.username}'){
    }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
    {{#     }
    }
    }  }}


    {{# if((!d.hcreator?' ':d.hcreator) == '${user.id}'){  }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
    {{# }  }}
    {{# for(var member in d.hmember){
    console.log('${user.username}');
    console.log(d.hmember[member]);
    if(d.hmember[member] == '${user.username}'){
    }}
    <a class="layui-btn layui-btn-xs" lay-event="trace">跟踪</a>
    {{#     }
    }
    }}

    {{# var currentUser= "${Session['currentPrincipal'].currentRoleList[0].roleName}";
    console.log(currentUser);
    if(currentUser==="pm" && d.hfrequency < 2){
    }}
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="email">跟踪提醒</a>
    {{#     }
    }}

    <#--    </@shiro.hasPermission>-->
</script>
<script>

    document.onkeydown = function (e) { // 回车提交表单
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code == 13) {
            $(".select .select-on").click();
        }
    }

    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        table.render({
            id: 'riskList',
            elem: '#riskList'
            , url: 'showRiskList'
            , cols: [[
                // {checkbox: false, fixed: true, width: '5%'}
                // ,
                {field: 'hid', title: '风险编号', width: '20%', sort: true}
                , {field: 'hdes', title: '风险描述', width: '20%'}
                , {field: 'pname', title: '项目名称', width: '15%', sort: true}
                , {field: 'htype', title: '风险类型', width: '10%'}
                // , {field: 'hstate', title: '风险状态', width: '10%'}
                // , {field: 'hgrade', title: '风险级别', width: '10%'}
                // , {field: 'hinfluence', title: '风险影响度', width: '10%'}
                , {field: 'hfrequency', title: '跟踪频度', width: '10%'}

                // , {field: 'htactics', title: '风险应对策略', width: '10%'}
                , {field: 'right', title: '操作', width: '25%', toolbar: "#barDemo"}
            ]],
            height: 'full-83'
        });

        //监听工具条
        table.on('tool(risk)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                console.log(data);
                detail('查看风险信息', 'showRiskDetail?riskId=' + data.hid, 1100, 600);
            } else if (obj.event === 'delete') {
                console.log(data);
                deleteRisk('删除风险信息', 'deleteRisk?riskId=' + data.hid);
            } else if (obj.event === 'edit') {
                edit('编辑风险信息', 'editRisk?riskId=' + data.hid, 1100, 600)
            } else if (obj.event === 'trace') {
                trace('跟踪风险', 'traceRisk?riskId=' + data.hid);
            } else if (obj.event === 'email') {
                email('发邮件', 'emailRisk?riskId=' + data.hid);
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

    });

    function deleteRisk(title, url) {
        if (title == null || title === '') {
            title = false;
        }
        if (url == null || url === '') {
            url = "error/404";
        }

        $.ajax({
            url: url,
            type: 'get',
            success: function (d) {
                console.log(d);
                parent.layer.msg("操作成功!", {time: 1000}, function () {
                    //重新加载父页面
                    layui.table.reload('riskList');
                });
            },
            error: function () {
                console.log('error');
                parent.layer.msg("操作失败", {time: 1000}, function () {
                    //重新加载父页面
                    // parent.location.reload();
                });
            }
        });
    }

    function detail(title, url, w, h) {
        if (title == null || title === '') {
            title = false;
        }

        if (url == null || url === '') {
            url = "error/404";
        }

        if (w == null || w === '') {
            w = ($(window).width() * 0.9);
        }

        if (h == null || h === '') {
            h = ($(window).height() - 50);
        }

        layer.open({
            id: 'risk-detail',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url + '&detail=true',
            // btn:['关闭']
        });
    }

    function edit(title, url, w, h) {
        if (title == null || title === '') {
            title = false;
        }
        if (url == null || url === '') {
            url = "error/404";
        }
        if (w == null || w === '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h === '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            id: 'edit-risk-info',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url + '&detail=true',
            // btn:['关闭']
        });
    }


    function trace(title, url) {
        if (title == null || title === '') {
            title = false;
        }
        if (url == null || url === '') {
            url = "error/404";
        }

        $.ajax({
            url: url,
            type: 'get',
            success: function (d) {
                console.log(d);
                parent.layer.msg("跟踪成功", {time: 1000}, function () {
                    layui.table.reload('riskList');
                });
            },
            error: function () {
                console.log('error');
                parent.layer.msg("操作失败", {time: 1000}, function () {
                });
            }
        });
    }

    function email(title, url) {
        if (title == null || title === '') {
            title = false;
        }
        if (url == null || url === '') {
            url = "error/404";
        }

        $.ajax({
            url: url,
            type: 'get',
            success: function (d) {
                console.log(d);
                parent.layer.msg("提醒成功，已发送邮件", {time: 1000}, function () {
                    // layui.table.reload('riskList');
                });
            },
            error: function () {
                console.log('error');
                parent.layer.msg("操作失败", {time: 1000}, function () {
                    //重新加载父页面
                    // parent.location.reload();
                });
            }
        });
    }
</script>
</body>

</html>
