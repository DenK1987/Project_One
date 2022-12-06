package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectone.R
import com.example.projectone.databinding.FragmentAddNoteBinding
import com.example.projectone.model.Note
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.isValid
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    private val repository = NotesRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarBack.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ListOfNotesFragment())
                    .addToBackStack("")
                    .commit()
            }
        }

        binding.titleNoteInputEditText.addTextWatcher(binding.titleNoteInputLayout)
        binding.messageNoteInputEditText.addTextWatcher(binding.messageNoteInputLayout)

        binding.titleNoteInputLayout.setEndIconOnClickListener { showDatePicker() }

        binding.buttonAddNote.setOnClickListener {
            addNoteAndNavigate()
        }

    }

    private fun showDatePicker() {
        if (binding.titleNoteInputEditText.isValid(
                binding.titleNoteInputLayout,
                getString(R.string.text_error_on_emptiness)
            ) ||
            binding.messageNoteInputEditText.isValid(
                binding.messageNoteInputLayout,
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
                        title = binding.titleNoteInputEditText.text.toString(),
                        message = binding.messageNoteInputEditText.text.toString(),
                        scheduleDate = Date(it),
                        dateOfCreation = Date(System.currentTimeMillis())
                    )
                )
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ListOfNotesFragment())
                    .addToBackStack("")
                    .commit()
            }
            datePicker.show(parentFragmentManager, "note")
        }
    }

    private fun addNoteAndNavigate() {
        if (binding.titleNoteInputEditText.isValid(
                binding.titleNoteInputLayout,
                getString(R.string.text_error_on_emptiness)
            ) ||
            binding.messageNoteInputEditText.isValid(
                binding.messageNoteInputLayout,
                getString(R.string.text_error_on_emptiness)
            )
        ) {
            repository.addNote(
                Note(
                    binding.titleNoteInputEditText.text.toString(),
                    binding.messageNoteInputEditText.text.toString()
                )
            )
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ListOfNotesFragment())
                .addToBackStack("")
                .commit()
        }
    }
}