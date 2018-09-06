package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/06
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchChargeDetailResVO extends PinganMchBaseResVO {
    //交易状态
    @JSONField(name = "TranStatus")
    private String TranStatus;

    //（0：成功，1：失败，2：异常,3:冲正，5：待处理）
    @JSONField(name = "TranAmt")
    private String TranAmt;

    //佣金费
    @JSONField(name = "CommissionAmt")
    private String CommissionAmt;

    //支付方式
    @JSONField(name = "PayMode")
    private String PayMode;

    //交易日期
    @JSONField(name = "TranDate")
    private String TranDate;

    //交易时间
    @JSONField(name = "TranTime")
    private String TranTime;

    //订单转入子账户
    @JSONField(name = "OrderInSubAcctNo")
    private String OrderInSubAcctNo;

    //订单转入子账户名称
    @JSONField(name = "OrderInSubAcctName")
    private String OrderInSubAcctName;

    //订单实际转入子账户
    @JSONField(name = "OrderActInSubAcctNo")
    private String OrderActInSubAcctNo;

    //订单实际子账户名称
    @JSONField(name = "OrderActInSubAcctName")
    private String OrderActInSubAcctName;

    //前置流水号
    @JSONField(name = "FrontSeqNo")
    private String FrontSeqNo;

    //交易描述
    @JSONField(name = "TranDesc")
    private String TranDesc;
}
