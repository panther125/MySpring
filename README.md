# <img src="doc/spring-framework.png" width="80" height="80"> mini-spring
## 关于

**mini-spring**是简化版的spring框架，能帮助你快速熟悉spring源码和掌握spring的核心原理。抽取了spring的核心逻辑，代码极度简化，保留spring的核心功能，如IoC和AOP、资源加载器、事件监听器、类型转换、容器扩展点、bean生命周期和作用域、应用上下文等核心功能。

## 功能
#### 基础篇
* IoC
    * 实现一个简单的容器
    * BeanDefinition和BeanDefinitionRegistry
    * Bean实例化策略InstantiationStrategy
    * 为bean填充属性
    * 为bean注入bean
    * 资源和资源加载器
    * 在xml文件中定义bean
    * 容器扩展机制BeanFactoryPostProcess和BeanPostProcessor
    * 应用上下文ApplicationContext
    * bean的初始化和销毁方法
    * Aware接口
    * bean作用域，增加prototype的支持
    * FactoryBean
    * 容器事件和事件监听器
* AOP
    * [切点表达式](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#切点表达式)
    * [基于JDK的动态代理](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基于JDK的动态代理)
    * [基于CGLIB的动态代理](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#基于CGLIB的动态代理)
    * [AOP代理工厂ProxyFactory](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#AOP代理工厂)
    * [几种常用的Advice: BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#几种常用的AdviceBeforeAdviceAfterAdviceAfterReturningAdviceThrowsAdvice)
    * [PointcutAdvisor：Pointcut和Advice的组合](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#pointcutadvisorpointcut和advice的组合)
    * [动态代理融入bean生命周期](https://github.com/DerekYRC/mini-spring/blob/main/changelog.md#动态代理融入bean生命周期)
    

#### 扩展篇
* PropertyPlaceholderConfigurer
* 包扫描
* @Value注解
* 基于注解@Autowired的依赖注入
* 类型转换（一）
* 类型转换（二）

#### 高级篇
* 解决循环依赖问题（一）：没有代理对象
* 解决循环依赖问题（二）：有代理对象

## 参考
- [bugstack](https://bugstack.cn/md/spring/develop-spring/2021-05-16-%E7%AC%AC1%E7%AB%A0%EF%BC%9A%E5%BC%80%E7%AF%87%E4%BB%8B%E7%BB%8D%EF%BC%8C%E6%89%8B%E5%86%99Spring%E8%83%BD%E7%BB%99%E4%BD%A0%E5%B8%A6%E6%9D%A5%E4%BB%80%E4%B9%88%EF%BC%9F.html)