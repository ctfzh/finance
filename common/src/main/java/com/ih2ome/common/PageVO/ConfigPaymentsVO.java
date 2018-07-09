package com.ih2ome.common.PageVO;

import com.ih2ome.common.enums.ConfigPayAssumeEnum;
import lombok.Data;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
@Data
public class ConfigPaymentsVO {
    //承担者(renter:租客，landlord:房东)
    private String payAssume;
    //费率信息(微信费率,支付宝费率,快捷支付费率)
    private String chargeInfo;
}
