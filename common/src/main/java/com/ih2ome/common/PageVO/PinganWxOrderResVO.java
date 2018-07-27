package com.ih2ome.common.PageVO;

import lombok.Data;

import java.util.List;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 * 获取订单列表返回对象
 **/
@Data
public class PinganWxOrderResVO {
    //订单列表page数据
    private PinganWxOrderPageVO pages;

    //订单列表list数据
    private List<PinganWxOrderListVO> list;
}
