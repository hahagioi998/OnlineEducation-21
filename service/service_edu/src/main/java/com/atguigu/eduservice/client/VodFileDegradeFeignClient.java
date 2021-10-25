package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @ClassName VodFileDegradeFeignClient
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-22 22:46
 * @Version 1.0
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R deleteVideoByVideoId(String videoId) {

        return R.error().message("time out");
    }
}
