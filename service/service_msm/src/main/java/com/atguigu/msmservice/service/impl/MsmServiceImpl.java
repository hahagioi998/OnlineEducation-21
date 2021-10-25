package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * @ClassName MsmServiceImpl
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-25 13:39
 * @Version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {
    /**
     * 发送短信
     */
    @Override
    public boolean send(String PhoneNumbers, String templateCode, Map<String, Object> param) {
        if(StringUtils.isEmpty(PhoneNumbers)) {
            return false;
        }

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4GJXyaWzm7QbQFrtz5RD", "bgn1LTBQsuVTvRWQDftEDfB6gWgJPh");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",PhoneNumbers);
        request.putQueryParameter("SignName","超级新球慕课在线教育网站");
        request.putQueryParameter("TemplateCode","SMS_207095122");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
