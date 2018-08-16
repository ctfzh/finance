package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/16
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryBindInfoReqVO extends PinganMchBaseReqVO {
    //查询标志 1：全部会员 2：单个会员
    @JSONField(name = "QueryFlag")
    private String QueryFlag;

    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //子账户账号(若QueryFlag为2时，子账户账号必输)
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //页码
    @JSONField(name = "PageNum")
    private String PageNum;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;


}
