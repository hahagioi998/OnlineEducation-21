package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.handler.selfexception.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtil;
import com.atguigu.vod.utils.InitObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VodController
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-20 14:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduVod/video")
public class VodController {

    @Autowired
    private VodService vodService;
    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    @PostMapping("/uploadALiYunVideo")
    public R uploadALiYunVideo(MultipartFile file){
        String videoId = vodService.uploadALiYunVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("deleteVideoByVideoId/{videoId}")
    public R deleteVideoByVideoId(@PathVariable String videoId){
        try{
            DefaultAcsClient client = InitObject.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            throw new GuliException(20001,"删除视频失败");
        }
    }

    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try {
            //创建初始化对象
            DefaultAcsClient client = InitObject.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"获取凭证失败");
        }
    }

}
