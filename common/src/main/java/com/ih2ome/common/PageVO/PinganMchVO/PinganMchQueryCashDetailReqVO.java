package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/11
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryCashDetailReqVO extends PinganMchBaseReqVO {
    //功能标志  1:当日，2：历史
    @JSONField(name = "FunctionFlag")
    private String FunctionFlag;

    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //查询标志 2：提现 3：清分
    @JSONField(name = "QueryFlag")
    private String QueryFlag;

    //开始日期
    @JSONField(name = "BeginDate")
    private String BeginDate;

    //结束日期
    @JSONField(name = "EndDate")
    private String EndDate;

    //页码
    @JSONField(name = "PageNum")
    private String PageNum;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;


}
