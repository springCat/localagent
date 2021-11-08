# localagent
## 用途:

  fegin 微服务本地开发自测时,需要

1. 本地启动多个模块项目相互调用,

2. 某一个接口调用本地服务,其他接口正常走服务调用

3. mock 接口数据
4. 也可以基于这个工程,开发其他功能

## 用法:
1.下载代码到本地,mvn clean install编译打包,采用的是localagent-1.0-SNAPSHOT-jar-with-dependencies.jar这个类

2. 在启动类上配置agent,指向打出的jar地址  -javaagent:XXXX\localagent.jar

3. 在resources目录下放入 localServer.setting
启动服务

4. 后续修改classes目录下的localServer.setting(运行时以这个目录的配置为准) 中配置接口或者工程路由,无需重启,会监控文件变更自动加载的


## 路由规则:
```
[servers]
bookstore-promotion-facade = 127.0.0.1:2223
bookstore-promotion-biz = :2224

[interfaces]
bookstore-promotion-facade/freeVipActivity/getFreeMonthActivityInfo = :2223
bookstore-interaction-biz/userGroupConfig/selectByUserGroupId = :8221
userGroupConfig/selectByUserGroupId = :8221
```

### 本地路由优先级规则:
	模块+接口名 > 接口名 > 模块 > 正常的路由策略
