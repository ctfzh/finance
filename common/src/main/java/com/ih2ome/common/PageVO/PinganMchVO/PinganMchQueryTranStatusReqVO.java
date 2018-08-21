package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/21
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryTranStatusReqVO extends PinganMchBaseReqVO {

    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //功能标志(2：会员间交易 3：提现，4：充值)
    @JSONField(name = "FunctionFlag")
    private String FunctionFlag;

    //交易网流水号
    @JSONField(name = "TranNetSeqNo")
    private String TranNetSeqNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //交易日期
    @JSONField(name = "TranDate")
    private String TranDate;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
