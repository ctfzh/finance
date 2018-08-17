package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/16
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchBindCardGetAmountReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;

    //会员名称
    @JSONField(name = "MemberName")
    private String MemberName;

    //会员证件类型（一般都是身份证。例如身份证，送1。）
    @JSONField(name = "MemberGlobalType")
    private String MemberGlobalType = "1";

    //会员证件号码
    @JSONField(name = "MemberGlobalId")
    private String MemberGlobalId;

    //会员账号
    @JSONField(name = "MemberAcctNo")
    private String MemberAcctNo;

    //银行类型
    @JSONField(name = "BankType")
    private String BankType;

    //开户行名称
    @JSONField(name = "AcctOpenBranchName")
    private String AcctOpenBranchName;

    //大小额行号
    @JSONField(name = "CnapsBranchId")
    private String CnapsBranchId;

    //超级网银行号
    @JSONField(name = "EiconBankBranchId")
    private String EiconBankBranchId;

    //手机号码
    @JSONField(name = "Mobile")
    private String Mobile;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
