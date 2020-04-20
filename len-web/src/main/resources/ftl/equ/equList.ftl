<#-- Created by IntelliJ IDEA.
 User: Administrator
 Date: 2017/12/6
 Time: 14:00
 To change this template use File | Settings | File Templates.
 设备管理-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>设备列表</title>
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
        设备编号：
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="rId" autocomplete="off">
        </div>
        <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>
        </button>
        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"
                data-type="reload">
            <i class="layui-icon">ဂ</i>
        </button>
    </div>

</div>
<div class="layui-col-md12" style="height:40px;margin-top:3px;">
    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-normal" data-type="add">
            <i class="layui-icon">&#xe608;</i>新增
        </button>
    </div>
</div>
<table id="equList" class="layui-hide" lay-filter="equ"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    {{# if(d.rentState == "未归还"){  }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
    {{# }  }}
    {{# if(d.rentState == "已归还"){  }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
    {{# }  }}
</script>
<script>
    layui.laytpl.toDateString = function (d, format) {
        var date = new Date(d || new Date())
            , ymd = [
            this.digit(date.getFullYear(), 4)
            , this.digit(date.getMonth() + 1)
            , this.digit(date.getDate())
        ]
            , hms = [
            this.digit(date.getHours())
            , this.digit(date.getMinutes())
            , this.digit(date.getSeconds())
        ];

        format = format || 'yyyy-MM-dd HH:mm:ss';

        return format.replace(/yyyy/g, ymd[0])
            .replace(/MM/g, ymd[1])
            .replace(/dd/g, ymd[2])
            .replace(/HH/g, hms[0])
            .replace(/mm/g, hms[1])
            .replace(/ss/g, hms[2]);
    };

    //数字前置补零
    layui.laytpl.digit = function (num, length, end) {
        var str = '';
        num = String(num);
        length = length || 2;
        for (var i = num.length; i < length; i++) {
            str += '0';
        }
        return num < Math.pow(10, length) ? str + (num | 0) : num;
    };

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
            id: 'equList',
            elem: '#equList'
            , url: 'showEquList'
            , cols: [[
                // {checkbox: false, fixed: true, width: '5%'}
                // ,
                {field: 'rId', title: '设备编号', width: '15%', sort: true}
                , {field: 'pName', title: '项目名称', width: '10%', sort: true}
                , {field: 'rManager', title: '资产管理者', width: '10%', sort: true}
                , {
                    field: 'rDate',
                    title: '使用期限',
                    width: '15%',
                    templet: '<div>{{ d.rDate ? layui.laytpl.toDateString(d.rDate,"yyyy-MM-dd"): "" }}</div>'
                }
                , {field: 'rState', title: '设备状态', width: '10%'}
                , {field: 'rentState', title: '租借状态', width: '10%'}
                , {
                    field: 'rFinish',
                    title: '归还时间',
                    width: '15%',
                    templet: '<div>{{ d.rFinish ? layui.laytpl.toDateString(d.rFinish,"yyyy-MM-dd"): "" }}</div>'
                }
                , {field: 'right', title: '操作', width: '15%', toolbar: "#barDemo"}
            ]]
            , page: true
            , height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var rId = $('#rId').val();
                table.reload('equList', {
                    where: {
                        rId: rId,
                    }
                });
            },
            reload: function () {
                $('#rId').val('');
                table.reload('equList', {
                    where: {
                        rId: null,
                    }
                });
            },
            add: function () {
                add('添加设备', 'addEquInfo', 700, 450);
            }
        };

        //监听工具条
        table.on('tool(equ)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                console.log(data);
                detail('查看设备', 'showEquDetail?rId=' + data.rId, 1100, 600);
            } else if (obj.event === 'delete') {
                layer.confirm('确定删除设备[<label style="color: #00AA91;">' + data.rId + '</label>]?', function () {
                    deleteEqu('删除设备', 'deleteEqu?rId=' + data.rId);
                });
            } else if (obj.event === 'edit') {
                edit('编辑设备', 'editEqu?rId=' + data.rId, 700, 450);
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

    function deleteEqu(title, url) {
        if (title == null || title === '') {
            title = false;
        }
        if (url == null || url === '') {
            url = "error/404";
        }

        $.ajax({
            url: url,
            type: "get",
            success: function (d) {
                layer.msg(d.msg, {icon: 6});
                layui.table.reload('equList');
            },
            error: function () {
                console.log('error');
                parent.layer.msg("操作失败");
            }
        });
    }

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
            id: 'equ-detail',
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

    /**
     * 更新
     */
    function edit(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            url = "/error/404";
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        layer.open({
            id: 'edit-equ',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url + '&detail=true'
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

        if (url == null || url == '') {
            url = "/error/404";
        }

        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }

        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }

        layer.open({
            id: 'add-equ',
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
