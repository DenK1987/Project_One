package com.example.projectone.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun transformDateInString(dateFormat: String, date: Date): String {
    try {
        val _dateFormat: DateFormat = SimpleDateFormat(dateFormat)
        return _dateFormat.format(date)
    } catch (exception: Exception) {
    }
    return ""
}
