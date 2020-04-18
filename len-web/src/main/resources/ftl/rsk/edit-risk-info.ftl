<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>新增项目基本信息</title>
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
                    <legend style="font-size:16px;">基本信息</legend>
                </fieldset>
            </div>
            <div class="layui-form-item">
                <label for="hId" class="layui-form-label">
                    风险编号
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="hId" readonly name="hId" value="${riskDetail.HId}"
                           class="layui-input" style="width: 300px;">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="pName" class="layui-form-label">
                    项目名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="pName" readonly name="pName" value="${riskDetail.PName}"
                           class="layui-input" style="width: 300px;">
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
                <label for="hDes" class="layui-form-label">
                    风险描述
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="hDes" name="hDes"
                              autocomplete="off" class="layui-textarea"
                              style="width:400px; height: 150px">${riskDetail.HDes}</textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="hTactics" class="layui-form-label">
                    应对策略
                </label>
                <div class="layui-input-inline">
                    <textarea type="text" id="hTactics" name="hTactics"
                              class="layui-textarea"
                              style="width:400px; height: 150px">${riskDetail.HTactics}</textarea>
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
    layui.use(['form', 'laydate'], function () {
        $ = layui.jquery;
        var form = layui.form, laydate = layui.laydate;
        $.ajax({
            url: "/proMember/showMemberList?riskId=${riskDetail.HId}",
            type: 'get',
            dataType: 'json',
            success: function (data) {
                console.info(data);

                $('#selectHManager').empty();
                $('#selectHMember').empty();

                // for (var pro in data['data']) {
                // // if(data['data'][u].projNo!=null)
                // console.log(pro);
                // $('#selectPName').append("<option value='" + data['data'][pro].id + "'>" + data['data'][pro].projName + ' ' + data['data'][pro].pmName + "</option>");

                for (var j in data['data']) {
                    console.log(data['data'][j]);
                    $('#selectHManager').append("<option value='" + data['data'][j].uId + "'>" + data['data'][j].uName + "</option>");
                    $('#selectHMember').append("<input type='checkbox' name='member' value='" + data['data'][j].uId + "' title='" + data['data'][j].uName + "'>");
                }
                // }
                //
                $("#selectHType option[value='${riskDetail.HType}']").prop("selected", true);
                $("#selectHGrade option[value='${riskDetail.HGrade}']").prop("selected", true);
                $("#selectHInfluence option[value='${riskDetail.HInfluence}']").prop("selected", true);
                $("#selectHState option[value='${riskDetail.HState}']").prop("selected", true);
                $("#selectHManager option[value='${riskDetail.HManager}']").prop("selected", true);
                <#list riskDetail.HMember as member>
                $("input[name='member'][value='${member}']").prop("checked", true);
                </#list>
                form.render();
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

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        //监听提交
        form.on('submit(edit)', function (data) {
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
                url: 'updateRisk',
                type: 'post',
                data: data.field,
                traditional: true,
                success: function (d) {
                    layer.msg("操作成功!", {time: 1000}, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.layui.table.reload('riskList');
                    });

                    // layer.msg("操作成功!");

                    return false;
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
