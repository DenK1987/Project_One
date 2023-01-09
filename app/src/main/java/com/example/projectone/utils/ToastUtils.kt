package com.example.projectone.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(textToast: String) {
    Toast.makeText(requireContext(), textToast, Toast.LENGTH_LONG).show()
}

