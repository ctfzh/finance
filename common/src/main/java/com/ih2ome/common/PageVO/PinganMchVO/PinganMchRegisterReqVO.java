package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/09
 * email sky.li@ixiaoshuidi.com
 * 会员子账户开户请求对象
 **/
@Data
public class PinganMchRegisterReqVO extends PinganMchBaseReqVO {
    //功能标志 1.开户，3销户
    @JSONField(name = "FunctionFlag")
    private String FunctionFlag;
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;
    //交易网会员代码
    @JSONField(name = "TranNetMemberCode")
    private String TranNetMemberCode;
    //会员属性(00：默认)
    @JSONField(name = "MemberProperty")
    private String memberProperty;
    //用户昵称
    @JSONField(name = "UserNickname")
    private String UserNickname;
    //手机号码
    @JSONField(name = "Mobile")
    private String Mobile;
    //邮箱
    @JSONField(name = "Email")
    private String Email;
    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;


}
