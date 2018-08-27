package com.ih2ome.service;

import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import com.ih2ome.model.caspain.ConfigPaymentsUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Sky
 * create 2018/08/24
 * email sky.li@ixiaoshuidi.com
 **/
public interface ConfigPaymentsChannelService {
    /**
     * 根据config_payments_user配置查询该用户的支付通道。
     *
     * @param configPaymentsUser
     * @return
     */
    List<ConfigPaymentsChannel> findPaymentsChannels(ConfigPaymentsUser configPaymentsUser);

    /**
     * 根据config_payments_user配置查询不是该用户设置的支付通道
     *
     * @param configPaymentsUser
     * @return
     */
    List<ConfigPaymentsChannel> findNotPaymentsChannels(ConfigPaymentsUser configPaymentsUser);
}
