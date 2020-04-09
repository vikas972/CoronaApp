package com.vikasmaurya.corona.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.vikasmaurya.corona.R




class NewsShow : AppCompatActivity() {
    var mywebview: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_show)

        mywebview = findViewById<WebView>(R.id.webview)
        val webSettings = mywebview!!.settings
        webSettings.javaScriptEnabled = true
        mywebview!!.loadUrl("https://www.ndtv.com/coronavirus?pfrom=home-mainnavgation")


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
