package com.example.projectone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Step2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step2)

        var isClickSkip = false

        val textSkip = findViewById<TextView>(R.id.skip_step2)
        textSkip.setOnClickListener {
            isClickSkip = true
            startActivity(Intent(this, Step3Activity::class.java))
        }

        Handler().postDelayed({
            if (!isClickSkip) {
                val i = Intent(this, Step3Activity::class.java)
                startActivity(i)
            }
        }, 10000)
    }
}