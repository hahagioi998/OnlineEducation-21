package com.atguigu.eduorder.client;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lishan
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
