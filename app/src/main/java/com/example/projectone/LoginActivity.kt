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

        val emailLayout = binding.inputLayoutEmailLogin
        val emailTextInputEditText = binding.inputEditTextEmailLogin

        val passwordLayout = binding.inputLayoutPasswordLogin
        val passwordTextInputEditText = binding.inputEditTextPasswordLogin

        emailTextInputEditText.addTextWatcher(emailLayout)
        passwordTextInputEditText.addTextWatcher(passwordLayout)

        binding.buttonLogin.setOnClickListener {
            emailTextInputEditText.isValid(emailLayout, getString(R.string.text_error_on_emptiness))
            passwordTextInputEditText.isValid(
                passwordLayout,
                getString(R.string.text_error_on_emptiness)
            )

            if (emailTextInputEditText.text.toString().isNotEmpty() &&
                passwordTextInputEditText.text.toString().isNotEmpty()
            ) {
                Toast.makeText(this, getString(R.string.message_valid), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, AddNoteActivity::class.java))
            }
        }
    }
}