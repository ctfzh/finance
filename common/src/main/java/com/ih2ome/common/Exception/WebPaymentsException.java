package com.ih2ome.common.Exception;

/**
 * @author Sky
 * create 2018/08/10
 * email sky.li@ixiaoshuidi.com
 **/
public class WebPaymentsException extends  Exception {
    public WebPaymentsException() {
        super();
    }

    public WebPaymentsException(String message) {
        super(message);
    }

    public WebPaymentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebPaymentsException(Throwable cause) {
        super(cause);
    }

    protected WebPaymentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
