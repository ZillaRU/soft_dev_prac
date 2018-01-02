<#--Created by IntelliJ IDEA.
User: Administrator
Date: 2017/12/27
Time: 12:40
To change this template use File | Settings | File Templates.-->

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>菜单管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
  <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
  <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
  <!--[if lt IE 9]>
  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>
<div class="x-body">
  <form class="layui-form layui-form-pane" style="margin-left: 20px;">
    <div style="width:100%;height:400px;overflow: auto;">
    <div class="layui-form-item">
      <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
        <legend style="font-size:16px;">基础信息</legend>
      </fieldset>
    </div>
    <div style="margin-left:25%">
        <div class="layui-form-item">
            <label for="menuType" class="layui-form-label">
                <span class="x-red">*</span>类型
            </label>
            <div class="layui-input-block" style="width:190px;">
                <select name="menuType" id="menuType" lay-verify="menuType"  lay-filter="menuType">
                    <option value=""></option>
                    <option value="2">一级菜单</option>
                    <option value="0">二级菜单</option>
                    <option value="1">按钮</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item" id="pDiv">
            <label for="pName" class="layui-form-label">
                父级菜单
            </label>
            <div class="layui-input-inline">
                <input type="hidden" name="pId" id="pId">
                <input type="text" id="pName"  onclick="showTree();"  lay-verify="pName"
                      class="layui-input">
            </div>
            <div id="treeNode"  style="display:none; position: absolute;z-index:1000;background-color: white;">
                <div  id="tree"></div>
            </div>
        </div>
    <div class="layui-form-item">
      <label for="name" class="layui-form-label">
        <span class="x-red">*</span>名称
      </label>
      <div class="layui-input-inline">
        <input type="text"  id="name" name="name"  lay-verify="name"
               autocomplete="off" class="layui-input">
      </div>
      <div id="ms" class="layui-form-mid layui-word-aux">
        <span class="x-red">*</span><span id="ums">必须填写</span>
      </div>
    </div>
    <div class="layui-form-item">
      <label for="url" class="layui-form-label">
        url
      </label>
      <div class="layui-input-inline">
        <input type="text" id="url" name="url" lay-verify="url"  autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">

      <label for="permission" class="layui-form-label">
        <span class="x-red">*</span>权限
      </label>
      <div class="layui-input-inline">
        <input type="text" id="permission" name="permission"  lay-verify="permission"
               autocomplete="off" class="layui-input">
      </div>
    </div>
    <div class="layui-form-item">
        <label for="icon" class="layui-form-label">
          <span class="x-red">*</span>图标
        </label>
        <div class="layui-input-inline">
          <input type="icon" id="icon" name="icon"  lay-verify="icon"
                 autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label for="orderNum" class="layui-form-label">
          <span class="x-red">*</span>序号
        </label>
        <div class="layui-input-inline">
          <input type="orderNum" id="orderNum" name="orderNum"  lay-verify="orderNum"
                 autocomplete="off" class="layui-input">
        </div>
    </div>
      <div style="height: 60px"></div>
    </div>
    </div>
  <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
    <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
      <button  class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
        增加
      </button>
      <button  class="layui-btn layui-btn-primary" id="close">
        取消
      </button>
    </div>
  </div>
  </form>
</div>
<script>
  layui.use(['form','layer'], function(){
    $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer;
    layui.tree({
        elem:'#tree',
        nodes:${menus}
        ,click: function(node){
            if(node.menuType=='1'){
                layer.msg('请勿选择按钮', {icon: 5,anim:6});
                return false;
            }
          $('#pId').val(node.id);
          $('#pName').val(node.name);
        }
  });

    //自定义验证规则
      var type=$('#menuType');
    form.verify({
    menuType:function(v){
        console.info(v=='')
        if(v==''){
            return '类型不能为空';
        }
    }
    ,pName:function(v){
      if(type.val()!='2'&&v.trim()==''){
        return '父菜单不能为空';
      }
    }
      ,name:function(v){
          if(v.trim()==''){
              return '名称不能为空';
          }
      }
      ,url:function(v){
            if(type.val()=='1'){
                $('#url').val('');
            }
            if(type.val()=='0'&&v.trim()==''){
                return 'url不能为空';
            }
        }
        ,permission:function(v){
            if(type.val()=='1'&&v.trim()==''){
                return '权限不能为空';
            }
        }
        ,orderNum: [/^[0-9]*[1-9][0-9]*$/, '请填写序号(正整数)']
    });

  form.on('select(menuType)', function(data){
      if(data.value=='2'){
          $('#pId').val('');
          dOs('pName',true);dOs('permission',true);dOs('url',false);
      }else if(data.value=='1'){//按钮
          dOs('url',true);dOs('pName',false);dOs('permission',false);
      }else if(data.value=='0'){
          dOs('url',false);dOs('pName',false);dOs('permission',true);
      }
  });
  /**
   * id :元素id
   * flag true:禁止输入，false 允许输入
   */
  function dOs(id,flag){
      var $id= $("#"+id);
      if(flag){
          $id.val('');
          $id.attr('disabled','disabled').css('background','#e6e6e6');
      }
      else
          $id.removeAttr('disabled').css('background','white')
  }

   $('#close').click(function(){
     var index = parent.layer.getFrameIndex(window.name);
     parent.layer.close(index);
   });
    //监听提交
    form.on('submit(add)', function(data){
      $.ajax({
        url:'addMenu',
        type:'post',
        data:data.field,
        async:false, dataType: "json", traditional: true,
        success:function(msg){
          var index = parent.layer.getFrameIndex(window.name);
          parent.layer.close(index);
          //window.parent.layui.table.reload('userList');
          window.top.layer.msg(msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
        },error:function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
          window.top.layer.msg('请求失败',{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
        }
      });
      return false;
    });
    form.render();
  });

  function showTree(){
      var p=$('#pName');
      var cityObj = p;
      var cityOffset =p.offset();
      var width=p.css('width');
      $('#treeNode').css({
          left: cityOffset.left + 'px',
          top: cityOffset.top + cityObj.outerHeight() + 'px',
          width:width,
          border:'1px solid #e6e6e6'
      }).slideDown('fast');
      $('body').bind('mousedown', onBodyDown);
      $('#treeNode').css('display','inline');
  }
  function hideMenu() {
      $('#treeNode').fadeOut('fast');
      $('body').unbind('blur', onBodyDown);
  }
  function onBodyDown(event) {
      if (! ( event.target.id == 'treeNode' || $(event.target).parents('#treeNode').length > 0)) {
          hideMenu();
      }
  }
</script>
</body>

</html>
