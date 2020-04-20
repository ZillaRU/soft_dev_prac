<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>新增设备信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;" autocomplete="off">
        <div style="width:100%;height: 90%;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">设备信息</legend>
                </fieldset>
            </div>


            <div class="layui-form-item">
                <label for="R_ID" class="layui-form-label">
                    <span class="x-red">*</span>设备编号
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rId" name="rId" lay-verify="rId"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="P_Name" class="layui-form-label">
                    <span class="x-red">*</span>项目名称
                </label>
                <div class="layui-input-inline">
                    <select id="selectPName" name="pName" lay-verify="nnull" lay-search>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="R_Manager" class="layui-form-label">
                    <span class="x-red">*</span>资产管理者
                </label>
                <div class="layui-input-inline">
                    <select id="selectRManager" name="rManager" lay-verify="nnull" lay-search>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="R_Date" class="layui-form-label">
                    <span class="x-red">*</span>使用期限
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rDate" name="rDate" lay-verify="rDate" placeholder="yyyy-MM-dd"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="R_State" class="layui-form-label">
                        设备状态
                    </label>
                    <div class="layui-input-inline">
                        <select id="selectRState" name="rState" lay-verify="nnull" lay-search>
                            <option value="完好">完好</option>
                            <option value="损坏">损坏</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="Rent_State" class="layui-form-label">
                        租借状态
                    </label>
                    <div class="layui-input-inline">
                        <select id="selectRentState" name="rentState" lay-verify="nnull" lay-search>
                            <option value="已归还">已归还</option>
                            <option value="未归还">未归还</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="R_Finish" class="layui-form-label">
                    归还日期
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rFinish" name="rFinish" lay-verify="rFinish" placeholder="yyyy-MM-dd"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div style="height: 60px"></div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                    新增
                </button>
                <button class="layui-btn layui-btn-primary" id="close">
                    取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>

    layui.use(['form', 'laydate', 'jquery', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form, laydate = layui.laydate, layer = layui.layer;

        $.ajax({
            url: '/equ/showPro',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.info(data);
                $('#selectPName').empty();
                for (var pro in data['data']) {
                    console.log(pro);
                    $('#selectPName').append("<option value= '" + data['data'][pro].id + "'>" + data['data'][pro].projName + "</option>");
                }
                form.render();
            }
        });

        $.ajax({
            url: '/equ/showUser',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.info(data);
                $('#selectRManager').empty();
                for (var pro in data['user']) {
                    console.log(pro);
                    $('#selectRManager').append("<option value= '" + data['user'][pro].id + "'>" + data['user'][pro].userName + "</option>");
                }
                form.render();
            }
        });

        $(function () {
            $('#R_ID').on("blur", function () {
                var R_ID = $('#R_ID').val();
                if (R_ID.match(/[\u4e00-\u9fa5]/)) {
                    return;
                }
                if (!/(.+){3,12}$/.test(R_ID)) {
                    return;
                }
                if (R_ID != '') {
                    $.ajax({
                        url: 'checkEqu?R_ID=' + R_ID, async: false, type: 'get', success: function (data) {
                            console.info(!data.flag);
                            flag = data.flag;
                            $('#ms').find('span').remove();
                            if (!data.flag) {
                                msg = data.msg;
                                $('#ms').append("<span style='color: red;'>" + data.msg + "</span>");
                                // layer.msg(msg,{icon: 5,anim: 6});
                            } else {
                                flag = true;
                                $('#ms').append("<span style='color: green;'>设备编号可用</span>");
                            }
                        }, beforeSend: function () {
                            $('#ms').find('span').remove();
                            $('#ms').append("<span>验证ing</span>");
                        }
                    });
                }
            });

        });

        //执行一个laydate实例
        var std = laydate.render({
            elem: '#rDate',
            min: 0 //new Date(), //.getDate().toLocaleString(),
        });
        var edd = laydate.render({
            elem: '#rFinish',
            max: 0
        });

        form.render();
        //自定义验证规则
        form.verify({
            rId: function (value) {
                if (value.trim() == "") {
                    return "设备编号 不能为空";
                }
            },
            pName: function (value) {
                if (value.trim() == "") {
                    return "项目名称 不能为空";
                }
            },
            rManager: function (value) {
                if (value.trim() == "") {
                    return "管理者 不能为空";
                }
            },
            rDate: function (value) {
                if (value.trim() == "") {
                    return "使用期限 不能为空";
                }
            }
        });

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        //监听提交
        form.on('submit(add)', function (data) {
            data.field.pName = $("#selectPName option:selected").text();
            layerAjax('addEqu', data.field, 'equList');
        });
    });
</script>
</body>

</html>
