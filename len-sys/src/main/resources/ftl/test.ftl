<html lang="en">
<head>
  <link rel="shortcut icon" href="${re.contextPath}/plugin/x-admin/favicon.ico" type="image/x-icon" />
  <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
  <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/font.css">
  <link rel="stylesheet" href="${re.contextPath}/plugin/x-admin/css/xadmin.css">
  <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="${re.contextPath}/plugin/x-admin/js/xadmin.js"></script>
</head>
<body>
<#macro tree data start end>
   <#if (start=="start")>
    <div class="left-nav">
    <div id="side-nav">
    <ul id="nav">
    </#if>
<#list data as child>
        <#if child.children?size gt 0>
              <li><a href="javascript:;">
                <i class="iconfont">&#xe6b8;</i><cite>${child.name}</cite><i class="iconfont nav_right">&#xe697;</i></a><ul class="sub-menu">
              <@tree data=child.children start="" end=""/>
              </ul>
              </li>
               <#else>
            <li>
              <a _href="order-list.html">
                <i class="iconfont">&#xe697;</i>
                <cite>${child.name}</cite>
              </a>
            </li>
          </#if>
      </#list>
  <#if (end=="end")>
  </ul>
  </div></div>
  </#if>
</#macro>
<@tree data=menu1 start="start" end="end"/>



</body>
</html>