<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>新增项目人员</title>
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
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height: 100%;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">人员信息填写</legend>
                </fieldset>
            </div>
            <div class="layui-form-item">
                <label for="projname" class="layui-form-label" style="width:100px;padding: 9px 0px;">
                    <span class="x-red">*</span>选择项目
                </label>
                <div class="layui-input-inline">
                    <select id="proId" name="proId" lay-search>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="userId" class="layui-form-label" style="width:100px;padding: 9px 0px;">
                        人员姓名
                    </label>
                    <div class="layui-input-inline">
                        <select id="userId" name="userId" lay-search>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="proRoleId" class="layui-form-label" style="width:100px;padding: 9px 0px;">
                        项目角色选择
                    </label>
                    <div class="layui-input-inline">
                        <select id="proRoleName" name="proRoleName">
                            <option value="devleader">开发负责人</option>
                            <option value="testleader">测试负责人</option>
                            <option value="dev">开发人员</option>
                            <option value="test">测试人员</option>
                            <option value="confman">配置管理员</option>
                            <option value="qa">QA</option>
                            <option value="epg">EPG</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                    增加
                </button>
                <button class="layui-btn layui-btn-primary" id="close" lay-even="">
                    取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form, layer = layui.layer;
        $.ajax({
            url: '/projectWorker/showAllPro',
            type: 'post',
            dataType: 'json',
            success: function(data){
                $('#proId').empty();
                for (var u in data['projs']){
                    $('#proId').append("<option value= '"+data['projs'][u].id +"'>"+ data['projs'][u].projName + "</option>");
                }
                form.render();
            }
        });
        $.ajax({
            url: '/user/showAllUser',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                $('#userId').empty();
                for (var u in data['users']) {
                    $('#userId').append("<option value= '"+ data['users'][u].id +"'>"+ data['users'][u].realName + "</option>");
                    }
                form.render();
            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            data.field.userId = $("#userId option:selected").val();
            data.field.proRoleName = $("#proRoleName option:selected").val();
            data.field.proId = $("#proId option:selected").val();
            data.field.proName = $("#proId option:selected").text();
            layerAjax('addProWor', data.field, 'proWorList');
            return false;
        });

        //点击取消按钮弹窗消失
        $('#close').click(function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    });
</script>
</body>

</html>
