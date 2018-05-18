package com.allen.res.collect.activity.kotlin

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.allen.res.collect.R
import android.os.Build
import android.widget.ImageView
import android.widget.Toast
import com.allen.res.titlebar.TitleBar
import android.view.View
import android.view.WindowManager


/**
 * 自定义Title
 */
class TitleBarActivity : AppCompatActivity() {

    private var mCollectView: ImageView? = null
    private var mIsSelected: Boolean = false

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_bar)

        var isImmersive = false
        if (hasKitKat() && !hasLollipop()) {
            isImmersive = true
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else if (hasLollipop()) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    //                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            isImmersive = true
        }

        val titleBar = findViewById<View>(R.id.title_bar) as TitleBar

        titleBar.setImmersive(isImmersive)

        titleBar.setBackgroundColor(Color.parseColor("#64b4ff"))

        titleBar.setLeftImageResource(R.mipmap.back_green)
        titleBar.setLeftText("返回")
        titleBar.setLeftTextColor(Color.WHITE)
        titleBar.setLeftClickListener { finish() }

        titleBar.setTitle("文章详情\n副标题")
        titleBar.setTitleColor(Color.WHITE)
        titleBar.setSubTitleColor(Color.WHITE)
        titleBar.setDividerColor(Color.GRAY)

        titleBar.setActionTextColor(Color.WHITE)
        mCollectView = titleBar.addAction(object : TitleBar.ImageAction(R.mipmap.collect) {
            override fun performAction(view: View) {
                Toast.makeText(this@TitleBarActivity, "点击了收藏", Toast.LENGTH_SHORT).show()
                mCollectView!!.setImageResource(R.mipmap.fabu)
                titleBar.setTitle(if (mIsSelected) "文章详情\n朋友圈" else "帖子详情")
                mIsSelected = !mIsSelected
            }
        }) as ImageView

        titleBar.addAction(object : TitleBar.TextAction("发布") {
            override fun performAction(view: View) {
                Toast.makeText(this@TitleBarActivity, "点击了发布", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun hasKitKat(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
    }

    fun hasLollipop(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }
}