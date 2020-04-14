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
    <script type="text/javascript">
        $(document).ready(function () {
            var flag = '${baseInfo}';
            if (flag) {
                $("form").disable();
            }
        });
    </script>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height:400px;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">项目人员信息详情</legend>
                </fieldset>
            </div>
            <div class="layui-form-item">
                <label for="proname" class="layui-form-label">
                    项目名称
                </label>
                <div class="layui-input-inline">
                    <input value="${baseInfo.proId}" type="hidden" name="id">
                    <input type="text" id="proName" value="${baseInfo.proName}"
                           autocomplete="off" class="layui-input">
                </div>
                <div class="layui-inline">
                    <label for="proStatus" class="layui-form-label">
                        项目状态
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="proStatus" name="proStatus" value="${baseInfo.proStatus}"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div id="finishStatus" class="layui-form-mid layui-word-aux">
                        <span id="finishStatus"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="devLeaderName" class="layui-form-label">
                    开发负责人
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="devLeaderName" name="devLeaderName" value="${baseInfo.devLeaderName}"
                           autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn">修改</button>
                </div>
                <label for="testLeaderName" class="layui-form-label">
                    测试负责人
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="testLeaderName" name="testLeaderName" value="${baseInfo.testLeaderName}"
                           autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline">
                    <button class="layui-btn layui-btn">修改</button>
                </div>
            </div>
            <div class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">开发人员信息</legend>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色选择</label>
                <div class="layui-input-block">
                    <#list boxJson as json>
                        <input type="checkbox" name="role" lay-filter="check" value="${json.id}" title="${json.name}"
                               <#if json.check?string=='true'>checked</#if>>
                    </#list>
                </div>
            </div>
            <div style="height: 60px"></div>
        </div>
        <#if !detail>
            <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
      position: fixed;bottom: 1px;margin-left:-20px;">
                <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                    <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
                        确定修改
                    </button>
                    <button class="layui-btn layui-btn-primary" id="close">
                        取消
                    </button>

                </div>
            </div>
        </#if>
    </form>
</div>
<table id="devInfoDetail" class="layui-hide" lay-filter="act" ></table>
<script>
    var flag, msg;
    layui.use('table', function () {
        var table = layui.table;
        //方法级渲染
        table.render({
            id: 'devInfoDetail',
            elem: '#devInfoDetail'
            , url: 'info'
            , testStr :'test'
            , cols: [[
                {checkbox: true, fixed: true, width: '5%'}
                , {
                    field: 'proName',
                    title: '项目名称',
                    width: '10%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'proStatus', title: '项目状态', width: '10%',}
                , {field: 'testLeaderName', title: '测试负责人', width: '10%'}
                , {field: 'devLeaderName', title: '开发负责人', width: '10%'}
                , {field: 'configManagerName', title: '配置管理人员', width: '12%'}
                , {field: 'qaManagerName', title: 'QA管理人员', width: '12%'}
                , {field: 'epgLeaderName', title: 'epg人员', width: '10%'}
                , {field: 'operate', title: '操作', width: '20%', toolbar: "#barDemo"}
            ]]
            , height: 'full-83'
        });
    $(function () {
        if ($('#proStatus').val() == 0)
            $("#proStatus").val('未完成');
            $("#finishStatus").text("未导入开发或测试人员");
        var proName = '${baseInfo.username}';
        if ($('#proName').val() == proName)
            flag = true;
        $('#proName').on("blur", function () {
            var proName = $('#proName').val();
            if (uname.match(/[\u4e00-\u9fa5]/)) {
                return;
            }
            if (!/(.+){3,12}$/.test(uname)) {
                return;
            }
            if (uname != '' && uname != name) {
                $.ajax({
                    url: 'checkUser?uname=' + uname, async: false, type: 'get', success: function (data) {
                        console.info(!data.flag);
                        flag = data.flag;
                        $('#ms').find('span').remove();
                        if (!data.flag) {
                            msg = data.msg;
                            $('#ms').append("<span style='color: red;'>" + data.msg + "</span>");
                            // layer.msg(msg,{icon: 5,anim: 6});
                        } else {
                            flag = true;
                            $('#ms').append("<span style='color: green;'>用户名可用</span>");
                        }
                    }, beforeSend: function () {
                        $('#ms').find('span').remove();
                        $('#ms').append("<span>验证ing</span>");
                    }
                });
            } else {
                flag = true;
            }
        });

    });
    layui.use(['form', 'layer', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer,
            upload = layui.upload;
        upload.render({
            elem: '#test10'
            , url: 'upload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo2').find('img').remove();
                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            }, done: function (res) {
                if (!res.flag) {
                    layer.msg(res.msg, {icon: 5, anim: 6});
                } else {
                    console.log(res);
                    $("#photo").val(res.msg);
                    console.info($('#photo').val());
                }
            }
        });

        //自定义验证规则
        form.verify({
            username: function (value) {
                if (value.trim() == "") {
                    return "用户名不能为空";
                }
                if (value.match(/[\u4e00-\u9fa5]/)) {
                    return "用户名不能为中文";
                }
                if (!/(.+){3,12}$/.test(value)) {
                    return "用户名必须3到12位";
                }
                if (typeof (flag) == 'undefined') {
                    return "用户名验证ing";
                }
                if (!flag) {
                    return msg;
                }
            }
            , email: function (value) {
                if (value != "") {
                    if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value)) {
                        return "邮箱格式不正确";
                    }
                }
            }
        });

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            var r = document.getElementsByName("role");
            var role = [];
            for (var i = 0; i < r.length; i++) {
                if (r[i].checked) {
                    console.info(r[i].value);
                    role.push(r[i].value);
                }
            }
            data.field.role = role;
            layerAjax('updateUser', data.field, 'userList');
            return false;
        });
        form.render();
    });
</script>
</body>

</html>
