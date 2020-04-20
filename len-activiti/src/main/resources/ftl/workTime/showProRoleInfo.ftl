<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>项目角色信息表单</title>
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
    <form class="layui-form layui-form-pane" style="margin-left: 20px; margin-top: 20px;">
        <div class="layui-form-item">
            <label for="proName" class="layui-form-label layui-bg-green" style="width: 130px">
                项⽬名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="proName" value="${proRoleOb.proName}" class="layui-input"
                       style="width:400px;text-align:center" readonly>
            </div>
        </div>
        <div class="layui-form-item ">
            <label for="pmName" class="layui-form-label layui-bg-green" style="width: 130px">
                项⽬经理
            </label>
            <div class="layui-input-inline">
                <input type="text" id="pmName" value="${proRoleOb.pmName}" class="layui-input"
                       style="width:400px; text-align:center" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="proDevLeader" class="layui-form-label layui-bg-green"
                   style="width: 130px">配置管理人员</label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" id="proConfMan" value="${proRoleOb.confMan}" class="layui-input"
                           style="width: 400px; text-align:center" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="proDevLeader" class="layui-form-label layui-bg-green" style="width: 130px">
                EPG
            </label>
            <div class="layui-input-inline">
                <input type="text" id="proEpg" value="${proRoleOb.epg}" class="layui-input"
                       style="width:400px;text-align:center" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="proDevLeader" class="layui-form-label layui-bg-green" style="width: 130px">
                QA
            </label>
            <div class="layui-input-inline">
                <input type="text" id="proQa" value="${proRoleOb.qa}" class="layui-input"
                       style="width:400px;text-align:center" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="proDevLeader" class="layui-form-label layui-bg-green" style="width: 130px">
                开发负责人
            </label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" id="proDevLeader" value="${proRoleOb.devLeader}" class="layui-input"
                           style="width:400px;text-align:center" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="proDev" class="layui-form-label layui-bg-green" style="width: 130px">
                开发人员
            </label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" id="proDev" value="${proRoleOb.dev}" class="layui-input"
                           style="width:400px;text-align:center" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="proTestLeader" class="layui-form-label layui-bg-green" style="width:130px;">测试负责人</label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" id="proTest" value="${proRoleOb.testLeader}" class="layui-input"
                           style="width:400px;text-align:center" readonly>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="proTest" class="layui-form-label layui-bg-green"
                   style="width:130px;">
                测试人员
            </label>
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" id="proTest" value="${proRoleOb.test}" class="layui-input"
                           style="width:400px;text-align:center" readonly>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
