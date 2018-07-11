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
    //�е���(renter:��ͣ�landlord:����)
    @ApiModelProperty(value = "�������óе���(renter:��ͣ�landlord:����)")
    private String payAssume;
    //������Ϣ(΢�ŷ���,֧��������,���֧������)
    @ApiModelProperty(value = "������Ϣ")
    private String chargeInfo;
}
