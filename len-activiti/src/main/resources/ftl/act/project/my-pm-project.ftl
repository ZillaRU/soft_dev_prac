<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>主管项目</title>
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
<table id="projList" class="layui-hide" lay-filter="proj"></table>
<script type="text/html" id="barDemo">
    <#--https://blog.csdn.net/huangbaokang/article/details/80593566-->
    <#--    <@shiro.hasPermission name="user:select">-->
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">基本信息</a>
    {{#  if(d.projState == '已立项'){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="getProc"><i class="layui-icon">&#xe6b2;</i>审批流程</a>
    {{#  } }}
    <#--    </@shiro.hasPermission>-->
    <#--    shiro-->
    {{#  if(d.projState == '已立项'){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="projFuncs">功能</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="projPerson">人员</a>
    {{#  } }}
    {{#  if(d.projState == '结束'){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="projArchive">申请归档</a>
    {{#  } }}

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
            id: 'projList',
            elem: '#projList'
            , url: 'showPMprojctList'
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
                , {field: 'projMainFunc', title: '主要功能', width: '36%'}
                , {field: 'projState', title: '状态', width: '10%', sort: true}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]]
            , page: false,
            height: 'full-83'
        });

        //监听工具条
        table.on('tool(proj)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                console.log(data.id);
                get_detail_layer('查看项目信息', 'showProjDetail?projId=' + data.id, 900, 600, 'proj_detail_layer');
            } else if (obj.event === 'projFuncs') {
                console.log(data.id);
                get_detail_layer('项目功能设置', 'projFunc?projId=' + data.id, 900, 600, 'proj_func_layer');
            } else if (obj.event === 'projPerson') {
                console.log(data.id);
                // todo
                // get_detail_layer('项目人员设置', 'projFunc?projId=' + data.id, 1100, 600, "proj_per_layer");
            } else if (obj.event === 'getProc') { // 查看流程现在走到了哪
                var url = 'getProc?processInstanceId=' + data.processInstanceId + '';
                console.log(url);
                get_detail_layer('审批详情', 'projApprovalProcess?processInstanceId=' + data.processInstanceId, 900, 600, 'proj_approval_proc_layer');
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
