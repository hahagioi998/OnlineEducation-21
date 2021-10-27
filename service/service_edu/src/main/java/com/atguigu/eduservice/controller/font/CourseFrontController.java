package com.atguigu.eduservice.controller.font;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.frontvo.CourseVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CourseFrontController
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-26 16:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduservice/courseFront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;
    /**
     * 分页条件查询
     * @param current
     * @param limit
     * @param courseVo
     * @return
     */
    @PostMapping("getFrontCourseList/{current}/{limit}")
    public R getFrontCourseList(@PathVariable long current, @PathVariable long limit,
                 @RequestBody(required = false) CourseVo courseVo){
        //创建Page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);

        Map<String,Object> map = courseService.getCourseList(pageCourse, courseVo);

        return R.ok().data(map);
    }

    /**
     * 课程详情
     * @param courseId
     * @return
     */
    @GetMapping("getBaseCourseInfoById/{courseId}")
    public R getBaseCourseInfoById(@PathVariable String courseId){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo =courseService.getBaseCourseInfoById(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}
