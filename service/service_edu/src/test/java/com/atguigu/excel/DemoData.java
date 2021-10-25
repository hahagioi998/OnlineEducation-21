package com.atguigu.excel;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName ExcelDemo
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-14 22:54
 * @Version 1.0
 */
//设置表头和添加的数据字段
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty("学生编号")
    private int sno;

    //设置表头名称
    @ExcelProperty("学生姓名")
    private String sname;

}
