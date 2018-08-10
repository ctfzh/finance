package com.ih2ome.common.PageVO.PinganMchVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/08/10
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganMchBaseResVO {
    //返回码
    private String TxnReturnCode;
    //返回信息
    private String TxnReturnMsg;
    //交易流水号
    private String CnsmrSeqNo;
}
