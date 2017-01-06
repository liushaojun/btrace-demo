package com.brook.btrace.demo;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author liushaojun
 * @create 17/1/6
 */
public class AllMethodCase {

    public static void main(String[] args) throws Exception{
        while (true) {
            HashMap map = new HashMap(30);
            map.put("a", 1);
            System.out.println("map's size is " + map.size());
            map.get("a");
            map.remove("a");
            System.out.println("map's size is" + map.size());
            TimeUnit.SECONDS.sleep(2);
        }

    }
}
