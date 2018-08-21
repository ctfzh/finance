package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/21
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryTranStatusResVO extends PinganMchBaseResVO {
    //记账标记记账标志（1：登记挂账 2：支付 3：提现 4：清分5:下单预支付 6：确认并付款 7：退款 8：支付到平台 N:其他）
    @JSONField(name = "BookingFlag")
    private String BookingFlag;

    //交易状态（0：成功，1：失败，2：待确认, 5：待处理，6：处理中）
    @JSONField(name = "TranStatus")
    private String TranStatus;

    //交易金额
    @JSONField(name = "TranAmt")
    private String TranAmt;

    //交易日期
    @JSONField(name = "TranDate")
    private String TranDate;

    //转入子账户账号
    @JSONField(name = "InSubAcctNo")
    private String InSubAcctNo;

    //转出子账户账号
    @JSONField(name = "OutSubAcctNo")
    private String OutSubAcctNo;

    //失败信息
    @JSONField(name = "FailMsg")
    private String FailMsg;

}
