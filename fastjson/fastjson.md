# 0x00 什么是FastJson

> Fastjson 是一个 Java 库，可用于将 Java 对象转换为其 JSON 表示形式。它还可用于将 JSON 字符串转换为等效的 Java 对象。Fastjson 可以处理任意 Java 对象，包括您没有源代码的预先存在的对象。

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.47</version>
</dependency>
```

## 1.序列化与反序列化

定义如下实体类：

```java
public class User {
    private String username;
    private String password;

    public User() {
        System.out.println("调用了无参构造方法");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("调用了有参构造方法");
    }

    public String getUsername() {
        System.out.println("调用了getUsername()");
        return username;
    }

    public void setUsername(String username) {
        System.out.println("调用了setUsername()");
        this.username = username;
    }

    public String getPassword() {
        System.out.println("调用了getPassword()");
        return password;
    }

    public void setPassword(String password) {
        System.out.println("调用了setPassword()");
        this.password = password;
    }

}
```

### 序列化

```java
    public static void main(String[] args) {
        // 通过构造方法创建对象
        User user = new User("pxb", "123456");
        // fastjson序列化，将java对象转换为json格式的字符串
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);
    }
```

控制台输出如下：

```
调用了有参构造方法
调用了getPassword()
调用了getUsername()
{"password":"123456","username":"pxb"}
```

可以看到，序列化是调用类的getter方法来获取属性的值。

### 反序列化

```java
public static void main(String[] args) {
    // 反序列化为User对象
    User userObject = JSON.parseObject("{\"password\":\"123456\",\"username\":\"pxb\"}", User.class);
    System.out.println(userObject);
}
```

控制台输出如下：

可以看到，在反序列化的过程中，会自动调用类的构造方法以及setter方法，最终返回了一个user对象。

```
调用了无参构造方法
调用了setPassword()
调用了setUsername()
serialize.User@45283ce2
```

**JSON.parseObject()与JSON.parse()的区别：**

fastjson反序列化的方法总共有三类，除了JSON.parseObject()与JSON.parse()，还有JSON.parseArray()。

这里主要说明JSON.parseObject()与JSON.parse()的区别，示例代码如下：

分别用JSON.parseObject()与JSON.parse()反序列化同一json字符串。

```java
public static void main(String[] args) {
    ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    String jsonStr = "{\"@type\":\"serialize.User\",\"password\":\"123456\",\"username\":\"pxb\"}";
    // parseObject方法反序列化
    JSONObject jsonObject = JSON.parseObject(jsonStr);
    System.out.println(jsonObject);
    // parse方法反序列化
    Object parse = JSON.parse(jsonStr);
    System.out.println(parse);
}
```

输出如下：

```
调用了无参构造方法
调用了setPassword()
调用了setUsername()
调用了getPassword()
调用了getUsername()
{"password":"123456","username":"pxb"}
调用了无参构造方法
调用了setPassword()
调用了setUsername()
serialize.User@15327b79
```

**由此可知：**

1. **JSON.parseObject()返回的是json格式的对象，而JSON.parse()返回的是一个普通的Java对象地址。**
2. **JSON.parseObject()调用了构造方法、setter方法、getter方法，而JSON.parse()没有调用getter方法。**

其实，JSON.parseObject()反序列化也是调用的JSON.parse()方法，只不过反序列化后会再调用JSON.toJSON()，将Java对象转换为JSONObject，源码如下：

```java
public static JSONObject parseObject(String text) {
    Object obj = parse(text);
    if (obj instanceof JSONObject) {
        return (JSONObject) obj;
    }

    try {
        return (JSONObject) JSON.toJSON(obj);
    } catch (RuntimeException e) {
        throw new JSONException("can not cast to JSONObject.", e);
    }
}
```

# 0x01 AutoType

## 1.为什么要有AutoType？

看如下场景：

定义一个接口Fruit；

定义一个Apple类实现Fruit接口；

定义一个Orange类实现Fruit接口；

Apple类与Orange类都有一个属性cnName。

```java
interface Fruit {}

class Apple implements Fruit{
    private String cnName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}

class Orange implements Fruit{
    private String cnName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
```

序列化一个Apple对象：

```java
public static void main(String[] args) {
    ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    // 创建一个apple对象
    Apple apple = new Apple();
    apple.setCnName("苹果");
    // 序列化
    String appleJson = JSON.toJSONString(apple);
    System.out.println(appleJson);
}
```

得到的json字符串为：{"cnName":"苹果"}；

同理，序列化一个Orange对象（设置属性名为“橙子”），得到的json字符串为：{"cnName":"橙子"}；

以上两个json字符串本质上是一样的，当我们反序列化{"cnName":"苹果"}时，无法知道需要反序列化为何种类型的对象，如下语句，将出现异常。

```java
Apple apple1 = (Apple)JSON.parse("{\"cnName\":\"苹果\"}");
```

```
Exception in thread "main" java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to serialize.Apple
	at serialize.FastJsonTest3.main(FastJsonTest3.java:18)
```

> 当一个类中包含了一个接口（或抽象类）的时候，在使用fastjson进行序列化的时候，会将子类型抹去，只保留接口（抽象类）的类型，使得反序列化时无法拿到原始类型。

为了解决以上问题，fastjson引入了AutoType，即在序列化对象的时候，通过@type指定对象的类型。序列化案例如下：

### AutoType序列化

```java
public static void main(String[] args) {
    // 1.2.25及以上的版本，需要手动开启AutoType
    ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    // 创建一个apple对象
    Apple apple = new Apple();
    apple.setCnName("苹果");
    // 序列化
    String appleJson = JSON.toJSONString(apple, SerializerFeature.WriteClassName);
    System.out.println(appleJson);
}
```

通过SerializerFeature.WriteClassName指定类型。输入如下：

@type的值为该类型的全限定名（包名+类名）

```
{"@type":"serialize.Apple","cnName":"苹果"}
```

## 2.AutoType带来的问题

fastjson遇到@type内容时，会将json字符串反序列化为@type指定的类型的对象，并会调用该类的构造方法、getter方法、setter方法，如果这些方法中存在危险函数，或者间接的调用到了危险函数，就会存在反序列化漏洞。

# 0x02 FastJson反序列化利用链

## JdbcRowSetImpl

















RMI利用的JDK版本≤ JDK 6u132、7u122、8u113

LADP利用JDK版本≤ 6u211 、7u201、8u191





1.2.24 默认开启autotype

1.2.25   默认关闭  增加了checkAutoType采用白名单和黑名单的方式对加载的第三方类库进行检查



![image-20220724150316875](C:\Users\llb\AppData\Roaming\Typora\typora-user-images\image-20220724150316875.png)

![image-20220724150542971](C:\Users\llb\AppData\Roaming\Typora\typora-user-images\image-20220724150542971.png)

![image-20220724150639080](C:\Users\llb\AppData\Roaming\Typora\typora-user-images\image-20220724150639080.png)

![image-20220724150900429](C:\Users\llb\AppData\Roaming\Typora\typora-user-images\image-20220724150900429.png)

![image-20220724153623348](C:\Users\llb\AppData\Roaming\Typora\typora-user-images\image-20220724153623348.png)

![image-20220724153921820](C:\Users\llb\AppData\Roaming\Typora\typora-user-images\image-20220724153921820.png)

!(C:\Users\llb\AppData\Roaming\Typora\typora-user-images\image-20220724154616385.png) 

https://www.bilibili.com/video/BV1of4y1u7CE?spm_id_from=333.337.search-card.all.click&vd_source=23bda2b83b29d244f71624c5ca495fda





https://xz.aliyun.com/t/8140#toc-0