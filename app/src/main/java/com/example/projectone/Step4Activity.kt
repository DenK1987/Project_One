package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class Step4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step4)

        var isClickSkip = false

        val textSkip = findViewById<TextView>(R.id.skipStep4)
        textSkip.setOnClickListener {
            isClickSkip = true
            startActivity(Intent(this, Step5Activity::class.java))
        }

        Handler().postDelayed({
            if (!isClickSkip) {
                val i = Intent(this, Step5Activity::class.java)
                startActivity(i)
            }
        }, 10000)
    }
}