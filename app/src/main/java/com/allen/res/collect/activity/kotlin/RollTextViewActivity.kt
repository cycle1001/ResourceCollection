package com.allen.res.collect.activity.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.allen.res.collect.R

/**
 * TextView超出宽度自动滚动，跑马灯效果
 */
class RollTextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll_text)
    }

    /**
     * android:ellipsize=”marquee”设置可以以横向滚动方式显示，但前提是需获得当前焦点，所以才有重写 isFocused(),返回为true则获取焦点；
     *
     * android:marqueeRepeatLimit=”marquee_forever”设置跑马灯显示次数，marquee_forever表示不间断无限次，也可以通过代码textview.setMarqueeRepeatLimit(1)设置次数(设置-1 则是无限滚动);
     */
}