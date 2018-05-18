package com.allen.nw.exception;

/**
 * 运行时自定义错误信息
 * 自由添加错误，可扩展
 * Created by _Smile on 2018/3/19 019.
 */
public class RunTimeException extends RuntimeException {
    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 0x1001;

    /**
     * 本地无缓存错误
     */
    public static final int NO_CACHE_ERROR = 0x1002;

    /**
     * 缓存过时错误
     */
    public static final int CACHE_TIMEOUT_ERROR = 0x1003;

    public RunTimeException(int resultCode) {
        super(getApiExceptionMessage(resultCode));
    }

    public RunTimeException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 转换错误数据
     *
     * @param code 错误码
     * @return 返回描述信息
     */
    private static String getApiExceptionMessage(int code) {
        switch (code) {
            case UNKNOWN_ERROR:
                return "错误：网络错误";
            case NO_CACHE_ERROR:
                return "错误：无缓存数据";
            case CACHE_TIMEOUT_ERROR:
                return "错误：缓存数据过期";
            default:
                return "错误：未知错误";
        }
    }
}
