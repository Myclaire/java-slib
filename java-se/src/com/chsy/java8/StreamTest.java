package com.chsy.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description 集合stream相关操作
 * @Author Daniel
 * @Creat At 2020-03-23
 */
public class StreamTest {

    public static void main(String[] args) {
        System.out.println("---------------stream使用------------");
        List<Student> stuList = InitData.getStudentList();

        /**
         * 需求：列出90分以上的学生姓名，并按照分数降序排序
         * 以前：1.遍历，2.判断90分以上的加入到另一个结果集合，3.降序排列
         * 使用stream：1.获取集合stream对象，2.filter过滤，3.sort排序，4.collect把stream对象转为集合对象
         */
        //以前的操作
        System.out.println("--------------ago");
        List<Student> result = new ArrayList<>();
        for (Student student : stuList) {
            if (student.getScore() >= 90) {
                result.add(student);
            }
        }
        result.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        System.out.println(result);

        //使用stream
        System.out.println("-----------use stream");
        result = stuList.stream().filter(s -> s.getScore() >= 90)
                        .sorted(Comparator.comparing(Student::getScore)
                        .reversed()).collect(Collectors.toList());

        System.out.println(result);

        System.out.println("------------use map method");
        //map方法获取list中的name
        List<String> names = stuList.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(names);
        //map方法获取list中name数据的长度
        List<Integer> lengths = stuList.stream().map(Student::getName).map(String::length).collect(Collectors.toList());
        System.out.println(lengths);
        //将每人的分数-10
        List<Integer> scores = stuList.stream().map(Student::getScore).map(i -> i - 10).collect(Collectors.toList());
        System.out.println(scores);

        System.out.println("-------------use reduce method");
        //reduce方法计算学生总分
        Integer total = stuList.stream().map(Student::getScore).reduce(0, (a, b) -> a + b);
        System.out.println("total:" + total);
        //返回Optional
        Optional<Integer> totalOp = stuList.stream().map(Student::getScore).reduce((a, b) -> a + b);
        System.out.println(totalOp.get());
        //计算最高分和最低分
        Optional<Integer> max = stuList.stream().map(Student::getScore).reduce(Integer::max);
        Optional<Integer> min = stuList.stream().map(Student::getScore).reduce(Integer::min);
        System.out.println("the max score:" + max.get());
        System.out.println("the min score:" + min.get());


        //numerical stream
        System.out.println("-----------numerical stream");
        dataStream();
        
        //create stream
        System.out.println("-----------create stream");
        creatStream();
    }
    
    /**
     * test to create stream
     *
     * @Param  []
     * @Return void
     * @Author Daniel
     * @Creat At 2020/3/23
     */
    private static void creatStream() {
        System.out.println("-----------Stream.of");
        Stream<String> str = Stream.of("I", "love", "this", "game!");
        str.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("-----------arr");
        int[] nums = {1, 3, 5, 2, 6};
        System.out.print("arr:");
        Arrays.stream(nums).mapToObj(n -> n + ",").forEach(System.out::print);
        IntStream intStream = Arrays.stream(nums);
        int sum = intStream.sum();
        System.out.println("\nsum:" + sum);

        System.out.println("------------function");
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
    }

    /**
     * test numerical stream
     *
     * @Param  []
     * @Return void
     * @Author Daniel
     * @Creat At 2020/3/23
     */
    private static void dataStream() {
        List<Student> list = InitData.getStudentList();
        //将stream转换为intstream
        int sum = list.stream().mapToInt(Student::getScore).sum();
        System.out.println("sum:" + sum);

        OptionalDouble avg = list.stream().mapToInt(Student::getScore).average();
        System.out.println("average:" + avg.getAsDouble());

        //get number of 1~100
        IntStream num = IntStream.rangeClosed(1, 100);

        //caculate the count of even numbers between 1 and 100
        long count = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0).count();
        System.out.println("the count of even numbers between 1 and 100:" + count);

    }
}
