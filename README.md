# BTrace demo

## 简介

BTrace 是一个线上诊断分析工具。而且是安全性，无侵入性，无需重启应用即可生效，
查看跟踪行为，不能有循环。它是一种将字节码程序进行一种热交换技术。
示例图：
![](http://dl.iteye.com/upload/attachment/0063/3755/c83c975a-aa7e-386e-85f6-d645745b6cf9.jpg)

## 作用
- 方法调用、返回、捕捉方法异常
- 获取行号、字段 get/set
- 方法调用/返回（在指定的方法中）
- 异常抛出前后
- 同步进入/退出
- 定时器

## 限制

- BTrace class不能新建类, 新建数组, 抛异常, 捕获异常,
- 不能调用实例方法以及静态方法(com.sun.btrace.BTraceUtils除外)
- BTrace1.2前不能有实例字段和方法，只能有无返回值的静态方法，所有字段也都必须是静态的。
- 不能定义外部, 内部, 匿名, 本地类
- 不能有同步块和方法
- 不能有循环
- 不能实现接口, 不能扩展类

注： 使用 1.3.8最新使用非静态方法报错

## 注解详解

1. @OnTimer定时触发Trace，时间可以指定，单位为毫秒.[参考Histogram例子](https://github.com/btraceio/btrace/blob/master/samples/Histogram.java)
2. @OnError 当trace代码抛异常或者错误时，该注解的方法会被执行. 如果同一个trace脚本中其他方法抛异常, 该注解方法也会被执行。
3. @OnExit 当trace方法调用内置exit(int)方法(用来结束整个trace程序)时, 该注解的方法会被执行. [参考ProbeExit例子](https://github.com/btraceio/btrace/blob/master/samples/ProbeExit.java)
4. @OnEvent 用来截获"外部"btrace client触发的事件, 比如按Ctrl-C 中断btrace执行时，并且选择2，或者输入事件名称，将执行使用了该注解的方法,
该注解的value值为具体事件名称。[具体参考例子HistoOnEvent](https://github.com/btraceio/btrace/blob/master/samples/HistoOnEvent.java)
5. @OnLowMemory 当内存超过某个设定值将触发该注解的方法, [具体参考例子MemAlerter](https://github.com/btraceio/btrace/blob/master/samples/MemAlerter.java)
6. @OnProbe 使用外部文件XML来定义trace方法以及具体的位置，具体参考示例SocketTracker1.java和java.net.socket.xml。
参数上的注解
7. @Self 用来指定被trace方法的this，[具体参考例子AWTEventTracer](https://github.com/btraceio/btrace/blob/master/samples/AWTEventTracer.java)可参考例子AWTEventTracer.java 和
[具体参考例子AllCalls1](https://github.com/btraceio/btrace/blob/master/samples/AllCalls1.java)
8. @Return 用来指定被trace方法的返回值，[具体参考例子Classload](https://github.com/btraceio/btrace/blob/master/samples/Classload.java)
9. @ProbeClassName (since 1.1) 用来指定被trace的类名, 可参考例子AllMethods.java
10. @ProbeMethodName (since 1.1) 用来指定被trace的方法名, 可参考例子WebServiceTracker.java。
11. @TargetInstance (since 1.1) 用来指定被trace方法内部被调用到的实例, 可参考例子AllCalls2.java
12. @TargetMethodOrField (since 1.1) 用来指定被trace方法内部被调用的方法名, 可参考例子AllCalls1.java 和 AllCalls2.java。
13. @OnMethod 拦截方法定义

## Location 注解的方法
> 一般都是用来做方法签名匹配用的,
他们一般和被trace方法中参数出现的顺序一致.
不过他们也可以与注解方法交错使用,
如果一个参数类型声明为 `AnyType[]`,
则表明它按顺序"通吃"方法所有参数.
未注解方法需要与`Location`结合使用:


- `Kind.ENTRY, Kind.RETURN`- 被trace方法参数
- `Kind.THROW` - 异常抛出
- `Kind.ERROR` - 异常没被捕获被抛出函数之外Error
- `Kind.CATCH` - 异常被捕捉
- `Kind.CALL` - 监控被调用函数里面所有调用的函数
- `Kind.LINE` - 行号

## 其他

- `@TLS` 定义ThreadLocal变量。例如进程间通信
- 接口（Interface）、父类和注解(Annotation)定义
  >比如我想匹配所有的`Filter`类，在接口或基类的名称前面，加个`+` 就行
  `@OnMethod(clazz="+com.vip.demo.Filter", method="doFilter")`
  也可以按类或方法上的`annotation`匹配，前面加上`@`就行
  `@OnMethod(clazz="@javax.jws.WebService", method="@javax.jws.WebMethod")`
- 同类产品
阿里开源`Greys`[github 地址](https://github.com/oldmanpushcart/greys-anatomy)比`BTrace`强大。

## 参考
- [Btrace 官网文档](https://kenai.com/projects/btrace/pages/UserGuide)
- [Btrace github地址](https://github.com/btraceio/btrace/)
- [Btrace简介](http://mgoann.iteye.com/blog/1409667)
- [Btrace入门到熟练小工完全指南](http://calvin1978.blogcn.com/articles/btrace1.html)