package com.example.projectone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.projectone.LoginActivity
import com.example.projectone.R
import com.example.projectone.Step2Activity
import com.example.projectone.databinding.ActivityAddNoteBinding
import com.example.projectone.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDiscoverPlatform.setOnClickListener {
            startActivity(Intent(this, Step2Activity::class.java))
        }

        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}