package com.brook.btrace.demo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author liushaojun
 * @create 17/1/6
 */
public class ReturnCase {
    public static void main(String[] args) {
       while (true){
           try {
               execute();
               TimeUnit.SECONDS.sleep(2);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }
    public static void execute() throws IOException {
        File tmp = null;
            tmp = File.createTempFile("test", ".tmp");
        if (tmp.exists()) {
            System.out.println("tmp path is " + tmp.getCanonicalPath());
            tmp.delete();
        }
    }
}
