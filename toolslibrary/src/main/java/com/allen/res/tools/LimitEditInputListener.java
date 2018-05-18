package com.allen.res.tools;

import android.support.annotation.NonNull;
import android.text.method.NumberKeyListener;

/**
 * EditText 设置限制输入字符
 * editText.setKeyListener(new LimitEditInputListener())
 */
public class LimitEditInputListener extends NumberKeyListener {

    /**
     * 限制输入的字符
     */
    @NonNull
    @Override
    protected char[] getAcceptedChars() {
        return new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'x', 'X'};
    }

    /**
     * 0无键盘 1英文键盘 2模拟键盘 3数字键盘
      */
    @Override
    public int getInputType() {
        return 3;
    }
}
