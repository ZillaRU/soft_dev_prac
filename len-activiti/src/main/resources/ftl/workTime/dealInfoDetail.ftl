<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>工时信息详情查看</title>
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
    <form class="layui-form layui-form-pane" style="margin-left: 20px; margin-top:20px " autocomplete="off">
        <div style="width:100%;height: 90%;overflow: auto;">
            <div class="layui-form-item">
                <label for="projname" class="layui-form-label" style="width:130px;">
                    项目名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="proName" value="${baseInfo.proName}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="pmName" class="layui-form-label" style="width:130px;">
                    项目经理
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="pmName" value="${baseInfo.pmName}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="receiveUserName" class="layui-form-label" style="width:130px;">
                    工时信息提交人
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="sendUserName" value="${baseInfo.sendUserName}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="subDate" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    提交日期
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="subDate" name="subDate" value="${baseInfo.submitDate}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="chooseFunc" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    功能名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="funcName" name="funcName" value="${baseInfo.funcName}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="chooseActiv" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    活动名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="activId" name="activId" value="${baseInfo.activId}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="startTime" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    开始时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="startTime" name="startTime" value="${baseInfo.startTime}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="endTime" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="endTime" name="endTime" value="${baseInfo.endTime}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="note" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    备注:
                </label>
                <textarea type="text" id="note" name="note" style="width:300px; height: 120px" readonly>
                    ${baseInfo.note}
                </textarea>
            </div>
        </div>
    </form>
</div>
</body>

</html>
