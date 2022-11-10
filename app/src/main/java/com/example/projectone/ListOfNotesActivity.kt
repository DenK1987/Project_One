package com.example.projectone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectone.databinding.ActivityListOfNotesBinding
import com.example.projectone.model.Note
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.ui.notesadapter.NotesAdapter
import com.example.projectone.utils.transformDateInString
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

private const val SHARE_CONTENT_TYPE = "text/plain"

class ListOfNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListOfNotesBinding

    private val repository = NotesRepository()

    private val notesAdapter = NotesAdapter(::shareNote, ::deleteNote)

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
            .setNegativeButton("Cancel") { _, _ -> }
            .setPositiveButton("Delete") { _, _ ->
                repository.deleteNote(note)
                notesAdapter.apply {
                    setList(repository.getListNotes())
                }
            }
            .setCancelable(false)
            .show()
    }
}