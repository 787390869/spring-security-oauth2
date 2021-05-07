**我想搭建一个这样的系统!**
- 1.首先保证是单体服务 <br/>
- 2.支持密码模式,授权码模式,刷新模式 <br/>
- 3.可以控制本服务接口权限,更可以控制接入的资源服务的接口权限 <br/>
- 4.不与Session Cookie耦合, 不依赖网关鉴权, 不依赖于任何Spring Cloud组件 </br>
- 5.用户角色权限本服务配置,用户本服务登录可访问任意应用 <br />
- 6.应用可在本平台上架,上架后从本平台统一登录,应用列表点击,携带授权码去到该应用 (正向授权码) </br>
- 7.应用可不在本平台上架,部署后从应用来到平台登录,拿到授权码后回调应用(反向授权码) </br>
- 8.应用轻松接入,sdk引入,常规接口配置后,登录,登出,刷新,登录用户信息优雅获取 </br>
- 9.拓展Google Authentication身份验证器 验证码验证
- 10.拓展AliYun智能风控平台 智能验证

**为什么要搭建一个这样的系统?**
- 1.关于Spring Security OAuth2网上的资料前篇一律,上来就是简单模式(implicit),密码模式(password),
客户端模式(client credentials), 授权码模式(authorization code)一种模式一个服务来实现,外加一个
资源服务器,这么多服务器,端口都不一样,根据不同模式要去不同端口登录,有违背单点登录的目的 </br>
- 2.服务搭建好了,如何应用到业务场景中? 比如不同应用的管理?应用用户角色权限的统一管理?当前登录用户信息
的获取?第三方应用如何优雅接入? </br>
- 3.授权服务来管理用户角色权限的话,相关的接口如何自己进行控制?授权服务资源服务同一进程后如何相互配合?
- 4.拓展授权码模式,自行生成授权码,JSON传给前端,前端带code跳转到第三方应用

**线上链接**
- 1.[SSO开放平台](http://sso.zhangziqiang.cn/) 开放平台(SSO) 与 应用中心, 代码&&文件系统(Resource Server)就构成了OAuth2协议的应用关系 

- 2.[前端应用接入文档](http://sso.zhangziqiang.cn/document/web)

- 3.[后端应用接入文档](http://sso.zhangziqiang.cn/document/server)

**技术栈**</br>
***后端***
- 1.Spring Boot 2.3.1.RELEASE
- 2.Spring Cloud OAuth2 2.2.5.RELEASE
- 3.Mybatis Plus 3.4.2
- 4.Mysql8 + P6spy + Druid
- 5.Spring Data Redis 2.3.1.RELEASE
- 6.Redis-Lock v1.0.0 (自写注解式用法的分布式锁)
- 7.Swagger + Knife4j
- 8.Google Authentication 身法验证器
- 9.阿里云 风控平台智能验证
- 10.Docker

***前端***
- 1.Vue 3.0.11
- 2.Ant Design Vue 2.1.3
- 3.TypeScript 4.1.3
- 4.composition api vue3.x写法 && Decorator + Vue Component 类继承写法

**SSO后端代码运行**
- 1.git clone master分支的代码
- 2.下载公网私服[maven-settings.xml](http://minio.zhangziqiang.cn:9200/candy/202105/04/settings.xml), 因为sso-platform-core所用到的redis-lock与sdk只有该私服才有,setting.xml需要设置你本地的repository地址哦
- 3.启动项目 *项目所用到的mysql与redis都是公网上的,所以可以直接启动*

***说明***
- 1.***为了方便大家调试,没有屏蔽我公网的数据与accessKey那些,请高抬贵手不搞破坏***
- 2.***application.yml 里可配置是否校验谷歌身份验证器与是否阿里云智能校验,默认只校验阿里云的***

**SSO前端代码运行**
- 1.git clone [阿里Git仓库master分支代码](https://code.aliyun.com/787390869/sso-platform-web.git)
- 2.npm install...
- 3.npm run dev

***说明***
- ***为方便大家调试,minio fastdfs地址都是我公网上的,请高抬贵手不要搞破坏***
---
- 关于sso服务的具体实现与sso SDK的使用方式可以到[后端应用接入文档](http://sso.zhangziqiang.cn/document/server) </br>
---
- 关于sso前端代码与Vue3.x脚手架的使用可以到[前端应用接入文档](http://sso.zhangziqiang.cn/document/web) </br>

---
***QQ: 787390869*** </br>
***WX: zzq19980410*** </br>
***有疑问可以评论或私聊我,也欢迎大家支出我不对的地方或者需要优化的地方,谢谢您的观看***

# 求点亮小星星,求求惹!!!!!

