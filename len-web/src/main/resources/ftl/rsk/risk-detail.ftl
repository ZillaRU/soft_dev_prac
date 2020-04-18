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
            /*height: 30px;*/
            width: 300px;
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
                <label for="hId" class="layui-form-label">
                    风险编号
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="hId" readonly name="hId" value="${riskDetail.HId}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="pName" class="layui-form-label">
                    项目名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="pName" readonly name="pName" value="${riskDetail.PName}"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="hType" class="layui-form-label">
                        风险类型
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="hType" readonly name="hType" class="layui-input"
                               value="${riskDetail.HType}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="hGrade" class="layui-form-label">
                        风险级别
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="hGrade" value="${riskDetail.HGrade}" readonly class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="hInfluence" class="layui-form-label">
                        风险影响度
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="hInfluence" name="hInfluence" value="${riskDetail.HInfluence}" readonly
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="hState" class="layui-form-label">
                        风险状态
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="hState" name="hState" value="${riskDetail.HState}" readonly
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="hFrequency" class="layui-form-label">
                        追踪频度
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="hFrequency" name="hFrequency" value="${riskDetail.HFrequency}" readonly
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="hManager" class="layui-form-label">
                        风险负责人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="hManager" name="hManager" value="${riskDetail.HManager}" readonly
                               class="layui-input">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="hMember" class="layui-form-label">
                        风险相关者
                    </label>
                    <div class="layui-input-inline">
                        <#list riskDetail.HMember as member>
                            <input type="text" id="hMember" name="hMember" value="${member}" readonly
                                   class="layui-input">
                        </#list>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="hDes" class="layui-form-label">
                    风险描述
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="hDes" name="hDes"
                              autocomplete="off" class="layui-textarea" readonly
                              style="width:400px; height: 150px">${riskDetail.HDes}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="hTactics" class="layui-form-label">
                    应对策略
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="hTactics" name="hTactics"
                              class="layui-textarea" readonly
                              style="width:400px; height: 150px">${riskDetail.HTactics}</textarea>
                </div>
            </div>

            <div style="height: 60px"></div>
        </div>
    </form>
</div>


<#--<script type="text/html" id="flag">-->
<#--    {{# if(d.flag){ }}-->
<#--    <span class="layui-badge layui-bg-green">通过</span>-->

<#--    {{# }else{ }}-->
<#--    <span class="layui-badge">未通过</span>-->
<#--    {{#  } }}-->
<#--</script>-->

<script>
    // console.log("ggg");
    console.log("${riskDetail}");
    layui.use(['form'], function () {
        var form = layui.form;
        <#--laydate.render({-->
        <#--    elem: '#d1',-->
        <#--    val: new Date(${projectDetail.startDate})-->
        <#--});-->
        <#--laydate.render({-->
        <#--    elem: '#d2',-->
        <#--    value: ${projectDetail.endDate}-->
        <#--});-->
        form.render();
    });
</script>
</body>
</html>
