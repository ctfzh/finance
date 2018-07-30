package com.ih2ome.common.PageVO;

import lombok.Data;

import java.util.Date;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 下单接口返回对象
 **/
@Data
public class PinganWxPayOrderResVO {
    //收单机构名称
    private String pmt_name;
    //收单机构标签
    private String pmt_tag;
    //商户流水号（从1开始自增长不重复
    private Integer ord_mct_id;
    //门店流水号（从1开始自增长不重复）
    private String ord_shop_id;
    //订单号
    private String ord_no;
    //订单类型
    private Integer ord_type;
    //原始金额（以分为单位，没有小数点）
    private Integer original_amount;
    //折扣金额（以分为单位，没有小数点）
    private Integer discount_amount;
    //抹零金额（以分为单位，没有小数点）
    private Integer ignore_amount;
    //交易帐号（银行卡号、支付宝帐号、微信帐号等，某些收单机构没有此数据）
    private String trade_account;
    //收单机构交易号
    private String trade_no;
    //实际交易金额（以分为单位，没有小数点）
    private Integer trade_amount;
    //二维码字符串
    private String trade_qrcode;
    //付款完成时间（以收单机构为准）
    private Date trade_pay_time;
    //订单状态（1交易成功，2待支付，4已取消，9等待用户输入密码确认）
    private Integer status;
    //收单机构原始交易数据
    private String trade_result;
    //开发者流水号
    private String out_no;
    //公众号订单支付地址，如果为非公众号订单，此参数为空
    private String jsapi_pay_url;
    //公众号id
    private String appId;
    //时间戳
    private String timeStamp;
    //随机字符串
    private String nonceStr;
    //签名方式
    private String signType;
    //统一下单接口返回prepay_id参数值
    private String package_info;
    //签名
    private String paySign;
}
