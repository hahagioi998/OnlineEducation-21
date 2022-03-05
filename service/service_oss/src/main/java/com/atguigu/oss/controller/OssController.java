package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-14 17:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduOss/fileOss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping
    public R uploadOssFile(MultipartFile file){
       String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
