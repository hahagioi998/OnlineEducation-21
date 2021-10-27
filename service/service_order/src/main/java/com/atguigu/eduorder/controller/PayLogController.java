package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-27
 */
@RestController
@RequestMapping("/eduorder/pay-log")
@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    /**
     * 生成微信支付二维码接口
     * @param orderNo
     * @return
     */
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
       Map map = payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    /**
     * 根据订单号查询支付状态
     * @param orderNo
     * @return
     */
    @GetMapping("getPayStatus/{orderNo}")
    public R getPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.getPayStatus(orderNo);
        if(map == null){
            return R.error().message("支付出错了！");
        }
        //如果map返回不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS") ){
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中...");
    }
}

