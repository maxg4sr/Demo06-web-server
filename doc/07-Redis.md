# Redis

## 关于Redis

Redis是一款基于内存存储的、使用类似于Map结构的NoSQL数据库。

**内存存储**：Redis的数据都是存储在内存（RAM）中的，所以，读写效率非常高，由于是存储在内存中的，所以，断电后数据会全部丢失，
但是，Redis自身会处理持久化存储（自动的将内存中的数据也存储到硬盘上），所以，简单使用时，并不用担心重启、关机后Redis丢失数据的问题

**类似Map结构**：在Redis中存入的数据都要求自定义一个名称，类似于Map中的Key，具体存入的值就是对应的Value，甚至，当反复使用同一个Key存入数据时，
事实上是后续的存入覆盖前序的存入

**NoSQL数据库**：只要是能够存储数据、具有存取功能的，都可以称之为数据库，Redis与传统的关系型数据库不同，它并不会使用到SQL语句来操作数据，
只需要简单的读写即可，所以，一般称之为NoSQL数据库（其实，还有其它NoSQL数据库，与Redis完全不同，例如Elasticsearch等）


通常，在应用程序中，将使用Redis存储一些数据，并且，在程序的执行过程中，将优先从Redis中查询数据，以提高查询效率，
同时，也能减轻MySQL等数据库服务器的压力，甚至起到保护MySQL等数据库服务器的作用。

## Redis 安装
 软件地址：https://robinliu.3322.org:8888/download/Redis-x64-3.2.100.msi
 可视化：https://gitee.com/qishibo/AnotherRedisDesktopManager/attach_files/958501/download/Another-Redis-Desktop-Manager.1.5.2.exe


## Redis的简单操作

**登录Redis客户端**：在**终端**输入`redis-cli`并执行即可

**停止Redis服务**：在登录Redis客户端后，执行`shutdown`

**启动Redis服务**：在**终端**执行`redis-server`，在Linux等操作系统，可以在此命令后添加` &`（空格加`&`符号）避免窗口被独占

**简单的测试Redis是否正常工作**：在登录Redis客户端后，执行`ping`，正常情况下，将得到`PONG`回应

**简单的存入数据：** 在登录Redis客户端后，使用`set K V`的格式即可存入数据，例如：`set name jack`

**简单的取出数据：** 在登录Redis客户端后，使用`get K`的格式即可取出数据，例如：`get name`，如果没有此`K`对应的数据，则返回`(nil)`，相当于Java中的`null`

**查询Key**：在登录Redis客户端后，使用`keys`命令可以查询Key，在此命令后，可以添加空格加星号，则表示查询所有Key，例如：`keys *`，在`keys`命令的空格之后，可以使用星号作为通配符，但也可以不是直接使用星号，例如可以写成：`keys p*`，则表示找出所有以`p`开头的Key

**清空redis**：在登录Redis客户端后，执行`flushall`即可清空Redis

Redis支持的数据类型: String、List、Set、Sorted Set、Hash。


## Redis编程

在Spring Boot项目中，要实现Redis编程（通过程序读写Redis），需要添加依赖：`spring-boot-starter-data-redis`。

Redis编程主要使用`RedisTemplate`工具类，此工具类是有2个泛型的类型的（类似于`Map`），通常，
建议使用`String`和`Serializable`这2个类型分别表示K和V的泛型类型。

需要在配置类中通过`@Bean`方法来配置一个`RedisTemplate`对象，则：

```java
package cn.tedu.csmall.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

@Slf4j
@Configuration
public class RedisConfiguration {

    public RedisConfiguration() {
        log.debug("加载配置类：RedisConfiguration");
    }

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }

}
```

然后，可以使用测试类来学习使用Redis编程：

```java
package cn.tedu.csmall.server;

import cn.tedu.csmall.server.pojo.vo.BrandDetailVO;
import cn.tedu.csmall.server.pojo.vo.BrandListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void testOpsForValueSet() {
        // 获取值操作器，只要在Redis中处理的数据的值（Value）类型是此String，则需要此操作器
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        // 使用操作器写入数据
        String key = "username";
        String value = "liuguobin";
        opsForValue.set(key, value);
        log.debug("已经向Redis中写入简单的String数据");
    }

    @Test
    public void testOpsForValueSet2() {
        // 获取值操作器，只要在Redis中处理的数据的值（Value）类型是此String，则需要此操作器
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        // 使用操作器写入数据
        String key = "email";
        String value = "liuguobin@qq.com";
        long timeout = 1L;
        opsForValue.set(key, value, timeout, TimeUnit.MINUTES);
        log.debug("已经向Redis中写入简单的String数据，有效期为{}分钟", timeout);
    }

    @Test
    public void testOpsForValueSet3() {
        // 获取值操作器，因为在配置类中配置RedisTemplate时，值序列化器是json的，所以，对象值也会转换成JSON字符串
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        // 使用操作器写入数据
        String key = "brand07";

        BrandDetailVO brandDetailVO = new BrandDetailVO();
        brandDetailVO.setId(7L);
        brandDetailVO.setName("华为");
        brandDetailVO.setPinyin("huawei");

        opsForValue.set(key, brandDetailVO);
        log.debug("已经向Redis中写入对象型数据，写入的对象为：{}", brandDetailVO);
    }

    @Test
    public void testOpsForValueGet() {
        // 获取值操作器，只要在Redis中处理的数据的值（Value）类型是此String，则需要此操作器
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        // 使用操作器读取数据
        String key = "brand07";
        Serializable value = opsForValue.get(key);
        log.debug("已经从Redis中读取到Key={}的数据：{}", key, value);
        log.debug("从Redis中读取到的数据的类型：{}", value.getClass().getName());
    }

    @Test
    public void testDelete() {
        String key = "username";
        Boolean delete = redisTemplate.delete(key);
        log.debug("删除Redis中Key={}的数据，结果：{}", key, delete);
    }

    @Test
    public void testKeys() {
        String keyPattern = "*";
        Set<String> keys = redisTemplate.keys(keyPattern);
        log.debug("通过Key的模式【{}】找到的Key：", keyPattern);
        for (String key : keys) {
            log.debug("\tkey={}", key);
        }
    }

    @Test
    public void testOpsForListRightPush() {
        // 准备列表数据
        List<BrandListItemVO> brands = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BrandListItemVO brand = new BrandListItemVO();
            brand.setId(i + 0L);
            brand.setName("品牌名称" + i);
            brands.add(brand);
        }
        // 即将向Redis中写入列表，首先，需要获取列表对应操作器
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        // 写入列表
        String key = "brands";
        for (BrandListItemVO brand : brands) {
            opsForList.rightPush(key, brand);
        }
        log.debug("已经向Redis中写入List型数据");
    }

    @Test
    public void testOpsForListSize() {
        // 需要获取列表对应操作器
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        // 读取List长度
        String key = "brands";
        Long size = opsForList.size(key);
        log.debug("读取Key={}的List型数据的长度：{}", key, size);
    }

    @Test
    public void testOpsForListIndex() {
        // 需要获取列表对应操作器
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        // 将要读取的List的Key
        String key = "brands";
        // 使用正数索引值时，与数组相同，Redis中的List的index是从0开始顺序编号的
        // 使用负数索引值时，将从List的末尾向前对元素进行编号索引，例如-1找出来的就是最后一个元素
        // 以长度为10的List为例，有效索引值分别为[0, 9]和[-10, -1]
        // 如果正数或负数的索引值越界，则返回null
        long index = 10L;
        Serializable serializable = opsForList.index(key, index);
        log.debug("读取Key={}的List型数据的索引为{}的数据：{}", key, index, serializable);
    }

    @Test
    public void testOpsForListRange() {
        // 需要获取列表对应操作器
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        // 将要读取的List的Key
        // 【测试一下】
        // start=0, end=9 >>> 全部
        // start=0, end=0 >>> 第1个
        // start=0, end=-1 >>> 全部【推荐：获取全部元素的参数值】
        // start=0, end=10 >>> 全部
        // start=0, end=-7 >>> 第1个到第4个
        // 把start也改成测试的其它值
        String key = "brands";
        long start = 0L;
        long end = -7L;
        List<Serializable> list = opsForList.range(key, start, end);
        log.debug("读取Key={}的List型数据的从{}到{}的数据集合：{}", key, start, end, list);
    }

}
```



## 关于Key的使用

在Redis中，各数据的Key都是一个自定义的“名称”，只存取一致，这个Key值是不影响基本使用的！

在开发实践中，由于需要存、取的数据可能较多，为了便于统一管理，这些Key值应该有一定的规律，通常，建议把数据的类型作为Key中的必要部分，
例如“品牌”数据的Key中就包含`brand`字样，“相册”数据的Key中就包含`album`字样，如果对应的数据是列表，在Key中可以使用`list`字样，
如果对应的只是此类型的若干数据中的其中1个，在Key中可以使用`item`字样，且各项数据的Key中应该还包含数据id……

在Key中的多个单词，强烈建议使用冒号作为分隔符号，例如：

- `brand:list`

- `brand:item:1`

- `brand:item:2`

- `album:list`

- `album:item:8`



## 关于Redis的应用场景

首先，Redis的特点：

- 读写速度非常快
    - 相比MySQL等
- 存在数据一致问题
    - 并不是每时每刻都与MySQL中的数据完全一样

所以，使用Redis的前提应该是：

- 需要高速的、高频率的读（可能包含写）
- 不太关注数据一致问题（偶尔不一致并不会带来严重后果）

## 关于ApplicationRunner

自定义组件类，实现`ApplicationRunner`，重写其中的`run()`方法，此`run()`方法将在启动项目的末期自动执行。

通常用于实现“启动项目时自动执行的任务”。



## 关于计划任务

自定义组件类，在类中自定义方法，在方法上配置`@Scheduled`注解，并在配置类上添加`@EnableScheduling`开启计划任务，则添加了`@Scheduled`注解的方法会根据注解配置周期性的执行。

关于`@Scheduled`注解，配置其中的`fixedRate`属性，表示每间隔多少毫秒执行一次，例如配置为`@Schedued(fixedRate = 5000)`则表示
每间隔5秒执行一次（每次执行时，在毫秒级的时间上可能略有差异），也可以使用`fixedDelay`属性来配置“距离上次执行后多久再执行下一次计划任务”，
同样也是周期性的执行。

另外，还可以配置`cron`属性来指定某个时间点的执行周期




















