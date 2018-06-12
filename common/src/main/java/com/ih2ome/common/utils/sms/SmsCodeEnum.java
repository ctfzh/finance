package com.ih2ome.common.utils.sms;

/**
 * @author Sky
 * create 2018/04/09
 * email sky.li@ixiaoshuidi.com
 **/
public enum SmsCodeEnum {
    EXCESS_POWER_OFF(0, "tIwPu1");
    //【水滴管家】亲爱的租客@var(customerName)，您好，您租住的房间@var(roomName)的房租账单在@var(payTime)即将到期，到期若未交租，将在@var(powerOffTime)上午9:00~10:00停电。请您及时交租，谢谢配合。祝您居住愉快
    int code;
    String name;

    SmsCodeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

