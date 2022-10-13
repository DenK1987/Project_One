package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class Activity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)

        var isClickSkip = false

        val textSkip = findViewById<TextView>(R.id.skip4)
        textSkip.setOnClickListener {
            isClickSkip = true
            startActivity(Intent(this, Activity6::class.java))

        }


//        Handler().postDelayed({
//            val i = Intent(this, Activity4::class.java)
//            startActivity(i)
//        }, 10000)

        Handler().postDelayed({
            if (!isClickSkip) {
                val i = Intent(this, Activity6::class.java)
                startActivity(i)
            }

        }, 10000)
    }
}