package com.brook.btrace.demo;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

import static com.sun.btrace.BTraceUtils.jstack;
import static com.sun.btrace.BTraceUtils.println;

/**
 * @author liushaojun
 * @create 17/1/6
 */
@BTrace
public class SystemGCDebug {
    @OnMethod(clazz = "java.lang.System", method = "gc")
    public static void onSystemGC() {
        println("entered System.gc()");
        jstack();
    }
}
