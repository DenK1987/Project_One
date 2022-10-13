package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class Activity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)

        var isClickSkip = false

        val textSkip = findViewById<TextView>(R.id.skip5)
        textSkip.setOnClickListener {
            isClickSkip = true
            startActivity(Intent(this, Activity7::class.java))

        }


//        Handler().postDelayed({
//            val i = Intent(this, Activity4::class.java)
//            startActivity(i)
//        }, 10000)

        Handler().postDelayed({
            if (!isClickSkip) {
                val i = Intent(this, Activity7::class.java)
                startActivity(i)
            }

        }, 10000)
    }
}