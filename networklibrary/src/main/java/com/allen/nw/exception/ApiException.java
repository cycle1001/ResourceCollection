package com.allen.nw.exception;

/**
 * 回调统一请求异常
 * Created by _Smile on 2018/3/19 019.
 */
public class ApiException extends Exception {

    /**
     * 错误码
     */
    private int code;

    /**
     * 显示的信息
     */
    private String displayMessage;

    public ApiException(Throwable throwable, @CodeException.CodeEp int code, String showMsg) {
        super(showMsg, throwable);
        setCode(code);
        setDisplayMessage(showMsg);
    }

    @CodeException.CodeEp
    public int getCode() {
        return code;
    }

    public void setCode(@CodeException.CodeEp int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
