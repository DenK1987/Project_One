package com.example.projectone.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun transformDateLongInString(dateFormat: String, date: Long): String {
    try {
        val _dateFormat: DateFormat = SimpleDateFormat(dateFormat)
        return _dateFormat.format(Date(date))
    } catch (exception: Exception) {
    }
    return ""
}
