
# 1.Lous 是什么

**1.1 名称及简介**:
*** 
Lous(劳斯) 谐音(lost 又名迷失) 是一套专注应用级别 API 网关处理架构,其主要目的是抽离API 层方法调用时非业务逻辑的渗透。
让代码开发更加专注(more focused)，业务代码更加整洁(more clean)。Lous 关注方法调用过程中常见的结果缓存(@EnableCache),恶意频繁攻击(@Duplica),
方法调用拦截和预处理和后处理功能。框架借鉴了spring-boot中约定大于配置思路,开箱即用,简化开发流程。框架废弃了经典的xml 配置化引入方式,采用基于
注解的方式引入所需的功能(more flexible)。

**1.2 主要内容**:
*** 
主要包括API层网关中常见的操作组件
* API 网关拦截处理器(@EnableKeep--@Power)

<img src="https://github.com/JacceYang/PersonProfile/blob/master/DX-20190823%402x.png" withd="240px">

> 支持应用级别 API网关，实现可配置化的拦截器，校验器和各种错误处理能力。 


* 重复频繁调用阻止器(@EnableKeep--@Duplica)
使用场景:针对Web层或者Service层对大量恶意或者重复请求做重复性校验。通过定义重复判定规则,对每一次请求做身份判断。通过定义时间窗口范围,将时间窗口内的一此或者自定义次数后的请求判定为重复请求，从而让系统执行拒绝请求逻辑。

特点：
>* 重复请求判定规则的SpEl表达定义和用户自定义模式
>* 时间窗口的自定义化
>* 拒绝规则的自定义化和默认支持

原理：

* 分级缓存(@EnableMCache)
> 主要实现缓存的分级存储

# 2.环境说明
*** 
|environment  | Version | Supported          |
|------------ | ------- | ------------------ |
|JDK Version  | >8.x   | :white_check_mark: |
|Spring Framework| >4.3.17   | :white_check_mark: |
|Spring Boot | >2.1.7   | :white_check_mark: |

项目中使用了极少量 lambda 表达式, 所以建议使用JDK >8.0 的开发环境,低版本需求请单🔨。
项目暂时没有精力支持xml标签解析,所以无法支持xml 配置化的能力,后面看情况支持。

# 3.起步
*** 
## 使用@Power注解实现API层方法调用的 拦截和处理
**场景说明**
> 在实际的开发过程中(无论是Web 还是service 层),经常需要校验方法调用者的身份和特征。依据特征数据,系统判断方法调用者是否有权限做一些修改或者查阅系统信息的操作.常见的实现方法如下:

*3.1 方法内部写预处理逻辑*
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
此种方式开发中最常见,将业务逻辑代码和校验姓名以及年龄的代码逻辑直接写在方法体内,对于少量的校验方法,此种形式也无没有太大弊端。但是当业务中大量的方法调用需要校验姓名和年龄时,此种方式就会在在代码中散落大量的相同逻辑代码，维护及其困难。为了解决此类问题,Spring 从1.x 时代就支持了AOP能力.

*3.2 AOP拦截方式*
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

*API 网关方法调用高级版*

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

**特点**
* 支持前后处理拦截器,错误处理拦截器,保证了对目标方法调用的全方位的控制和调用环境控制
* 拦截器的配置化和组合化。所有的拦截器都可以灵活的在@Power 注解中组合定义。
* 拦截和影响方法调用处理结果。通过在Pre-Handler中proceed()传递逻辑,决定是否完成调用链上后续的方法调用。
* 通过定义Post-Handler 中的 proceed()传递逻辑 或者是filter() 逻辑，控制或者改写放回结果。
* 通过定义Collector 函数为 Pre|Post Handler 方法调用构造上下文环境。保证调用链路的先决条件。
* 无需配置AOP各种类或者配置文件,简化开发流程。
* 支持Spring bean 形式的处理器定义。方法级别的Collector 定义.实现简单。


# 4.0版本发布
## 4.1 V0.1.0Beta (测试版)
### 时间[2019-08-11 ~2019-08-22]
* EnableKeep,EnbleMCache注解功能实现.
* 分级缓存Cache 基础注解GetCache，PutCache 实现，注解的解析器。
* 基于注解的缓存拦截器
* API 网关 功能实现, 主要包括:
  * 方法级别请求前置和后置处理 
  * 启动服务阶段校验 @Power 注解的正确性
  * 新增Collector 上下文信息聚合器。
 
## 4.2 V1.0 Release (发布版)
### 时间[2019-08-31 ~2019-09-07]



# 5.0 作者简介
*** 
一个机械专业程序源，喜欢捣鼓事情，为人低调低调，3年C/C++,2年Java。互联网大厂打酱油.

技术交流加微信：
<img src="https://github.com/JacceYang/PersonProfile/blob/master/WechatIMG147.jpeg" width="240px">

邮箱：chaoyang_sjtu@126.com

# 6.0 附录
## 满江(岳飞)
怒发冲冠，凭阑处、潇潇雨歇。抬望眼、仰天长啸，壮同激烈。三十功名尘与土，八千里路云和月。莫等闲、白了少年头，空悲切。 
靖康耻，犹未雪；臣子憾，何时灭。驾长车踏破、贺兰山缺。壮志饥餐胡虏肉笑谈渴饮匈奴血。待从头、收拾旧山河。朝天阙。
