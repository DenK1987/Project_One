package com.example.projectone

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.projectone.utils.addTextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textSignup = findViewById<TextView>(R.id.signup_login)
        textSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        val emailLayout = findViewById<TextInputLayout>(R.id.input_layout_email_login)
        val emailTextInputEditText =
            findViewById<TextInputEditText>(R.id.input_edit_text_email_login)

        val passwordLayout = findViewById<TextInputLayout>(R.id.input_layout_password_login)
        val passwordTextInputEditText =
            findViewById<TextInputEditText>(R.id.input_edit_text_password_login)

        val button = findViewById<AppCompatButton>(R.id.button_login)
        button.setOnClickListener {
            if (isValidEmailOrPassword(emailTextInputEditText.text.toString())) {

            } else {
                emailLayout.error = getString(R.string.text_error_on_emptiness)
            }
            if (isValidEmailOrPassword(passwordTextInputEditText.text.toString())) {

            } else {
                passwordLayout.error = getString(R.string.text_error_on_emptiness)
            }
            if (isValidEmailOrPassword(emailTextInputEditText.text.toString()) &&
                isValidEmailOrPassword(passwordTextInputEditText.text.toString())) {
                Toast.makeText(this, getString(R.string.message_valid), Toast.LENGTH_LONG).show()
            }
        }

        emailTextInputEditText.addTextWatcher(emailLayout)
        passwordTextInputEditText.addTextWatcher(passwordLayout)

    }

    private fun isValidEmailOrPassword(text: String): Boolean {
        return text.isNotEmpty()
    }
}