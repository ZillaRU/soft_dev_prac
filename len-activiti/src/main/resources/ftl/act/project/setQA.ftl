<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目QA设置</title>
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
<div class="x-body" style="height: fit-content">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;" autocomplete="off">
        <div style="width:100%;height: 400px;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">项目QA设置</legend>
                </fieldset>
            </div>
                <div class="layui-form-item" style="height: fit-content">
                    <label for="qa_manager" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                        <span class="x-red">*QA Manager</span>
                    </label>
                    <div class="layui-input-inline">
                        <select id="selectId1" name="qaManager" lay-search>
                        </select>
                    </div>
                    <input name="projId" value="${projId}" hidden readonly>
                </div>
                <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
                    <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                        <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                            提交
                        </button>
                        <button class="layui-btn layui-btn-primary" id="close">
                            取消
                        </button>
                    </div>
                </div>
    </form>
</div>
</body>
<script>
    layui.use(['form'], function () {
        $ = layui.jquery;
        var form = layui.form;

        $.ajax({
            url: '/user/showAllUser',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                $('#selectId1').empty();
                for (var u in data['users']) {
                    var dpmt = data['users'][u].department;
                    if (dpmt == 'QA')
                        $('#selectId1').append("<option value='" + data['users'][u].id + "'>" + dpmt + ' ' + data['users'][u].realName + "</option>");
                }
                form.render();
            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            data.field.qaName = $("#selectId1 option:selected").text();
            layerAjax('setUpQA', data.field, 'projList');
        });
    });
</script>
</html>

