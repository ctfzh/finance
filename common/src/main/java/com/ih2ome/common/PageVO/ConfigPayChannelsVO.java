package com.ih2ome.common.PageVO;

import cfca.sadk.org.bouncycastle.util.Integers;
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
    @ApiModelProperty(value = "微信通道展示")
    private Integer wxShow;
    @ApiModelProperty(value = "支付宝通道展示")
    private Integer aliShow;
    @ApiModelProperty(value = "银行卡通道展示")
    private Integer cardShow;
    @ApiModelProperty(value = "微信通道方式")
    private String wxType;
    @ApiModelProperty(value = "支付宝通道方式")
    private String aliType;
    @ApiModelProperty(value = "快捷支付方式")
    private String cardType;
    @ApiModelProperty(value = "费用承担方(renter:租客landlord:房东)")
    private String payAssume;
    @ApiModelProperty(value = "用户类型（0:普通用户，）")
    private Integer userType;
}
