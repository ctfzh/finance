package com.ih2ome.common.PageVO.PinganWxPayVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 查询订单明细返回对象
 **/
@Data
public class PinganWxOrderViewResVO {
    //收单机构名称（支付方式标签名称）
    private String pmt_name;
    //收单机构标签（支付方式标签）
    private String pmt_tag;
    //商户流水号（从1开始自增长不重复）
    private Integer ord_mct_id;
    //门店流水号（从1开始自增长不重复）
    private Integer ord_shop_id;
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
    //支付方式名称
    private String diy_pmt_name;
    //收银员编号
    private String scr_id;
    //收银员真实姓名
    private String scr_true_name;
    //收银员用户名
    private String scr_user_name;
    //订单描述
    private String ord_name;
    //下单时间
    private Date add_time;
    //交易时间
    private Date trade_time;
    //交易号2
    private String trade_no2;
    //交易自定义代码
    private String trade_code;
    //备注（分账信息）
    private String remark;
    //原始订单号
    private String original_ord_no;
    //币种
    private String currency;
    //币种符号
    private String currency_sign;
    //终端号
    private String tml_no;
    //终端名称
    private String tml_name;
    //门店编号
    private String shop_no;
    //门店简称
    private String shop_name;
    //门店全称
    private String shop_full_name;
    //相关的退款记录
    private String related_order;
    //相关的应用
    private String app_list;

    @Override
    public String toString() {
        return "PinganWxOrderViewResVO{" +
                "pmt_name='" + pmt_name + '\'' +
                ", pmt_tag='" + pmt_tag + '\'' +
                ", ord_mct_id=" + ord_mct_id +
                ", ord_shop_id=" + ord_shop_id +
                ", ord_no='" + ord_no + '\'' +
                ", ord_type=" + ord_type +
                ", original_amount=" + original_amount +
                ", discount_amount=" + discount_amount +
                ", ignore_amount=" + ignore_amount +
                ", trade_account='" + trade_account + '\'' +
                ", trade_no='" + trade_no + '\'' +
                ", trade_amount=" + trade_amount +
                ", trade_qrcode='" + trade_qrcode + '\'' +
                ", trade_pay_time=" + trade_pay_time +
                ", status=" + status +
                ", trade_result='" + trade_result + '\'' +
                ", out_no='" + out_no + '\'' +
                ", diy_pmt_name='" + diy_pmt_name + '\'' +
                ", scr_id='" + scr_id + '\'' +
                ", scr_true_name='" + scr_true_name + '\'' +
                ", scr_user_name='" + scr_user_name + '\'' +
                ", ord_name='" + ord_name + '\'' +
                ", add_time=" + add_time +
                ", trade_time=" + trade_time +
                ", trade_no2='" + trade_no2 + '\'' +
                ", trade_code='" + trade_code + '\'' +
                ", remark='" + remark + '\'' +
                ", original_ord_no='" + original_ord_no + '\'' +
                ", currency='" + currency + '\'' +
                ", currency_sign='" + currency_sign + '\'' +
                ", tml_no='" + tml_no + '\'' +
                ", tml_name='" + tml_name + '\'' +
                ", shop_no='" + shop_no + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_full_name='" + shop_full_name + '\'' +
                ", related_order='" + related_order + '\'' +
                ", app_list='" + app_list + '\'' +
                '}';
    }
}
