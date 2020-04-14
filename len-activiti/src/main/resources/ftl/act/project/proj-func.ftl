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
<body>
<div class="x-body">
    <div class="layui-form-item">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
            <legend style="font-size:16px;">项目功能</legend>
        </fieldset>
    </div>
    <button type="button" class="layui-btn layui-btn-normal" id="importData" onchange="uploadFile(this)">
        <i class="layui-icon">&#xe67c;</i>导入
    </button>
    <input type="file" style="display: none" id="file" name="file" onchange="uploadFile(this)"/>
    <button class="layui-btn layui-btn-primary" id="export_xlsx" onclick="exportData()">
        导出
    </button>
    <button class="layui-btn layui-btn-warm" onclick="downloadTemplate()">模板下载</button>
</div>
<table id="projFuncList" class="layui-hide"></table>
<script>
    layui.use("table", function () {
        // layui.use(["layer", "upload", "table"], function () {
        var layer = layui.layer, table = layui.table;
        table.render({
            id: 'projFuncList',
            elem: '#projFuncList'
            , url: 'showProjFunc'
            , where: {projId: '${projectId}'}
            <#--, data: {'projId': ${projectId}}-->
            , cols: [[
                {checkbox: false}
                , {field: 'funcId', title: '功能编号', width: '16%', sort: true}
                , {field: 'funcName', title: '功能名称', width: '60%'}
                // , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]]
            , page: false,
            height: 'full-83'
        });
        // refreshTable();
        <#--layui.upload.render({-->
        <#--    elem: "#importData",//导入id-->
        <#--    url: "/excel/importData",-->
        <#--    data: {'projId': '${projectId}'},-->
        <#--    type: "post",-->
        <#--    size: '3072',-->
        <#--    accept: "file",-->
        <#--    exts: 'xls|xlsx|xlsm|xlt|xltx|xltm',-->
        <#--    done: function (result) {-->
        <#--        if (result.status == 0) {-->
        <#--            refreshTable()-->
        <#--        }-->
        <#--        if (result.message != null) {-->
        <#--            refreshTable();-->
        <#--            layer.msg(result.message)-->
        <#--        }-->
        <#--    }-->
        <#--});-->
    });

    function uploadFile(file) {
        //var clientid = $("#clientid").val();
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
        //var index = layer.msg('正在上传，请稍候',{icon: 16,time:false,shade:0.8});
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
                //var str=data.data.strList;
                // if (res == "SUCCESS") {
                layer.msg(res, function () {
                    window.location.reload();
                });
                // } else {
                //     layer.msg("导入失败");
                // }
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

    function refreshTable() {
        // projFuncList
    }
</script>

</body>
</html>

