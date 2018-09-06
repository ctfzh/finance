package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/06
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchAccSupplyResVO extends PinganMchBaseResVO {
    //前置流水号
    @JSONField(name = "FrontSeqNo")
    private String FrontSeqNo;

    //子账户账号
    @JSONField(name = "SubAcctNo")
    private String SubAcctNo;

    //备注
    @JSONField(name = "Remark")
    private String Remark;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
