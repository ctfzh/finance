package com.ih2ome.common.support;

/**
 * @author Sky
 * create 2018/05/11
 * email sky.li@ixiaoshuidi.com
 **/
public class DomainThreadLocal {
    public DomainThreadLocal() {
    }

    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();

    public static void set(Integer managerWebId) {
        LOCAL.set(managerWebId);
    }

    public static Integer getManagerWebId() {
        return LOCAL.get();
    }


}
