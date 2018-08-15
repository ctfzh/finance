package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/08/10
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchBaseReqVO {
    //交易流水号
    @JSONField(name = "CnsmrSeqNo")
    private String CnsmrSeqNo;
    //商户号
    @JSONField(name = "MrchCode")
    private String MrchCode = "4004";

}
