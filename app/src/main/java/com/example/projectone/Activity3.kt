package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.core.os.postDelayed

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        var isClickSkip = false

        val textSkip = findViewById<TextView>(R.id.skip2)
        textSkip.setOnClickListener {
            isClickSkip = true
            startActivity(Intent(this, Activity4::class.java))

        }


//        Handler().postDelayed({
//            val i = Intent(this, Activity4::class.java)
//            startActivity(i)
//        }, 10000)

        Handler().postDelayed({
            if (!isClickSkip) {
                val i = Intent(this, Activity4::class.java)
                startActivity(i)
            }

        }, 10000)


    }
}