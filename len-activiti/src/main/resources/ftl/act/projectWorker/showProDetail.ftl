<#--Created by IntelliJ IDEA.
User: zhuxiaomeng
Date: 2017/12/18
Time: 10:05
To change this template use File | Settings | File Templates.-->

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>项目人员信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/ztree/css/metroStyle/metroStyle.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/update-setting.js"></script>
</head>

<body>
<div class="x-body">
    <div style="width:100%;height:100px;overflow: auto;">
        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">项⽬人员信息详情</legend>
            </fieldset>
        </div>
        <div class="layui-form-item">
            <label for="proname" class="layui-form-label">
                项⽬名称
            </label>
            <div class="layui-input-inline">
                <input value="${baseInfo.proId}" type="hidden" name="id">
                <input type="text" id="proName" value="${baseInfo.proName}" autocomplete="off" class="layui-input"
                       readonly>
            </div>
            <div class="layui-inline">
                <label for="proStatus" class="layui-form-label">
                    项⽬状态
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="proStatus" name="proStatus" value="${baseInfo.proStatus}" autocomplete="off"
                           class="layui-input">
                </div>
                <div id="finishStatus" class="layui-form-mid layui-word-aux">
                    <span id="finishStatus"></span>
                </div>
            </div>
        </div>
    </div>
</div>
<table id="worList" class="layui-table" lay-filter="worList" ></table>


<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            id: 'worList',
            elem: '#worList'
            , url: 'allWor?proId=' + '${baseInfo.proId}'
            , cols: [[
                {field: 'proRoleName', title: '项目人员角色', width: '20%'}
                , {field: 'userName', title: '项目人员名称', width: '20%'}
                , {field: 'userEmail', title: '项目人员邮箱', width: '20%'}
                , {field: 'userPhone', title: '项目人员电话', width: '20%'}
            ]]
            , page: true
            , height: 'full-83'
        });
    });
    $(function () {
        if ($('#proStatus').val() === "未完成")
            $("#finishStatus").text("未完成全部角色的导入");
    });


</script>
</body>

</html>
