<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>新增项目基本信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="https://www.layuicdn.com/layui-v2.5.6/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
</head>
<#--222项目名称，数据类型为CHAR(11)，不允许NULL，该表的外码（连接项目信息管理数据表）；-->
<#--H_ID：风险ID，数据类型为CHAR(11)，不允许NULL，该表的主码；-->
<#--222H_Type：风险类型，数据类型为VARCHAR(20)，不允许NULL；-->
<#--H_Des：风险描述，数据类型为VARCHAR(100)，不允许NULL；-->
<#--222H_Grade：风险级别，数据类型为VARCHAR(20)，不允许NULL；-->
<#--222H_Influence：风险影响度，数据类型为VARCHAR(30)，不允许NULL；-->
<#--H_Tactics：风险应对策略，数据类型为VARCHAR(100)，不允许NULL；-->
<#--222H_State：风险状态，数据类型为VARCHAR(20)，不允许NULL；-->
<#--H_Manager：风险责任人，数据类型为CHAR(11)，不允许NULL，与项目人员信息管理数据表相对应；-->
<#--222H_Frequency：风险频度，分为高、中、低三类，数据类型为VARCHAR(5)，不允许NULL；-->
<#--H_Relate：风险相关者，-->
<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" lay-filter="projectId" style="margin-left: 20px;" autocomplete="off">
        <div style="width:100%;height: 90%;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">基本信息</legend>
                </fieldset>
            </div>


            <div class="layui-form-item">
                <label for="P_Name" class="layui-form-label">
                    <span class="x-red">*</span>项目名称
                </label>
                <div class="layui-input-inline">
                    <select id="selectPName" lay-filter="pname" name="pId" lay-verify="nnull" lay-search></select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="H_Type" class="layui-form-label">
                        <span class="x-red">*</span>风险类型
                    </label>
                    <div class="layui-input-inline">
                        <select id="selectHType" name="hType" lay-verify="nnull" lay-search>
                            <option value="1">范围风险</option>
                            <option value="2">质量风险</option>
                            <option value="3">进度风险</option>
                            <option value="4">成本风险</option>
                            <option value="5">技术风险</option>
                            <option value="6">管理风险</option>
                            <option value="7">商业风险</option>
                            <option value="8">法律风险</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="H_Grade" class="layui-form-label">
                        <span class="x-red">*</span>风险级别
                    </label>
                    <div class="layui-input-inline">
                        <select id="selectHGrade" name="hGrade" lay-verify="nnull" lay-search>
                            <option value="high">高</option>
                            <option value="medium">中</option>
                            <option value="low">低</option>
                        </select>
                    </div>
                </div>
            </div>
            <#--            <div class="layui-form-item">-->
            <#--                <label for="H_Frequency" class="layui-form-label" style="width:130px;padding: 9px 0px;">-->
            <#--                    <span class="x-red">*</span>风险跟踪频度-->
            <#--                </label>-->
            <#--                <div class="layui-input-inline">-->
            <#--                    <select id="selectHFrequency" name="hFrequency" lay-verify="nnull" lay-search>-->
            <#--                        <option value="high">高</option>-->
            <#--                        <option value="medium">中</option>-->
            <#--                        <option value="low">低</option>-->
            <#--                    </select>-->
            <#--                </div>-->
            <#--            </div>-->
            <div class="layui-form-item">
                <label for="H_Influence" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">风险影响度</span>
                </label>
                <div class="layui-input-inline">
                    <select id="selectHInfluence" name="hInfluence" lay-verify="nnull" lay-search>
                        <option value="catastrophe">灾难</option>
                        <option value="major">重大</option>
                        <option value="moderate">中等</option>
                        <option value="slight">轻微</option>
                        <option value="sslight">极轻微</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="H_State" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">风险状态</span>
                </label>
                <div class="layui-input-inline">
                    <select id="selectHState" name="hState" lay-verify="nnull" lay-search>
                        <option value="todo">未开始</option>
                        <option value="doing">进行中</option>
                        <option value="done">已完成</option>
                    </select>
                </div>
            </div>


            <div class="layui-form-item">
                <label for="H_Manager" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">风险负责人</span>
                </label>
                <div class="layui-input-inline">
                    <select id="selectHManager" name="HManager" lay-verify="nnull" lay-search></select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="H_Member" class="layui-form-label" style="width:130px;padding: 9px 0px;">
                    <span class="x-red">风险相关者</span>
                </label>
                <div class="layui-input-inline" id="selectHMember">
                    <#--                    <select multiple="multiple"  name="HMember" lay-verify="nnull" lay-search></select>-->
                </div>
            </div>

            <div class="layui-form-item">
                <label for="H_Des" class="layui-form-label">
                    <span class="x-red">*</span>风险描述
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="txtHDes" name="hDes" lay-verify="nnull"
                              class="layui-input-block"
                              style="width:400px; height: 150px"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="H_Tactics" class="layui-form-label">
                    <span class="x-red">*</span>应对策略
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="txtHTactics" name="hTactics" lay-verify="nnull"
                              autocomplete="off" class="layui-input-block"
                              style="width:400px; height: 150px"></textarea>
                </div>
            </div>

            <div style="height: 60px"></div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <input class="layui-btn" id="import" value="组织标准风险库导入">
                <input class="layui-btn" id="import-similar" value="类似项目导入">
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
    layui.use(['form', 'laydate'], function () {
        $ = layui.jquery;
        var form = layui.form, laydate = layui.laydate;
        var proData;
        $.ajax({
            url: '/proMember/showProjectList',
            type: 'get',
            dataType: 'json',
            success: function (data) {
                console.info(data);
                proData = data['data'];

                $('#selectPName').empty();
                $('#selectHManager').empty();
                $('#selectHMember').empty();

                for (var pro in data['data']) {
                    // if(data['data'][u].projNo!=null)
                    console.log(pro);
                    $('#selectPName').append("<option value='" + data['data'][pro].id + "'>" + data['data'][pro].projName + ' ' + data['data'][pro].pmName + "</option>");

                }
                for (var j in data['data'][0].projMember) {
                    console.log(j);
                    $('#selectHManager').append("<option value='" + data['data'][0].projMember[j].uId
                        + "'>" + data['data'][0].projMember[j].uName + "</option>");
                    $('#selectHMember').append("<input type='checkbox' name='member' value='" + data['data'][0].projMember[j].uId
                        + "' title='" + data['data'][0].projMember[j].uName + "'>");
                }

                form.render();
            }
        });

        form.on('select(pname)', function (data) {
            console.log(data.value); //得到美化后的DOM对象

            $('#selectHManager').empty();
            $('#selectHMember').empty();
            for (var pro in proData) {
                if (proData[pro].id === data.value) {
                    for (var j in proData[pro].projMember) {
                        console.log(j);
                        $('#selectHManager').append("<option value='" + proData[pro].projMember[j].uId
                            + "'>" + proData[pro].projMember[j].uName + "</option>");
                        $('#selectHMember').append("<input type='checkbox' name='member' value='" + proData[pro].projMember[j].uId
                            + "' title='" + proData[pro].projMember[j].uName + "'>");
                    }
                }
            }
            form.render();
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

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        $('#import').click(function () {
            var w = ($(window).width() * 0.8);
            var h = ($(window).height() * 0.8);
            layer.open({
                id: 'import-risk',
                type: 2,
                area: [w + 'px', h + 'px'],
                fix: false,
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: '导入风险识别',
                content: 'importRisk'

            });
        });

        $('#import-similar').click(function () {
            var w = ($(window).width() * 0.8);
            var h = ($(window).height() * 0.8);
            var proId = form.val("projectId");
            console.log(proId.pId);
            layer.open({
                id: 'import-similar-risk',
                type: 2,
                area: [w + 'px', h + 'px'],
                fix: false,
                maxmin: true,
                shadeClose: true,
                shade: 0.4,
                title: '导入相似风险识别',
                content: 'importSimilarRisk?proId=' + proId.pId
            });
        });

        //监听提交
        form.on('submit(add)', function (data) {
            var HMember = [];
            $("input[name='member']:checked").each(function (i) {//把所有被选中的复选框的值存入数组
                console.log("menber:" + $(this).val());
                HMember.push($(this).val());
            });
            data.field.HMember = HMember;
            console.log(data.field);
            // alert('bj');
            // alert(data);
            $.ajax({
                url: 'addRisk',
                type: 'post',
                data: data.field,
                traditional: true,
                success: function (d) {

                    // layerAjax('addRisk', data.field, 'iList');
                    // parent.layer.msg("操作成功!", {time: 1000}, function () {
                    // });
                    //
                    // var index = parent.layer.getFrameIndex(window.name);
                    // if(d.flag){
                    //     // parent.layer.close(index);
                    //     // window.parent.layui.table.reload('projList');
                    layer.msg("操作成功!");
                    // }else{
                    //     layer.msg(d.msg,{icon:5});
                    // }
                }, error: function () {
                    console.log('error');
                    //     layer.alert("请求失败", {icon: 6},function () {
                    //         var index = parent.layer.getFrameIndex(window.name);
                    //         parent.layer.close(index);
                    //     });
                }
            });
        });


    });
</script>
</body>

</html>
