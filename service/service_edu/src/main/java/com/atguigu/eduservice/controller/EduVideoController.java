package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.handler.selfexception.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-15
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    /**
     * 根据id查询小节信息
     * @param videoId
     * @return
     */
    @GetMapping("/getVideoInfo/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("eduVideo",eduVideo);
    }

    /**
     * 添加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 修改小节
     * @param eduVideo
     * @return
     */
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    /**
     * 删除小节
     * @param id
     * @return
     * TODO 这个方法需要完善，删除小节的同时删除其视频
     */

    @DeleteMapping("deleteVideoById/{id}")
    public R deleteVideoById(@PathVariable String id){
        //根据小节id获取视频id
        EduVideo video = eduVideoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id删除视频
            R r = vodClient.deleteVideoByVideoId(videoSourceId);
            if(r.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器。。。");
            }
        }
        //根据小节id删除小节
        eduVideoService.deleteVideoById(id);
        return R.ok();
    }
}

