<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>新增项目基本信息</title>
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
    <form class="layui-form layui-form-pane" style="margin-left: 20px;" autocomplete="off">
        <div style="width:100%;height: 90%;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">基本信息</legend>
                </fieldset>
            </div>
            <div class="layui-form-item">
                <label for="projname" class="layui-form-label">
                    <span class="x-red">*</span>项目名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="projName" name="projName" lay-verify="nnull" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="projno" class="layui-form-label">
                        项目编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="projNo" name="projNo" class="layui-input" lay-verify="no_pattern">
                    </div>
                    <label>（格式：YEAR CUST T NO）</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="proj_customer" class="layui-form-label">
                        客户代号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="proj_customer" name="projCustomer" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="pm_name" class="layui-form-label">
                        <span class="x-red">*</span>项目经理
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="pmName" value="${user.realName}" readonly class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="startDate" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">*预定日</span>
                </label>

                <div class="layui-input-inline">
                    <input type="text"  id="startDate" name="startDate"  lay-verify="nnull"  placeholder="yyyy-MM-dd"
                           autocomplete="off" class="layui-input">
                </div>

            </div>
            <div class="layui-form-item">
                <label for="end_date" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">*交付日</span>
                </label>

                <div class="layui-input-inline">
                    <input type="text"  id="endDate" name="endDate"  lay-verify="nnull"  placeholder="yyyy-MM-dd"
                           autocomplete="off" class="layui-input">
                </div>

            </div>
            <div class="layui-form-item">
                <label for="milestone" class="layui-form-label">
                    <span class="x-red">*</span>里程碑
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="milestone" name="milestone" lay-verify="nnull"
                              autocomplete="off" class="layui-input-block"
                              style="width:400px; height: 150px"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="projTech" class="layui-form-label">
                    <span class="x-red">*</span>主要技术
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="projTech" name="projTech" lay-verify="nnull"
                              class="layui-input-block"
                              style="width:400px; height: 150px"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="projDomain" class="layui-form-label">
                    <span class="x-red">*</span>领域
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="projDomain" name="projDomain" lay-verify="nnull"
                              class="layui-input-block"
                              style="width:400px; height: 150px"></textarea>
                </div>
            </div>
            <div class="layui-form-item" style="height: 20%; width: 80%">
                <label for="projMainFunc" class="layui-form-label">
                    <span class="x-red">*</span>主要功能
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="projMainFunc" name="projMainFunc" lay-verify="nnull"
                              class="layui-input-block"
                              style="width:400px; height: 150px"></textarea>
                </div>
            </div>
            <div style="height: 60px"></div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                    增加
                </button>
                <button class="layui-btn layui-btn-primary" id="close">
                    取消
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'laydate', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form, laydate = layui.laydate, layer = layui.layer;

        //执行一个laydate实例
        var std = laydate.render({
            elem: '#startDate',
            min: 0, //new Date(), //.getDate().toLocaleString(),
            done: function (value, date) {
                edd.config.min = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }; // 开始日选好后，重置结束日的最小日期
                edd.config.value = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }; // 将结束日的初始值设定为开始日
            }
        });
        var edd = laydate.render({
            elem: '#endDate',
            done: function (value, date) {
                std.config.max = {
                    year: date.year,
                    month: date.month - 1,
                    date: date.date
                }; // 结束日选好后，重置开始日的最大日期
            }
        });

        //自定义验证规则
        form.verify({
            nnull: function (value) {
                if (value.trim() == "") {
                    return "*项 不能为空";
                }
            },
            no_pattern: function (value) {
                // if(value) 正则匹配项目编号 yyyyccccTxx
            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('applyProject', data.field, 'projList');
        });
    });
</script>
</body>

</html>
