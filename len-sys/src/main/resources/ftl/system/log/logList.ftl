<#-- Created by IntelliJ IDEA.
 User: zxm
 Date: 2017/12/19
 Time: 18:00
 To change this template use File | Settings | File Templates.
 角色管理-->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>日志</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport"
        content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
  <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
  <link rel="stylesheet" href="${re.contextPath}/plugin/lenos/main.css">
  <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"
          charset="utf-8"></script>
</head>

<body>
<div class="lenos-search">
  <div class="select">
    操作用户：
    <div class="layui-inline">
      <input class="layui-input" height="20px" id="userName" autocomplete="off">
    </div>
    操作类型：
    <div class="layui-inline">
      <input class="layui-input" height="20px" id="type" autocomplete="off">
    </div>
    <button class="select-on layui-btn layui-btn-sm" data-type="select"><i class="layui-icon"></i>
    </button>
    <button class="layui-btn layui-btn-sm icon-position-button" id="refresh" style="float: right;"
            data-type="reload">
      <i class="layui-icon">ဂ</i>
    </button>
  </div>
</div>
<div class="layui-col-md12" style="height:40px;margin-top:3px;">
    <div class="layui-btn-group">
    <@shiro.hasPermission name="control:del">
        <button class="layui-btn layui-btn-normal" data-type="del">
            <i class="layui-icon">&#xe640;</i>删除
        </button>
    </@shiro.hasPermission>
    </div>
</div>

<table id="logList" class="layui-hide" lay-filter="log"></table>
<script type="text/html" id="toolBar">
  <shiro.hasPermission name="control:del">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
  </shiro.hasPermission>
</script>
<script>
  layui.laytpl.toDateString = function(d, format){
    var date = new Date(d || new Date())
        ,ymd = [
      this.digit(date.getFullYear(), 4)
      ,this.digit(date.getMonth() + 1)
      ,this.digit(date.getDate())
    ]
        ,hms = [
      this.digit(date.getHours())
      ,this.digit(date.getMinutes())
      ,this.digit(date.getSeconds())
    ];

    format = format || 'yyyy-MM-dd HH:mm:ss';

    return format.replace(/yyyy/g, ymd[0])
    .replace(/MM/g, ymd[1])
    .replace(/dd/g, ymd[2])
    .replace(/HH/g, hms[0])
    .replace(/mm/g, hms[1])
    .replace(/ss/g, hms[2]);
  };

  //数字前置补零
  layui.laytpl.digit = function(num, length, end){
    var str = '';
    num = String(num);
    length = length || 2;
    for(var i = num.length; i < length; i++){
      str += '0';
    }
    return num < Math.pow(10, length) ? str + (num|0) : num;
  };

  document.onkeydown = function (e) { // 回车提交表单
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
      $(".select .select-on").click();
    }
  }

  layui.use('table', function () {
    var table = layui.table;
    //方法级渲染
    table.render({
      id: 'logList',
      elem: '#logList'
      , url: 'showLogList'
      , cols: [[
          {checkbox: true, fixed: true, width: '5%'}
        , {field: 'userName', title: '操作人', width: '10%', sort: true}
        , {field: 'type', title: '操作类型', width: '10%', sort: true}
        , {field: 'text', title: '描述内容', width: '10%', sort: true}
        , {field: 'param', title: '参数', width: '45%', sort: true}
        , {field: 'createTime', title: '操作时间', width: '10%',templet: '<div>{{ layui.laytpl.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss") }}</div>'}
        , {field: 'text', title: '操作', width: '10%', toolbar:'#toolBar'}

      ]]
      , page: true
      ,  height: 'full-83'
    });

    var $ = layui.$, active = {
      select: function () {
        var userName = $('#userName').val();
        var type = $('#type').val();
        table.reload('logList', {
          where: {
              userName: userName,
              type: type
          }
        });
      }
      ,del:function(){
          var checkStatus = table.checkStatus('logList')
                  , data = checkStatus.data;
          if (data.length ==0) {
              layer.msg('请选择要删除的数据', {icon: 5});
              return false;
          }
          var ids=[];
          for(item in data){
              ids.push(data[item].id);
          }
          del(ids);
        }
      ,reload:function(){
        $('#userName').val('');
       $('#type').val('');
        table.reload('logList', {
          where: {
              userName: null,
              type: null
          }
        });
      },
    };
    //监听工具条
    table.on('tool(log)', function (obj) {
      var data = obj.data;
      if (obj.event === 'del') {
          var ids=[];
          del(ids.push(data.id));
      }
    });

    $('.layui-col-md12 .layui-btn').on('click', function () {
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
    $('.select .layui-btn').on('click', function () {
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });

  });
  /**批量删除id*/
  function del(ids) {
    $.ajax({
      url: "del",
      type: "post",
      data: {ids: ids},
      dataType: "json", traditional: true,
      success: function (data) {
        layer.msg(data.msg, {icon: 6});
        layui.table.reload('logList');
      }
    });
  }
</script>
</body>

</html>
