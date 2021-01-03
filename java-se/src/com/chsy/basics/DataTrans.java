package com.chsy.basics;

public class DataTrans {

    public static void main(String[] args) {
        int i = 128;
        byte b = (byte) i; //强制转换，内存溢出

        //强制转换，高到低
        //自动转换，低到高

        System.out.println(i);
        System.out.println(b);

        System.out.println((int) 5.4);
        System.out.println((int) 5.5);
        System.out.println((int) 5.6);

        //jdk7新特性，数字之间可以用下划线分割
        int money = 10_000_000_00;
        System.out.println(money);
        System.out.println(money * 20); //-1474836480，计算的时候溢出了

        long total = money * 20;
        System.out.println(total); //-1474836480，默认是int，转换之前已经存在问题了

        long total2 = money * (long) 20;
        System.out.println(total2); //20000000000，正常
    }
}
