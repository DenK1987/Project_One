package com.example.projectone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        var isClickSkip = false

        val textSkip = findViewById<TextView>(R.id.skip)
        textSkip.setOnClickListener {
            isClickSkip = true
            startActivity(Intent(this, Activity3::class.java))

        }


//        Handler().postDelayed({
//            val i = Intent(this, Activity4::class.java)
//            startActivity(i)
//        }, 10000)

        Handler().postDelayed({
            if (!isClickSkip) {
                val i = Intent(this, Activity3::class.java)
                startActivity(i)
            }

        }, 10000)
    }
}