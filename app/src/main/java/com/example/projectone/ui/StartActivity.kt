package com.example.projectone.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectone.R
import com.example.projectone.databinding.ActivityStartBinding
import com.example.projectone.ui.fragments.MainAppFragment

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainAppFragment())
            .commit()
    }
}