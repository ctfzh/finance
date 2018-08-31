package com.ih2ome.common.PageVO.PinganMchVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/31
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchQueryReconciliationDocResVO extends PinganMchBaseResVO {
    //本次交易返回查询结果记录数
    @JSONField(name = "ResultNum")
    private String ResultNum;

    //交易信息数组
    @JSONField(name = "TranItemArray")
    private List<PinganMchQueryReconciliationDocArrVO> TranItemArray;

    //保留域
    @JSONField(name = "ReservedMsg")
    private String ReservedMsg;
}
