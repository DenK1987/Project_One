package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<AppCompatButton>(R.id.button)
        button.setOnClickListener {
            startActivity(Intent(this, Activity2::class.java))
        }

        val textLogin = findViewById<TextView>(R.id.login)
        textLogin.setOnClickListener {
            startActivity(Intent(this, Activity21::class.java))
        }
    }
}