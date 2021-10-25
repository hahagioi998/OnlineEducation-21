package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-10-15
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean deleteVideoById(String id);

    void deleteVideoByCourseId(String courseId);
}
