package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val button = findViewById<AppCompatButton>(R.id.button_start)
        button.setOnClickListener {
            startActivity(Intent(this, Step2Activity::class.java))
        }

        val textLogin = findViewById<TextView>(R.id.login_start)
        textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}