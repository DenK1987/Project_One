package com.example.projectone.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectone.utils.Constant
import com.example.projectone.utils.transformDateLongInString
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "message")
    val message: String,
    @ColumnInfo(name = "dateOfCreation")
    val dateOfCreation: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "scheduleDate")
    val scheduleDate: Long? = null,
    @ColumnInfo(name = "userEmail")
    val userEmail: String
) : Parcelable {

    val noteType: NoteType
        get() {
            if (scheduleDate == null) {
                return NoteType.CURRENT
            }
            return when {
                transformDateLongInString(
                    dateFormat = Constant.DATE_FORMAT,
                    date = scheduleDate
                ) == transformDateLongInString(
                    dateFormat = Constant.DATE_FORMAT,
                    date = System.currentTimeMillis()
                ) -> NoteType.EQUAL_CURRENT

                scheduleDate > System.currentTimeMillis() -> NoteType.AFTER_CURRENT

                else -> NoteType.BEFORE_CURRENT
            }
        }
}