package com.chsy.java8;

import java.util.ArrayList;
import java.util.List;
/**
 * @Description lambda表达式的使用
 * @Author Daniel
 * @Creat At 2020-03-23
 */
public class Lambda {

    public static void main(String[] args) {

        //不使用lambda
        MyInterface oldMethod = new MyInterface(){
            @Override
            public void m1(int a, int b) {
                System.out.println(a + b);
            }
        };

        oldMethod.m1(2, 3);

        //使用lambda
        MyInterface newMethod = (int a, int b) -> {
            System.out.println(a + b);
        };

        newMethod.m1(5, 5);

        System.out.println("-----------");

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.forEach(m -> System.out.println(m));
        list.forEach(System.out::println);

    }
}
