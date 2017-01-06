package com.brook.btrace.demo;

import java.util.Random;
import java.util.UUID;

public class RemoteClass {

    public String f1(String a, int b) {
        System.out.println(a + " " + b);
        return a;
    }

    public static void main(String[] args) {
        RemoteClass rc = new RemoteClass();
        while (true) {
            rc.f1(UUID.randomUUID().toString(), new Random().nextInt());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }
}