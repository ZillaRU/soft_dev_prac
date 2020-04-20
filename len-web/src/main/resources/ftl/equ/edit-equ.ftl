<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>修改设备信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
    <style>
        .layui-input {
            /*height: 30px;*/
            width: 300px;
        }
    </style>
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height:400px;overflow: auto;">
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
                    <input type="text" id="rId" readonly name="rId" value="${equDetail.RId}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="P_Name" class="layui-form-label">
                    <span class="x-red">*</span>项目名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="pName" readonly name="pName" value="${equDetail.PName}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="R_Manager" class="layui-form-label">
                    <span class="x-red">*</span>资产管理者
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rManager" readonly name="rManager" value="${equDetail.RManager}"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="R_Date" class="layui-form-label">
                        <span class="x-red">*</span>使用期限
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="rDate" name="rDate"
                               value="${equDetail.RDate?string("yyyy-MM-dd")}"
                               class="layui-input">
                    </div>
                    <div id="ms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">日期请按xxxx-xx-xx格式填写</span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="R_State" class="layui-form-label">
                        <span class="x-red"></span>设备状态
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
                        <span class="x-red"></span>租借状态
                    </label>
                    <div class="layui-input-inline">
                        <select disabled id="selectRentState" name="rentState" lay-verify="nnull" lay-search>
                            <option value="已归还">已归还</option>
                            <option value="未归还">未归还</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="R_Finish" class="layui-form-label">
                        <span class="x-red"></span>归还日期
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="rFinish" name="rFinish"
                               value="${(equDetail.RFinish?string("yyyy-MM-dd"))!}"
                               class="layui-input">
                    </div>
                    <div id="ms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">日期请按xxxx-xx-xx格式填写</span>
                    </div>
                </div>
            </div>
            <div style="height: 60px"></div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <input type="button" class="layui-btn layui-btn-normal" lay-filter="edit" lay-submit="" value="修改">
                <input type="button" class="layui-btn layui-btn-primary" id="close" value="取消">
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'laydate', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form, layer = layui.layer,
            laydate = layui.laydate;
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

        //执行一个laydate实例
        laydate.render({
            elem: '#rDate',
            min: 0 //new Date(), //.getDate().toLocaleString(),
        });
        laydate.render({
            elem: '#rFinish',
            max: 0
        });
        form.render();
        //监听提交
        form.on('submit(edit)', function (data) {
            console.log(data.field);
            $.ajax({
                url: 'updateEqu',
                type: 'post',
                data: data.field,
                traditional: true,
                success: function (d) {
                    layer.msg("操作成功!", {time: 1000}, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.layui.table.reload('equList');
                    });

                    return false;
                }, error: function () {
                    console.log('error');
                }
            });
        });
    })
</script>
</body>
</html>
