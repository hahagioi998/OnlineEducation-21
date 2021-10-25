package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginVo
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-25 15:34
 * @Version 1.0
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVo {
    @NotBlank
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;
}
