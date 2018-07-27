package com.ih2ome.common.PageVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 下单接口请求对象
 **/
@Data
public class PinganWxPayOrderReqVO {
    //开发者流水号,确认同一门店唯一
    private String out_no;
    //付款方式编号
    private String pmt_tag;
    //商户自定义付款方式名称，当pmt_tag为Diy时，pmt_name不能为空
    private String pmt_name;
    //订单名称（描述）
    private String ord_name;
    //原始交易金额（以分为单位，没有小数点）
    private Integer original_amount;
    //折扣金额（以分为单位，没有小数点）
    private Integer discount_amount;
    //抹零金额（以分为单位，没有小数点）
    private Integer ignore_amount;
    //实际交易金额（以分为单位，没有小数点）
    private Integer trade_amount;
    //交易帐号（收单机构交易的银行卡号，手机号等，可为空）
    private String trade_account;
    //交易号（收单机构交易号，可为空）
    private String trade_no;
    //订单备注
    private String remark;
    //订单标记，订单附加数据
    private String tag;
    //异步通知地址
    private String notify_url;
    //订单交易内部特殊指令（空中分账子订单信息）
    private String ord_cmd;
    //公众号/服务窗支付必填参数，支付结果跳转地址
    private String jump_url;

}
