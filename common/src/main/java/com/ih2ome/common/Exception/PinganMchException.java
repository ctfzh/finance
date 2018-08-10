package com.ih2ome.common.Exception;

/**
 * @author Sky
 * create 2018/08/10
 * email sky.li@ixiaoshuidi.com
 * 平安商户开通,绑卡等异常
 **/
public class PinganMchException extends Exception {
    public PinganMchException() {
    }

    public PinganMchException(String message) {
        super(message);
    }

    public PinganMchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinganMchException(Throwable cause) {
        super(cause);
    }

    public PinganMchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
