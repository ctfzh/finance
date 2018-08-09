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
    @ApiModelProperty(value = "费用承担方(renter:租客landlord:房东)")
    private String payAssume;
    @ApiModelProperty(value = "是否使用平安微信支付（0:false ,其他:true）")
    private Integer usePaPay;
}
