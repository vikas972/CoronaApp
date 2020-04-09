package com.vikasmaurya.corona.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appdid.topautocare.Helper.BottomNavigationViewHelper
import com.vikasmaurya.corona.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File



class MainDashboard : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener  {

    internal var backButtonCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main_dashboard)

        //loading the default fragment
        loadFragment(WorldFragment())


        val navigation = findViewById<BottomNavigationView>(R.id.nav)
        BottomNavigationViewHelper.disableShiftMode(navigation)


        navigation.setOnNavigationItemSelectedListener(this)

        for (i in 0 until navigation.menu.size()) {
            val menuItem = navigation.menu.getItem(i)
            val spannableTitle = SpannableStringBuilder(menuItem.title)
            menuItem.title = spannableTitle
        }

        //        String value=getIntent().getStringExtra("value");
        //        // Log.d("value",""+value);
        //        if(value==null) {
        //            loadFragment(new ActivityFragment());
        //        }else {
        //            loadFragment(new PackageFragment());
        //            navigation.setSelectedItemId(R.id.packages);
        //        }

        //getting bottom navigation view and attaching the listener

    }

    fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.framelayout, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (menuItem.itemId) {

            R.id.world -> fragment = WorldFragment()

            R.id.country -> fragment = CountryFragment()

            R.id.credits -> credits()
            R.id.news -> news()

            R.id.more -> more()

        }

        return loadFragment(fragment)
    }



    private fun credits() {
        val mCredits = LayoutInflater.from(this).inflate(R.layout.privacy_policy,null) as WebView
        mCredits.loadUrl("file:///android_asset/all_credits.html")
        val mAlertDialog = AlertDialog.Builder(this@MainDashboard,R.style.Theme_AppCompat_Light_Dialog_Alert)
            .setTitle(getString(R.string.Request))
            .setView(mCredits)
            .setPositiveButton(android.R.string.ok,null)

        mAlertDialog.show()
    }

    private fun news(){
        val newsIntent = Intent(applicationContext, NewsShow::class.java)
        startActivity(newsIntent)

    }
    private fun more(){
        val moreIntent = Intent(applicationContext, Instruction::class.java)
        startActivity(moreIntent)

    }


    override fun onBackPressed() {


        if (backButtonCount >= 1) {

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)

        } else {
            try {
                Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show()
                backButtonCount++
            } catch (e: Exception) {

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            trimCache(applicationContext)
        } catch (e: Exception) {

        }

    }

    companion object {

        fun trimCache(context: Context) {
            try {
                val dir = context.cacheDir
                deleteDir(dir)
            } catch (e: Exception) {

            }

        }

        fun deleteDir(dir: File?): Boolean {
            if (dir != null && dir.isDirectory) {
                val children = dir.list()
                for (i in children.indices) {
                    val success = deleteDir(File(dir, children[i]))
                    if (!success) {
                        return false
                    }
                }
                return dir.delete()
            } else {
                return false
            }
        }

    }

}
