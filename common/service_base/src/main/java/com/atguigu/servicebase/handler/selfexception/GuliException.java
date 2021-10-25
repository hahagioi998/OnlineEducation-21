package com.atguigu.servicebase.handler.selfexception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName GuliException
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-12 17:27
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
