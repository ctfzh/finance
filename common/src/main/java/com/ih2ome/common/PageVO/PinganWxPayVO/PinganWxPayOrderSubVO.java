package com.ih2ome.common.PageVO.PinganWxPayVO;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author Sky
 * create 2018/07/27
 * email sky.li@ixiaoshuidi.com
 * 充值----子订单对象
 **/
@Data
public class PinganWxPayOrderSubVO {
    //0-普通支付订单，1-子订单信息
    @JSONField(name = "SFJOrdertype")
    private String SFJOrdertype;
    //备注类型
    @JSONField(name = "remarktype")
    private String remarktype;
    //平台代码
    @JSONField(name = "plantCode")
    private String plantCode;
    //订单列表
    private List<PinganWxPayOrderSubDataVO> orderList;
}
