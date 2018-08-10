package com.ih2ome.common.PageVO.PinganWxPayVO;

import lombok.Data;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class PinganWxPayListReqVO {

    //支付类型（2主扫，3被扫，4jsapi支付,5app支付）,可能含有多个条件组合。多个以小写逗号分开
    private String pmt_type;
}
