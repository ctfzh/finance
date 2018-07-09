package com.ih2ome.service.impl;

import com.ih2ome.common.PageVO.ConfigPaymentsVO;
import com.ih2ome.common.enums.ConfigPayAssumeEnum;
import com.ih2ome.common.enums.ConfigPayChannelEnum;
import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.dao.caspain.ConfigPaymentsSetDao;
import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import com.ih2ome.model.caspain.ConfigPaymentsSet;
import com.ih2ome.service.ConfigPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
@Service
public class ConfigPaymentsServiceImpl implements ConfigPaymentsService {
    @Autowired
    private ConfigPaymentsChannelDao configPaymentsChannelDao;
    @Autowired
    private ConfigPaymentsSetDao configPaymentsSetDao;


    @Override
    public ConfigPaymentsVO findConfigPaymentsInfo(Integer userId) throws Exception {
        ConfigPaymentsVO configPaymentsVO = new ConfigPaymentsVO();
        Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
        configPaymentsSetExample.createCriteria().andEqualTo("createdById", userId).andEqualTo("isDelete", 0);
        List<ConfigPaymentsSet> configPaymentsSets = configPaymentsSetDao.selectByExample(configPaymentsSetExample);
        StringBuilder chargeInfo = new StringBuilder();
        String assumePerson = null;
        //判断是否是中建等使用平安银行的客户
        if (!userId.equals(2788)) {
            Example configPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
            Double pinganpayWxCharge = 0.0;
            if (configPaymentsSets.size() == 0) {
                configPaymentsChannelExample.createCriteria().andLike("payChannel", "%pinganpay%").andEqualTo("isDelete", 0);
                configPaymentsChannelExample.setOrderByClause("created_at desc");
                List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelDao.selectByExample(configPaymentsChannelExample);
                for (ConfigPaymentsChannel configPaymentsChannel : configPaymentsChannels) {
                    if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.PINGANPAY_WX.name())) {
                        pinganpayWxCharge = configPaymentsChannel.getDefaultCharge() * 10;
                    }
                }
                assumePerson = ConfigPayAssumeEnum.RENTER.name();
            } else {
                for (ConfigPaymentsSet configPaymentsSet : configPaymentsSets) {
                    Integer paymentsChannelId = configPaymentsSet.getPaymentsChannelId();
                    ConfigPaymentsChannel configPaymentsChannel = configPaymentsChannelDao.selectByPrimaryKey(paymentsChannelId);
                    if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.PINGANPAY_WX.name())) {
                        pinganpayWxCharge = configPaymentsChannel.getDefaultCharge() * 10;
                    }
                    assumePerson = configPaymentsSet.getAssumePerson();
                }
            }
            configPaymentsVO.setChargeInfo(chargeInfo.append("手续费用为").append(pinganpayWxCharge).append("‰").toString());
            configPaymentsVO.setPayAssume(assumePerson);
//            configPaymentsVO.setChargeInfo();
        } else {
            //普通用户(使用通联微信,支付宝,连连快捷支付)

        }
        return configPaymentsVO;
    }
}
