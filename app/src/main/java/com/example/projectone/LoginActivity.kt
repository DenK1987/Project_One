package com.example.projectone

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectone.databinding.ActivityLoginBinding
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.isValid

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.emailLoginInputEditText.addTextWatcher(binding.emailLoginInputLayout)
        binding.passwordLoginInputEditText.addTextWatcher(binding.passwordLoginInputLayout)

        binding.buttonLogin.setOnClickListener {
            binding.emailLoginInputEditText.isValid(
                binding.emailLoginInputLayout,
                getString(R.string.text_error_on_emptiness)
            )
            binding.passwordLoginInputEditText.isValid(
                binding.passwordLoginInputLayout,
                getString(R.string.text_error_on_emptiness)
            )

            if (binding.emailLoginInputEditText.text.toString().isNotBlank() &&
                binding.passwordLoginInputEditText.text.toString().isNotBlank()
            ) {
                Toast.makeText(this, getString(R.string.message_valid), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, AddNoteActivity::class.java))
            }
        }
    }
}