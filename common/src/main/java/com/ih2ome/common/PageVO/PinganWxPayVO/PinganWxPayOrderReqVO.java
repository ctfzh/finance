package com.ih2ome.common.PageVO.PinganWxPayVO;

import com.alibaba.fastjson.annotation.JSONField;
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
    //分账数据
    private String cmd;
    //异步通知地址
    private String notify_url;

    //微信分配的子商户公众账号ID
    private String sub_appid;

    //用户在子商户appid下的唯一标识
    private String sub_openid;

    //JSAPI返回必填值为1
    @JSONField(name = "JSAPI")
    private String JSAPI;

}
