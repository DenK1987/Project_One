package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.isEmailValid
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {

    private lateinit var firstNameLayout: TextInputLayout
    private lateinit var firstNameTextInputEditText: TextInputEditText

    private lateinit var lastNameLayout: TextInputLayout
    private lateinit var lastNameTextInputEditText: TextInputEditText

    private lateinit var emailLayout: TextInputLayout
    private lateinit var emailTextInputEditText: TextInputEditText

    private lateinit var passwordLayout: TextInputLayout
    private lateinit var passwordTextInputEditText: TextInputEditText

    companion object {
        const val MAX_LENGTH_NAME = 255
        const val MIN_LENGTH_NAME = 3
        const val MAX_LENGTH_PASSWORD = 50
        const val MIN_LENGTH_PASSWORD = 6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val textLogin = findViewById<TextView>(R.id.login_signup)
        textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        firstNameLayout = findViewById(R.id.input_layout_first_name)
        firstNameTextInputEditText = findViewById(R.id.input_edit_text_first_name)

        lastNameLayout = findViewById(R.id.input_layout_last_name)
        lastNameTextInputEditText = findViewById(R.id.input_edit_text_last_name)

        emailLayout = findViewById(R.id.input_layout_email_signup)
        emailTextInputEditText = findViewById(R.id.input_edit_text_email_signup)

        passwordLayout = findViewById(R.id.input_layout_password_signup)
        passwordTextInputEditText = findViewById(R.id.input_edit_text_password_signup)

        firstNameTextInputEditText.addTextWatcher(firstNameLayout)
        lastNameTextInputEditText.addTextWatcher(lastNameLayout)
        emailTextInputEditText.addTextWatcher(emailLayout)
        passwordTextInputEditText.addTextWatcher(passwordLayout)

        val button = findViewById<AppCompatButton>(R.id.button_signup)
        button.setOnClickListener {
            validFirstName()
            validLastName()
            validEmail()
            validPassword()
        }
    }

    private fun validFirstName() {
        val firstName = firstNameTextInputEditText.text.toString()

        if (firstName.isEmpty()) {
            firstNameLayout.error = getString(R.string.text_error_on_emptiness)
        }

        if (firstName.length !in MAX_LENGTH_NAME downTo MIN_LENGTH_NAME && firstName.isNotEmpty()) {
            firstNameLayout.error = getString(R.string.text_error_by_number_characters_in_name)
        }
    }

    private fun validLastName() {
        val lastName = lastNameTextInputEditText.text.toString()

        if (lastName.isEmpty()) {
            lastNameLayout.error = getString(R.string.text_error_on_emptiness)
        }

        if (lastName.length !in MAX_LENGTH_NAME downTo MIN_LENGTH_NAME && lastName.isNotEmpty()) {
            lastNameLayout.error = getString(R.string.text_error_by_number_characters_in_name)
        }
    }

    private fun validEmail() {
        val email = emailTextInputEditText.text.toString()

        if (email.isEmpty()) {
            emailLayout.error = getString(R.string.text_error_on_emptiness)
        }

        if (!emailTextInputEditText.isEmailValid() && email.isNotEmpty()) {
            emailLayout.error = getString(R.string.text_error_email_incorrect_format)
        }
    }

    private fun validPassword() {
        val password = passwordTextInputEditText.text.toString()

        if (password.isEmpty()) {
            passwordLayout.error = getString(R.string.text_error_on_emptiness)
        }

        val filterUpperCasePassword = password.filter {
            it.isUpperCase()
        }
        val filterLowerCasePassword = password.filter {
            it.isLowerCase()
        }
        val filterDigitPassword = password.filter {
            it.isDigit()
        }
        val filterCharPassword = password.filter {
            it.isLetter().not() && it.isDigit().not()
        }

        when {
            password.length !in MAX_LENGTH_PASSWORD downTo MIN_LENGTH_PASSWORD
                    && password.isNotEmpty() -> {
                passwordLayout.error =
                    getString(R.string.text_error_by_number_characters_in_password)
                return
            }
            filterUpperCasePassword.isEmpty() && password.isNotEmpty() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_big_letter)
                return
            }
            filterLowerCasePassword.isEmpty() && password.isNotEmpty() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_small_letter)
                return
            }
            filterDigitPassword.isEmpty() && password.isNotEmpty() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_digit)
                return
            }
            filterCharPassword.isEmpty() && password.isNotEmpty() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_char)
                return
            }
        }
    }
}