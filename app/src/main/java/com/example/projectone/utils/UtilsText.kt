package com.example.projectone.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

fun TextInputEditText.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this.text.toString()).matches()
}