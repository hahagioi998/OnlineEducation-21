package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.frontvo.CourseVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-10-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoByCourseId(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据id查修课程信息

    CoursePublishVo publishCourseInfo(String courseId);

    void deleteCourse(String courseId);

    /**
     * 条件查询带分页
     * @param pageCourse
     * @param courseVo
     * @return
     */
    Map<String, Object> getCourseList(Page<EduCourse> pageCourse, CourseVo courseVo);


    CourseWebVo getBaseCourseInfoById(String courseId);
}
