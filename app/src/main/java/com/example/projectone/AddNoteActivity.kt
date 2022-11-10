package com.example.projectone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectone.databinding.ActivityAddNoteBinding
import com.example.projectone.model.Note
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.isValid
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding

    private val repository = NotesRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.toolbarCustom) {
            toolbarBack.setOnClickListener {
                startActivity(Intent(this@AddNoteActivity, ListOfNotesActivity::class.java))
            }
        }

        binding.inputEditTextTitleAddNote.addTextWatcher(binding.inputLayoutTitleAddNote)
        binding.inputEditTextMessageAddNote.addTextWatcher(binding.inputLayoutMessageAddNote)

        binding.inputLayoutTitleAddNote.setEndIconOnClickListener { showDatePicker() }

        binding.buttonAddNote.setOnClickListener {
            addNoteAndNavigate()
        }
    }

    private fun showDatePicker() {
        if (binding.inputEditTextTitleAddNote.isValid(
                binding.inputLayoutTitleAddNote,
                getString(R.string.text_error_on_emptiness)
            ) ||
            binding.inputEditTextMessageAddNote.isValid(
                binding.inputLayoutMessageAddNote,
                getString(R.string.text_error_on_emptiness)
            )
        ) {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.title_calendar))
                    .setSelection(Calendar.getInstance().timeInMillis)
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                repository.addNote(
                    note = Note(
                        title = binding.inputEditTextTitleAddNote.text.toString(),
                        message = binding.inputEditTextMessageAddNote.text.toString(),
                        scheduleDate = Date(it),
                        dateOfCreation = Date(it)
                    )
                )
                startActivity(Intent(this, ListOfNotesActivity::class.java))
            }
            datePicker.show(supportFragmentManager, "note")
        }
    }

    private fun addNoteAndNavigate() {
        if (binding.inputEditTextTitleAddNote.isValid(
                binding.inputLayoutTitleAddNote,
                getString(R.string.text_error_on_emptiness)
            ) ||
            binding.inputEditTextMessageAddNote.isValid(
                binding.inputLayoutMessageAddNote,
                getString(R.string.text_error_on_emptiness)
            )
        ) {
            repository.addNote(
                Note(
                    binding.inputEditTextTitleAddNote.text.toString(),
                    binding.inputEditTextMessageAddNote.text.toString()
                )
            )
            startActivity(Intent(this, ListOfNotesActivity::class.java))
        }
    }
}
