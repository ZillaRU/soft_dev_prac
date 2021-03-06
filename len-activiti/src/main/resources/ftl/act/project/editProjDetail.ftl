<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.all.js"
            charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js" charset="utf-8"></script>
    <style>
        .layui-input {
            height: 30px;
            width: 120px;
        }

        .x-nav {
            padding: 0 20px;
            position: relative;
            z-index: 99;
            border-bottom: 1px solid #e5e5e5;
            height: 32px;
            overflow: hidden;
        }
    </style>
</head>

<body>


<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;" autocomplete="off">
        <div style="width:100%;height: 90%;overflow: auto;">
            <div class="layui-form-item">
                <label for="projname" class="layui-form-label">
                    项目名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="projName" readonly name="projName" value="${projectDetail.projName}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="projno" class="layui-form-label">
                        项目编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="projNo" readonly name="projNo" class="layui-input"
                               value="${projectDetail.projNo}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="proj_customer" class="layui-form-label">
                        客户代号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="proj_customer" readonly name="projCustomer" class="layui-input"
                               value="${projectDetail.projCustomer}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="pm_name" class="layui-form-label">
                        项目经理
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="pmName" value="${projectDetail.pmName}" readonly class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="startDate" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    预定日
                </label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="d1" lay-verify="nnull"
                           value="${(projectDetail.startDate?string("yyyy-MM-dd"))!}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="end_date" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    交付日
                </label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="d1" lay-verify="nnull"
                           value="${(projectDetail.endDate?string("yyyy-MM-dd"))!}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="milestone" class="layui-form-label">
                    里程碑
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="milestone" name="milestone"
                              autocomplete="off" class="layui-input-block"
                              style="width:400px; height: 150px">${projectDetail.milestone}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="projTech" class="layui-form-label">
                    主要技术
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="projTech" name="projTech"
                              class="layui-input-block"
                              style="width:400px; height: 150px">${projectDetail.projTech}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="projDomain" class="layui-form-label">
                    领域
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="projDomain" name="projDomain"
                              class="layui-input-block"
                              style="width:400px; height: 150px">${projectDetail.projDomain}</textarea>
                </div>
            </div>
            <div class="layui-form-item" style="height: 20%; width: 80%">
                <label for="projMainFunc" class="layui-form-label">
                    主要功能
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="projMainFunc" name="projMainFunc"
                              class="layui-input-block"
                              style="width:400px; height: 150px">${projectDetail.projMainFunc}</textarea>
                </div>
            </div>
            <input type="hidden" name="id" value="${projectDetail.id}" readonly>
            <div style="height: 60px"></div>
            <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
                <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                    <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                        更新
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, laydate = layui.laydate;
        laydate.render({
            elem: '#d1'
        });
        laydate.render({
            elem: '#d2'
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('updateProjectInfo', data.field, 'projList');
        });
        form.render();
    });
</script>
</body>
</html>
