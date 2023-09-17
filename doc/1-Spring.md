## 创建项目

通过Spring创建向导来创建项目，创建参数：

- Server URL：https://start.spring.io 或 https://start.springboot.io
- Name：`jsd2208-csmall-server`
- Group：`cn.tedu`
- Artifact：`csmall-server`（手动调整）
- Package Name：`cn.tedu.csmall.server`（手动调整）
- Java版本：`8`（`1.8`）

在后续的界面中，可以随意选择某个版本，并且，不勾选任何依赖项。

当项目创建成功后，打开`pom.xml`，在默认的第8行中，将版本号改为`2.5.9`。


## Spring框架作用
主要解决了创建对象和管理对象的问题

在使用Spring框架后，当需要某个对象时，直接通过Spring获取此对象即可。

Spring可以保证类的对象的唯一性，使得各组件出现依赖关系时，不会创建多个相同的对象。

由于Spring会创建很多个对象并管理起来，开发者需要时直接获取即可，所以，Spring也通常被称之为“Spring容器”。


## 通过Spring创建并管理对象--组件扫描

Spring提供了组件扫描的机制，它可以扫描指定的包，并查找此包及其子孙包下是否存在组件类，判定组件类的标准是
类上是否存在组件注解，基础的组件注解有：

- `@Component` - `通用`
- `@Controller` - `控制器`
- `@Service` - `业务层`
- `@Repository` - `持久层`

在Spring框架的解释中，以上4个注解是完全等效的，只是语义不同。

所以，当某个类需要被Spring框架创建对象时，需要：

1. 确保这个类在组件扫描的范围之内(@ComponentScan("指定包"))
2. 确保这个类添加了组件注解

提示：在Spring Boot项目中，默认就已经配置好了组件扫描，扫描的根包就是创建项目时已存在的包。

## 通过Spring创建并管理对象--@Bean方法

在Spring框架的使用中，如果某个类添加了`@Configuration`注解，则此类将是“配置类”。

注意：配置类也必须在组件扫描的范围内。

在配置类中，可以自行添加一些方法，在方法上添加`@Bean`注解，则此方法会被Spring自动调用，且方法返回的对象也会被Spring管理。

在同一个配置类中，允许存在多个`@Bean`方法。

关于方法的声明：

- 访问权限：应该是`public`
- 返回值类型：你希望Spring管理的对象的类型
- 方法体：应该正确的返回与声明匹配的对象
- 方法名称：自定义

所以，当某个类需要被Spring框架管理对象时，需要：

- 在配置类中添加`@Bean`方法，使用此方法返回需要被管理的对象



## 如何选择创建并管理对象的方式

仅自定义的类型可以使用组件扫描的方式，当然，自定义的类型也可以使用`@Bean`方法的做法，但是不推荐。

非自定义的类型不可以使用组件扫描的方式（因为你没有办法在这些类型上添加组件注解），只能使用`@Bean`方法的做法。



## 练习

在`controller`包中创建`BrandController`、`PictureController`、`AlbumController`，也都将由Spring来创建对象，并测试是否可以获取到对象



## 自动装配

当某个属性需要值时，或被Spring调用的方法需要参数值时，Spring框架会自动从容器中查找符合的对象，自动为属性或方法的参数赋值。

在属性上，添加`@Autowired`注解，即可使得Spring尝试自动装配。



## 练习

假设在项目中存在：

- `IBrandRepository`
- `BrandRepositoryImpl`
- `IBrandService`
- `BrandServiceImpl`
- `BrandController`

其依赖关系是：在`Service`组件中需要使用到`Repository`，在`Controller`组件中需要使用到`Service`。

要实现以上目标，需要：

- 以上各类和接口都应该在组件扫描的根包或其子孙包下

- `IBrandRepository`、`IBrandService`不需要添加注解，而`BrandRepositoryImpl`应该添加`@Repository`注解，
  `BrandServiceImpl`添加`@Service`注解，`BrandController`添加`@Controller`注解

- 在`BrandServiceImpl`中声明`BrandRepositoryImpl`类型的变量（暂时声明为`public`）

    - 【测试】在没有添加`@Autowired`之前，此变量的值为`null`

        - 可以在测试类中自动装配`BrandServiceImpl`，并在测试方法中输出`BrandRepositoryImpl`变量的值，例如：

        - ```
      @Autowired
      BrandServiceImpl brandService;
      
      @Test
      public void testBrandAutowired() {
          System.out.println(brandService.brandRepositoryImpl);
      }
      ```

    - 【测试】当已经添加`@Autowired`之后，此变量的值为`BrandRepositoryImpl`类型的对象

- 使得`BrandRepositoryImpl`实现`IBrandRepository`接口

    - 【测试】将`BrandServiceImpl`中，原本声明为`BrandRepositoryImpl`类型的变量，改为`IBrandRepository`类型，
      再次观察，此变量的值不变

- 在`BrandController`中声明`BrandServiceImpl`类型的变量

    - 【测试】在没有添加`@Autowired`之前，此变量的值为`null`
    - 【测试】当已经添加`@Autowired`之后，此变量的值为`BrandServiceImpl`类型的对象

- 使得`BrandServiceImpl`实现`IBrandService`接口

    - 【测试】将`BrandController`中，原本声明为`BrandServiceImpl`类型的变量，改为`IBrandService`类型，
      再次观察，此变量的值不变



## 练习

假设在项目中存在：

- `IAlbumRepository`
- `AlbumRepositoryImpl`
- `IAlbumService`
- `AlbumServiceImpl`
- `AlbumController`

其依赖关系是：在`Service`组件中需要使用到`Repository`，在`Controller`组件中需要使用到`Service`。

案例目标：

- Spring能自动创建`AlbumRepositoryImpl`类的对象

    - 在`repo.impl`包下创建此类，并且，在此类上添加`@Repository`
    - 检验方式：添加构造方法并输出；在测试类中，自动装配此类型的对象，并输出

- Spring能自动创建`AlbumServiceImpl`类的对象

    - 同上，此类应该在`service.impl`包下（`impl`包不存在，需创建）

- 在`AlbumServiceImpl`类有`AlbumRepositoryImpl`的属性，并自动装配

    - 在`AlbumServiceImpl`内部，声明`AlbumRepositoryImpl`类型的变量，并添加`@Autowired`注解
    - 检查方式：在测试类中，输出`AlbumServiceImpl`对象的`AlbumRepositoryImpl`属性，应该是有值的

- 创建`IAlbumRepository`接口，使得`AlbumRepositoryImpl`实现`IAlbumRepository`接口，并且，在`AlbumServiceImpl`应该是基于接口编程的，所以，原本声明为`AlbumRepositoryImpl`的变量，改为`IAlbumRepository`接口类型的声明

- 实现在`Controller`组件中需要使用到`Service`，则需要先创建`AlbumController`类，并添加`@Controller`注解，然后，在`AlbumController`内部，声明`public AlbumServiceImpl albumService;`，并在此属性上添加`@Autowired`，则此属性将被Spring自动装配值，如果要调整为基于接口的编程，还需要创建`IAlbumService`接口，并使得`AlbumServiceImpl`实现此接口，最后，在`AlbumController`中，原本的声明`public AlbumServiceImpl albumService;`改为`public IAlbumService albumService;`即可

    - 检查方式：在测试类中自动装配`AlbumController`，在测试方法中输出`AlbumController`变量中的`albumService`属性的值，应该会输出有效值

    - ```
    @Autowired
    AlbumController albumController;
    
    @Test
    public void testAlbumControllerAutowired() {
        System.out.println(albumController.albumService);
    }
    ```


### Spring IoC与DI

IoC：Inversion of Control，即：控制反转，表现为：将对象的控制权（创建权力、管理权力）交给框架。

DI：Dependency Injection，即：依赖注入，当前类中的代码需要通过另一个类的执行来实现，则当前类依赖于另一个类，
例如Controller依赖Service，Service依赖Mapper，Spring可以通过自动装配等机制为依赖项赋值，由于在编写的源代码中并没有使用到赋值符号（`=`），
所以，这个行为叫作“注入”。

Spring框架通过DI完善的实现了IoC，所以，DI是一种实现手段，IoC是需要实现的目标。



### Spring Bean的作用域

Spring管理的对象默认`@Scope("singleton")`都是**单例**的，可以使用`@Scope("prototype")`使之“**非单例**”。

**单例**: 单个实例(对象)

当Spring管理的对象是单例状态时，默认是**预加载**的，也可以使用`@Lazy`注解将其配置为**懒加载**的。



### Spring Bean的生命周期（了解）
创建-初始化-调用-销毁

在自定义的、被Spring管理的类中，可以自定义方法，在方法上添加`@PostConstruct`注解后，此方法将变为Spring Bean生命周期中的**初始化方法**，
则会在创建此类的对象之后，被Spring自动调用，还可以自定义方法，在方法上添加`@PreDestroy`注解后，此方法将变为Spring Bean生命周期中的**销毁方法**，
则会在销毁对象的前一刻，被Spring自动调用。

```
@Component
public class FileLogger{

    public PrintWriter out;

    @PostConstruct 
    public void init() throws IOException{
        out = new PringWriter("demo.txt");
        System.out.println("打开demo文件");
    }

    @PreDestroy
    public void close() throws IOException{
        out.close();
        System.out.println("关闭demo文件");
    }  
}
测试：
@Test
public void test(){
    FileLogger fileLogger = ac.getBean(FileLogger.class);
    fileLogger.out.println("Hi");
    fileLogger.out.println("Test");
}
```


### Spring AOP

AOP：面向切面的编程
    就是在不改变程序现有代码的前提下，可以设置某个方法运行之前或者之后新增额外代码的操作

注意：AOP并不是Spring特有的技术，只是Spring对AOP提供了很好的支持

在项目中，处理的处理流程大致是：

```
注册 --（请求）--> Controller --> Service.reg() --> Mapper

登录 --（请求）--> Controller --> Service.login() --> Mapper

改密 --（请求）--> Controller --> Service.changePassword() --> Mapper
```

名词解释：
切面：是一个可以加入额外代码运行的特定的位置
织入：选定一个切面，利用动态代理技术，为原有的方法的持有者生成动态对象，然后将他和切面关联。
通知：通知要织入代码的运行时机
    前置通知、后置通知、环绕通知、异常通知


假设存在某个需求：需要统计每个业务方法的执行耗时。

要想实现此目标，可以自定义一个切面类：

```java
package cn.tedu.csmall.server.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 统计各Service方法的执行耗时的切面类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Component
@Aspect
public class TimerAspect {

    // 切面的通知（Advice），可以是：Around（环绕）、Before、After、AfterThrowing、AfterReturning
    // 其它通知的执行例如：
    // Advice >>> Before
    // try {
    //      pjp.proceed();
    //      Advice >>> AfterReturning
    // } catch (Throwable e) {
    //      Advice >>> AfterThrowing
    // } finally {
    //      Advice >>> After
    // }
    @Around("execution(* cn.tedu.csmall.server.service.impl.*.*(..))")
    public Object timer(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("执行了切面方法……");

        long start = System.currentTimeMillis();

        // 调用此方法，将执行业务方法（因为通知中配置的表达式匹配的是业务方法）
        // 注意：调用的proceed()方法是抛出异常的，【必须】在当前切面方法上声明抛出（不能try...catch）
        // 调用proceed()方法的返回值就是业务方法的返回值，【必须】作为当前切面方法的返回值
        Object result = pjp.proceed();

        long end = System.currentTimeMillis();
        log.debug("当前切面匹配到的组件类：{}", pjp.getTarget());
        log.debug("当前切面匹配到的方法：{}", pjp.getSignature());
        log.debug("当前切面匹配到的方法的参数列表：{}", pjp.getArgs());
        log.debug("当前业务方法的执行耗时：{}ms", (end - start));

        // 【必须】返回调用proceed()方法返回的结果
        return result;
    }

}
```

AOP主要解决的问题：性能监测、日志跟踪、事务管理等等。

事实上，在Java开发领域中，有许多主流的框架都使用到了AOP，所以，一般不需要手动编写AOP。





# 上面章节中我们使用了下面的表达式来定义切面

```
@Pointcut("execution(public * cn.tedu.knows.faq.controller.TagController.tags(..))")
```

下面来介绍这个切面定义表达式的详细语法规则

语法模板为:

```
execution(
modifier-pattern?
ret-type-pattern
declaring-type-pattern?
name-pattern(param-pattern)
throws-pattern?)
```

带?的是可选属性,不带?是必须写的

* modifier-pattern:访问修饰符(可选)
* ret-type-pattern:返回值类型(必写)
* declaring-type-pattern:全路径类名(可选)
* name-pattern:方法名(必写)
* param-pattern:参数列表(必写)
* throws-pattern:抛出的异常类型(可选)

切面表达式含义：
execution(* *(..)):
    匹配所有(Spring框架能扫描到的范围内)方法

execution(public * cn.tedu.jsd2208.csmall.server.controller.*.*(..)):
    匹配 cn.tedu.jsd2208.csmall.server.controller 所有类的被public修饰的方法

execution(* cn.tedu.jsd2208.csmall.server.controller.BrandController.*(..)):
    匹配 cn.tedu.jsd2208.csmall.server.controller.BrandController 所有的方法




随笔：

面对对象设计原则: 开闭原则 - 对扩展开放、对修改关闭

面对接口编程


### 关于@Autowired注解与@Resource注解的区别

在使用Spring框架时，在类的属性上使用`@Autowired`或`@Resource`都可以实现自动装配！

关于这2个注解，`@Autowired`是Spring框架定义的注解，`@Resource`注解是`javax`包中注解！

关于`@Autowired`的装配机制是：

1. 先根据类型在Spring容器中查找匹配的对象，看看有多少个
2. 如果匹配类型的对象的数量为0，即没有，取决于`@Autowired`的`required`属性
    1. 如果`required=true`，则装配失败，在加载Spring时就会报错
    2. 如果`required=false`，则放弃自动装配，需要自动装配的属性的值为`null`，加载Spring时并不会报错，但在后续的使用中，可能会出现NPE
3. 如果匹配类型的对象有且仅有1个，则直接装配
4. 如果匹配类型的对象的数量超过1个（2个或更多个），将尝试根据名称来装配
    1. 如果存在名称匹配的对象，则成功装配
    2. 如果不存在名称匹配的对象，则装配失败，在加载Spring时就会报错

关于`@Resource`的装配机制是先尝试根据名称来装配，如果成功，则装配完成，如果失败，会尝试根据类型来装配，如果仍失败，则装配失败！

从最终的体验来看，这2个注解都可以实现相同的效果，使用`@Autowired`能成功装配的，使用`@Resource`也可以，
使用`@Autowired`无法装配的，使用`@Resource`也无法装配。






