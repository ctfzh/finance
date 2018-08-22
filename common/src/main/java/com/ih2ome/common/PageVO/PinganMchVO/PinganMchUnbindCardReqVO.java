package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/22
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchUnbindCardReqVO extends PinganMchBaseReqVO {
    //功能标志(1:解绑)
    @JSONField(name = "FunctionFlag")
    private String FunctionFlag;

    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //会员账号（提现账号）
    @JSONField(name = "MemberAcctNo")
    private String MemberAcctNo;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;


}
