package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author Sky
 * create 2018/09/06
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchAccRegulationResVO extends PinganMchBaseResVO {
    //前置流水号
    @JSONField(name = "FrontSeqNo")
    private String FrontSeqNo;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;

}
