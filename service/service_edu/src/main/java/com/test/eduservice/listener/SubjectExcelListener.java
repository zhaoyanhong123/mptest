package com.test.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.eduservice.entity.EduSubject;
import com.test.eduservice.entity.excel.SubjectData;
import com.test.eduservice.service.EduSubjectService;
import com.test.servicebase.exceptionhandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;

    public SubjectExcelListener() {

    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //读取excel内容，一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        //一行一行读取，每次读取两个值，第一个值一级分类，第二个值二级分类
        EduSubject eduSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (eduSubject == null) {//没有相同的一级分类，进行添加
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(eduSubject);
        }
        String pid = eduSubject.getId();
        EduSubject twoeduSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (twoeduSubject == null) {//没有相同的一级分类，进行添加
            twoeduSubject = new EduSubject();
            twoeduSubject.setParentId(pid);
            twoeduSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoeduSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
