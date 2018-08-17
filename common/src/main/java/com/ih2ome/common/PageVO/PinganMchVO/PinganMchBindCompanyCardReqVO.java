package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/17
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchBindCompanyCardReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;

    //提现账号
    @JSONField(name = "TakeCashAcctNo")
    private String TakeCashAcctNo;

    //鉴权金额
    @JSONField(name = "AuthAmt")
    private String AuthAmt;

    //币种
    @JSONField(name = "Ccy")
    private String Ccy = "RMB";

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;

}
