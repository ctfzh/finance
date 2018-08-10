package com.ih2ome.common.Exception;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
public class PinganWxPayException extends Exception {
    public PinganWxPayException() {
        super();
    }

    public PinganWxPayException(String message) {
        super(message);
    }

    public PinganWxPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinganWxPayException(Throwable cause) {
        super(cause);
    }

    protected PinganWxPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
