# HBase-Quickstart
HBase快速入门

该应用程序充当的是HBase的Client角色，连接HBase服务端，通过提供的JavaAPI增删改查HBase数据。

## 一、Maven依赖

作为客户端，只需要引入hbase-client，依赖版本需要与服务端的保持一致。

```xml
<dependency>
    <groupId>org.apache.hbase</groupId>
    <artifactId>hbase-client</artifactId>
    <version>1.2.9</version>
</dependency>

```

## 二、HBase连接配置

```properties
hbase.zookeeper.quorum=node2:2181,node3:2181,node4:2181
```

其实连接的就是Zookeeper的地址，Zookeeper存储所有Region的寻址入口。

## 三、API使用示例

待续...