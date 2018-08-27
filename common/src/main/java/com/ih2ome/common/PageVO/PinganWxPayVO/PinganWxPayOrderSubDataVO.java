package com.ih2ome.common.PageVO.PinganWxPayVO;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(name = "SubAccNo")
    private String SubAccNo;
    //0-冻结支付 1-普通支付
    @JSONField(name = "PayModel")
    private String PayModel;
    //手续费
    @JSONField(name = "TranFee")
    private String TranFee;
    //子订单金额
    @JSONField(name = "subamount")
    private String subamount;
    //子订单号
    @JSONField(name = "suborderId")
    private String suborderId;
    //子订单信息
    @JSONField(name = "object")
    private String object;
}
