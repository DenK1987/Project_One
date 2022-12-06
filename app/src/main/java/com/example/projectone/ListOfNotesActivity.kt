package com.example.projectone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectone.databinding.ActivityListOfNotesBinding
import com.example.projectone.model.Note
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.ui.fragments.ViewingNoteBottomSheetDialog
import com.example.projectone.ui.notesadapter.NotesAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private const val SHARE_CONTENT_TYPE = "text/plain"

class ListOfNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListOfNotesBinding

    private val repository = NotesRepository()

    private val notesAdapter = NotesAdapter(::onClickNote)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.toolbarCustom) {
            toolbarBack.setOnClickListener {
                startActivity(Intent(this@ListOfNotesActivity, LoginActivity::class.java))
            }
            toolbarTitle.text = getString(R.string.logout)
            toolbarAction.visibility = View.VISIBLE
            toolbarAction.setOnClickListener {
                startActivity(Intent(this@ListOfNotesActivity, AddNoteActivity::class.java))
            }
        }

        binding.listOfNotes.apply {
            layoutManager = LinearLayoutManager(this@ListOfNotesActivity)
            adapter = notesAdapter.apply {
                setList(repository.getListNotes())
            }
        }
    }

    private fun onClickNote(note: Note) {
        ViewingNoteBottomSheetDialog()
    }

    private fun shareNote(note: Note) {
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

    private fun deleteNote(note: Note) {
        MaterialAlertDialogBuilder(this)
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
}