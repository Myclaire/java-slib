package com.chsy.shardingjdbc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chsy.shardingjdbc.entity.Course;
import com.chsy.shardingjdbc.mapper.CourseMapper;
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
