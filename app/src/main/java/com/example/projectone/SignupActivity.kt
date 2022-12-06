package com.example.projectone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectone.databinding.ActivitySignupBinding
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.validEmail
import com.example.projectone.utils.validName
import com.example.projectone.utils.validPassword

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.firstNameInputEditText.addTextWatcher(binding.firstNameInputLayout)
        binding.lastNameInputEditText.addTextWatcher(binding.lastNameInputLayout)
        binding.emailSignupInputEditText.addTextWatcher(binding.emailSignupInputLayout)
        binding.passwordSignupInputEditText.addTextWatcher(binding.passwordSignupInputLayout)

        binding.buttonSignup.setOnClickListener {
            binding.firstNameInputEditText.validName(binding.firstNameInputLayout)
            binding.lastNameInputEditText.validName(binding.lastNameInputLayout)
            binding.emailSignupInputEditText.validEmail(binding.emailSignupInputLayout)
            binding.passwordSignupInputEditText.validPassword(binding.passwordSignupInputLayout)
        }
    }
}