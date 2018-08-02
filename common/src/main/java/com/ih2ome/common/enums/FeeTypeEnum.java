package com.ih2ome.common.enums;

/**
 * @author Sky
 * create 2018/08/02
 * email sky.li@ixiaoshuidi.com
 * 费用类型
 **/
public enum FeeTypeEnum {
    ELECTRIC(18, "电费"), RENT(1, "房租");
    private Integer code;
    private String name;

    FeeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (FeeTypeEnum feeTypeEnum : FeeTypeEnum.values()) {
            if (feeTypeEnum.getCode().equals(code)) {
                return feeTypeEnum.getName();
            }
        }
        return null;
    }
}
