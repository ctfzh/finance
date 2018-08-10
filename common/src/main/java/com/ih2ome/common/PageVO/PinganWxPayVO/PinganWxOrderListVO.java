package com.ih2ome.common.PageVO.PinganWxPayVO;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 * 订单列表list数据
 **/
@Data
public class PinganWxOrderListVO {
    //收单机构名称（支付方式标签名称）
    private String pmt_name;
    //收单机构标签（支付方式标签）
    private String pmt_tag;
    //订单名称（描述）
    private String ord_name;
    //订单流水号
    private Integer ord_id;
    //订单号
    private String ord_no;
    //订单类型（1交易，2辙单【退款】)
    private Integer ord_type;
    //下单时间
    private Date add_time;
    //交易帐号（银行卡号、支付宝帐号、微信帐号等，某些收单机构没有此数据）
    private String trade_account;
    //实际交易金额（以分为单位，没有小数点）
    private Integer trade_amount;
    //开始交易时间
    private Date trade_time;
    //收单机构返回的交易号（某些收单机构没有交易号）
    private String trade_no;
    //二维码（用户主动扫码的才会有数据）
    private String trade_qrcode;
    //订单付款成功时间（支付方式返回的原始数据，可能会小于下单时间）
    private Date trade_pay_time;
    //订单备注(分账信息)
    private String remark;
    //原始金额（以分为单位，没有小数点）
    private Integer original_amount;
    //折扣金额（以分为单位，没有小数点）
    private Integer discount_amount;
    //抹零金额（以分为单位，没有小数点）
    private Integer ignore_amount;
    //收单机构原始交易数据，如果返回非标准json数据，请自行转换
    private String trade_result;
    //开发者流水号
    private String out_no;
    //自定义支付方式名称
    private String diy_pmt_name;
    //商户流水号（从1开始自增长不重复）
    private Integer ord_mct_id;
    //门店流水号（从1开始自增长不重复）
    private Integer ord_shop_id;
    //币种
    private String currency;
    //币种符号
    private String currency_sign;
    //收银员编号
    private String scr_id;
    //收银员真实姓名
    private String scr_true_name;
    //终端号
    private String tml_no;
    //门店编号
    private String shop_no;
    //门店简称
    private String shop_name;
    //门店全称
    private String shop_full_name;
    //订单状态（1交易成功，2待支付，9待输入密码，4已取消）
    private String status;

    @Override
    public String toString() {
        return "PinganWxOrderListVO{" +
                "pmt_name='" + pmt_name + '\'' +
                ", pmt_tag='" + pmt_tag + '\'' +
                ", ord_name='" + ord_name + '\'' +
                ", ord_id=" + ord_id +
                ", ord_no='" + ord_no + '\'' +
                ", ord_type=" + ord_type +
                ", add_time=" + add_time +
                ", trade_account='" + trade_account + '\'' +
                ", trade_amount=" + trade_amount +
                ", trade_time=" + trade_time +
                ", trade_no='" + trade_no + '\'' +
                ", trade_qrcode='" + trade_qrcode + '\'' +
                ", trade_pay_time=" + trade_pay_time +
                ", remark='" + remark + '\'' +
                ", original_amount=" + original_amount +
                ", discount_amount=" + discount_amount +
                ", ignore_amount=" + ignore_amount +
                ", trade_result='" + trade_result + '\'' +
                ", out_no='" + out_no + '\'' +
                ", diy_pmt_name='" + diy_pmt_name + '\'' +
                ", ord_mct_id=" + ord_mct_id +
                ", ord_shop_id=" + ord_shop_id +
                ", currency='" + currency + '\'' +
                ", currency_sign='" + currency_sign + '\'' +
                ", scr_id='" + scr_id + '\'' +
                ", scr_true_name='" + scr_true_name + '\'' +
                ", tml_no='" + tml_no + '\'' +
                ", shop_no='" + shop_no + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_full_name='" + shop_full_name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
