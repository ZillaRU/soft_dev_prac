## 项目说明
- 使用了开源脚手架[lenos](https://gitee.com/bweird/lenos) (p为spring boot版本扩展名)一款快速开发模块化脚手架，采用spring boot 2.0.1+spring+SpringMvc+mybatis+shiro+swagger+ehcache+quartz+freemarker+layui技术开发，集成了Activiti5.22。
- 项目部署说明
   - 修改 application.yml imagePath 路径 把image文件夹图片赋值进路径，即可正常展示头像
   - 数据库使用mysql5.6，在云端，项目可直接运行。

## 启动说明
```bash
mvn clean package
mvn package
java -jar len-web.jar
```
## 技术
采用的技术即lenos脚手架集成的部分技术。
* jdk：1.8
* 核心框架：spring boot 2.0.1.RELEASE
* 安全框架：Apache Shiro（用于用户角色权限控制）
* 工作流引擎：Activiti5.22
* 数据库连接池：druid
* 视图框架：spring mvc
* 持久层框架：MyBatis
* 模板引擎：freemarker
* 缓存：redis、ehcache（目前未使用）
* 定时：quartz 2.3.0（目前未使用）
* 前端页面：layui

## 演示地址
尚在开发整合阶段，暂不提供
