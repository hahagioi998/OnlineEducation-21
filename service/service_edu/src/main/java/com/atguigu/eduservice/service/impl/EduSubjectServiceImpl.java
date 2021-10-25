package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-10-14
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类

    @Override
    public void addSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in,SubjectData.class,new SubjectExcelListener(subjectService))
                    .sheet()
                    .doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getOneTwoSubject() {
        //1.查询所有的一级分类
        QueryWrapper<EduSubject>  wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //2.查询所有的二级分类
        QueryWrapper<EduSubject>  wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        //创建最终封装list
        List<OneSubject> oneFinalSubjectList = new ArrayList<>();
        //3.封装所有的一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            oneFinalSubjectList.add(oneSubject);

            //4.封装所有的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject tSubject = twoSubjectList.get(j);
                //判断二级分类的父id是不是一级分类的id
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    //把tSubject值复制到twoSubject里面，压入twoFinalSubjectList
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级分类下面所有的二级分类最终的数据压入一级分类
            oneSubject.setChildren(twoFinalSubjectList);
        }

        return oneFinalSubjectList;
    }
}
