package com.ih2ome.common.PageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 * saas微信下单请求数据封装
 **/
@Data
@ApiModel
public class SaasWxPayOrderReqVO {
    @ApiModelProperty("手续费用")
    @NotNull(message = "手续费用不能为空")
    private Double payCharge;

    @ApiModelProperty("入账金额")
    @NotNull(message = "入账金额不能为空")
    private Double enterPayMoney;

    @ApiModelProperty("总金额")
    @NotNull(message = "总金额不能为空")
    private Double totalMoney;
    
    @ApiModelProperty("主账号(房东)id")
    @NotNull(message = "主账号(房东)id不能为空")
    private String landlordId;

    @ApiModelProperty("房间地址")
    @NotNull(message = "房间地址不能为空")
    private String address;

    @ApiModelProperty("用户微信token")
    @NotNull(message = "用户微信token不能为空")
    private String token;

    @ApiModelProperty("支付类型")
    @NotNull(message = "支付类型不能为空")
    private Integer feeType;

    @ApiModelProperty("用户在子商户appid下的唯一标识")
    @NotNull(message = "openId不能为空")
    private String openId;

    @ApiModelProperty("pay_orders_id")
    @NotNull(message = "saas订单Id不能为空")
    private String payOrderId;
}
