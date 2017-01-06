package com.brook.btrace.demo;

import static com.sun.btrace.BTraceUtils.*;

import java.util.Map;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

@BTrace
public class ExpandCapacityDebug {
    @OnMethod(clazz = "java.util.HashMap", method = "resize",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/"))
    public static void traceMapExpandCapacity(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod,
                                              @Self Object self, int newCapacity) {
        String point = probeClass+ "."+probeMethod;
        //java/util/HashMap.resize
        Class clazz = classForName("java.util.HashMap");
        println(BTraceUtils.Strings.strcat(point, "======"));
        //获取实例protected变量
        Map.Entry[] table= (Map.Entry[]) get(field(clazz, "table", true), self);
        int threshold = getInt(field(clazz, "threshold", true), self);
        int size = getInt(field(clazz, "size", true), self);
        println(BTraceUtils.Strings.strcat("newCapacity:", str(newCapacity)));
        println(Strings.strcat("table.length:", str(table.length)));
        println(Strings.strcat("size:", str(size)));
        println(Strings.strcat("threshold:", str(threshold)));
        println(Strings.strcat(point, "------------"));
    }
}
