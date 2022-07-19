# Gblog-Api

<p align="center">
  <img width="80" src="https://s1.ax1x.com/2022/07/15/jhlfH0.jpg"/>
</p>
<p align="center">
  <a href="https://gitee.com/zhloong/Gblog-zhloong">
    <img src="https://gitee.com/zhloong/Gblog-zhloong/badge/star.svg?theme=white" alt="star"/>
    <img src="https://gitee.com/zhloong/Gblog-zhloong/badge/fork.svg" alt="fork"/>
  </a>
  <a href="https://github.com/zhl-yang/Gblog-zhloong">
      <img src="https://img.shields.io/github/stars/zhl-yang/Gblog-zhloong.svg?style=social" alt="Github star"/>
      <img src="https://img.shields.io/github/forks/zhl-yang/Gblog-zhloong.svg?style=social" alt="Github forks"/>
  </a>
  <a href="https://github.com/vuejs/vue">
    <img src="https://img.shields.io/badge/vue-2.6.11-brightgreen.svg" alt="vue"/>
  </a>
  <a href="https://github.com/zhl-yang/Gblog-zhloong/blob/master/license">
    <img src="https://img.shields.io/github/license/mashape/apistatus.svg" alt="license"/>
  </a>
</p>

### 仓库

> 前端仓库：[码云](https://gitee.com/zhloong/Gblog-zhloong) | [github](https://github.com/zhl-yang/Gblog-zhloong)  
> 后端仓库：
>
> [博客展示地址](https://blog.zhloong.xyz)

### 介绍

>
> [Gblog-zhloong](https://gitee.com/zhloong/Gblog-zhloong) 的Java后端代码
> 
> 文件目录含数据初始化文件（见sql文件夹）
>
> 项目还不是很完整，博客管理后台及用户相关模块需要自己编写
> 
> 欢迎star

### 技术选型

本项目基于SpringBoot实现

| 名称 | 版本 |
| ---- | ---- |
| [SpringBoot](https://spring.io/projects/spring-boot) | [3.0.0]()|
| [maven](https://maven.apache.org/) | [3.5.0]()|
| [MySQL](https://www.mysql.com/) | [5.7]()|
| [mybatis](https://mybatis.org/) | [3.2.0]()|
| [mybatis-plus](https://mybatis.plus/) | [3.2.0]()|
| [Redis](https://redis.io/) | [6.2.6]()|
| [druid](https://druid.alibaba.com/) | [1.0.29]()|
| [swagger](https://swagger.io/) | [3.0.0]()|
| [spring-boot-starter-data-redis](https://spring.io/projects/spring-boot-starter-data-redis) | [3.0.0]()|
| [logback](https://logback.qos.ch/) | [5.2]()|

### 相关工具包

| 名称 | 版本 | 描述 |
| ---- | ---- | ---- |
| [hutool](https://gitee.com/dromara/hutool) | [5.8.0.M2]()|🍬小而全的Java工具类库，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。|
| [lombok](https://gitee.com/lombok/lombok) | [1.18.12]()|🍬Lombok是一个轻量级的API支持库，可以帮助你简化代码，提高开发效率。|
| [java-jwt](https://gitee.com/bennyhuo/java-jwt) | [1.7.0]()|🍬JWT是一个Java语言的安全框架，可以用来生成、验证、解码、刷新、授权等。|
| [commons-pool2](https://gitee.com/bennyhuo/commons-pool2) | [2.6.1]()|🍬Commons Pool2是一个Java线程池框架，可以用来管理线程池，提高程序的性能。|
| [kaptcha](https://gitee.com/bennyhuo/kaptcha) | [1.4.7]()|🍬Kaptcha是一个Java语言的验证码生成器，可以用来生成验证码。|
| [shiro-core](https://gitee.com/bennyhuo/shiro-core) | [1.2.4]()|🍬Shiro是一个Java语言的安全框架，可以用来实现权限管理、登录认证、授权管理、访问控制等。|
| [tlog-all-spring-boot-starter](https://gitee.com/bennyhuo/tlog-all-spring-boot-starter) | [1.0.0]()|🍬Tlog是一个日志管理系统，可以用来管理日志。|

### 环境要求
JDK 1.8、Redis 6.2.6+、MySQL 5.7

### 导入数据库
```
1、数据库名:gblog
2、数据库字符集设置为:utf8mb4
3、执行sql文件导入数据
```
### 配置数据库
```
修改配置文件：blog-rest/src/main/resources/application-prod.yml
注意Redis密码配置，无密码则为空
```

### 启动服务

```
打开文件：blog-rest/src/main/java/com/zhloong/BlogRestApplication.java
执行main方法
```

### 编译打包

```
cd blog-rest/
mvn package
```


