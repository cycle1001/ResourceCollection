package com.allen.res.tools;

import android.content.Context;

import java.lang.reflect.Method;

/**
 * 屏幕相关工具类
 */
public class ScreenUtils {

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 检查OPPO手机是否有刘海屏
     *
     * @param context 上下文
     * @return true false
     * @deprecated OPPO目前在设置 -- 显示 -- 应用全屏显示 -- 凹形区域显示控制，里面有关闭凹形区域开关。
     */
    public static boolean hasNotchInScreenAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     * 检查vivo手机是否有刘海屏
     *
     * @param context 上下文
     * @return true false
     * @deprecated vivo在设置--显示与亮度--第三方应用显示比例中可以切换是否全屏显示还是安全区域显示。
     * vivo不提供接口获取刘海尺寸，目前vivo的刘海宽为100dp,高为27dp。
     */
    public static boolean hasNotchAtVoio(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            LogUtils.e("hasNotchAtVoio", "hasNotchAtVoio ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            LogUtils.e("hasNotchAtVoio", "hasNotchAtVoio NoSuchMethodException");
        } catch (Exception e) {
            LogUtils.e("hasNotchAtVoio", "hasNotchAtVoio Exception");
        }
        return ret;
    }

    /**
     * 华为-使用刘海区显示
     *
     * 使用新增的meta-data属性android.notch_support。
     * 在应用的AndroidManifest.xml中增加meta-data属性，此属性不仅可以针对Application生效，也可以对Activity配置生效。
     * 如下所示：
     * <meta-data android:name="android.notch_support" android:value="true"/>
     *
     * 对Application生效，意味着该应用的所有页面，系统都不会做竖屏场景的特殊下移或者是横屏场景的右移特殊处理。
     * 对Activity生效，意味着可以针对单个页面进行刘海屏适配，设置了该属性的Activity系统将不会做特殊处理。
     */

    /**
     * 检查华为手机上是否有刘海屏
     * @param context 上下文
     * @return true为有刘海，false则没有
     */
    public static boolean hasNotchAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            LogUtils.e("hasNotchAtHuawei", "hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            LogUtils.e("hasNotchAtHuawei", "hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            LogUtils.e("hasNotchAtHuawei", "hasNotchAtHuawei Exception");
        }
        return ret;
    }

    /**
     * 获取华为手机刘海尺寸
     * @param context 上下文
     * @return 获取刘海尺寸：width、height
     *          int[0]值为刘海宽度 int[1]值为刘海高度
     */
    public static int[] getNotchSizeAtHuawei(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            LogUtils.e("getNotchSizeAtHuawei", "getNotchSizeAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            LogUtils.e("getNotchSizeAtHuawei", "getNotchSizeAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            LogUtils.e("getNotchSizeAtHuawei", "getNotchSizeAtHuawei Exception");
        }
        return ret;
    }
}
