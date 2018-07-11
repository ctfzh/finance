package com.ih2ome.common.PageVO;

import com.ih2ome.common.enums.ConfigPayAssumeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class ConfigPaymentsVO {
    @ApiModelProperty(value = "费用承担方(renter:租客landlord:房东)")
    private String payAssume;

    @ApiModelProperty(value = "费用信息")
    private String chargeInfo;
}
