package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/06
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchAccSupplyReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //收单渠道类型
    @JSONField(name = "AcquiringChannelType")
    private String AcquiringChannelType;

    //订单号
    @JSONField(name = "OrderNo")
    private String OrderNo;

    //金额
    @JSONField(name = "Amt")
    private String Amt;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
