## lenos收录到layui官方2018年度最佳案例名单中
![图片说明](http://ww4.sinaimg.cn/large/0060lm7Tly1fnjfv8d366j310x0hsjwr.jpg "图片说明")

## 交流群
* 您有疑问，我们解答，您有建议，我们吸取，您有idea 我们欢迎
- <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=2c71822be7b8c061087a94647663a742a274626a846b76647743ed556a24cabc"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="开源脚手架lenos交流群" title="开源脚手架lenos交流群"></a>

## 普通版
- 地址：[lenos](https://gitee.com/bweird/lenos) 

## 项目说明
- lenos(p为spring boot版本扩展名)一款快速开发模块化脚手架，采用spring boot+spring+SpringMvc+mybatis+shiro+swagger+ehcache+quartz+freemarker+layui技术开发；实现功能有系统模块：菜单管理、用户管理、角色管理，系统监控：系统日志、接口api、sql监控。本项目会一直维护并集成新的技术，给您的开发节约时间成本，本项目拥有非boot版本，本项目也为纪念逝去的2017年，也是总结之作。
## 未来蓝图
![图片说明](http://ww4.sinaimg.cn/large/0060lm7Tly1fniijr2z0tj30ud0urwga.jpg "图片说明")

## 功能说明
- 项目目前拥有
- 系统管理：菜单管理、用户管理、角色管理
- 统一查询 pagehelper分页，查询调用BaseServiceImpl show model set数据，传入T，配合xml编写，即可自定义查询。
- 采用shiro技术，可配置化权限管理，精确到按钮(也可以是某一表单)功能分配
- 登录次数校验，超出定义次数后冻结一段时间账号
- 系统监控：系统日志、接口api、系统监控、可配置定时任务
- 前端可配置化定时任务。
- 采用swagger可视化出实时方法格式以及数据属性，采用阿里drud监控sql。
## 启动说明
- db使用mysql，项目数据库在 根目录db文件夹下，
导入数据库后 设定数据库用户名密码 在文件lenosp\len-web\src\main\resources\application.yml中
项目开始会报实体类 get set错误，这是正常的，因为本项目entity使用的是 lombok 大大简化了代码量
您可以直接运行，项目可以正常启动。
如何消除？
如果您使用的为idea 只需要file -> setting->plugins->Browse Repositeories 输入 lombok 集成插件重启idea即可消除错误
如果您使用 eclipse 需要下载 lombk jar包 手动集成。
## 技术
* jdk：1.8
* 核心框架：Spring Framework 1.5.9.RELEASE
* 安全框架：Apache Shiro
* 数据库连接池：druid
* 视图框架：spring mvc
* 持久层框架：MyBatis
* 模板引擎：freemarker
* 缓存：ehcache
* 定时：quartz 2.3.0
* 前端页面：layui

## ps
- lenos承诺永久开源，全部免费，无任何收费地方
- 如果您喜欢lenos，可以clone下来使用，您的star将是本人前进的动力，本项目无丝毫保留开源，如果您有技术疑问，可以加群交流。
- 本项目处于未成年阶段，如果您有好的idea，欢迎参与开源。
- 如果lenos对您有一点帮助，您可以点个star，就是对作者最大的支持了。
* lenos脚手架会一直更新下去，我们的征途是星辰大海

## 项目图片
* 登录账号：admin 密码：123456
![图片说明](http://ww1.sinaimg.cn/large/0060lm7Tly1fn2bsi2kexj311y0hsdmw.jpg "图片说明")
![图片说明](http://ww2.sinaimg.cn/large/0060lm7Tly1fn2c1yaqrjj311y0hvdhj.jpg "图片说明")

* 菜单管理分为一级菜单 二级菜单 按钮(也可以是元素)权限
![图片说明](http://ww2.sinaimg.cn/large/0060lm7Tly1fn2c2l057sj311y0hu767.jpg "图片说明")

* 用户可以上传头像
![图片说明](http://ww2.sinaimg.cn/large/0060lm7Tly1fn2c33qyvrj311y0hv40e.jpg "图片说明")
![图片说明](http://ww2.sinaimg.cn/large/0060lm7Tly1fn2c3m4b77j311y0hpq4b.jpg "图片说明")

* 自定义定时类，实现Job，前端配置定时类，即可控制任务类，已实现定时类获取spring上下文，
* 项目启动加载完bean后利用spring boot监听开启一个线程，检测已启动的定时任务，进行开启。
![图片说明](http://ww1.sinaimg.cn/large/0060lm7Tly1fn873a0sqnj311y0gc0tr.jpg "图片说明")
![图片说明](http://ww1.sinaimg.cn/large/0060lm7Tly1fn876ntgczj30t707xdgf.jpg "图片说明")

* 日志监控 利用aop 自定义拦截日志持久化到数据库并对数据进行监控
![图片说明](http://ww3.sinaimg.cn/large/0060lm7Tly1fn8793d3llj311y0gxq4s.jpg "图片说明")

* 接口
![图片说明](http://ww2.sinaimg.cn/large/0060lm7Tly1fn2c4swdjrj311y0hptam.jpg "图片说明")
![图片说明](http://ww2.sinaimg.cn/large/0060lm7Tly1fn2c5ev8tgj30w50e7wfs.jpg "图片说明")
![图片说明](http://ww2.sinaimg.cn/large/0060lm7Tly1fn2dvrcl9lj30wd0e6gmd.jpg "图片说明")

