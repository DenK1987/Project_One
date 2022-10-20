package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class Step3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step3)

        var isClickSkip = false

        val textSkip = findViewById<TextView>(R.id.skip_step3)
        textSkip.setOnClickListener {
            isClickSkip = true
            startActivity(Intent(this, Step4Activity::class.java))
        }

        Handler().postDelayed({
            if (!isClickSkip) {
                val i = Intent(this, Step4Activity::class.java)
                startActivity(i)
            }
        }, 10000)
    }
}