package com.vikasmaurya.corona.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.vikasmaurya.corona.R

class Payment : AppCompatActivity() {
    var mywebview: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        mywebview = findViewById<WebView>(R.id.webview)
        val webSettings = mywebview!!.settings
        webSettings.javaScriptEnabled = true
        mywebview!!.loadUrl("https://pmnrf.gov.in/en/online-donation")


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
