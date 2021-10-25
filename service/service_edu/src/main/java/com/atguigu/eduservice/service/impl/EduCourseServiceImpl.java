package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.handler.selfexception.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        //将CourseInfoVo对象转换为EduCourse对象
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0){
            throw new GuliException(20001,"添加课程失败");
        }

        String cId = eduCourse.getId();

        //2.向课程简介表添加简介
        EduCourseDescription description = new EduCourseDescription();
        description.setId(cId);
        description.setDescription(courseInfoVo.getDescription());
        descriptionService.save(description);
        return cId;
    }

    @Override
    public CourseInfoVo getCourseInfoByCourseId(String courseId) {
        //1.查询课程表信息
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //2.查询描述表信息
        EduCourseDescription courseDescription = descriptionService.getById(courseId);
        courseDescription.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int result = baseMapper.updateById(eduCourse);
        if(result == 0){
            throw new GuliException(20001,"修改课程信息失败");
        }
        //2.修改课程描述信息表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        descriptionService.updateById(eduCourseDescription);
    }

    /**
     * 根据id查修课程信息
     * @param courseId
     * @return
     */
    @Override
    public CoursePublishVo publishCourseInfo(String courseId) {
        CoursePublishVo coursePublishInfo = baseMapper.getPublishCourseInfo(courseId);
        return coursePublishInfo;
    }

    @Override
    public void deleteCourse(String courseId) {
        //1.根据课程id删除小节
        videoService.deleteVideoByCourseId(courseId);
        //2.根据课程id删除章节
        chapterService.deleteChapterByCourseId(courseId);
        //3.根据课程id删除描述
        descriptionService.removeById(courseId);
        //4.根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if(result ==0){
            throw new GuliException(20001,"删除失败！");
        }
    }
}
