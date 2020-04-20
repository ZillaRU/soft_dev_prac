<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>功能更名</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>

</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;" autocomplete="off">
        <div style="width:100%;height: 90%;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">功能新名称</legend>
                </fieldset>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-inline">
                    <input type="text" id="funcName" name="funcName"
                           class="layui-input" style="width: 300px;" lay-verify="nnull">
                </div>
            </div>
            <div style="height: 60px"></div>
        </div>
        <input type="hidden" id="projId" name="projId" value="${projectId}">
        <input type="hidden" id="funcId" name="funcId" value="${funcId}">
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <input type="button" class="layui-btn layui-btn-normal" lay-filter="edit" lay-submit="" value="修改">
                <input type="button" class="layui-btn layui-btn-primary" id="close" value="取消">
            </div>
        </div>
    </form>
</div>
<script>
    layui.use('form', function () {
        $ = layui.jquery;
        var form = layui.form;

        //自定义验证规则
        form.verify({
            nnull: function (value) {
                if (value.trim() == "") {
                    return "*项 不能为空";
                }
            }
        });

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        //监听提交
        form.on('submit(edit)', function (data) {
            $.ajax({
                url: 'editFunc',
                type: 'post',
                data: data.field,
                traditional: true,
                success: function (d) {
                    layer.msg("操作成功!", {time: 1000}, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.layui.table.reload('funcList');
                    });
                    return false;
                }, error: function () {
                    console.log('error');
                }
            });
        });
    });
</script>
</body>

</html>
