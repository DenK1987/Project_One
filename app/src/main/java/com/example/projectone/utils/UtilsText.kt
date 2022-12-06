package com.example.projectone.utils

import android.text.Editable
import android.text.TextWatcher
import com.example.projectone.R
import com.example.projectone.utils.Constant.MAX_LENGTH_NAME
import com.example.projectone.utils.Constant.MAX_LENGTH_PASSWORD
import com.example.projectone.utils.Constant.MIN_LENGTH_NAME
import com.example.projectone.utils.Constant.MIN_LENGTH_PASSWORD
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object Constant {
    const val TIME_DELAY = 5000L
    const val DATE_FORMAT = "dd/MM/yyyy"
    const val MAX_LENGTH_NAME = 255
    const val MIN_LENGTH_NAME = 3
    const val MAX_LENGTH_PASSWORD = 50
    const val MIN_LENGTH_PASSWORD = 6
}

fun TextInputEditText.addTextWatcher(layout: TextInputLayout) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (layout.error?.isNotBlank() == true) layout.error = null
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    })
}

fun TextInputEditText.isValid(layout: TextInputLayout, errorMessage: String): Boolean {
    return if (this.text.toString().isBlank()) {
        layout.error = errorMessage
        false
    } else {
        true
    }
}

fun TextInputEditText.validName(nameLayout: TextInputLayout) {
    val firstName = this.text.toString()

    if (firstName.isBlank()) {
        nameLayout.error = this.context.getString(R.string.text_error_on_emptiness)
    }

    if (firstName.length !in MAX_LENGTH_NAME downTo MIN_LENGTH_NAME && firstName.isNotBlank()) {
        nameLayout.error =
            this.context.getString(R.string.text_error_by_number_characters_in_name)
    }
}

fun TextInputEditText.validEmail(emailLayout: TextInputLayout) {
    val email = this.text.toString()

    if (email.isBlank()) {
        emailLayout.error = this.context.getString(R.string.text_error_on_emptiness)
    }

    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString())
            .matches() && email.isNotBlank()
    ) {
        emailLayout.error = this.context.getString(R.string.text_error_email_incorrect_format)
    }
}

fun TextInputEditText.validPassword(passwordLayout: TextInputLayout) {
    val password = this.text.toString()

    if (password.isBlank()) {
        passwordLayout.error = this.context.getString(R.string.text_error_on_emptiness)
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
                this.context.getString(R.string.text_error_by_number_characters_in_password)
            return
        }
        filterUpperCasePassword.isBlank() && password.isNotBlank() -> {
            passwordLayout.error =
                this.context.getString(R.string.text_error_password_one_big_letter)
            return
        }
        filterLowerCasePassword.isBlank() && password.isNotBlank() -> {
            passwordLayout.error =
                this.context.getString(R.string.text_error_password_one_small_letter)
            return
        }
        filterDigitPassword.isBlank() && password.isNotBlank() -> {
            passwordLayout.error = this.context.getString(R.string.text_error_password_one_digit)
            return
        }
        filterCharPassword.isBlank() && password.isNotBlank() -> {
            passwordLayout.error = this.context.getString(R.string.text_error_password_one_char)
            return
        }
    }
}