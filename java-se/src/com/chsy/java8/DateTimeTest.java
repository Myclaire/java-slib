package com.chsy.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;

/**
 * @Description about date and time
 * @Author Daniel
 * @Creat At 2020-03-23
 */
public class DateTimeTest {

    public static void main(String[] args) {
        System.out.println("---------get date--------");
        getDate();
        System.out.println("---------get time-------");
        getTime();
        System.out.println("---------get datetime--------");

        LocalDateTime now = LocalDateTime.now();
        System.out.println("now : " + now);

        LocalDateTime setTime = LocalDateTime.of(2020, 03, 24, 23, 59, 59);
        System.out.println("set time is : " + setTime);

        //get data of our country
        getCountryLive();

        //set time zone
        setTimeZone();

    }

    private static void setTimeZone() {
        //获取本地的时区时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("local zone : " + now);

        //设定时区为美国洛杉矶
        ZoneId zone = ZoneId.of("America/Los_Angeles");
        //获取指定的时区时间
        ZonedDateTime usa = ZonedDateTime.now(zone);
        System.out.println("America/Los_Angeles : " + usa);
    }

    /**
     * 计算祖国成立多长时间了
     */
    private static void getCountryLive() {
        // 建国日期
        LocalDate begin = LocalDate.of(1949, 10, 1);
        // 今天日期
        LocalDate end = LocalDate.now();

        Period period = Period.between(begin, end);
        System.out.println("祖国已成立：");
        System.out.println(period.getYears() + "年");
        System.out.println(period.getMonths() + "月");
        System.out.println(period.getDays() + "天");

        LocalDate date = LocalDate.of(1949, 10, 1);
        LocalTime time = LocalTime.of(7, 20, 00);

        LocalDateTime begin2 = LocalDateTime.of(date, time);
        LocalDateTime end2 = LocalDateTime.now();

        Duration between = Duration.between(begin2, end2);

        System.out.println("祖国成立：" + between.toDays() + "天");
    }

    private static void getTime() {
        //now time
        LocalTime time1 = LocalTime.now();
        //no ms
        LocalTime time2 = LocalTime.now().withNano(0);
        System.out.println("time1 : " + time1);
        System.out.println("time2 : " + time2);

        LocalTime afterHours = time1.plusHours(2);
        System.out.println("2 hours later : " + afterHours);

        //set time
        LocalTime time3 = LocalTime.of(23, 59, 59, 59);
        LocalTime time4 = LocalTime.parse("23:58:58");

        //计算两个localTime相差时间
        Duration duration = Duration.between(time1, afterHours);
        System.out.println("time 相差：" + duration.toHours() + " hours");
    }

    private static void getDate() {
        //date of today
        LocalDate dateToday = LocalDate.now();
        System.out.println("today's date is : " + dateToday);

        int year = dateToday.getYear();
        int month = dateToday.getMonthValue();
        int day = dateToday.getDayOfMonth();
        System.out.println("today's date is : " + year + "年" + month + "月" + day + "日");

        //format the date
        String df = dateToday.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        System.out.println("after format, today's date is : " + df);

        //judge the leap year
        boolean leap = dateToday.isLeapYear();
        System.out.println("this year is leap year? : " + leap);

        //get the days of a month
        int days = dateToday.lengthOfMonth();
        System.out.println("this month's days : " + days);

        //set date
        LocalDate date1 = LocalDate.parse("2020-03-01");
        LocalDate date2 = LocalDate.of(2020, 03, 01);
        //judge if equal
        if (date1.equals(date2)) {
            System.out.println("equals ? they are : " + true);
        }

        //set the date after a week
        LocalDate dateAfter = dateToday.plus(1, ChronoUnit.WEEKS);
        System.out.println("today is " + dateToday);
        System.out.println("after a week is " + dateAfter);

        //计算两个localDate相差时间
        Period period = Period.between(dateToday, dateAfter);
        System.out.println("date 相差：" + period.getDays() + " days");
    }
}
