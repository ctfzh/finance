package com.ih2ome.service.impl;

import com.ih2ome.common.PageVO.CalculateChargeVO;
import com.ih2ome.common.PageVO.ConfigPaymentsVO;
import com.ih2ome.common.enums.ConfigPayAssumeEnum;
import com.ih2ome.common.enums.ConfigPayChannelEnum;
import com.ih2ome.common.enums.ConfigPayWayEnum;
import com.ih2ome.common.utils.ConstUtils;
import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.dao.caspain.ConfigPaymentsSetDao;
import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import com.ih2ome.model.caspain.ConfigPaymentsSet;
import com.ih2ome.service.ConfigPaymentsService;
import org.apache.ibatis.annotations.Case;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class ConfigPaymentsServiceImpl implements ConfigPaymentsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPaymentsServiceImpl.class);

    //使用平安支付的用户信息
    @Value("${bigCustomersInfo}")
    private String bigCustomersInfo;

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
        //判断是否为中建等大客户
        if (bigCustomersInfo.contains(userId.toString())) {
            Example configPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
            Double pinganpayWxCharge = 0.0;
            if (configPaymentsSets.size() == 0) {
                configPaymentsChannelExample.createCriteria().andLike("payChannel", "%pinganpay%").andEqualTo("isDelete", 0);
                configPaymentsChannelExample.setOrderByClause("created_at desc");
                List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelDao.selectByExample(configPaymentsChannelExample);
                for (ConfigPaymentsChannel configPaymentsChannel : configPaymentsChannels) {
                    if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.PINGANPAY_WX.getName())) {
                        pinganpayWxCharge = configPaymentsChannel.getDefaultCharge() * 10;
                    }
                }
                assumePerson = ConfigPayAssumeEnum.RENTER.getName();
            } else {
                for (ConfigPaymentsSet configPaymentsSet : configPaymentsSets) {
                    Integer paymentsChannelId = configPaymentsSet.getPaymentsChannelId();
                    ConfigPaymentsChannel configPaymentsChannel = configPaymentsChannelDao.selectByPrimaryKey(paymentsChannelId);
                    if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.PINGANPAY_WX.getName())) {
                        pinganpayWxCharge = configPaymentsChannel.getDefaultCharge() * 10;
                    }
                    assumePerson = configPaymentsSet.getAssumePerson();
                }
            }
            if (assumePerson.equals(ConfigPayAssumeEnum.RENTER.getName())) {
                configPaymentsVO.setChargeInfo(chargeInfo.append("手续费用为").append(pinganpayWxCharge).append("‰").append(",小数部分按1元计").toString());
            } else {
                configPaymentsVO.setChargeInfo(chargeInfo.append("手续费用为").append(pinganpayWxCharge).append("‰").toString());
            }
            configPaymentsVO.setPayAssume(assumePerson);
        } else {
            //普通客户
            Example configPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
            Double allianpayWxCharge = 0.0;
            Double alipayCharge = 0.0;
            Double llianpayCardCharge = 0.0;
            if (configPaymentsSets.size() == 0) {
                //ͨ通联微信支付
                configPaymentsChannelExample.createCriteria().andEqualTo("payChannel", "allianpay_wx").andEqualTo("isDelete", 0);
                ConfigPaymentsChannel configPaymentsChannelWx = configPaymentsChannelDao.selectOneByExample(configPaymentsChannelExample);
                allianpayWxCharge = configPaymentsChannelWx.getDefaultCharge();
                configPaymentsChannelExample.clear();
                //支付宝支付
                configPaymentsChannelExample.createCriteria().andEqualTo("payChannel", "alipay").andEqualTo("isDelete", 0);
                ConfigPaymentsChannel configPaymentsChannelAli = configPaymentsChannelDao.selectOneByExample(configPaymentsChannelExample);
                alipayCharge = configPaymentsChannelAli.getDefaultCharge();
                configPaymentsChannelExample.clear();
                //快捷支付(0.0)

                //费用承担方
                assumePerson = ConfigPayAssumeEnum.RENTER.getName();
            } else {
                for (ConfigPaymentsSet configPaymentsSet : configPaymentsSets) {
                    Integer paymentsChannelId = configPaymentsSet.getPaymentsChannelId();
                    ConfigPaymentsChannel configPaymentsChannel = configPaymentsChannelDao.selectByPrimaryKey(paymentsChannelId);
                    if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.ALLIANPAY_WX.getName())) {
                        allianpayWxCharge = configPaymentsSet.getServiceCharge();
                    } else if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.ALIPAY.getName())) {
                        alipayCharge = configPaymentsSet.getServiceCharge();
                    } else if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.LLIANPAY_CARD.getName())) {
                        llianpayCardCharge = configPaymentsSet.getServiceCharge();
                    }
                    assumePerson = configPaymentsSet.getAssumePerson();
                }
            }
            configPaymentsVO.setChargeInfo(chargeInfo.append("! : 使用微信或支付宝支付通道,将收取").append(allianpayWxCharge).append("%的手续费(借记卡支付通道暂免手续费)").toString());
            configPaymentsVO.setPayAssume(assumePerson);
        }
        return configPaymentsVO;
    }

    @Override
    public void setConfigPaymentsInfo(Integer userId, String assumePerson) throws Exception {
        List<ConfigPaymentsChannel> configPaymentsChannels = new ArrayList<>();
        //使用平安支付渠道的客户
        if (bigCustomersInfo.contains(userId.toString())) {
            //查询平安的支付通道有几种
            Example configPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
            configPaymentsChannelExample.createCriteria().andLike("payChannel", "%pinganpay%").andEqualTo("isDelete", 0);
            configPaymentsChannels = configPaymentsChannelDao.selectByExample(configPaymentsChannelExample);
            //使用通联,连连支付渠道的客户
        } else {
            //查询非平安的支付通道
            Example configPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
            configPaymentsChannelExample.createCriteria().andNotLike("payChannel", "%pinganpay%").andEqualTo("isDelete", 0);
            configPaymentsChannels = configPaymentsChannelDao.selectByExample(configPaymentsChannelExample);
        }
        Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
        for (ConfigPaymentsChannel configPaymentsChannel : configPaymentsChannels) {
            Integer channelId = configPaymentsChannel.getId();
            configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", channelId)
                    .andEqualTo("createdById", userId);
            ConfigPaymentsSet configPaymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
            //如果该支付渠道未设置，则添加
            if (configPaymentsSet == null) {
                ConfigPaymentsSet paymentsSet = new ConfigPaymentsSet();
                paymentsSet.setAssumePerson(assumePerson);
                paymentsSet.setServiceCharge(configPaymentsChannel.getDefaultCharge());
                paymentsSet.setPaymentsChannelId(configPaymentsChannel.getId());
                paymentsSet.setCreatedById(userId);
                paymentsSet.setCreatedAt(new Date());
                configPaymentsSetDao.insert(paymentsSet);
                //如果该平安支付渠道已设置,则修改
            } else {
                configPaymentsSet.setAssumePerson(assumePerson);
                configPaymentsSet.setUpdatedAt(new Date());
                configPaymentsSet.setUpdatedById(userId);
                configPaymentsSetDao.updateByPrimaryKey(configPaymentsSet);
            }
            configPaymentsSetExample.clear();
        }
    }

    @Override
    public CalculateChargeVO calculateCharge(Integer userId, ConfigPayWayEnum payWay, Double money) throws Exception {
        CalculateChargeVO calculateChargeVO = new CalculateChargeVO();
        calculateChargeVO.setInitPayMoney(money);
        if (bigCustomersInfo.contains(userId.toString())) {
            Example ConfigPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
            Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
            switch (payWay) {
                //微信支付方式(pinganpay_wx)
                case WX:
                    ConfigPaymentsChannelExample.createCriteria().andEqualTo("payChannel", ConfigPayChannelEnum.PINGANPAY_WX.getName()).andEqualTo("isDelete", 0);
                    ConfigPaymentsChannel pinganpayWxChannel = configPaymentsChannelDao.selectOneByExample(ConfigPaymentsChannelExample);
                    Integer channelId = pinganpayWxChannel.getId();
                    configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", channelId)
                            .andEqualTo("createdById", userId);
                    ConfigPaymentsSet paymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
                    if (paymentsSet == null) {
                        //默认租客承担
                        Double defaultCharge = pinganpayWxChannel.getDefaultCharge();
                        Double charge = ConstUtils.getDecimalFormat(defaultCharge / 100);
                        Double payCharge = Math.ceil((money + money * charge) * charge);
                        calculateChargeVO.setPayCharge(payCharge);
                        calculateChargeVO.setEnterPayMoney(money);
                        calculateChargeVO.setTotalPayMoney(money + payCharge);
                    } else {
                        Double charge = ConstUtils.getDecimalFormat(paymentsSet.getServiceCharge() / 100);
                        String assumePerson = paymentsSet.getAssumePerson();
                        //设置的是租客承担
                        if (assumePerson.equals(ConfigPayAssumeEnum.RENTER.getName())) {
                            Double payCharge = Math.ceil((money + money * charge) * charge);
                            calculateChargeVO.setEnterPayMoney(money);
                            calculateChargeVO.setPayCharge(payCharge);
                            calculateChargeVO.setTotalPayMoney(money + payCharge);
                            //设置的是公寓方承担
                        } else {
                            Double payCharge = new BigDecimal(money * charge).setScale(2, RoundingMode.UP).doubleValue();
                            calculateChargeVO.setPayCharge(payCharge);
                            calculateChargeVO.setEnterPayMoney(money - payCharge);
                            calculateChargeVO.setTotalPayMoney(money);
                        }
                    }
                    ConfigPaymentsChannelExample.clear();
                    configPaymentsSetExample.clear();
                    break;
                //快捷支付方式(未接入)
                case CARD:
                    break;
                //其他支付方式(未接入)
                default:
                    break;
            }
            //普通客户，使用通联微信，连连快捷，支付宝的用户
        } else {
            Example ConfigPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
            Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
            Integer channelId;
            ConfigPaymentsSet paymentsSet;
            switch (payWay) {
                //通联微信支付
                case WX:
                    ConfigPaymentsChannelExample.createCriteria().andEqualTo("payChannel", ConfigPayChannelEnum.ALLIANPAY_WX.getName()).andEqualTo("isDelete", 0);
                    ConfigPaymentsChannel allianpayWxChannel = configPaymentsChannelDao.selectOneByExample(ConfigPaymentsChannelExample);
                    channelId = allianpayWxChannel.getId();
                    configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", channelId)
                            .andEqualTo("createdById", userId);
                    paymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
                    //处理由谁支付费用计算
                    disposeChargeMethod(money, calculateChargeVO, allianpayWxChannel, paymentsSet);
                    ConfigPaymentsChannelExample.clear();
                    configPaymentsSetExample.clear();
                    break;
                case ALI:
                    ConfigPaymentsChannelExample.createCriteria().andEqualTo("payChannel", ConfigPayChannelEnum.ALIPAY.getName()).andEqualTo("isDelete", 0);
                    ConfigPaymentsChannel alipayWxChannel = configPaymentsChannelDao.selectOneByExample(ConfigPaymentsChannelExample);
                    channelId = alipayWxChannel.getId();
                    configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", channelId)
                            .andEqualTo("createdById", userId);
                    paymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
                    //处理由谁支付费用计算
                    disposeChargeMethod(money, calculateChargeVO, alipayWxChannel, paymentsSet);
                    ConfigPaymentsChannelExample.clear();
                    configPaymentsSetExample.clear();
                    break;
                case CARD:
                    ConfigPaymentsChannelExample.createCriteria().andEqualTo("payChannel", ConfigPayChannelEnum.LLIANPAY_CARD.getName()).andEqualTo("isDelete", 0);
                    ConfigPaymentsChannel llianpayChannel = configPaymentsChannelDao.selectOneByExample(ConfigPaymentsChannelExample);
                    channelId = llianpayChannel.getId();
                    configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", channelId)
                            .andEqualTo("createdById", userId);
                    paymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
                    //处理由谁支付费用计算
                    disposeChargeMethod(money, calculateChargeVO, llianpayChannel, paymentsSet);
                    ConfigPaymentsChannelExample.clear();
                    configPaymentsSetExample.clear();
                    break;
                default:
                    break;
            }
        }
        return calculateChargeVO;
    }

    //处理费用
    private void disposeChargeMethod(Double money, CalculateChargeVO calculateChargeVO, ConfigPaymentsChannel channel, ConfigPaymentsSet paymentsSet) {
        if (paymentsSet == null) {
            //默认租客承担
            Double defaultCharge = channel.getDefaultCharge();
            Double charge = ConstUtils.getDecimalFormat(defaultCharge / 100);
            Double renterCharge = new BigDecimal(money * charge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            calculateChargeVO.setPayCharge(renterCharge);
            calculateChargeVO.setEnterPayMoney(money);
            calculateChargeVO.setTotalPayMoney(money + renterCharge);
        } else {
            Double charge = ConstUtils.getDecimalFormat(paymentsSet.getServiceCharge() / 100);
            String assumePerson = paymentsSet.getAssumePerson();
            //设置的是租客承担
            if (assumePerson.equals(ConfigPayAssumeEnum.RENTER.getName())) {
                Double payCharge = new BigDecimal(money * charge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                calculateChargeVO.setPayCharge(payCharge);
                calculateChargeVO.setEnterPayMoney(money);
                calculateChargeVO.setTotalPayMoney(money + payCharge);
                //设置的是公寓方承担
            } else {
                Double payCharge = new BigDecimal(money * charge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                calculateChargeVO.setPayCharge(payCharge);
                calculateChargeVO.setEnterPayMoney(money - payCharge);
                calculateChargeVO.setTotalPayMoney(money);
            }
        }
    }
}
