package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ChapterVo
 * @Description TODO
 * @Author lishan
 * @DATE 2021-10-16 10:22
 * @Version 1.0
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children;
}
