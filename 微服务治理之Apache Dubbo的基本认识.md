### Dubbo是什么？以及为什么要用？
dubbo 主要是一个分布式服务治理解决方案，那么什么是服务治理？服务
治理主要是针对大规模服务化以后，服务之间的路由、负载均衡、容错机制、
服务降级这些问题的解决方案，而 Dubbo 实现的不仅仅是远程服务通信，
并且还解决了服务路由、负载、降级、容错等功能。

### Dubbo目前支持哪些协议进行服务发布，请实现将服务以Dubbo协议以及REST协议进行发布
1.默认的dubbo协议  
2.rmi协议 Java标准的远程调用协议  
3.hessian协议 基于Hessian的远程调用协议  
4.http协议 基于http远程调用协议  
5.WebService协议 基于WebService的远程调用协议  

> [Dubbo客户端](https://github.com/snowriverL/distribute/tree/master/dubbo-client)

> [Dubbo服务端](https://github.com/snowriverL/distribute/tree/master/dubbo-server)
### 在Dubbo中，zookeeper主要起到什么样的作用？请简单描述一下 Dubbo服务注册发现的实现原理

>* 作用： 管理服务结点及监控服务结点

>* 实现原理

1、服务提供者在暴露服务时，会向注册中心注册自己，具体就是在${service interface}/providers目录下添加 一个节点（临时），服务提供者需要与注册中心保持长连接，一旦连接断掉（重试连接）会话信息失效后，注册中心会认为该服务提供者不可用（提供者节点会被删除）。

2、消费者在启动时，首先也会向注册中心注册自己，具体在${interface interface}/consumers目录下创建一个节点。

3、消费者订阅${service interface}/ [ providers、configurators、routers ]三个目录，
这些目录下的节点删除、新增事件都会通知消费者，根据通知，重构服务调用器(Invoker)。