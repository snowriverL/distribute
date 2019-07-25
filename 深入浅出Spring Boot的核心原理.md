## 基于你对springboot的理解描述一下什么是springboot
> springboot 框架是为了能够帮助使用 spring 框架的开发者快速高效的构建一个基于 spirng 框架以及 spring 生态体系的应用解决方案。它是对“约定优于配置”这个理念下的一个最佳实践。因此它是一个服务于框架的框架，服务的范围是简化配置文件。

## 约定优于配置指的是什么？
> 在程序开发过程中我们约定好一些规则可以使我们更少的进行配置和代码编写

* #### SpringBoot中的体现

> 1. maven 的目录结构  
a) 默认有 resources 文件夹存放配置文件  
b) 默认打包方式为 jar
> 2. spring-boot-starter-web 中默认包含 spring mvc 相关依赖以及内置的 tomcat 容器，使得构建一个 web 应用更加简单
> 3. 默认提供 application.properties/yml 文件
> 4. 默认通过 spring.profiles.active 属性来决定运行环境时读取的配置文件
> 5. EnableAutoConfiguration 默认对于依赖的 starter 进行
自动装载

## @SpringBootApplication由哪几个注解组成，这几个注解分别表示什么作用

* ####  @Configuration
> 它是 JavaConfig形式的基于 Spring IOC 容器的配置类使用的一种注解。因为 SpringBoot 本质上就是一个 spring 应用，所以通过这
个注解来加载 IOC 容器的配置是很正常的。所以在启动类里面标注了@Configuration，意味着它其实也是一个 IoC
容器的配置类。
* ####  @ComponentScan
> 扫描指定路径下的标识了需要装配的类，自动装配到 spring 的 Ioc 容器中；不指定路径会扫描当前package
* ####  @EnableAutoConfiguration
> EnableAutoConfiguration 的主要作用其实就是帮助springboot应用把所有符合条件的@Configuration 配置
都加载到当前 SpringBoot 创建并使用的 IoC 容器中。

## springboot自动装配的实现原理
* #### @Import 注解可以配置三种不同的 class
> 1. 基于普通 bean 或者带有@Configuration 的 bean 进行注入
> 2. 实现 ImportSelector 接口进行动态注入
> 3. 实现 ImportBeanDefinitionRegistrar 接口进行动态注入

> springboot通过 import 导入第三方提供的 bean 的配置类：AutoConfigurationImportSelector
EnableAutoConfiguration 会帮助springboot 应用把所有符合@Configuration 配置都加载
到当前 SpringBoot 创建的 IoC 容器，而这里面借助了Spring 框架提供的一个工具类 SpringFactoriesLoader 的
支持。以及用到了 Spring 提供的条件注解@Conditional，选择性的针对需要加载的 bean 进行条件过滤,
会先扫描 spring-autoconfiguration-metadata.properties文件，最后在扫描 spring.factories 对应的类时，会结合
前面的元数据进行过滤，为什么要过滤呢？ 原因是很多的@Configuration 其实是依托于其他的框架来加载的，
如果当前的 classpath 环境下没有相关联的依赖，则意味着这些类没必要进行加载，所以，通过这种条件过滤可以
有效的减少@configuration 类的数量从而降低SpringBoot 的启动时间。

## spring中的spi机制的原理是什么？
> 通过自动装配，固定扫描目录下spring.factories的内容，结合spring-autoconfiguration-metadata.properties过滤不需要装配的实例，
将实例动态注入到SpringIOC容器中。