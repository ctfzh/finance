package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/10
 * email sky.li@ixiaoshuidi.com
 * 维护会员绑定提现账户联行号 请求参数
 **/
@Data
public class PinganMchMntAccountReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //会员绑定账号
    @JSONField(name = "MemberBindAcctNo")
    private String MemberBindAcctNo;

    //开户行名称
    @JSONField(name = "AcctOpenBranchName")
    private String AcctOpenBranchName;

    //大小额行号
    @JSONField(name = "CnapsBranchId")
    private String CnapsBranchId;

    //超级网银行号
    @JSONField(name = "EiconBankBranchId")
    private String EiconBankBranchId;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;

}
