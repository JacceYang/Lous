

# 1.Lous 是什么

## 1.1 名称及简介
Lous(劳斯) (谐音lost 迷失) 是一套专注应用级别 API 网关处理架构,设计初衷是抽离API级别方法调用时非业务逻辑代码对的业务代码的渗透。
让代码开发更加专注(more focused)，业务代码更加整洁(more neat)。Lous 关注方法调用过程中常见的结果缓存(@EnableSmartCache),恶意频繁攻击和频繁调用(@Duplix),
方法调用拦截和预处理和后处理功能(@Power)。框架借鉴了spring-boot中约定大于配置思想,开箱即用,简化开发流程。废弃了经典的xml 配置化引入方式,采用基于
注解的方式引入所需的功能(more flexible)。

## 1.2 主要内容

Lous 框架紧密围绕API层接口调用,依据不同场景提供Power,Duplix,SmartCache三大基础组件,每一个组件针对不同的业务场景,实现了不同的能力。用户可以方便的在
应用启动类上使用Enablexxx 注解启动特定组件。

### 1.2.1 Power: API 网关拦截处理器(@EnableKeep--@Power)
Power 的设计借鉴了AOP思想,但是不同于常见的AOP框架,其接入更加方便,快捷。系统将方法调用的过程分割为调用前和调用后处理两部分,其中每一个handler 的调用用户可以依据自己的业务特点定制抽象成一个通用的handler,比如鉴权逻辑,参数校验逻辑,通用日志同步逻辑等.用户定义好不同的handler 之后,通过在目标方法上注解@Power, 并依据业务特点配置不同的handler. 在配置handler时，用户可以依据调用目标方法的时间不同,分别在pre-handler 和post-handler 中配置对应的处理器即可。系统兼具上下文信息收集能力,在handler中需要额外用到的调用上下文环境数据时,可以通过在@Power上配置collector 来收集所需数据。在handler处理和方法调用的每个阶段,用户只需要通过获取PowerInvokeContext对象,通过PowerInvokeContext#getProperty和PowerInvokeContext#getMethodParameter方便的获取到属性值和方法值。整个系统的调用流程如下所示:

<img src="https://github.com/JacceYang/PersonProfile/blob/master/Power%20model.png" width="90%">

> NOTE:支持应用级别 API网关，实现可配置化的拦截器，校验器和各种错误处理能力。系统提供了通用化的框架能力,对于一些通用且常见的校验逻辑,可以在系统框架上扩展,抽象出单个的handler。在不同的应用下通过配置 prehandler={"common_handler1","common_handler2"} 即可。


说明:Power作为Keep 的子组件,其功能的开启需要@EnableKeep.由于系统默认不开启Power 组件。当你需要用到Power 组件时。请在application.properties 或者applicaiton.yml 中通过lous.power.enable=true 开启。


### 1.2.2 @Duplix:阻断器(@EnableKeep--@Duplix) 

Duplix 作为方法调用阻断器,其功能表面类似于限流降级器。但是其设计之初并不是为了对服务限流降级,而是对特定的请求实体做限流和阻断。典型的应用场景是相同请求的频繁调用,幂等调用和请求阻断。应用中通常存在这样的API方法,其方法调用对于同一个用户来说,不忍许频繁提交相同请求，比如B端商户提交数据走审核流程。通常审核并不希望频繁的提交相同的数据。此时通过使用Duplix 阻断器，并配置请求标示Key和内容 标示Content 的解析规则,重复判断规则（时间区间次数和下次提交的时间区间间隔两个维度）等参数。系统对于来自相同的key 的相同内容请求,通过判定规则决定是否阻断本次请求,从而达到请求阻断的作用，幂等和阻断在此不做详细解释。整个调用的处理流程如下图：

<img src="https://github.com/JacceYang/PersonProfile/blob/master/WX20190824-111939%402x.png" width="70%" vertical-align="middle">
使用场景:针对Web层或者Service层对大量恶意或者重复请求做重复性校验。通过定义重复判定规则,对每一次请求做身份判断。通过定义时间窗口范围,将时间窗口内的一此或者自定义次数后的请求判定为重复请求，从而让系统执行拒绝请求逻辑。

说明：Duplix 由于需要获取单次请求的content 和上次请求做相同校验。所以系统底层需要依赖数据存储能力。对于分布式缓存架构,用户在使用本架构时需要实现数据存储相关的MemCache两个接口。系统自身提供了本地缓存的能力,同时针对于实际应用时服务器缓存使用量的问题，提供了配置单机最大缓存使用量,对于超过缓存部分的请求，系统会利用后台的守护线程 _**自动淘汰掉生命周期最短**_ 的缓存，直到满足最大缓存设置。由于分布式环境每次请求对应的服务器可能不同，因此需要本地缓存在多台服务器之间同步能力。目前系统二期会支持(如上图中的服务主从复制)。

特点：
>* 重复请求判定规则的SpEl表达定义和用户自定义模式
>* 时间窗口的自定义化
>* 拒绝规则的自定义化和默认支持

### 1.2.3 SmartCaching: Smart缓存(@EnableSmartCaching)
Smart缓存，不仅仅是一个基于注解的缓存调用组件。其主要的特征是分级和缓存跃迁,其主要的特点是自动分级和智能跃迁能力。
* 分级：缓存分级是由于不同的缓存介质对请求的响应速度不同。典型的缓存介质速率如下：RAM >Redis >MySql .分级缓存只针对缓存部分，上列中的RAM 和Redis 进行分级。依据响应速度从快到慢依次分为1级,2级...n 级,每以级对应一个存储介质,具体分的级数依据存储介质种类确定。各级存储介质由于缓存操作的API 有所差别，所以对应的需要实现 Cache 和CacheManager 接口。系统启动后依据此两个接口完成缓存的各种操作和分级及跃迁。系统默认实现了本地缓存。同时用户可以依据自己的使用习惯配置常见的Caffine,Guava Cach 等框架。

* 跃迁：缓存跃迁是主要是缓存从低级缓存向高级缓存迁移过程。迁移规则目前主要基于缓存的热度，缓存容量,用户自定义规则作为迁移能量值,超过上级的能量值，本级缓存完成一次能量迁移(类似于波尔能量跃迁理论)。


> 主要实现缓存的分级存储和智能跃迁.具体使用说明见Smart Caching [使用文档]()

# 2.环境说明

|environment  | Version | Supported          |
|------------ | ------- | ------------------ |
|JDK Version  | >8.x   | :white_check_mark: |
|Spring Framework| >4.3.17   | :white_check_mark: |
|Spring Boot | >2.0.7   | :white_check_mark: |

项目中使用了极少量 lambda 表达式,和内存计算的API都是基于JDK8.0 的, 所以建议使用JDK >8.0 的开发环境,低版本需求请单🔨。
考虑xml 配置样式 处在淘汰的边缘,所以项目暂时没有支持xml标签解析,配置化的能力,强烈需求会支持，看后面看情况。

# 3.起步

## 3.1 使用@Power注解实现API层方法调用的 拦截和处理

**场景说明**
> 在实际的开发过程中(无论是Web 还是service 层),经常需要校验方法调用者的身份和特征。依据特征数据,系统判断方法调用者是否有权限做一些修改或者查阅系统信息的操作.常见的实现方法如下:

### 3.1.1 方法内部写预处理逻辑
```java
public class LoginServiceImpl implements LoginService {
    
    @Override
    public boolean login(String name, Integer age) {
        if(name.startsWith("yang")&& age>18){
            // 成年以 yang 开头的名字用户有权限登录 并做一些修改和浏览操作
            Object result=   writeData();
            return true;
        }
        return false;
    }

    private Object writeData(){
            ///// 处理业务逻辑
    }

    public Object country(){
        Object us = PropertyValueWrapper.wrapValue(new String[]{"contry"}, "CN");
        return us;
    }
}

```
此种方式开发中最常见,将业务逻辑代码和校验姓名以及年龄的代码逻辑直接写在方法体内,对于少量的校验方法,此种形式也无没有太大弊端。但是当业务中大量的方法调用需要校验姓名和年龄时,此种方式就会在在代码中散落大量的相同逻辑代码，维护极其困难。Spring 从1.x 时就支持了AOP能力,AOP的出现无疑为开发者解决此类问题提供了一种不错的思路。

### 3.1.2 AOP拦截方式
```java
@AspectJ
public class LoginAdviceAspectJ {

    @Pointcut("execution(* com.iyetoo.springdemo.aopdemo.LoginInterfaceImpl.*.login(..))")
		@Pointcut("com.iyetoo.springdemo.aop.anotation.UserAop")
    public void loginMethod(){} // 只是一个point cut 标示而已

    @Before("loginMethod()")
    public void beforeLogin(JoinPoint point){
        Signature signature = point.getSignature();
        if(name.startsWith("yang")&& age>18){
            //xxxxx
        }
    }

    @AfterReturning("execution(* com.iyetoo.springdemo.aopdemo.LoginInterfaceImpl.*.login(..))")
    public void beforeThrowError(JoinPoint point){
        
    }
}
```
此种方式就是采用注解形式的AOP解决方法拦截和注解的方式。此种方式能够解决大量方法调用时的统一拦截和处理。但是存在无法依据拦截器的执行结果判断是否执行下面的方法调用。同时多个过滤逻辑重叠校验时无法利用上下文调用执行结果的上下文数据。使得前一个拦截器执行的结果无法很好的在下一个拦截器中利用。最后此种方式需要代码中配置切面和各种切点方法。当需要拦截的方法分布在大量分散的类中时,切点逻辑较为复杂。

### 3.1.3 API 网关方法调用高级版

```java

@SpringBootApplication
@EnableKeep(annotation = { Power.class}) 
public class LousApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LousApplication.class, args);
        LoginService loginServiceImpl = (LoginService) run.getBean("loginServiceImpl");
        loginServiceImpl.login("ang", 15);
        System.out.println("-------end---------");

    }
}

```
通过以上的@EnableKeep注解,应用就开启了@Keep 组件能力.通过引入{Power.class} 自组件功能,可以开启Keep 组件下的子组件能力,支持不同的系统能力.


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
通过在方法上添加@Power 注解 实现方法调用API 拦截的处理器配置和处理能力。如上 @Power(preHandler = {"age","user"},collector = "#{@loginServiceImpl.country}") 中定义了login 方法调用的 预处理器定义, 收集器定义。预处理定义了2个拦截校验逻辑bean, age 和user ，其定义顺序为处理器的执行顺序。 collector 定义了方法调用前的依赖数据收集。

**特点**
* 支持前后处理拦截器,错误处理拦截器,保证了对目标方法调用的全方位的控制和调用环境控制。
* 拦截器的配置化和组合化。所有的拦截器都可以灵活的在@Power 注解中组合定义。
* 拦截和影响方法调用处理结果。通过在Pre-Handler中proceed()传递逻辑,决定是否完成调用链上后续的方法调用。
* 通过定义Post-Handler 中的 proceed()传递逻辑 或者是filter() 逻辑，控制或者改写放回结果。
* 通过定义Collector 函数为 Pre|Post Handler 方法调用构造上下文环境。保证调用链路的先决条件。
* 无需配置AOP各种类或者配置文件,简化开发流程。
* 支持Spring bean 形式的处理器定义。方法级别的Collector 定义.实现简单。

***
## 3.2 使用@Duplix 注解API 接口方法阻断

**场景说明**
> 在应用接口方法调用过程中,有时存在一类接口。它们对相同的调用请求,存在不同形式的限制，最简单的是频繁重复请求,幂等请求等。针对此类场景,Duplix通过配置基于时间和调用次数两个维度的组合,实现对调用者的调用的阻断。

**例子:频繁重复提交**

配置组件启动注解
通过在启动类上应用@EnableKeep(annotation = {Duplix.class})  注解接口启动Keep 组件下的Duplix功能组件.
```java
@SpringBootApplication
@EnableKeep(annotation = {Duplix.class}) 
public class LousApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LousApplication.class, args);

    }
}
```

接口定义阻断逻辑
频繁提交主要针对相同用户的相同内容提交起到阻断。在利用Duplix 配置阻断逻辑时需要定义用户标示(key)和
内容标示(目前主要以函数paramters 参数内容作为content)。key 的定义可以采用SpEl 表达式。

```java

@RestController
public class FuncController {
    @GetMapping("/demo/fun")
    @Duplix(scene = Scene.METHOD,key = "#name+#age",expire = 4,msg = "msg:提交太频繁!")
    public ResponVo getName(String name,Integer age){
        return ResponVo.success(name+age);
    }
}
```
如上例,以name+age 组合作为调用者身份的标示。其内容作为重复的判断依据。当在第一次调用完成后的4 秒的时间内（expire=4）有相同身份者的相同内容提交时， 系统将阻断本次请求,并返回调用者 "提交太频繁"信息。

***
## 3.3 使用@SmartCaching 基于注解的智能缓存
**场景说明**
> 缓存在大型应用开发中几乎是一个必不可少的组件。用户通过实现SmartCaching 的Cache 和CacheManager 接口，即可将数据存储集成到 SmartCaching 组件。用户通PutCache 和GetCache注解依据接口的实际调用情况控制缓存的访问和更新。

* 通过在启动类上应用@EnableSmartCaching注解启动Smart Caching 组件功能
```java
@SpringBootApplication
@EnableKeep(annotation = {Duplix.class}) 
public class LousApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LousApplication.class, args);

    }
}

```

* 针对不同的存储介质，分别实现实现Cache 和CacheManager和接口。对于公司中常用的存储介质，可以封装好相应的Cache 和CacheManager,在应用启动的时通过判断Cache 和CacheManager 类型的实现Bean 将缓存介质依据GetCache和PostCache 中配置情况自由组合到框架缓存中。


* 针对具体应用场景配置GetCache 和PutCache
```java

public class LoginServiceImpl implements LoginService {
      @GetCache(key = "#name+#age", cacheName = "login-record",cacheMode = CachingMode.LOCAL)
    @Override
    public boolean login(String name, Integer age) {
        if (name.contains("yang")) {
            return true;
        }
        return false;
    }
}

```

# 4.0版本发布
## 4.1 V0.1.0Beta (测试版)
### 时间[2019-08-11 ~2019-08-22]
* EnableKeep,EnableSmartCaching注解功能实现.
* 分级缓存Cache 基础注解GetCache，PutCache 实现，注解的解析器。
* 基于注解的缓存拦截器
* API 网关 功能实现, 主要包括:
  * 方法级别请求前置和后置处理 
  * 启动服务阶段校验 @Power 注解的正确性
  * 新增Collector 上下文信息聚合器。
 
## 4.2 V1.0 Release (发布版)
### 时间[2019-08-31 ~2019-09-07]

## 4.3 V2.0.0 beta 
### 时间[2019-xx-xx ~2019-xx-xx]
* 【Cache】本地缓存快照和快照加载能力,序列化和反序列化本地缓存在程序推出和启动时,本项功能默认不开启。
* 【Cache】缓存Promotion 功能实现,多级缓存时,内存基于默认规则和用户定义规则的缓存数据迁移。保证最热缓存在最高速存储介质。
* 【Cache】 本地缓存使用空间控制.
* 【Duplix】 分布式请求本地缓存数据同步能力。保证相同请求到不同的物理机器上依然能够被拦截.
* 【Duplix】 支持高并发下幂等逻辑控制。
* 【Power】  handler 依赖解析和并行调用，并行调用下fastFail 逻辑设计（并行情况下有一个调用失败，则终止本此其他handler 调用并快速返回）
* 【power】同组Handler 的简单逻辑运算能力。


# 5.0 作者简介
*** 
一个机械专业软件工程师，喜欢捣鼓事情，为人低调，2.5年C/C++,近2年Java,1年前端开发。互联网大厂打酱油.

如果有兴趣可以下载源码阅读哦。源码风格参照了主流框架大师Juergen Hoeller等作者的代码风格,以及rt.jar 开发者们精简的代码风格。虽然和大师间距离依然很大，但是依然无法阻止我向他们看齐的心。

技术交流加微信：
<img src="https://github.com/JacceYang/PersonProfile/blob/master/WechatIMG147.jpeg" width="240px">

邮箱：chaoyang_sjtu@126.com


# 6.0 附录
## 满江红 写怀（岳飞)
怒发冲冠，凭阑处、潇潇雨歇。抬望眼，仰天长啸，壮怀激烈。三十功名尘与土，八千里路云和月。莫等闲、白了少年头，空悲切。
靖康耻，犹未雪。臣子恨，何时灭。驾长车，踏破贺兰山缺。壮志饥餐胡虏肉，笑谈渴饮匈奴血。待从头、收拾旧山河，朝天阙。


											2019年8月24
