package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.projectone.utils.addTextWatcher
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
            validation()
        }
    }

    private fun isNotEmpty(text: String): Boolean {
        return text.isNotEmpty()
    }

    private fun validation() {
        val firstName = firstNameTextInputEditText.text.toString()
        val lastName = lastNameTextInputEditText.text.toString()
        val email = emailTextInputEditText.text.toString()
        val password = passwordTextInputEditText.text.toString()

        if (!isNotEmpty(firstName)) {
            firstNameLayout.error = getString(R.string.text_error_on_emptiness)
        }
        if (!isNotEmpty(lastName)) {
            lastNameLayout.error = getString(R.string.text_error_on_emptiness)
        }
        if (!isNotEmpty(email)) {
            emailLayout.error = getString(R.string.text_error_on_emptiness)
        }
        if (!isNotEmpty(password)) {
            passwordLayout.error = getString(R.string.text_error_on_emptiness)
        }

        val filterEmail = email.filter {
            it == '@' && it == '.'
        }
        val filter2Email = email.filter {
            it == '@'
        }
        val filter3Email = email.filter {
            it == ' '
        }
        val filter4Email = email.filter {
            it == '.'
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
            firstName.length !in 255 downTo 3 && isNotEmpty(firstName) -> {
                firstNameLayout.error = getString(R.string.text_error_by_number_characters_in_name)
                return
            }
            lastName.length !in 255 downTo 3 && isNotEmpty(lastName) -> {
                lastNameLayout.error = getString(R.string.text_error_by_number_characters_in_name)
                return
            }
            filterEmail.length == 2 && email.length == 2 && isNotEmpty(email) -> {
                emailLayout.error = getString(R.string.text_error_email_input_format)
                return
            }
            filter2Email.length != 1 && isNotEmpty(email) -> {
                emailLayout.error = getString(R.string.text_error_email_for_number_dogs)
                return
            }
            filter3Email.isNotEmpty() && isNotEmpty(email) -> {
                emailLayout.error = getString(R.string.text_error_email_for_presence_spaces)
                return
            }
            filter4Email.isEmpty() && isNotEmpty(email) -> {
                emailLayout.error = getString(R.string.text_error_email_for_presence_dot)
                return
            }
            password.length !in 50 downTo 6 && isNotEmpty(password) -> {
                passwordLayout.error = getString(R.string.text_error_by_number_characters_in_password)
                return
            }
            filterUpperCasePassword.isEmpty() && isNotEmpty(password) -> {
                passwordLayout.error = getString(R.string.text_error_password_one_big_letter)
                return
            }
            filterLowerCasePassword.isEmpty() && isNotEmpty(password) -> {
                passwordLayout.error = getString(R.string.text_error_password_one_small_letter)
                return
            }
            filterDigitPassword.isEmpty() && isNotEmpty(password) -> {
                passwordLayout.error = getString(R.string.text_error_password_one_digit)
                return
            }
            filterCharPassword.isEmpty() && isNotEmpty(password) -> {
                passwordLayout.error = getString(R.string.text_error_password_one_char)
                return
            }
        }
    }
}