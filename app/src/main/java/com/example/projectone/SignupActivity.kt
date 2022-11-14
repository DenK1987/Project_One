package com.example.projectone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectone.databinding.ActivitySignupBinding
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.isEmailValid
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

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
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        firstNameLayout = binding.inputLayoutFirstName
        firstNameTextInputEditText = binding.inputEditTextFirstName

        lastNameLayout = binding.inputLayoutLastName
        lastNameTextInputEditText = binding.inputEditTextLastName

        emailLayout = binding.inputLayoutEmailSignup
        emailTextInputEditText = binding.inputEditTextEmailSignup

        passwordLayout = binding.inputLayoutPasswordSignup
        passwordTextInputEditText = binding.inputEditTextPasswordSignup

        firstNameTextInputEditText.addTextWatcher(firstNameLayout)
        lastNameTextInputEditText.addTextWatcher(lastNameLayout)
        emailTextInputEditText.addTextWatcher(emailLayout)
        passwordTextInputEditText.addTextWatcher(passwordLayout)

        binding.buttonSignup.setOnClickListener {
            validFirstName()
            validLastName()
            validEmail()
            validPassword()
        }
    }

    private fun validFirstName() {
        val firstName = firstNameTextInputEditText.text.toString()

        if (firstName.isBlank()) {
            firstNameLayout.error = getString(R.string.text_error_on_emptiness)
        }

        if (firstName.length !in MAX_LENGTH_NAME downTo MIN_LENGTH_NAME && firstName.isNotBlank()) {
            firstNameLayout.error = getString(R.string.text_error_by_number_characters_in_name)
        }
    }

    private fun validLastName() {
        val lastName = lastNameTextInputEditText.text.toString()

        if (lastName.isBlank()) {
            lastNameLayout.error = getString(R.string.text_error_on_emptiness)
        }

        if (lastName.length !in MAX_LENGTH_NAME downTo MIN_LENGTH_NAME && lastName.isNotBlank()) {
            lastNameLayout.error = getString(R.string.text_error_by_number_characters_in_name)
        }
    }

    private fun validEmail() {
        val email = emailTextInputEditText.text.toString()

        if (email.isBlank()) {
            emailLayout.error = getString(R.string.text_error_on_emptiness)
        }

        if (!emailTextInputEditText.isEmailValid() && email.isNotBlank()) {
            emailLayout.error = getString(R.string.text_error_email_incorrect_format)
        }
    }

    private fun validPassword() {
        val password = passwordTextInputEditText.text.toString()

        if (password.isBlank()) {
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
                    && password.isNotBlank() -> {
                passwordLayout.error =
                    getString(R.string.text_error_by_number_characters_in_password)
                return
            }
            filterUpperCasePassword.isBlank() && password.isNotBlank() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_big_letter)
                return
            }
            filterLowerCasePassword.isBlank() && password.isNotBlank() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_small_letter)
                return
            }
            filterDigitPassword.isBlank() && password.isNotBlank() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_digit)
                return
            }
            filterCharPassword.isBlank() && password.isNotBlank() -> {
                passwordLayout.error = getString(R.string.text_error_password_one_char)
                return
            }
        }
    }
}