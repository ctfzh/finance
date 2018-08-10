package com.ih2ome.common.PageVO.PinganWxPayVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 充值---子订单信息对象
 **/
@Data
public class PinganWxPayOrderSubDataVO {
    //入账会员子账户
    private String SubAccNo;
    //0-冻结支付 1-普通支付
    private String PayModel;
    //手续费
    private String TranFee;
    //子订单金额
    private String subamount;
    //子订单号
    private String suborderId;
    //子订单信息
    private String object;
}
