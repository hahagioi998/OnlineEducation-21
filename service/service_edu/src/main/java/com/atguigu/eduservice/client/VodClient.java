package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName VodClient
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-22 20:50
 * @Version 1.0
 */
@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    @DeleteMapping("/eduVod/video/deleteVideoByVideoId/{videoId}")
    R deleteVideoByVideoId(@PathVariable("videoId") String videoId);
}
