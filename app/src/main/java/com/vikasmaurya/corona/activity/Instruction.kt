package com.vikasmaurya.corona.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.vikasmaurya.corona.R


class Instruction : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
        val button = findViewById<Button>(R.id.payment)
        button?.setOnClickListener()
        {
            val pay = Intent(applicationContext, Payment::class.java)
            startActivity(pay)
        }
        val button1 = findViewById<Button>(R.id.vis)
        button1?.setOnClickListener()
        {
            val vis = Intent(applicationContext, Visual::class.java)
            startActivity(vis)
        }
        val button2 = findViewById<Button>(R.id.check)
        button2?.setOnClickListener()
        {
            val check = Intent(applicationContext, Health::class.java)
            startActivity(check)
        }


    }
}
