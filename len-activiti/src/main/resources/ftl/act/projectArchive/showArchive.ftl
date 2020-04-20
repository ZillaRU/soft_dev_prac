<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目归档审核</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${re.contextPath}/plugin/lenos/main.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"
            charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>

</head>

<body>

<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;" autocomplete="off">
        <div style="width:100%;height: 90%;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 12px;">
                    <legend style="font-size:16px;">输出资产标准列表</legend>
                </fieldset>
            </div>
            <div class="layui-form-item">
                <label for="proBasic" class="layui-inline">
                    <span class="x-red">01.</span>项目基础数据表
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proPro">
                    <span class="x-red">02.</span>项目提案书
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proQuo">
                    <span class="x-red">03.</span>项目报价书
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proEst">
                    <span class="x-red">04.</span>项目估算表
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proPlan">
                    <span class="x-red">05.</span>项目计划书
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proCut">
                    <span class="x-red">06.</span>项目过程裁剪表
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proCost">
                    <span class="x-red">07.</span>项目成本管理表
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proRequest">
                    <span class="x-red">08.</span>项目需求变更管理表
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proRisk">
                    <span class="x-red">09.</span>项目风险管理表
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="cusAccept">
                    <span class="x-red">10.</span>客户验收问题表
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="cusRepo">
                    <span class="x-red">11.</span>客户验收报告
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proSummary">
                    <span class="x-red">12.</span>项目总结
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="expLes">
                    <span class="x-red">13.</span>最佳经验和教训
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="devTool">
                    <span class="x-red">14.</span>开发工具
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="devMod">
                    <span class="x-red">15.</span>开发模板（设计模板，测试模板）
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
            <div class="layui-form-item">
                <label for="staCheck">
                    <span class="x-red">16.</span>各阶段检查单
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proBasic" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="qaSum">
                    <span class="x-red">17.</span>QA总结
                    <input type="checkbox" name="check">
                </label>
            </div>
            <div class="layui-form-item">
                <label for="proline" class="layui-inline">
                    <span class="x-red">----------------------------------------------</span>
                </label>
            </div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-normal" id="btn" type="button">
                    审核通过
                </button>
            </div>
        </div>
    </form>
</div>
<script>
    document.onkeydown = function (e) { // 回车提交表单
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code == 13) {
            $(".select .select-on").click();
        }
    };

    $(document).ready(function () {
        $("#btn").click(function () {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg('确定归档信息审核通过吗？', {
                    time: 5000,//5秒自动关闭
                    btn: ['确定', '取消'],
                    yes: function (index) {
                        $.ajax({
                            url: 'proCheck',
                            data: {
                                projId: '${projectID}'
                            },
                            type: "POST",
                            dataType: "json",
                            success: function (data) {
                                var index = parent.layer.getFrameIndex(window.name);
                                window.top.layer.msg(data.msg, {
                                    icon: 6,
                                    offset: 'rb',
                                    area: ['120px', '80px'],
                                    anim: 2
                                });
                                parent.layer.close(index);
                                parent.location.replace(parent.location.href);
                            }, error: function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                window.top.layer.msg('请求失败', {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                                parent.layer.close(index);
                            }
                        });
                        layer.close(index);
                    }
                });
            });
        })
    });
</script>
</body>
</html>