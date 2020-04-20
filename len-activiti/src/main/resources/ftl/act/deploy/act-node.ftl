<#--Created by IntelliJ IDEA.
User: zxm
Date: 2017/1/23
Time: 10:00
To change this template use File | Settings | File Templates.-->

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>流程节点分配</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
  <link rel="stylesheet" href="${re.contextPath}/plugin/ztree/css/metroStyle/metroStyle.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js" charset="utf-8"></script>
</head>

<body>
<div class="x-body">
  <form class="layui-form layui-form-pane" style="margin-left: 20px;">
    <div style="width:100%;height:400px;overflow: auto;">
      <div class="layui-tab">
        <ul class="layui-tab-title">
        <#list actList as act>
            <li <#if act_index==0>class="layui-this"</#if>><i class="layui-icon">&#xe715;</i>${act.name}</li>
        </#list>
        </ul>
        <div class="layui-tab-content">
          <#list actList as act>
            <div class="layui-tab-item <#if act_index==0>layui-show</#if>">
             <div style="display: none" name="id">${act.id}</div>
              <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">分配角色</legend>
              </fieldset>
                <div class="layui-form-item">
                  <div class="layui-input-block" style="margin-left: 0px;">
                    <#list act.boxJson as json>
                      <input type="checkbox" name="${act_index}_${json_index}_role_${act.id}_${act.name}"
                             title="${json.name}" lay-filter="check" value="${json.id}" <#if json.check?string=='true'>checked</#if>>
                    </#list>
                  </div>
                </div>
              <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">分配成员</legend>
              </fieldset>
              <div class="layui-form-item">
                开发中..ing
              </div>
            </div>
          </#list>
        </div>
      </div>
  <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
    <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
      <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
        确定
      </button>
      <button  class="layui-btn layui-btn-primary"   data-type="close">
        取消
      </button>
    </div>
  </div>
    </div>
  </form>
</div>
<script>
  layui.use(['form','layer'], function(){
    $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer;
    var $ = layui.$, active = {
      close: function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
      }
    };
    $('.layui-form-item .layui-btn').on('click', function () {
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
    //监听提交
    form.on('submit(add)', function(data){
      $.ajax({
        url:'updateNode',
        type:'post',
        data:data.field,
        success:function(d){
            var index = parent.layer.getFrameIndex(window.name);
            if(d.flag){
              parent.layer.close(index);
              window.parent.layui.table.reload('actList');
              window.top.layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
            }else{
              layer.msg(d.msg,{icon:5});
            }
        },error:function(){
          layer.alert("请求失败", {icon: 6},function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
          });
        }
      });
      return false;
    });
    form.render();
  });
</script>
</body>

</html>
