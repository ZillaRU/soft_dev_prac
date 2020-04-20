<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目功能设置</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
</head>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="edit"><i class="layui-icon">&#xe642;</i>修改</a>
</script>
<body>
<div class="x-body">
    <div class="layui-inline">
        <button type="button" class="layui-btn layui-btn-sm" id="importData" onchange="uploadFile(this)">
            <i class="layui-icon">&#xe67c;</i>导入
        </button>
        <input type="file" style="display: none" id="file" name="file" onchange="uploadFile(this)"/>
        <a href="/excel/exportData?projId=${projectId}">
            <button class="layui-btn layui-btn-sm" id="export_xlsx" onclick="exportData()">
                <i class="layui-icon">&#xe67d;</i>导出
            </button>
        </a>
        <a href="/excelTemple/funcTemple.xls" download="funcTemple.xls">
            <button class="layui-btn layui-btn-sm">
                <i class="layui-icon">&#xe601;</i>模板下载
            </button>
        </a>
    </div>
    <div class="select">
        功能名称:
        <div class="layui-inline">
            <input class="layui-input" height="20px" id="funcName" autocomplete="off">
        </div>
        <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>
        </button>

        <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"
                data-type="reload">
            <i class="layui-icon">ဂ</i>
        </button>
    </div>
</div>
<table id="projFuncList" class="layui-hide" lay-filter="func"></table>
<script>
    layui.use("table", function () {
        var layer = layui.layer, table = layui.table;
        table.render({
            id: 'projFuncList',
            elem: '#projFuncList'
            , url: 'showProjFunc'
            , where: {
                projId: '${projectId}'
            }
            , cols: [[
                {checkbox: false}
                , {field: 'funcId', title: '功能编号', width: '16%', sort: true}
                , {field: 'funcName', title: '功能名称', width: '60%'}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]]
            , page: true,
            height: 'full-83'
        });

        var $ = layui.$, active = {
            select: function () {
                var funcName = $('#funcName').val();
                table.reload('projFuncList', {
                    where: {
                        projId: '${projectId}',
                        funcName: funcName
                    }
                });
            },
            reload: function () {
                $('#funcName').val('');
                table.reload('projFuncList', {
                    where: {
                        projId: '${projectId}',
                        funcName: ""
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
        table.on('tool(func)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                update('编辑功能', 'updateFunc?projId=' + '${projectId}' + '&funcId=' + data.funcId, 700, 450);
            }
        });

        // refreshTable();
        layui.upload.render({
            elem: "#importData",//导入id
            url: "/excel/importData",
            data: {projId: '${projectId}'},
            type: "post",
            size: '3072',
            accept: "file",
            exts: 'xls|xlsx|xlsm|xlt|xltx|xltm',
            done: function (result) {
                table.reload('projFuncList');
                if (result.message != null) {
                    layer.msg(result.message)
                }
            }
        });
    });

    function uploadFile(file) {
        var fileName = $("#importData").val();
        if (fileName == '') {
            layer.msg('请选择文件！', {});
            return false;
        }
        //验证文件格式
        var fileType = (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)).toLowerCase();
        if (fileType != 'xls') {
            layer.msg('文件格式不正确！');
            return false;
        }

        $.ajaxFileUpload({
            url: "/excel/importData", //用于文件上传的服务器端请求地址
            secureuri: false, //一般设置为false
            fileElementId: 'file', //文件上传空间的id属性  <input type="file" id="file" name="file" />
            type: 'post',
            dataType: 'text', //返回值类型 一般设置为
            data: {'projId': '${projectId}'},
            async: true,
            success: function (res) {
                console.log(res);
                layer.msg(res, function () {
                    layui.table.reload("projFuncList")
                });
            }

        });
        return false;
    }

    //导出
    function exportData() {
        $.ajax({
            type: "post", url: "/excel/exportData", data: {'projId': '${projectId}'}, success: function (result) {
                if (result.status == 0) {
                    window.open(result.data)
                }
                if (result.msg != null) {
                    layer.msg(result.msg)
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg('{"status":"' + XMLHttpRequest.status + '","readyState":"' + XMLHttpRequest.readyState + '","textStatus":"' + textStatus + '","errorThrown":"' + errorThrown + '"}')
            }
        })
    }

    //模板下载
    function downloadTemplate() {
        $.ajax({
            type: "post", url: "/excel/downloadTemplate", data: {}, success: function (result) {
                if (result.status == 0) {
                    window.open(result.data)
                }
                if (result.msg != null) {
                    layer.msg(result.msg)
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg('{"status":"' + XMLHttpRequest.status + '","readyState":"' + XMLHttpRequest.readyState + '","textStatus":"' + textStatus + '","errorThrown":"' + errorThrown + '"}')
            }
        })
    }

    function update(title, url, w, h) {
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
            id: 'func-update',
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

