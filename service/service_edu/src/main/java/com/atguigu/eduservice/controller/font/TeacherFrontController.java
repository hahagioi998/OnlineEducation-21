package com.atguigu.eduservice.controller.font;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TeacherFrontController
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-26 11:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduservice/teacherFront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;
    /**
     * 前端讲师分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit){

        Page<EduTeacher> pageTeacher = new Page<>(page,limit);

        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);

        return R.ok().data(map);

    }

    /**
     * 根据id查询讲师基本信息
     * @param id
     * @return
     */
    @GetMapping("/getTeacherInfoByTeacherId/{id}")
    public R getTeacherInfoById(@PathVariable String id){
        //根据id查询讲师基本信息
        EduTeacher teacherInfo = teacherService.getById(id);
        //根据id查询课程基本信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseInfo = courseService.list(wrapper);

        return R.ok().data("teacherInfo",teacherInfo).data("courseInfo",courseInfo);
    }
}
