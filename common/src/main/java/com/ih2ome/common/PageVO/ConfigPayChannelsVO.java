package com.ih2ome.common.PageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sky
 * create 2018/07/17
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class ConfigPayChannelsVO {
    @ApiModelProperty(value = "微信手续费用百分比")
    private Double wxPercent;
    @ApiModelProperty(value = "支付宝手续费用百分比")
    private Double aliPercent;
    @ApiModelProperty(value = "银行卡手续费用百分比")
    private Double cardPercent;
    @ApiModelProperty(value = "费用承担方(renter:租客landlord:房东)")
    private String payAssume;
    @ApiModelProperty(value = "是否使用平安微信支付（0:false ,1:true）")
    private Integer usePaPay;
}
