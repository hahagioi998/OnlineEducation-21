package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @ClassName MsmService
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-25 13:38
 * @Version 1.0
 */
public interface MsmService {
    boolean send(String PhoneNumbers, String templateCode, Map<String,Object> param);
}
