package com.ih2ome.common.enums;

/**
 * @author Sky
 * create 2018/08/27
 * email sky.li@ixiaoshuidi.com
 **/
public enum ConfigPayUserTypeEnum {
    COMMON_USER(0),
    PINGAN_USER(1);
    private Integer code;

    ConfigPayUserTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
