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

</div>
<table id="riskList" class="layui-hide" lay-filter="risk"></table>
<script type="text/html" id="barDemo">
    <#--    <@shiro.hasPermission name="user:select">-->
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
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
                {field: 'hid', title: '风险编号', width: '25%', sort: true}
                , {field: 'pname', title: '项目名称', width: '20%', sort: true}
                , {field: 'htype', title: '风险类型', width: '10%'}
                , {field: 'hstate', title: '风险状态', width: '10%'}
                , {field: 'hgrade', title: '风险级别', width: '10%'}
                , {field: 'hinfluence', title: '风险影响度', width: '10%'}
                , {field: 'hfrequency', title: '风险跟踪频度', width: '5%'}
                // , {field: 'hdes', title: '风险描述', width: '10%'}
                // , {field: 'htactics', title: '风险应对策略', width: '10%'}
                , {field: 'right', title: '操作', width: '10%', toolbar: "#barDemo"}
            ]],
            height: 'full-83'
        });

        var $ = layui.$, active = {
            // select: function () {
            //     var projname = $('#projname').val();
            //     console.info(projname);
            //     table.reload('projList', {
            //         where: {
            //             projName: projname
            //         }
            //     });
            // },
            // reload: function () {
            //     $('#projname').val('');
            //     table.reload('projList', {
            //         where: {
            //             projName: null
            //         }
            //     });
            // },
            // detail: function () {
            //     var checkStatus = table.checkStatus('riskList')
            //         , data = checkStatus.data;
            //     if (data.length != 1) {
            //         layer.msg('请选择一行查看,已选[' + data.length + ']行', {icon: 5});
            //         return false;
            //     }
            //     detail('查看项目信息', 'showRiskDetail?riskId=' + data[0].id, 1100, 600);
            // }
        };

        //监听表格复选框选择
        table.on('checkbox(risk)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(risk)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                console.log(data);
                detail('查看项目信息', 'showRiskDetail?riskId=' + data.hid, 1100, 600);
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

    function detail(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        ;
        if (url == null || url == '') {
            url = "error/404";
        }
        ;
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        ;
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        ;
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

    /*弹出层*/
    /*
     参数解释：
     title   标题
     url     请求的url
     id      需要操作的数据id
     w       弹出层宽度（缺省调默认值）
     h       弹出层高度（缺省调默认值）
     */
    function add(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        ;
        if (url == null || url == '') {
            url = "404.html";
        }
        ;
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        ;
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        ;
    }
</script>
</body>

</html>
