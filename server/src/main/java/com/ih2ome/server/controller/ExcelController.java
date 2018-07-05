package com.ih2ome.server.controller;

import com.ih2ome.common.support.ExcelData;
import com.ih2ome.common.utils.ExcelUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sky
 * create 2018/07/04
 * email sky.li@ixiaoshuidi.com
 **/
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("用户信息数据");
        //添加表头
        List<String> titles = new ArrayList();
        //for(String title: excelInfo.getNames())
        titles.add("title1");
        titles.add("title2");
        titles.add("title3");
        data.setTitles(titles);
        //添加列
        List<List<Object>> rows = new ArrayList();
        List<Object> row1 = new ArrayList<>();
        row1.add("张三");
        row1.add("李四");
        row1.add("赵六");
        List<Object> row2 = new ArrayList<>();
        row2.add("a");
        row2.add("b");
        row2.add("c");
        List<Object> row3 = new ArrayList<>();
        row3.add("a");
        row3.add("b");
        row3.add("c");
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        data.setRows(rows);
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = fdate.format(new Date()) + ".xlsx";
        ExcelUtils.exportExcel(response, fileName, data);
    }
}
