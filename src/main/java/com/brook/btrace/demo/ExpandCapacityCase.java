package com.brook.btrace.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExpandCapacityCase {
    public static final int ONE_MB = 1024 * 1024;
    public static void main(String[] args) throws Exception {
        mapExpandCapacity();
    }
    /**
     * java.util.HashMap#resize(int)
     * 何时扩容，发生扩容时候的上下文情况
     */
    public static void mapExpandCapacity() throws Exception {
            while (true) {
                //这是模拟主要逻辑
                Map<Integer, Byte[]> map = new HashMap<Integer, Byte[]>();
                //16,0.75 16*0.75=12
                int size = 100;
                for (int i = 0; i < size; i++) {
                    map.put(i, new Byte[ONE_MB]);
                }
                System.out.println("Expand SIZE = " + map.size());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}