package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/04
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchAccRegulationReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //收单渠道类型
    @JSONField(name = "AcquiringChannelType")
    private String AcquiringChannelType;

    //订单号
    @JSONField(name = "OrderNo")
    private String OrderNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;

    //子账户名称
    @JSONField(name = "SubAcctName")
    private String SubAcctName;

    //金额
    @JSONField(name = "Amt")
    private String Amt;

    //币种
    @JSONField(name = "Ccy")
    private String Ccy;

    //备注
    @JSONField(name = "Remark")
    private String Remark;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;


}
