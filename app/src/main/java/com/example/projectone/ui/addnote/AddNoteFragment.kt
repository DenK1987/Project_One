package com.example.projectone.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.projectone.R
import com.example.projectone.databinding.FragmentAddNoteBinding
import com.example.projectone.models.Note
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.ui.listnotes.ListOfNotesFragment
import com.example.projectone.utils.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    private val viewModel: AddNoteViewModel by viewModels()

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

        binding.titleNoteInputEditText.addTextWatcher(binding.titleNoteInputLayout)
        binding.messageNoteInputEditText.addTextWatcher(binding.messageNoteInputLayout)

        binding.titleNoteInputLayout.setEndIconOnClickListener { showDatePicker() }

        binding.buttonAddNote.setOnClickListener {
            addNoteAndNavigate()
        }
    }

    private fun showDatePicker() {
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        val textTitle = binding.titleNoteInputEditText.text.toString()
        val textMessage = binding.messageNoteInputEditText.text.toString()
        val userEmail = sharedPreferencesRepository.getUserEmail().toString()

        binding.run {
            titleNoteInputEditText.isValid(
                titleNoteInputLayout,
                getString(R.string.text_error_on_emptiness)
            )
            messageNoteInputEditText.isValid(
                binding.messageNoteInputLayout,
                getString(R.string.text_error_on_emptiness)
            )

            titleNoteInputEditText.validateForMaxLengthOf50Characters(titleNoteInputLayout)
            messageNoteInputEditText.validateForMaxLengthOf140Characters(messageNoteInputLayout)
        }

        if (textTitle.isNotBlank()
            && textTitle.length <= Constant.MAX_LENGTH_TITLE
            && textMessage.isNotBlank()
            && textMessage.length <= Constant.MAX_LENGTH_MESSAGE
        ) {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(getString(R.string.title_calendar))
                    .setSelection(Calendar.getInstance().timeInMillis)
                    .build()
            datePicker.addOnPositiveButtonClickListener {
                viewModel.addNote(
                    note = Note(
                        title = textTitle,
                        message = textMessage,
                        scheduleDate = it,
                        dateOfCreation = System.currentTimeMillis(),
                        userEmail = userEmail
                    )
                )

                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
                    .menu.findItem(R.id.listNotes)
                    .isChecked = true

                navigationFragments(parentFragmentManager, ListOfNotesFragment())
            }
            datePicker.show(parentFragmentManager, "note")
        }
    }

    private fun addNoteAndNavigate() {
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        val textTitle = binding.titleNoteInputEditText.text.toString()
        val textMessage = binding.messageNoteInputEditText.text.toString()
        val userEmail = sharedPreferencesRepository.getUserEmail().toString()

        binding.run {
            titleNoteInputEditText.isValid(
                titleNoteInputLayout,
                getString(R.string.text_error_on_emptiness)
            )
            messageNoteInputEditText.isValid(
                binding.messageNoteInputLayout,
                getString(R.string.text_error_on_emptiness)
            )

            titleNoteInputEditText.validateForMaxLengthOf50Characters(titleNoteInputLayout)
            messageNoteInputEditText.validateForMaxLengthOf140Characters(messageNoteInputLayout)
        }

        if (textTitle.isNotBlank()
            && textTitle.length <= Constant.MAX_LENGTH_TITLE
            && textMessage.isNotBlank()
            && textMessage.length <= Constant.MAX_LENGTH_MESSAGE
        ) {
            viewModel.addNote(
                Note(
                    title = textTitle,
                    message = textMessage,
                    dateOfCreation = System.currentTimeMillis(),
                    scheduleDate = null,
                    userEmail = userEmail
                )
            )

            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation)
                .menu.findItem(R.id.listNotes)
                .isChecked = true

            navigationFragments(parentFragmentManager, ListOfNotesFragment())
        }
    }
}