package com.brook.btrace.demo;

/**
 * @author liushaojun
 * @create 17/1/6
 */
public class SystemGCCase {
    public static void main(String[] args) {
        while (true){

            System.gc();
        }
    }
}
