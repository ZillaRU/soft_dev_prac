<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>写入项目工时信息提交</title>
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
                    工时信息审核人
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="receiveUserName" value="${baseInfo.receiveUserName}" class="layui-input"
                           style="text-align:center" readonly>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="subDate" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">*</span>选择提交日期
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="subDate" name="subDate" placeholder="YYYY-MM-dd"
                            autocomplete="off" class="layui-input" lay-verify="subDate">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="chooseFunc" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">*</span>选择功能名称
                </label>
                <div class="layui-input-inline">
                    <select id="chooseFunc" name="chooseFunc" lay-search lay-filter="chooseFunc">
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="chooseActiv" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">*</span>选择活动名称
                </label>
                <div class="layui-input-inline">
                    <select id="chooseActiv" name="chooseActiv" lay-search lay-verify="nnull">
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="startTime" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">*</span>选择开始时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="startTime" name="startTime" autocomplete="off" class="layui-input"
                           placeholder="HH:mm:ss" lay-verify="substartTime">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="endTime" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">*</span>选择结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="endTime" name="endTime" autocomplete="off" placeholder="HH:mm:ss" class="layui-input" lay-verify="subEndTime">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="note" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    备注:
                </label>
                <textarea type="text" id="note" name="note"
                          autocomplete="off" class="layui-input-block"
                          style="width:300px; height: 120px">
                </textarea>
            </div>
        </div>

        <div style="height: 60px">
            <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
                <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                    <button class="layui-btn layui-btn-normal" lay-filter="submit" lay-submit="">
                        确认提交
                    </button>
                    <button class="layui-btn layui-btn-primary" id="close">
                        取消
                    </button>
                </div>
            </div>
        </div>
    </form>
<script>
    layui.use(['form', 'laydate', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form, laydate = layui.laydate, layer = layui.layer;
        laydate.render({
            elem: "#subDate",
            type: "date",
            min: -2,
            max: 0
        });
        laydate.render({
            elem: '#startTime',
            type: 'time',
            format: 'HH:mm:ss'
            });
        laydate.render({
            elem: '#endTime',
            type: 'time',
            format: 'HH:mm:ss'
            });

        $.ajax({
            url: '/workTime/allFuncs?proId='+ '${baseInfo.proId}',
            type: 'get',
            dataType: 'json',
            success: function (data) {
                for(var u=0; u<data['projs'].length; u++){
                    $('#chooseFunc').append("<option value= '"+data['projs'][u].funcId+"'>" + data['projs'][u].funcName + "</option>");
                }
                form.render();
            }
        });

        $.ajax({
            url: '/workTime/allActiv',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                for(var u=0; u<data['activ_type'].length; u++){
                    if(u===0){
                        for (var u1 = 0; u1 < data['engi_activ'].length; u1++) {
                            $('#chooseActiv').append("<option value= '" + "00000"+ u1 + "'>" + data['activ_type'][u]+">"+data['engi_activ'][u1] + "</option>");
                        }
                    }
                    else if(u===1){
                        for (var u2 = 0; u2 < data['mana_activ'].length; u2++) {
                            $('#chooseActiv').append("<option value= '" + "00100"+ u2 + "'>" + data['activ_type'][u]+">"+data['mana_activ'][u2] + "</option>");
                        }
                    }
                    else if (u===2) {
                        for (var u3 = 0; u3 < data['out_activ'].length; u3++) {
                            $('#chooseActiv').append("<option value= '" + "00200"+ u3 + "'>" + data['activ_type'][u]+">"+data['out_activ'][u3] + "</option>");
                        }
                    }
                    else {
                        for (var u4 = 0; u4 < data['supp_activ'].length; u4++) {
                            $('#chooseActiv').append("<option value= '" + "00300"+ u4 + "'>" + data['activ_type'][u]+">"+data['supp_activ'][u4] + "</option>");
                        }
                    }
                }
                form.render();
            }
        });

        form.render();

        //自定义验证规则
        form.verify({
            nnull: function (value) {
                if (value.trim() == "") {
                    return "*项 不能为空";
                }
            },
            subDate: function (value) {
                if (value.trim() != "" && !(value.trim().match("[0-9]{4}-[0-9]{2}-[0-9]{2}"))) {
                    return "日期格式填写错误";
                }
            },
            substartTime: function (value) {
                if (value.trim() != "" && !(value.trim().match("[0-9]{2}:[0-9]{2}:[0-9]{2}"))) {
                    return "时间格式填写错误";
                }
            },
            subEndTime: function (value) {
                var staTime = $('#startTime').val();
                if (value.trim() != "" && !(value.trim().match("[0-9]{2}:[0-9]{2}:[0-9]{2}"))) {
                    return "时间格式填写错误";
                }
                else if(value<staTime){
                    return "结束时间应大于开始时间";
                }
            }
        });

        //监听提交
        form.on('submit(submit)', function (data) {
            data.field.proName = "${baseInfo.proName}";
            data.field.proId = "${baseInfo.proId}";
            data.field.pmName = "${baseInfo.pmName}";
            data.field.receiveUserId = "${baseInfo.receiveUserId}";
            data.field.receiveUserName = '${baseInfo.receiveUserName}';
            data.field.sendUserId = '${baseInfo.sendUserId}';
            data.field.sendUserName = '${baseInfo.sendUserName}';
            data.field.activId = $("#chooseActiv option:selected").val();
            data.field.funcId = $("#chooseFunc option:selected").val();
            data.field.funcName = $("#chooseFunc option:selected").text();
            data.field.startTime = $("#startTime").val();
            data.field.endTime = $("#endTime").val();
            data.field.submitDate = $("#subDate").val();
            data.field.note = $("#note").val().trim();
            console.log(data.field);
            layerAjax('submWorInfo', data.field, 'mySubWorkTimeList');
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
