package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity21 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_21)

        val textSignup = findViewById<TextView>(R.id.signup)
        textSignup.setOnClickListener {
            startActivity(Intent(this, Activity7::class.java))
        }
    }
}