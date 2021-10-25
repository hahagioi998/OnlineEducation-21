package com.atguigu.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName RegisterVo
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-25 16:50
 * @Version 1.0
 */
@Data
@ApiModel(value="注册对象", description="注册对象")
public class RegisterVo {

    @ApiModelProperty(value = "昵称")
    @NotBlank
    private String nickname;

    @ApiModelProperty(value = "手机号")
    @NotBlank
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank
    private String code;
}
