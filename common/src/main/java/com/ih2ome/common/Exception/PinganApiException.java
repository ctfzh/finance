package com.ih2ome.common.Exception;

/**
 * @author Sky
 * create 2018/07/26
 * email sky.li@ixiaoshuidi.com
 **/
public class PinganApiException extends Exception {
    public PinganApiException() {
        super();
    }

    public PinganApiException(String message) {
        super(message);
    }

    public PinganApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinganApiException(Throwable cause) {
        super(cause);
    }

    protected PinganApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
