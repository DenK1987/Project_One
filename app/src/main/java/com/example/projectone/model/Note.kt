package com.example.projectone.model

import android.os.Parcelable
import com.example.projectone.utils.Constant.DATE_FORMAT
import com.example.projectone.utils.transformDateInString
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Note(
    val title: String,
    val message: String,
    val dateOfCreation: Date = Date(System.currentTimeMillis()),
    val scheduleDate: Date? = null
) : Parcelable {

    val noteType: NoteType
        get() {
            if (scheduleDate == null) {
                return NoteType.CURRENT
            }
            return when {
                transformDateInString(
                    dateFormat = DATE_FORMAT,
                    date = scheduleDate
                ) == transformDateInString(
                    dateFormat = DATE_FORMAT,
                    date = Date(System.currentTimeMillis())
                ) -> NoteType.EQUAL_CURRENT

                scheduleDate.after(Date(System.currentTimeMillis())) -> NoteType.AFTER_CURRENT

                else -> NoteType.BEFORE_CURRENT
            }
        }
}


