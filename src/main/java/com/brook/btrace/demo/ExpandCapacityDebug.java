package com.brook.btrace.demo;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace(name = "监控map扩容")
public class ExpandCapacityDebug {

    /**
     * 只能拦截public 修饰的方法
     */
    @OnMethod(clazz = "java.util.HashMap"
            , method = "put",
            location = @Location(value = Kind.CALL
                    , clazz = "/.*/", method = "/.*/"))
    public static void traceMapExpandCapacity(@Self Object self,
                                              @ProbeMethodName String pmn,
                                              @TargetMethodOrField String method,
                                              AnyType arg) {
        println("pmn :" + pmn); // put
        println("method is :"+ method); // hash
        println("arg is "+ arg); //[1-99]
    }
    @OnMethod(clazz = "java.util.HashMap", method = "<init>")
    public static void onInit(@Self Object self) {
        println("init map......");
        Object capacity = get(field(classOf(self), "threshold", true), self);
        println("init class is " + BTraceUtils.name(BTraceUtils.Reflective.classOf(self)) + " ,init" +
                " capacity is " + capacity);
    }

    @OnExit
    public static void onExit(int code) {
        println("exit of btrace." + str(code));
    }

    @OnEvent
    public static void onEvent() {
        println("send a event of btrace.");
    }
    //@OnTimer(1000)
    //public static void ontime() {
    //    println("hello");
    //    i++;
    //
    //}

}
