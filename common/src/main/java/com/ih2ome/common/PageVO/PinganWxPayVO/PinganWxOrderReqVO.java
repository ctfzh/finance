package com.ih2ome.common.PageVO.PinganWxPayVO;

import io.swagger.models.auth.In;

import java.util.Date;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 * 获取订单列表请求对象
 **/
public class PinganWxOrderReqVO {
    //分页编号，默认1
    private Integer page;
    //每页返回的数据条数，默认10，最多100
    private Integer pagesize;
    //开发者流水号
    private String out_no;
    //交易号（收单机构交易号，可为空）
    private String trade_no;
    //付款订单号
    private String ord_no;
    //支付方式标签
    private String pmt_tag;
    //交易方式1消费，2辙单（退款）
    private Integer ord_type;
    //订单状态（1交易成功，2待支付，4已取消，9等待用户输入密码确认）
    private Integer status;
    //订单开始日期，不传时间时返回最近90天记录
    private Date sdate;
    //订单结束日期，不传时间时返回最近90天记录
    private Date edate;

}
