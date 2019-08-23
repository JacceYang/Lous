# 背景




# 项目简介

**项目名称**:
*** 
Lous(劳斯) 谐音(lost 又名迷失) 是一套专注应用级别 API 网关处理架构,其主要目的是抽离API 层方法调用时非业务逻辑的渗透。
让代码开发更加专注(more focused)，业务代码更加整洁(more clean)。Lous 关注方法调用过程中常见的结果缓存(@EnableCache),恶意频繁攻击(@Duplica),
方法调用拦截和预处理和后处理功能。框架借鉴了spring-boot中约定大于配置思路,开箱即用,简化开发流程。框架废弃了经典的xml 配置化引入方式,采用基于
注解的方式引入所需的功能(more flexible)。

**项目内容**:
主要包括API层网关中常见的操作组件
* API 网关拦截处理器

<img src="https://github.com/JacceYang/PersonProfile/blob/master/DX-20190823%402x.png" withd="240px">

> 支持应用级别 API网关，实现可配置化的拦截器，校验器和各种错误处理能力。 
> 




* 分级缓存
> 主要实现缓存的分级存储


**环境说明**
 >JDK Version >=JDK 8.0 
 > Spring Framework >= 4.3.17
 
**起步**

*** API 方法调用 ***

```java

@SpringBootApplication
@EnableKeep(annotation = {Duplica.class, Power.class}) // Enable api gateway annotation ,and sub component  Duplica(频频调用拦截) and Power(方法调用拦截 处理开启 )

public class LousApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LousApplication.class, args);
        LoginService loginServiceImpl = (LoginService) run.getBean("loginServiceImpl");
        loginServiceImpl.login("ang", 15);
        System.out.println("-------end---------");

    }
}

```
通过以上的@EnableKeep注解,应用就开启了@Keep 组件能力.通过引入{Duplica.class, Power.class} 自组件功能,可以开启Keep 组件下的子组件能力,支持不同的系统能力.



```java

@Component
public class LoginServiceImpl implements LoginService {
    private AtomicInteger counter = new AtomicInteger(0);
    @Override
    @Power(preHandler = {"age","user"},collector = "#{@loginServiceImpl.country}")
    public boolean login(String name, Integer age) {
        if(name.contains("yang")){
            return true;
        }
        return false;
    }

    public Object country(){
        Object us = PropertyValueWrapper.wrapValue(new String[]{"contry"}, "US");
        return us;
    }
}

```
通过在方法上添加@Power 注解 实现方法调用API 拦截的处理器配置和处理能力。如上 @Power(preHandler = {"age","user"},collector = "#{@loginServiceImpl.country}") 中定义了login 方法调用的 预处理器定义, 聚合器定义。预处理定义了2个拦截校验逻辑bean, age 和user ，其定义顺序为处理器的执行顺序。 collector 定义了方法调用前的依赖数据收集。


#版本发布
## V0.1.0Beta (初始化版本)
### 时间[2019-08-11 ~2019-08-22]
* EnbleMCache,EnableKeep 注解功能实现.
* 分级缓存Cache 基础注解GetCache，PutCache 实现，注解的解析器。
* 基于注解的缓存拦截器
* API 网关 功能实现, 主要包括:
 方法级别请求前置和后置处理。
 


## V1.0 Release (发布版本)


# 作者简介
一个机械专业程序源，喜欢捣鼓事情，但是又比较低调。

技术交流加微信：
<img src="https://github.com/JacceYang/PersonProfile/blob/master/WechatIMG147.jpeg" withd="240px">

邮箱：chaoyang_sjtu@126.com
