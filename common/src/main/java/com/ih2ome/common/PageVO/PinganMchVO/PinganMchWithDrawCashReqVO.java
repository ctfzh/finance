package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchWithDrawCashReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;

    //子账户名称
    @JSONField(name = "SubAcctName")
    private String SubAcctName;

    //提现账号
    @JSONField(name = "TakeCashAcctNo")
    private String TakeCashAcctNo;

    //提现账户名称
    @JSONField(name = "TakeCashAcctName")
    private String TakeCashAcctName;

    //币种
    @JSONField(name = "Ccy")
    private String Ccy = "RMB";

    //可提现金额
    @JSONField(name = "CashAmt")
    private String CashAmt;

    //提现手续费
    @JSONField(name = "TakeCashCommission")
    private String TakeCashCommission;

    //短信指令号
    @JSONField(name = "MessageOrderNo")
    private String MessageOrderNo;

    //短信验证码
    @JSONField(name = "MessageCheckCode")
    private String MessageCheckCode;

    //备注
    @JSONField(name = "Remark")
    private String Remark;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;

    //网银签名
    @JSONField(name = "WebSign")
    private String WebSign;

}
