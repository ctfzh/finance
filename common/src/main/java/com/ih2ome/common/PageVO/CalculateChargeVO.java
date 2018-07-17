package com.ih2ome.common.PageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sky
 * create 2018/07/11
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class CalculateChargeVO {
    @ApiModelProperty(value = "手续费用")
    private Double payCharge;

    @ApiModelProperty(value = "总金额")
    private Double totalPayMoney;

    @ApiModelProperty(value = "初始金额")
    private Double initPayMoney;

    @ApiModelProperty(value = "入账金额")
    private Double enterPayMoney;

    @ApiModelProperty(value = "费用承担者")
    private String payAssume;
}
