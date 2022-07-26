JNDI 官方文档：https://docs.oracle.com/javase/tutorial/jndi/index.html

为什么要有Reference：https://docs.oracle.com/javase/jndi/tutorial/objects/storing/reference.html



通过名称查找对象，LDAP是通用的东西，并不是java的，所以通过LDAP查找java对象，必然存在将字符串转换为Java对象的过程 可以是反射、类加载、实例化等

JNDI-RMI  底层调用的是原生RMI

Reference参数：类名 工厂名 工厂的位置   工厂：要执行的代码逻辑

