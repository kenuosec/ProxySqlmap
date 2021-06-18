### ProxySqlmap

**ProxySqlmap - 被动式发现SQL注入**

### 使用教程:

[![https://www.hualigs.cn/image/60cc44daa3f47.jpg](https://www.hualigs.cn/image/60cc44daa3f47.jpg)](https://www.hualigs.cn/image/60cc44daa3f47.jpg)

> 配置->配置

程序使用需要配置SQLmapApi以及监听端口:

[![https://www.hualigs.cn/image/60cc453954aeb.jpg](https://www.hualigs.cn/image/60cc453954aeb.jpg)](https://www.hualigs.cn/image/60cc453954aeb.jpg)

在SQLMAP中使用命令开开启SQLmapApi:

```
sqlmapapi -s --host=0.0.0.0 --port=8899

[15:15:07] [INFO] Running REST-JSON API server at '0.0.0.0:8899'..
[15:15:07] [INFO] Admin ID: 167f411a44783abc6abd061d8677888b
[15:15:07] [DEBUG] IPC database: /tmp/sqlmapipc-HNLL7U
[15:15:07] [DEBUG] REST-JSON API server connected to IPC database
[15:15:07] [DEBUG] Using adapter 'wsgiref' to run bottle
```

[![https://www.hualigs.cn/image/60cc45fb4eddd.jpg](https://www.hualigs.cn/image/60cc45fb4eddd.jpg)](https://www.hualigs.cn/image/60cc45fb4eddd.jpg)

保存后进行域名配置：

[![https://www.hualigs.cn/image/60cc462b85561.jpg](https://www.hualigs.cn/image/60cc462b85561.jpg)](https://www.hualigs.cn/image/60cc462b85561.jpg)

这里需要使用正则表达式:

```
^www.baidu.com$
^www.redsec.club$
```

点击配置->开启后程序将进行工作，将代理设置为127.0.0.1:8081即可，程序会自己对你所点击过的URL以及POST请求进行测试:

[![https://www.hualigs.cn/image/60cc4747971b2.jpg](https://www.hualigs.cn/image/60cc4747971b2.jpg)](https://www.hualigs.cn/image/60cc4747971b2.jpg)

[![https://www.hualigs.cn/image/60cc474781371.jpg](https://www.hualigs.cn/image/60cc474781371.jpg)](https://www.hualigs.cn/image/60cc474781371.jpg)

在检测完成后可选中条目点击右键->打开可查看到漏洞详情：

[![https://www.hualigs.cn/image/60cc47a96def2.jpg](https://www.hualigs.cn/image/60cc47a96def2.jpg)](https://www.hualigs.cn/image/60cc47a96def2.jpg)
