package com.ih2ome.service.impl;

import com.ih2ome.common.PageVO.CalculateChargeVO;
import com.ih2ome.common.PageVO.ConfigPayChannelsVO;
import com.ih2ome.common.PageVO.ConfigPaymentsVO;
import com.ih2ome.common.enums.ConfigPayAssumeEnum;
import com.ih2ome.common.enums.ConfigPayChannelEnum;
import com.ih2ome.common.enums.ConfigPayWayEnum;
import com.ih2ome.common.utils.ConstUtils;
import com.ih2ome.dao.caspain.ConfigPaymentsChannelDao;
import com.ih2ome.dao.caspain.ConfigPaymentsSetDao;
import com.ih2ome.model.caspain.ConfigPaymentsChannel;
import com.ih2ome.model.caspain.ConfigPaymentsSet;
import com.ih2ome.model.caspain.ConfigPaymentsUser;
import com.ih2ome.service.ConfigPaymentsChannelService;
import com.ih2ome.service.ConfigPaymentsService;
import com.ih2ome.service.ConfigPaymentsUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author Sky
 * create 2018/07/09
 * email sky.li@ixiaoshuidi.com
 **/
@Service
@Transactional
public class ConfigPaymentsServiceImpl implements ConfigPaymentsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPaymentsServiceImpl.class);

    @Autowired
    private ConfigPaymentsChannelDao configPaymentsChannelDao;
    @Autowired
    private ConfigPaymentsSetDao configPaymentsSetDao;
    @Autowired
    private ConfigPaymentsUserService configPaymentsUserService;
    @Autowired
    private ConfigPaymentsChannelService configPaymentsChannelService;

    @Override
    public ConfigPaymentsVO findConfigPaymentsInfo(Integer userId, Boolean bool) throws Exception {
        ConfigPaymentsVO configPaymentsVO = new ConfigPaymentsVO();
        //查询该用户的支付渠道选择
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserService.selectUserType(userId);
        //查询该用户的通道选择
        List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelService.findPaymentsChannels(configPaymentsUser);
        //判断是否为中建等大客户
        Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
        String wxMessage = "";
        String aliMessage = "";
        String cardMessage = "";
        String assumePerson = ConfigPayAssumeEnum.RENTER.name();
        for (ConfigPaymentsChannel configPaymentsChannel : configPaymentsChannels) {
            configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", configPaymentsChannel.getId())
                    .andEqualTo("createdById", userId);
            ConfigPaymentsSet paymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
            Double charge = 0.0;
            if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.PINGANPAY_WX.getName())) {
                if (paymentsSet == null) {
                    charge = configPaymentsChannel.getDefaultCharge();
                } else {
                    charge = paymentsSet.getServiceCharge();
                    assumePerson = paymentsSet.getAssumePerson();
                }
                wxMessage = "微信手续费" + 6.6 + "‰,最低0.1元/笔!";
            } else if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.ALLIANPAY_WX.getName())) {
                if (paymentsSet == null) {
                    charge = configPaymentsChannel.getDefaultCharge();
                } else {
                    charge = paymentsSet.getServiceCharge();
                    assumePerson = paymentsSet.getAssumePerson();
                }
                wxMessage = "微信手续费" + charge + "%,最低0.1元/笔!";
            } else if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.ALIPAY.getName())) {
                if (paymentsSet == null) {
                    charge = configPaymentsChannel.getDefaultCharge();
                } else {
                    charge = paymentsSet.getServiceCharge();
                    assumePerson = paymentsSet.getAssumePerson();
                }
                aliMessage = "支付宝手续费" + charge + "%,最低0.1元/笔!";
            } else if (configPaymentsChannel.getPayChannel().equals(ConfigPayChannelEnum.LLIANPAY_CARD.getName())) {
                if (paymentsSet == null) {
                    charge = configPaymentsChannel.getDefaultCharge();
                } else {
                    charge = paymentsSet.getServiceCharge();
                    assumePerson = paymentsSet.getAssumePerson();
                }
                cardMessage = "银行卡暂不收取手续费!";
            }
            configPaymentsSetExample.clear();
        }
        configPaymentsVO.setWxChargeInfo(wxMessage);
        configPaymentsVO.setAliChargeInfo(aliMessage);
        configPaymentsVO.setCardChargeInfo(cardMessage);
        configPaymentsVO.setPayAssume(assumePerson);
        return configPaymentsVO;
    }

    /**
     * 手续费用设置
     */
    @Override
    public void setConfigPaymentsInfo(Integer userId, String assumePerson, Boolean bool) throws Exception {
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserService.selectUserType(userId);
        //查询该用户的通道选择
        List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelService.findPaymentsChannels(configPaymentsUser);
        //查询该用户没有使用的通道
        List<ConfigPaymentsChannel> notConfigPaymentsChannels = configPaymentsChannelService.findNotPaymentsChannels(configPaymentsUser);
        Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
        //清除原先的未使用通道配置
        for (ConfigPaymentsChannel notConfigPaymentsChannel : notConfigPaymentsChannels) {
            configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", notConfigPaymentsChannel.getId())
                    .andEqualTo("createdById", userId);
            ConfigPaymentsSet configPaymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
            if (configPaymentsSet != null) {
                configPaymentsSet.setDeletedAt(new Date());
                configPaymentsSet.setDeletedById(userId);
                configPaymentsSet.setIsDelete(1);
                configPaymentsSetDao.updateByPrimaryKey(configPaymentsSet);
            }
            configPaymentsSetExample.clear();
        }
        //设置新的通道配置
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

    /**
     * 计算费用
     */
    @Override
    public CalculateChargeVO calculateCharge(Integer userId, ConfigPayWayEnum payWay, Double money, Boolean bool) throws Exception {
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserService.selectUserType(userId);
        Integer userType = configPaymentsUser.getUserType();
        double minDefaultCharge = 0.1;
        CalculateChargeVO calculateChargeVO = new CalculateChargeVO();
        calculateChargeVO.setInitPayMoney(money);
        if (bool) {
            switch (payWay) {
                case WX:
                    //微信支付方式(pinganpay_wx)
                    if (configPaymentsUser.getWxType().equals(ConfigPayChannelEnum.PINGANPAY_WX.getName())) {
                        //获取通道和用户配置
                        Map<String, Object> map = getPayChannelAndPaySet(ConfigPayChannelEnum.PINGANPAY_WX.getName(), userId);
                        ConfigPaymentsSet paymentsSet = (ConfigPaymentsSet) map.get("paymentsSet");
                        ConfigPaymentsChannel paymentsChannel = (ConfigPaymentsChannel) map.get("paymentsChannel");
                        if (paymentsSet == null) {
                            //默认租客承担
                            Double defaultCharge = paymentsChannel.getDefaultCharge();
                            Double charge = ConstUtils.getDecimalFormat(defaultCharge / 100);
                            Double payCharge = new BigDecimal((money + money * charge) * charge).setScale(1, RoundingMode.UP).doubleValue();
                            if (payCharge <= minDefaultCharge) {
                                payCharge = minDefaultCharge;
                            }
                            calculateChargeVO.setPayAssume(ConfigPayAssumeEnum.RENTER.getName());
                            calculateChargeVO.setPayCharge(payCharge);
                            calculateChargeVO.setEnterPayMoney(money);
                            calculateChargeVO.setTotalPayMoney(ConstUtils.getDecimalFormat(money + payCharge));
                        } else {
                            Double charge = ConstUtils.getDecimalFormat(paymentsSet.getServiceCharge() / 100);
                            String assumePerson = paymentsSet.getAssumePerson();
                            //设置的是租客承担
                            if (assumePerson.equals(ConfigPayAssumeEnum.RENTER.getName())) {
                                Double payCharge = new BigDecimal((money + money * charge) * charge).setScale(1, RoundingMode.UP).doubleValue();
                                if (payCharge <= minDefaultCharge) {
                                    payCharge = minDefaultCharge;
                                }
                                calculateChargeVO.setPayAssume(ConfigPayAssumeEnum.RENTER.getName());
                                calculateChargeVO.setEnterPayMoney(money);
                                calculateChargeVO.setPayCharge(payCharge);
                                calculateChargeVO.setTotalPayMoney(ConstUtils.getDecimalFormat(money + payCharge));
                                //设置的是公寓方承担
                            } else {
                                Double payCharge = new BigDecimal(money * charge).setScale(1, RoundingMode.UP).doubleValue();
                                if (payCharge <= minDefaultCharge) {
                                    payCharge = minDefaultCharge;
                                }
                                calculateChargeVO.setPayAssume(ConfigPayAssumeEnum.LANDLORD.getName());
                                calculateChargeVO.setPayCharge(payCharge);
                                calculateChargeVO.setEnterPayMoney(ConstUtils.getDecimalFormat(money - payCharge));
                                calculateChargeVO.setTotalPayMoney(money);
                            }
                        }
                        calculateChargeVO.setChannelType(paymentsChannel.getPayChannel());
                        //微信支付方式(allianpay_wx)
                    } else if (configPaymentsUser.getWxType().equals(ConfigPayChannelEnum.ALLIANPAY_WX.getName())) {
                        //获取通道和用户配置
                        Map<String, Object> map = getPayChannelAndPaySet(ConfigPayChannelEnum.ALLIANPAY_WX.getName(), userId);
                        ConfigPaymentsSet paymentsSet = (ConfigPaymentsSet) map.get("paymentsSet");
                        ConfigPaymentsChannel paymentsChannel = (ConfigPaymentsChannel) map.get("paymentsChannel");
                        //处理由谁支付费用计算
                        disposeChargeMethod(money, calculateChargeVO, paymentsChannel, paymentsSet);
                        calculateChargeVO.setChannelType(paymentsChannel.getPayChannel());
                    }
                    break;
                case CARD:
                    //快捷支付(llianpay_card)
                    if (configPaymentsUser.getCardType().equals(ConfigPayChannelEnum.LLIANPAY_CARD.getName())) {
                        //获取通道和用户配置
                        Map<String, Object> map = getPayChannelAndPaySet(ConfigPayChannelEnum.LLIANPAY_CARD.getName(), userId);
                        ConfigPaymentsSet paymentsSet = (ConfigPaymentsSet) map.get("paymentsSet");
                        ConfigPaymentsChannel paymentsChannel = (ConfigPaymentsChannel) map.get("paymentsChannel");
                        //处理由谁支付费用计算
                        disposeChargeMethod(money, calculateChargeVO, paymentsChannel, paymentsSet);
                        calculateChargeVO.setChannelType(paymentsChannel.getPayChannel());
                    } else {

                    }
                    break;
                case ALI:
                    //支付宝支付（alipay）
                    if (configPaymentsUser.getAliType().equals(ConfigPayChannelEnum.ALIPAY.getName())) {
                        Map<String, Object> map = getPayChannelAndPaySet(ConfigPayChannelEnum.ALIPAY.getName(), userId);
                        ConfigPaymentsSet paymentsSet = (ConfigPaymentsSet) map.get("paymentsSet");
                        ConfigPaymentsChannel paymentsChannel = (ConfigPaymentsChannel) map.get("paymentsChannel");
                        //处理由谁支付费用计算
                        disposeChargeMethod(money, calculateChargeVO, paymentsChannel, paymentsSet);
                        calculateChargeVO.setChannelType(paymentsChannel.getPayChannel());
                    } else {

                    }
                    break;
                //其他支付方式(未接入)
                default:
                    break;
            }
            //普通客户，使用通联微信，连连快捷，支付宝的用户
        } else {
            String channelName = "";
            switch (payWay) {
                //通联微信支付
                case WX:
                    channelName = ConfigPayChannelEnum.ALLIANPAY_WX.getName();
                    break;
                case ALI:
                    channelName = ConfigPayChannelEnum.ALIPAY.getName();
                    break;
                case CARD:
                    channelName = ConfigPayChannelEnum.LLIANPAY_CARD.getName();
                    break;
                default:
                    break;
            }
            //获取支付通道和支付设置
            Map<String, Object> map = getPayChannelAndPaySet(channelName, userId);
            ConfigPaymentsSet paymentsSet = (ConfigPaymentsSet) map.get("paymentsSet");
            ConfigPaymentsChannel paymentsChannel = (ConfigPaymentsChannel) map.get("paymentsChannel");
            //处理由谁支付费用计算
            disposeChargeMethod(money, calculateChargeVO, paymentsChannel, paymentsSet);
            calculateChargeVO.setChannelType(paymentsChannel.getPayChannel());
        }
        return calculateChargeVO;
    }


    //根据用户id，支付渠道名称查询ConfigPaymentsChannel,ConfigPaymentsSet
    private Map<String, Object> getPayChannelAndPaySet(String channelName, Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Example configPaymentsChannelExample = new Example(ConfigPaymentsChannel.class);
        Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
        configPaymentsChannelExample.createCriteria().andEqualTo("payChannel", channelName).andEqualTo("isDelete", 0);
        ConfigPaymentsChannel paymentsChannel = configPaymentsChannelDao.selectOneByExample(configPaymentsChannelExample);
        Integer channelId = paymentsChannel.getId();
        configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", channelId)
                .andEqualTo("createdById", userId);
        ConfigPaymentsSet paymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
        map.put("paymentsChannel", paymentsChannel);
        map.put("paymentsSet", paymentsSet);
        configPaymentsChannelExample.clear();
        configPaymentsSetExample.clear();
        return map;
    }


    //处理费用（非平安支付渠道用户）
    private void disposeChargeMethod(Double money, CalculateChargeVO calculateChargeVO, ConfigPaymentsChannel channel, ConfigPaymentsSet paymentsSet) {
        double minDefaultCharge = 0.1;
        if (paymentsSet == null) {
            //默认租客承担
            Double defaultCharge = channel.getDefaultCharge();
            Double charge = ConstUtils.getDecimalFormat(defaultCharge / 100);
            Double renterCharge = money * charge;
            if (channel.getPayChannel().equals(ConfigPayChannelEnum.LLIANPAY_CARD.getName())) {
                renterCharge = 0.0;
            } else {
                if (renterCharge <= minDefaultCharge) {
                    renterCharge = minDefaultCharge;
                } else {
                    renterCharge = new BigDecimal(renterCharge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
            }
            calculateChargeVO.setPayAssume(ConfigPayAssumeEnum.RENTER.getName());
            calculateChargeVO.setPayCharge(renterCharge);
            calculateChargeVO.setEnterPayMoney(money);
            calculateChargeVO.setTotalPayMoney(ConstUtils.getDecimalFormat(money + renterCharge));
        } else {
            Double charge = ConstUtils.getDecimalFormat(paymentsSet.getServiceCharge() / 100);
            String assumePerson = paymentsSet.getAssumePerson();
            //设置的是租客承担
            if (assumePerson.equals(ConfigPayAssumeEnum.RENTER.getName())) {
                Double payCharge = money * charge;
                if (channel.getPayChannel().equals(ConfigPayChannelEnum.LLIANPAY_CARD.getName())) {
                    payCharge = 0.0;
                } else {
                    if (payCharge <= minDefaultCharge) {
                        payCharge = minDefaultCharge;
                    } else {
                        payCharge = new BigDecimal(payCharge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }
                calculateChargeVO.setPayAssume(ConfigPayAssumeEnum.RENTER.getName());
                calculateChargeVO.setPayCharge(payCharge);
                calculateChargeVO.setEnterPayMoney(money);
                calculateChargeVO.setTotalPayMoney(ConstUtils.getDecimalFormat(money + payCharge));
                //设置的是公寓方承担
            } else {
                Double payCharge = money * charge;
                if (channel.getPayChannel().equals(ConfigPayChannelEnum.LLIANPAY_CARD.getName())) {
                    payCharge = 0.0;
                } else {
                    if (payCharge <= minDefaultCharge) {
                        payCharge = minDefaultCharge;
                    } else {
                        payCharge = new BigDecimal(payCharge).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                }
                calculateChargeVO.setPayAssume(ConfigPayAssumeEnum.LANDLORD.getName());
                calculateChargeVO.setPayCharge(payCharge);
                calculateChargeVO.setEnterPayMoney(ConstUtils.getDecimalFormat(money - payCharge));
                calculateChargeVO.setTotalPayMoney(money);
            }
        }
    }


    @Override
    public ConfigPayChannelsVO getConfigChannelInfo(Integer userId, Boolean bool) throws Exception {
        ConfigPayChannelsVO configPayChannelsVO = new ConfigPayChannelsVO();
        ConfigPaymentsUser configPaymentsUser = configPaymentsUserService.selectUserType(userId);
        BeanUtils.copyProperties(configPaymentsUser, configPayChannelsVO);
        //查询该用户的通道选择
        List<ConfigPaymentsChannel> configPaymentsChannels = configPaymentsChannelService.findPaymentsChannels(configPaymentsUser);
        Example configPaymentsSetExample = new Example(ConfigPaymentsSet.class);
        for (ConfigPaymentsChannel configPaymentsChannel : configPaymentsChannels) {
            Integer channelId = configPaymentsChannel.getId();
            configPaymentsSetExample.createCriteria().andEqualTo("isDelete", 0).andEqualTo("paymentsChannelId", channelId)
                    .andEqualTo("createdById", userId);
            ConfigPaymentsSet configPaymentsSet = configPaymentsSetDao.selectOneByExample(configPaymentsSetExample);
            if (ConfigPayChannelEnum.PINGANPAY_WX.getName().equals(configPaymentsChannel.getPayChannel()) ||
                    ConfigPayChannelEnum.ALLIANPAY_WX.getName().equals(configPaymentsChannel.getPayChannel())) {
                if (configPaymentsSet == null) {
                    configPayChannelsVO.setPayAssume(ConfigPayAssumeEnum.RENTER.getName());
                    configPayChannelsVO.setWxPercent(configPaymentsChannel.getDefaultCharge());
                } else {
                    configPayChannelsVO.setPayAssume(configPaymentsSet.getAssumePerson());
                    configPayChannelsVO.setWxPercent(configPaymentsSet.getServiceCharge());
                }
            } else if (ConfigPayChannelEnum.ALIPAY.getName().equals(configPaymentsChannel.getPayChannel())) {
                if (configPaymentsSet == null) {
                    configPayChannelsVO.setPayAssume(ConfigPayAssumeEnum.RENTER.getName());
                    configPayChannelsVO.setAliPercent(configPaymentsChannel.getDefaultCharge());
                } else {
                    configPayChannelsVO.setPayAssume(configPaymentsSet.getAssumePerson());
                    configPayChannelsVO.setAliPercent(configPaymentsSet.getServiceCharge());
                }
            } else if (ConfigPayChannelEnum.LLIANPAY_CARD.getName().equals(configPaymentsChannel.getPayChannel())) {
                if (configPaymentsSet == null) {
                    configPayChannelsVO.setPayAssume(ConfigPayAssumeEnum.RENTER.getName());
                    configPayChannelsVO.setCardPercent(configPaymentsChannel.getDefaultCharge());
                } else {
                    configPayChannelsVO.setPayAssume(configPaymentsSet.getAssumePerson());
                    configPayChannelsVO.setCardPercent(configPaymentsSet.getServiceCharge());
                }
            }
            configPaymentsSetExample.clear();
        }
        return configPayChannelsVO;
    }

}
