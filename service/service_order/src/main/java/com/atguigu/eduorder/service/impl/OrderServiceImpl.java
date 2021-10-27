package com.atguigu.eduorder.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.mapper.OrderMapper;
import com.atguigu.eduorder.service.OrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;
    /**
     * 生成订单
     * @param courseId
     * @param memberId
     * @return
     */
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过用户id远程调用获取用户信息
        UcenterMemberOrder ucenterMember = ucenterClient.getUserInfoOrder(memberId);
        //通过课程id远程调用获取课程信息
        CourseWebVoOrder courseDto = eduClient.getCourseInfo(courseId);
        //创建order对象，向order对象设置所需数据
        Order order = new Order();
        //订单号
        order.setOrderNo(OrderNoUtil.getOrder());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName(courseDto.getTeacherName());
        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        //支付状态(0： 未支付 1： 已支付)
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
