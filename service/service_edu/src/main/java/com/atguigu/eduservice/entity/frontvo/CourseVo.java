package com.atguigu.eduservice.entity.frontvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CourseVo
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-26 16:47
 * @Version 1.0
 */
@ApiModel(value = "课程查询对象", description = "课程查询对象封装")
@Data

public class CourseVo implements Serializable {
    @ApiModelProperty(value = "课程名称")
    private String title;
    @ApiModelProperty(value = "讲师id")
    private String teacherId;
    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;
    @ApiModelProperty(value = "二级类别id")
    private String subjectId;
    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;
    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;
    @ApiModelProperty(value = "价格排序")
    private String priceSort;

}
