package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author lishan
 */
public interface VodService {

    String uploadALiYunVideo(MultipartFile file);

}
