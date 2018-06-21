package com.allen.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 文字超出控件宽度时,自动滚动显示,类似跑马灯效果
 */
public class RollTextView extends AppCompatTextView {

    public RollTextView(Context context) {
        super(context);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
