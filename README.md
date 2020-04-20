<<<<<<< HEAD
# 配置
配置文件**application.yml**在![如图，现在选中的是application-mysql-dev](https://upload-images.jianshu.io/upload_images/10854666-c0543524604ae982.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![可选的配置文件，用于环境切换（测试、开发、生产）](https://upload-images.jianshu.io/upload_images/10854666-fb8017daed405801.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以在配置文件中，设置tomcat的端口（springboot内置了Tomcat），要连接的数据库。
```
server:
  port: 8081 #这里写Tomcat端口
spring:
  devtools:
      restart:
        enabled: true
  datasource:
        url: jdbc:mysql://xx.xx.xx.xx:xxxx/DB_NAME?useUnicode=true&characterEncoding=UTF-8
        username: 用户名
        password: "密码"
```
目前，最好都从我的数据库（lenos_origin）克隆一个自己的数据库，在自己的上面改，防止乱掉。自己建表时记得`ENGINE=InnoDB DEFAULT CHARSET=utf8;`，尽量少用外键（逻辑上外键可，物理外键尽量少，可以存一些冗余信息，减少query次数还好写，比如某表存了xxxid，还经常需要显示xxx_name，不妨把name也直接存表了）。

# lenosp脚手架说明

*   使用了开源脚手架[lenos](https://gitee.com/bweird/lenos) (p为spring boot版本扩展名)一款快速开发模块化脚手架，采用spring boot 2.0.1+spring+SpringMvc+mybatis+shiro+swagger+ehcache+quartz+freemarker+layui技术开发，集成了Activiti5.22。
*   项目部署说明
    *   修改 application.yml imagePath 路径 把image文件夹图片赋值进路径，即可正常展示头像
    *   数据库使用mysql5.6，在云端，项目可直接运行。

# 启动说明

```source-shell
mvn clean package
mvn package
java -jar len-web.jar
```

# 技术

采用的技术即lenos脚手架集成的部分技术。

*   jdk：1.8
*   核心框架：spring boot 2.0.1.RELEASE
*   安全框架：Apache Shiro（用于用户角色权限控制）
*   工作流引擎：Activiti5.22
*   数据库连接池：druid
*   视图框架：spring mvc
*   持久层框架：MyBatis
*   模板引擎：freemarker
*   缓存：redis、ehcache（目前未使用）
*   定时：quartz 2.3.0（目前未使用）
*   前端页面：layui

# 模块和可参考的内容✨
典型的springMVC+MyBatis，可以自己查一下。
- ######注意区分系统内权限和项目内权限。涉及工作范围的疑问*一定要群里说*，私聊可能更乱😂。
- ######已有的模块：![](https://upload-images.jianshu.io/upload_images/10854666-fc22088c415bccc4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
除了负责工作流部分的（这个系统应该仅有立项审批流程用到工作流），**都写在len-web里**就可以了，或者自己开模块，自己开模块可以先写套hello controller，确定能正常工作了再写。
- **len-activiti** @我
引入了activiti5.22，数据库里act_开头的23张表都是activiti引擎需要操纵的，通过在这个模块调用activiti的xxxxxService来管理。
- **len-core** @all 不需要修改，只需要使用
这里有个通用service层，通用数据库操作增删改都有了，使用只需要`public interface xxxxxService extends BaseService<entity类型,‘主键’类型>`，xxxxxService就可以直接调这些操作了。
![](https://upload-images.jianshu.io/upload_images/10854666-f75a179e4c69f53b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- **len-sys** @all 尽量不要修改，只使用
要修改，先在群里商量。这块主要是系统内的用户，角色，用户-角色，菜单，角色-菜单，菜单和用户系统内的权限挂钩。
- **len-web** @all 
     ###### 🏀🏀🏀典型的springMVC+MyBatis，一套大概是：
     **数据库表 — entity，resource里的mapper**
     **mapper — service（service的impl）—controller里调service — ftl页面**

#常见操作一览
- 获取当前用户id和当前用户
```
String id = Principal.getPrincipal().getId(); // getPrincipal() return type: CurrentUser
SysUser user = userService.selectByPrimaryKey(id);
```
-  pull、push
push前需要邀请为collaborator。
![push前最好在群里说一哈](https://upload-images.jianshu.io/upload_images/10854666-e72011e45815a4be.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- 用IDEA自带/Navicat/Workbench操作数据库很方便，连接信息在前面说过的application.yml
- shiro拦截（建议先忽略，之后来得及再加，目前只是拦截不登录就访问）
![shiro配置](https://upload-images.jianshu.io/upload_images/10854666-528ba3dfd45c1f8a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
**`filterMap.put("xxxxxxx", "anon");`xxxxxxx是不登录就可访问的url。**
![](https://upload-images.jianshu.io/upload_images/10854666-dfa01b96a16baef7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![shiro也是一写写一套的= =](https://upload-images.jianshu.io/upload_images/10854666-521efabec6d8ded5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- [Git在IDEA中的使用（详细图文全解）](https://blog.csdn.net/mucaoyx/article/details/98476174)
     ######大家各自新建分支，push到分支。merge前一定要检查！！！
=======
# [开发说明 | AchieveIt和lenosp脚手架 Q&A](https://www.jianshu.com/p/85f585f4fedf)

# 演示在[这里](http://39.97.120.235:8081/)
admin 123456
其余密码和用户名相同
QA_leader_Tom
admin
chieflwj
EPG-leader-Grace
config
PMwifi
>>>>>>> 8d6e2dac417cc9f507568d67c6294e9daa3ec78a
