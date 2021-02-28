package com.chsy.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chsy.shardingjdbc.entity.Course;
import com.chsy.shardingjdbc.entity.Udict;
import com.chsy.shardingjdbc.entity.User;
import com.chsy.shardingjdbc.mapper.CourseMapper;
import com.chsy.shardingjdbc.mapper.UdictMapper;
import com.chsy.shardingjdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingJdbcApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UdictMapper udictMapper;

    //=================测试公共表==================
    @Test
    void addUdict() {
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    @Test
    void deleteUdict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_id", 572935979397021697L);
        udictMapper.delete(wrapper);
    }

    //=================测试垂直分库================
    @Test
    void addUserDb() {
        User user = new User();
        user.setUserName("Tom");
        user.setUstatus("Normal");
        userMapper.insert(user);
    }

    @Test
    void getUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 572931926487203841L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    //=================测试水平分库================
    @Test
    void addCourseDb() {
        Course course = new Course();
        course.setCname("java-database");
        course.setUserId(101L);
        course.setCstatus("Normal");
        courseMapper.insert(course);
    }

    @Test
    void getCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", 100L);
        wrapper.eq("cid", 572918395293401089L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //=================测试水平分表================
    @Test
    void addCourse() {
        for (int i = 0; i <= 10; i++) {
            Course course = new Course();
            course.setCname("java");
            course.setUserId(100L);
            course.setCstatus("Normal");
            courseMapper.insert(course);
        }
    }

    @Test
    void getCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 572910584496914432L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }
}
