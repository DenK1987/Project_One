package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText

class Activity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_7)

        val textLogin = findViewById<TextView>(R.id.login2)
        textLogin.setOnClickListener {
            startActivity(Intent(this, Activity21::class.java))
        }
    }
}