package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/31
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryReconciliationDocReqVO extends PinganMchBaseReqVO {
    //资金汇总账号
    @JSONField(name = "FundSummaryAcctNo")
    private String FundSummaryAcctNo;

    //文件类型
    @JSONField(name = "FileType")
    private String FileType;

    //文件日期
    @JSONField(name = "FileDate")
    private String FileDate;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
