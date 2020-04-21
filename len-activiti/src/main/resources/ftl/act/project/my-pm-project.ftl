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
<table id="projList" class="layui-hide" lay-filter="proj"></table>
<script type="text/html" id="barDemo">
    {{#  if(d.projState == '申请立项'){ }}
    <a class="layui-btn layui-btn-xs" lay-event="editDetail">修正信息</a>
    {{#  } else{ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">基本信息</a>
    {{#  } }}
    {{#  if(d.projState == '已立项' || d.projState == '申请立项' || d.projState == '已驳回'){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="getProc"><i class="layui-icon">&#xe6b2;</i>审批流程</a>
    {{#  } }}
    {{#  if(d.projState == '已立项'){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="projFuncs">功能</a>
    {{#  } }}
    {{#  if(d.projState == '进行中'){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="projReady">交付确认</a>
    {{#  } }}
    {{#  if(d.projState == '已交付'){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="projEnd">结束确认</a>
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
                    width: '18%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'projNo', title: '编号', width: '10%', sort: true}
                , {field: 'projCustomer', title: '客户代号', width: '10%'}
                , {field: 'projMainFunc', title: '主要功能', width: '20%'}
                , {field: 'projState', title: '状态', width: '10%', sort: true}
                , {field: 'right', title: '操作', width: '24%', toolbar: "#barDemo"}
            ]]
            , page: true,
            height: 'full-83'
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
        table.on('tool(proj)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                get_detail_layer('查看项目信息', 'showProjDetail?projId=' + data.id, 900, 600, 'proj_detail_layer');
            } else if (obj.event === 'editDetail') {
                get_detail_layer('查看项目信息', 'editProjDetail?projId=' + data.id, 900, 600, 'proj_detail_layer');
            } else if (obj.event === 'projFuncs') {
                get_detail_layer('项目功能设置', 'projFunc?projId=' + data.id, 900, 600, 'proj_func_layer');
            } else if (obj.event === 'projArchive') {
                confirm_op("确认申请归档？", 'archiveCheck', data.id);
            } else if (obj.event === 'getProc') { // 查看流程现在走到了哪
                get_detail_layer('审批详情', 'projApprovalProcess?processInstanceId=' + data.processInstanceId, 900, 600, 'proj_approval_proc_layer');
            } else if (obj.event === 'projReady') {
                confirm_op("确认已交付给客户？", 'projReady', data.id);
            } else if (obj.event === 'projEnd') {
                confirm_op("确认项目结束？", 'projEnd', data.id);
            }
        });

    });

    function confirm_op(alert_msg, url, projId) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.msg(alert_msg, {
                btn: ['确定', '取消'],
                yes: function (index) {
                    $.ajax({
                        url: url,
                        data: {
                            'projId': projId
                        },
                        type: "POST",
                        dataType: "json",
                        success: function (data) {
                            var index = parent.layer.getFrameIndex(window.name);
                            window.top.layer.msg(data.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                            parent.layer.close(index);
                            parent.location.replace(parent.location.href);
                        }, error: function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            window.top.layer.msg('请求失败', {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                            parent.layer.close(index);
                        }
                    });
                    layer.close(index);
                }
            });
        });
    }

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
