package com.test.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty("学生编号")//设置表头
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String sname;
}
