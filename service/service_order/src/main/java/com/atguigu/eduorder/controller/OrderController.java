package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-10-27
 */
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据课程id生成订单
     * @param courseId
     * @return
     */
    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //创建订单
        String orderNo = orderService.createOrders(courseId,memberId);
        return R.ok().data("orderNo",orderNo);
    }
    /**
     * 根据订单id查询订单信息
     * @param id
     * @return
     */
    @GetMapping("getOrderInfo/{id}")
    public R getOrderInfo(@PathVariable String id){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",id);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("order",order);
    }

}

