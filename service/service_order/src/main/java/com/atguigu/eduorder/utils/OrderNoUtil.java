package com.atguigu.eduorder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName OrderNoUtil
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-27 15:54
 * @Version 1.0
 */
public class OrderNoUtil {

    public static String getOrder(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
}
