package com.test.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1 设置写入文件夹地址和excel文件名称
        String fileName = "E:\\write.xlsx";
        //2 调用easyexcel里面的方法实现写操作
        //write方法里面两个参数 第一个参数文件路径名称 第二个参数实体类class
       // EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
        //实现excel读操作
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSname("lucy" + i);
            demoData.setSno(i);
            list.add(demoData);
        }
        return list;
    }
}
