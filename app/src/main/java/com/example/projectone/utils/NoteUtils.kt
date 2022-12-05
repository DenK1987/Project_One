package com.example.projectone.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.projectone.R
import com.example.projectone.model.Note

private const val SHARE_CONTENT_TYPE = "text/plain"

fun AppCompatActivity.shareNote(note: Note) {
    Intent(Intent.ACTION_SEND)
        .apply {
            type = SHARE_CONTENT_TYPE
            putExtra(Intent.EXTRA_TEXT, note.title)
            putExtra(Intent.EXTRA_SUBJECT, note.message)
        }
        .also { intent ->
            startActivity(
                Intent.createChooser(intent, getString(R.string.share_note))
            )
        }
}
