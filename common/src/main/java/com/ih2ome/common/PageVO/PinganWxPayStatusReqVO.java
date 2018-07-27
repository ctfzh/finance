package com.ih2ome.common.PageVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 查询订单支付状态请求对象
 **/
@Data
public class PinganWxPayStatusReqVO {
    //订单号，开发者流水号必须填写一个
    private String ord_no;
    //订单号，开发者流水号必须填写一个
    private String out_no;

}
