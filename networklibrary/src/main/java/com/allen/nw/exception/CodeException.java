package com.allen.nw.exception;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义错误code类型：注解写法
 * 可自由扩展
 * Created by _Smile on 2018/3/19 019.
 */
public class CodeException {

    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 0x1;

    /**
     * http错误
     */
    public static final int HTTP_ERROR = 0x2;

    /**
     * json错误
     */
    public static final int JSON_ERROR = 0x3;

    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 0x4;

    /**
     * 运行时异常-包含自定义异常
     */
    public static final int RUNTIME_ERROR = 0x5;

    /**
     * 无法解析该域名
     */
    public static final int UNKNOWN_HOST_ERROR = 0x6;

    @IntDef({NETWORK_ERROR, HTTP_ERROR, JSON_ERROR, UNKNOWN_ERROR, RUNTIME_ERROR, UNKNOWN_HOST_ERROR})
    @Retention(RetentionPolicy.SOURCE)

    @interface CodeEp {

    }

}
