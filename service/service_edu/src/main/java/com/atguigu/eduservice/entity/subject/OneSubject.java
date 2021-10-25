package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OneSubject
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-15 15:07
 * @Version 1.0
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children;
}
