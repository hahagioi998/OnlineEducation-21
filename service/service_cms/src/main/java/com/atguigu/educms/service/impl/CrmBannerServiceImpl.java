package com.atguigu.educms.service.impl;

import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.mapper.CrmBannerMapper;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner显示接口
 * </p>
 *
 * @author testjava
 * @since 2021-10-24
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 查询所有banner
     * @return
     */
    @Override
    @Cacheable(key = "'selectIndexList'",value = "banner")
    public List<CrmBanner> selectIndexList() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 0,2");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        System.out.println(list);
        return list;
    }
}
