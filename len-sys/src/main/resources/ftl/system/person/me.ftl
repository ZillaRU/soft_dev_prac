<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
    <script type="text/javascript" src="/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="/plugin/tools/tool.js"></script>
    <script type="text/javascript" src="/plugin/tools/update-setting.js"></script>
</head>
<body>
<form class="layui-form layui-form-pane" style="margin-left: 20px;">
    <div class="layui-form-item">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
            <legend style="font-size:16px;">头像上传</legend>
        </fieldset>
        <div class="layui-input-inline">
            <div class="layui-upload-drag" style="margin-left:10%;" id="test10">
                <i style="font-size:30px;" class="layui-icon"></i>
                <p style="font-size: 10px">点击上传，或将文件拖拽到此处</p>
            </div>
        </div>
        <div class="layui-input-inline">

            <div  id="demo2" style="margin-top: 20px;margin-left: 50px">
                <img src="/images/${re.contextPath}/${user.photo}" width="100px" height="100px" class="layui-upload-img layui-circle">
            </div>

        </div>
    </div>
    <div class="layui-form-item">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
            <legend style="font-size:16px;">基础信息</legend>
        </fieldset>
    </div>
    <div class="layui-form-item">
        <label for="uname" class="layui-form-label">
            <span class="x-red">*</span>用户名
        </label>
        <div class="layui-input-inline">
            <input value="${user.id}" type="hidden" name="id">
            <input type="text"  id="uname" value="${user.username}" readonly lay-verify="username"
                   autocomplete="off" class="layui-input">
        </div>
        <div id="ms" class="layui-form-mid layui-word-aux">
            <span class="x-red">*</span><span id="ums">将会成为您唯一的登入名</span>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label for="realName" class="layui-form-label">
                <span class="x-red">*</span>真实姓名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="realName" value="${user.realName}" name="realName" lay-verify="realName"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label for="age" class="layui-form-label">
                <span class="x-red">*</span>年龄
            </label>
            <div class="layui-input-inline">
                <input type="text" id="age" name="age" value="${user.age}" lay-verify="number"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div>
        <label for="email" class="layui-form-label">
            <span class="x-red"></span>邮箱
        </label>
        <div class="layui-input-block">
            <input type="email" id="email" value="${user.email}" style="width: 93%" name="email"  lay-verify="email"
                   autocomplete="off" class="layui-input">
            <input id="photo" value="${user.photo}" name="photo" type="hidden">
        </div>
    </div>
    <a  class="layui-btn layui-btn-normal" lay-filter="*"  lay-submit>
        更新
    </a>
</form>
</body>
<script>
    var flag;
    $(function () {
        let name='${user.username}';
        if($('#uname').val()===name)
            flag=true;
        let uNameFun=$('#uname');
        uNameFun.on('blur',function(){
           let uName=uNameFun.val();
            if(uName.match(/[\u4e00-\u9fa5]/)) return;
            if(!/(.+){3,12}$/.test(uName)) return;

            if(uName!=''&&uName!=name) {
                $.ajax({
                    url: 'checkUser?uname=' + uname, async: false, type: 'get', success: function (data) {
                        flag = data.flag;
                        $('#ms').find('span').remove();
                        if (!data.flag) {
                            msg = data.msg;
                            $('#ms').append("<span style='color: red;'>"+data.msg+"</span>");
                            // layer.msg(msg,{icon: 5,anim: 6});
                        }else{
                            flag=true;
                            $('#ms').append("<span style='color: green;'>用户名可用</span>");
                        }
                    },beforeSend:function(){
                        $('#ms').find('span').remove();
                        $('#ms').append("<span>验证ing</span>");
                    }
                });
            }else{
                flag=true;
            }
        });
    });

    layui.use(['form','layer','upload'], function(){
        $ = layui.jquery;
        var form = layui.form
                ,layer = layui.layer,
                upload = layui.upload;
        upload.render({
            elem: '#test10'
            ,url: '/user/upload'
            ,before: function(obj){
                //预读，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo2').find('img').remove();
                    $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            },done: function(res){
                if(!res.flag){
                    layer.msg(res.msg,{icon: 5,anim: 6});
                }else{
                    $("#photo").val(res.msg);
                }
            }
        });

        //自定义验证规则
        form.verify({
            username: function(value){
                if(value.trim()==""){
                    return "用户名不能为空";
                }
                if(value.match(/[\u4e00-\u9fa5]/)){
                    return "用户名不能为中文";
                }
                if(!/(.+){3,12}$/.test(value)){
                    return "用户名必须3到12位";
                }
                if(typeof(flag)=='undefined'){
                    return "用户名验证ing";
                }
                if(!flag){
                    return msg;
                }
            }
            ,email:function(value){
                if(value!=""){
                    if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(value)){
                        return "邮箱格式不正确";
                    }
                }
            }
        });

        //监听提交
        form.on('submit(*)', function(data){
            $.ajax({
                url:'/person/updateUser',
                type:'post',
                data:data.field,
                traditional: true,
                success:function(d){
                    if(d.flag){
                        window.top.layer.msg(d.msg,{icon:6,offset: 'rb',area:['200px','80px'],anim:2});
                    }else{
                        layer.msg(d.msg,{icon:5});
                    }
                },error:function(e){
                    layer.msg('发生错误',{icon:6});
                }
            });
            return false;
        });
    });
</script>
</html>