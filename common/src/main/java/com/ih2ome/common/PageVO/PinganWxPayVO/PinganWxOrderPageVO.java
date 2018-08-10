package com.ih2ome.common.PageVO.PinganWxPayVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 * 订单列表page数据
 **/
@Data
public class PinganWxOrderPageVO {
    //记录总数
    private String totalnum;
    //总页数
    private String totalpage;
    //每显记录数
    private String pagesize;
    //当前页数
    private String page;
}
