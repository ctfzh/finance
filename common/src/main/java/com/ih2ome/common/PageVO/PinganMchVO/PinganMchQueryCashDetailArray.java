package com.ih2ome.common.PageVO.PinganMchVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/09/11
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryCashDetailArray {
    //01:提现 02:清分
    private String BookingFlag;

    //交易状态：0：成功
    private String TranStatus;

    //记账说明
    private String BookingMsg;

    //交易网会员代码
    private String TranNetMemberCode;

    //子账户账号
    private String SubAcctNo;

    //子账户名称
    private String SubAcctName;

    //交易金额
    private String TranAmt;

    //手续费
    private String Commission;

    //交易日期
    private String TranDate;

    //交易时间
    private String TranTime;

    //前置流水号
    private String FrontSeqNo;

    //备注
    private String Remark;

}
