package com.atguigu.educenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.handler.selfexception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-25
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     *	会员登录
     *	@param loginVo
     *	@return
     */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"帐号或密码为必填项");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if(ucenterMember == null){
            throw new GuliException(20001,"手机号码不存在");
        }
        //校验密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new GuliException(20001,"密码错误");
        }
        if(ucenterMember.getIsDisabled()){
            throw new GuliException(20001,"帐号已被禁用");
        }
        String token = JwtUtils.getJwtToken(ucenterMember.getId(),ucenterMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        @NotBlank String code = registerVo.getCode();
        @NotBlank String mobile = registerVo.getMobile();
        @NotBlank String password = registerVo.getPassword();
        @NotBlank String nickname = registerVo.getNickname();
        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)){
            throw new GuliException(20001,"请先填写完整");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer integer = baseMapper.selectCount(wrapper);
        if(integer > 0){
            throw new GuliException(20001,"手机号码重复，请重新输入");
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw new GuliException(20001,"验证码错误或已经失效");
        }
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        baseMapper.insert(member);
    }

}
