package com.vikasmaurya.corona.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.vikasmaurya.corona.R

class Health : AppCompatActivity() {
    var mywebview: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health)

        mywebview = findViewById<WebView>(R.id.webview)
        val webSettings = mywebview!!.settings
        webSettings.javaScriptEnabled = true
        mywebview!!.loadUrl("http://www.covid19org.com/")


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
