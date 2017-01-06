package com.brook.btrace.demo;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class BTraceHelloWorld {

    /**
     * 官方文档说 1.2 之前可以不用声明public static void， 但是使用 1.3 不行
     */

    @TLS
    private static long startTime = 0;

    @OnMethod(clazz = "com.brook.btrace.demo.RemoteClass", method = "execute")
    public static void startMethod() {
        startTime = timeMillis();
    }

    @OnMethod(clazz = "com.brook.btrace.demo.RemoteClass", method = "execute", location = @Location(Kind.RETURN))
    public static void endMethod() {
        println(strcat("the class method execute time=>", str(timeMillis() - startTime)));
        println("-------------------------------------------");
    }

    // 正则表达式定位
    @OnMethod(clazz = "/.*RemoteClass/", method = "execute", location = @Location(Kind.RETURN))
    public static void traceExecute(@ProbeClassName String name, @ProbeMethodName String method, int sleepTime) {
        println(strcat("the class name=>", name));
        println(strcat("the class method=>", method));
        println(strcat("the class method params=>", str(sleepTime)));

    }


    // @Self Object self 如果是静态方法则self，则为null.
    @OnMethod(clazz = "java.io.File", method = "createTempFile", location = @Location(value = Kind.RETURN))
    public static void r(@ProbeClassName String clazz ,
                         @ProbeMethodName String method,
                         String prefix, String suffix,
                         @Return AnyType result) {
        println(clazz + "#" + method
                + " args " + prefix + ":"
                + suffix + " return " + result);
    }
}