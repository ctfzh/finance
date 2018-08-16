package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import groovy.transform.Field;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/16
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchBindPersonalCardReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;

    //会员账号
    @JSONField(name = "MemberAcctNo")
    private String MemberAcctNo;

    //短信验证码
    @JSONField(name = "MessageCheckCode")
    private String MessageCheckCode;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
