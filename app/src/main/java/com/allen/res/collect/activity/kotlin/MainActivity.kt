package com.allen.res.collect.activity.kotlin

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.allen.res.collect.webview.WebViewActivity
import com.allen.res.collect.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnWebView(view: View) {
        startActivity(Intent(MainActivity@this, WebViewActivity::class.java))
    }

    fun btnCircleImageView(view: View) {
        startActivity(Intent(MainActivity@this, CircleImageViewActivity::class.java))
    }

    fun btnTitleBar(view: View) {
        startActivity(Intent(MainActivity@this, TitleBarActivity::class.java))
    }

    fun btnBgSelector(view: View) {
        Toast.makeText(MainActivity@this, "点击", Toast.LENGTH_SHORT).show()
    }

    fun btnSVGAPlayer(view: View) {
        startActivity(Intent(MainActivity@this, SVGAPlayerActivity::class.java))
    }
}
