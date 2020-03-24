package com.chsy.java8;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description 方法引用
 * @Author chenglong
 * @Creat At 2020-03-23
 */
public class Lambda_reference {

    public static void main(String[] args) {
        Integer[] nums = {1,3,8,4,2};
        for (Integer num : nums) {
            System.out.println(num);
        }

        //不使用lambda
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        System.out.println("---------");
        for (Integer num : nums) {
            System.out.println(num);
        }
    }
}
