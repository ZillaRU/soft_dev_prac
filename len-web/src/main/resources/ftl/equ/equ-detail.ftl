<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>设备详情</title>
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
                <label for="R_ID" class="layui-form-label">
                    设备编号
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rId" readonly name="rId" value="${equDetail.RId}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="P_Name" class="layui-form-label">
                        项目名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="pName" readonly name="pName" class="layui-input"
                               value="${equDetail.PName}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="R_Manager" class="layui-form-label">
                        资产管理者
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="rManager" readonly name="rManager" class="layui-input"
                               value="${equDetail.RManager}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="R_Date" class="layui-form-label">
                    使用期限
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rDate" name="rDate" value="${equDetail.RDate?string("yyyy-MM-dd")}" readonly
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="R_State" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    设备状态
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rState" name="rState" value="${equDetail.RState}" readonly
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="Rent_State" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    租借状态
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rentState" name="rentState" value="${equDetail.rentState}" readonly
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="R_Finish" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    归还日期
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="rFinish" name="rFinish" value="${(equDetail.RFinish?string("yyyy-MM-dd"))!}"
                           readonly class="layui-input">
                </div>
            </div>
            <div style="height: 60px"></div>
        </div>
    </form>
</div>


<script>
    console.log("${equDetail}");
    layui.use(['form'], function () {
        var form = layui.form;
        form.render();
    });
</script>
</body>
</html>
