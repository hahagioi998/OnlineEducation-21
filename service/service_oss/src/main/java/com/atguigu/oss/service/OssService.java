package com.atguigu.oss.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lishan
 */
@Service
public interface OssService {
        /**
         * 文件上传至阿里云
         *
         * @param file
         * @return
         */
    String uploadFileAvatar(MultipartFile file);
}