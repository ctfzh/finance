package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/17
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryTransferInfoReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //原交易流水号
    @JSONField(name = "OldTranSeqNo")
    private String OldTranSeqNo;

    //交易日期
    @JSONField(name = "TranDate")
    private String TranDate;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
