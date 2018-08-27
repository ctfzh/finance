package com.ih2ome.service.impl;

import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import com.ih2ome.model.caspain.ConfigPaymentsUser;
import com.ih2ome.service.ConfigPaymentsChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sky
 * create 2018/08/24
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class ConfigPaymentsChannelServiceImpl implements ConfigPaymentsChannelService {

    @Autowired
    private ConfigPaymentsChannelDao configPaymentsChannelDao;

    /**
     * 根据config_payments_user配置查询该用户的支付通道
     *
     * @param configPaymentsUser
     * @return
     */
    @Override
    public List<ConfigPaymentsChannel> findPaymentsChannels(ConfigPaymentsUser configPaymentsUser) {
        List<String> channelNames = new ArrayList<String>();
        String wxType = configPaymentsUser.getWxType();
        String aliType = configPaymentsUser.getAliType();
        String cardType = configPaymentsUser.getCardType();
        if (StringUtils.isNotBlank(wxType)) {
            channelNames.add(wxType);
        }
        if (StringUtils.isNotBlank(aliType)) {
            channelNames.add(aliType);
        }
        if (StringUtils.isNotBlank(cardType)) {
            channelNames.add(cardType);
        }
        //微信类型选择
        Example example = new Example(ConfigPaymentsChannel.class);
        example.createCriteria().andEqualTo("isDelete", 0).andIn("payChannel", channelNames);
        List<ConfigPaymentsChannel> paymentsChannels = configPaymentsChannelDao.selectByExample(example);
        return paymentsChannels;
    }

    /**
     * 根据config_payments_user配置查询不是该用户设置的支付通道
     *
     * @param configPaymentsUser
     * @return
     */
    @Override
    public List<ConfigPaymentsChannel> findNotPaymentsChannels(ConfigPaymentsUser configPaymentsUser) {
        List<String> channelNames = new ArrayList<String>();
        String wxType = configPaymentsUser.getWxType();
        String aliType = configPaymentsUser.getAliType();
        String cardType = configPaymentsUser.getCardType();
        if (StringUtils.isNotBlank(wxType)) {
            channelNames.add(wxType);
        }
        if (StringUtils.isNotBlank(aliType)) {
            channelNames.add(aliType);
        }
        if (StringUtils.isNotBlank(cardType)) {
            channelNames.add(cardType);
        }
        //微信类型选择
        Example example = new Example(ConfigPaymentsChannel.class);
        example.createCriteria().andEqualTo("isDelete", 0).andNotIn("payChannel", channelNames);
        List<ConfigPaymentsChannel> paymentsChannels = configPaymentsChannelDao.selectByExample(example);
        return paymentsChannels;
    }
}
