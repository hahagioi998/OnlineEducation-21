package com.atguigu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-14 23:19
 * @Version 1.0
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    //一行一行读取excel内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("***"+data);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头内:"+headMap);
    }

    //读取完成之后执行的方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
