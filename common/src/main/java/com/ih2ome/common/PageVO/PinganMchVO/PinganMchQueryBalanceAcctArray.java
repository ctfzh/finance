package com.ih2ome.common.PageVO.PinganMchVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryBalanceAcctArray {
    //子账号
    private String SubAcctNo;
    //子账户属性
    private String SubAcctProperty;
    //交易网会员代码
    private String TranNetMemberCode;
    //子账户名称
    private String SubAcctName;
    //账户可用余额
    private String AcctAvailBal;
    //可提现金额
    private String CashAmt;
    //维护日期
    private String MaintenanceDate;

}
