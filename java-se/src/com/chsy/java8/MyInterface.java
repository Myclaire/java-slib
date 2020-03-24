package com.chsy.java8;

@FunctionalInterface
public interface MyInterface {

    void m1(int a, int b);

    default String m2(){return null;}

    default void m3(){}
}
