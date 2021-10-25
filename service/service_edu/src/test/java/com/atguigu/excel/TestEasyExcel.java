package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestEasyExcel
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-14 22:57
 * @Version 1.0
 */
public class TestEasyExcel {
    @Test
    public void write(){
        String fileName = "D:\\guli_log\\student.xlsx";
        EasyExcel.write(fileName,DemoData.class)
                .sheet("学生列表").
                doWrite(data());
    }

    @Test
    public void read(){
        String fileName = "D:\\guli_log\\student.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }

}
