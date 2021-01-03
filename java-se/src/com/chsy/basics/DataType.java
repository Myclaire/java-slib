package com.chsy.basics;

public class DataType {

    public static void main(String[] args) {
        //整数拓展：进制  二进制0b  十进制  八进制0  十六进制0x
        int i1 = 10;//10
        int i2 = 010;//8
        int i3 = 0x10;//16

        //浮点数拓展
        //算钱用float和double有问题，有限，离散，舍入误差，接近但不等于
        //使用BigDecimal
        float f = 0.1f; //0.1
        double d = 1.0/10; //0.1
        System.out.println(f == d); //false

        float d1 = 232323343432444f;
        float d2 = d1 + 1;
        System.out.println(d1 == d2);

        //字符拓展
        System.out.println("==========================");
        char c1 = 'a';
        char c2 = '张';

        System.out.println(c1);
        System.out.println((int) c1);
        System.out.println(c2);
        System.out.println((int) c2);

        char c3 = '\u0061';
        System.out.println(c3);

        System.out.println("========================");
        String sa = new String("hello");
        String sb = new String("hello");
        System.out.println(sa == sb);

        String sc = "hello";
        String sd = "hello";
        System.out.println(sc == sd);

        //布尔值扩展
        boolean flag = true;
        if (flag == true) {}
        if (flag) {}
        //Less is More! 代码要精简易懂
    }
}
