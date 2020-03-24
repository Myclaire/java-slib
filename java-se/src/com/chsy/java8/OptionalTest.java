package com.chsy.java8;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Description use of Optional class
 * @Author Daniel
 * @Creat At 2020-03-23
 */
public class OptionalTest {

    public static void main(String[] args) {
        List<Student> list = InitData.getStudentList();
        Optional<Integer> count = list.stream().filter(s -> s.getScore() < 60).map(Student::getScore).reduce((a, b) -> a + b);
        System.out.println("-------don't pass sum:" + count.orElse(0));

        Map<String, String> map = InitData.getStudentMap();
        String name = Optional.ofNullable(map.get("1005")).orElse("no person");
        System.out.println("-------1005:" + name);
    }
}
