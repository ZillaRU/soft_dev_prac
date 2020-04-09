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
<div class="x-body" style="height: 500px;">

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
                    if (dpmt == 'EPG')
                        $('#selectId1').append("<option value='" + data['users'][u].id + "'>" + dpmt + ' ' + data['users'][u].realName + "</option>");
                }
                form.render();
            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            data.field.epgName = $("#selectId1 option:selected").text();
            layerAjax('setUpEPG', data.field, 'projList');
        });
    });
</script>
</html>

