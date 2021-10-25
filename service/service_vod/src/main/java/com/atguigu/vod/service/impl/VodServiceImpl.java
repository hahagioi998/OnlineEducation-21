package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atguigu.servicebase.handler.selfexception.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName VodServiceImpl
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-20 14:55
 * @Version 1.0
 */
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadALiYunVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,title,originalFilename,inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                //log.warn(errorMessage);
                if(StringUtils.isEmpty(videoId)){
                    throw new GuliException(20001, errorMessage);                 }             }
                     return videoId;
        } catch (IOException e) {
            throw new GuliException(20001, "vod 服务上传失败");
        }
    }

}
