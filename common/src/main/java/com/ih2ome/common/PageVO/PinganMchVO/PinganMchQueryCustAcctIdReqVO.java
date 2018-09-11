package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/10
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryCustAcctIdReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;

}
