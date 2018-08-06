package com.ih2ome.common.support;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sky
 * create 2018/07/04
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class ExcelData implements Serializable {
    private static final long serialVersionUID = 4444017239100620999L;


    private List<String> titles;

    private List<List<Object>> rows;


    private String name;
}
