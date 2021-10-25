package com.atguigu.eduservice.controller.font;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName IndexFrontController
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-24 13:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduservice/indexFront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询前8条热门课程，前4条名师
     * @return
     */
    @Cacheable(key = "'courseAndTeacher'",value = "index")
    @GetMapping("index")
    public R index(){
        //查询前8条热门课程
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("view_count");
        courseWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(courseWrapper);

        //前4条名师
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherWrapper);
        System.out.println(teacherList);
        return R.ok().data("course",courseList).data("teacher",teacherList);
    }
}
