package com.example.projectone.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectone.R
import com.example.projectone.model.Note
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.ui.notesadapter.NotesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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

fun Fragment.deleteNote(note: Note, repository: NotesRepository, notesAdapter: NotesAdapter) {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(note.title)
        .setMessage(note.message)
        .setNegativeButton(getString(R.string.negative_button_cancel)) { _, _ -> }
        .setPositiveButton(getString(R.string.positive_button_delete)) { _, _ ->
            repository.deleteNote(note)
            notesAdapter.apply {
                setList(repository.getListNotes())
            }
        }
        .setCancelable(false)
        .show()
}
