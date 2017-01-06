package com.brook.btrace.demo;

import java.util.Random;
import java.util.logging.Logger;

public class RemoteClass {

    public static void main(String[] args) throws Exception {
        while (true) {
            Random random = new Random();
            execute(random.nextInt(4000));
        }

    }

    public static Integer execute(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
        }
        Logger.getGlobal().info("sleep time is=>" + sleepTime);
        return 0;
    }
}