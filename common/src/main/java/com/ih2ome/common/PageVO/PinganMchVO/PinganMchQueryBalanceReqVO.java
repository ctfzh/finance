package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/20
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryBalanceReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号()
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //查询标志（2：普通会员子账号 3：功能子账号）
    @JSONField(name = "QueryFlag")
    private String QueryFlag;

    //页码
    @JSONField(name = "PageNum")
    private String PageNum;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;


}
