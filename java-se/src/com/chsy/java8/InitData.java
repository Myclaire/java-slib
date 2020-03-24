package com.chsy.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description data initialize
 * @Author Daniel
 * @Creat At 2020-03-23
 */
public class InitData {

    public static List<Student> getStudentList() {
        List<Student> stuList = new ArrayList<>(10);

        stuList.add(new Student("刘一", 85));
        stuList.add(new Student("陈二", 90));
        stuList.add(new Student("张三", 98));
        stuList.add(new Student("李四", 88));
        stuList.add(new Student("王五", 83));
        stuList.add(new Student("赵六", 95));
        stuList.add(new Student("孙七", 87));
        stuList.add(new Student("周八", 84));
        stuList.add(new Student("吴九", 100));
        stuList.add(new Student("郑十", 95));
        return stuList;
    }

    public static Map<String, String> getStudentMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1001", "刘一");
        map.put("1002", "陈二");
        map.put("1003", "张三");
        map.put("1004", "李四");
        return map;
    }
}
