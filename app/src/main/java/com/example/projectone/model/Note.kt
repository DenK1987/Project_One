package com.example.projectone.model

import com.example.projectone.utils.transformDateInString
import java.util.*

data class Note(
    val title: String,
    val message: String,
    val dateOfCreation: Date = Date(System.currentTimeMillis()),
    val scheduleDate: Date? = null
) {

    val noteType: NoteType
        get() {
            if (scheduleDate == null) {
                return NoteType.CURRENT
            }
            return when {
                transformDateInString(
                    dateFormat = "dd/MM/yyyy",
                    date = scheduleDate
                ) == transformDateInString(
                    dateFormat = "dd/MM/yyyy",
                    date = Date(System.currentTimeMillis())
                ) -> NoteType.EQUAL_CURRENT

                scheduleDate.after(Date(System.currentTimeMillis())) -> NoteType.AFTER_CURRENT

                else -> NoteType.BEFORE_CURRENT
            }
        }
}


