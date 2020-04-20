<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>项目风险信息</title>
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

<div class="layui-form-item">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
        <legend style="font-size:16px;">导入模板信息</legend>
    </fieldset>
</div>
<table id="riskImportList" class="layui-hide" lay-filter="risk"></table>
<script type="text/html" id="barDemo">

    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="import">导入</a>

</script>

<script>
    layui.use(['table', 'form'], function () {
        $ = layui.jquery;
        var table = layui.table, form = parent.layui.form;
        //方法级渲染
        table.render({
            id: 'riskImportList',
            elem: '#riskImportList'
            , url: '${re.contextPath}/ftl/rsk/risk-list.json'
            , cols: [[
                {field: 'hDes', title: '风险描述', width: '30%', sort: true}
                , {field: 'hTactics', title: '应对策略', width: '50%'}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]],
            height: 'full-83'
        });

        table.on('tool(risk)', function (obj) {
            var data = obj.data;
            if (obj.event === 'import') {
                console.log(data);

                window.parent.$("#selectHType option[value='" + data.hType + "']").prop("selected", true);
                window.parent.$("#selectHGrade option[value='" + data.hGrade + "']").prop("selected", true);
                window.parent.$("#selectHInfluence option[value='" + data.hInfluence + "']").prop("selected", true);
                window.parent.$("#selectHState option[value='" + data.hState + "']").prop("selected", true);
                window.parent.$("#txtHDes").text(data.hDes);
                window.parent.$("#txtHTactics").text(data.hTactics);
                form.render();
            }
        });

    });

</script>
</body>

</html>
