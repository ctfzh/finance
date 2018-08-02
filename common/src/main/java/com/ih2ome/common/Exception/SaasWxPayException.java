package com.ih2ome.common.Exception;

/**
 * @author Sky
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 **/
public class SaasWxPayException extends Exception {
    public SaasWxPayException() {
        super();
    }

    public SaasWxPayException(String message) {
        super(message);
    }

    public SaasWxPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaasWxPayException(Throwable cause) {
        super(cause);
    }

    protected SaasWxPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
