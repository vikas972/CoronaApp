package com.vikasmaurya.corona.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.vikasmaurya.corona.R

class SplashScreen : AppCompatActivity() {

    var mImg: ImageView? = null
    val SPLASH_SCREEN_TIME_OUT = 3000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        mImg = findViewById(R.id.splash_icon_img)

        startSplashScreen()

    }

    private fun startSplashScreen() {
        startAnimation()
        Handler().postDelayed(
            {
                    startActivity(Intent(this, MainDashboard::class.java))
                    overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
                    finish()

            }, SPLASH_SCREEN_TIME_OUT.toLong()
        )
    }

    private fun startAnimation() {
        val mAnimation =
            AnimationUtils.loadAnimation(this, R.anim.splash_zoom)
        mImg!!.startAnimation(mAnimation)
    }
}
