package com.vikasmaurya.corona.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.vikasmaurya.corona.R

class Visual : AppCompatActivity() {
    var mywebview: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual)
        mywebview = findViewById<WebView>(R.id.webview)
        val webSettings = mywebview!!.settings
        webSettings.javaScriptEnabled = true
        mywebview!!.loadUrl("https://www.tradingview.com/covid19/")


        mywebview!!.webViewClient = WebViewClient()
    }

    override fun onBackPressed() {
        if (mywebview!!.canGoBack()) {
            mywebview!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
