package com.example.projectone.ui.listnotes.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.projectone.databinding.DialogInfoNoteBinding
import com.example.projectone.models.Note

class NotePlanningInfoDialog : DialogFragment() {

    private lateinit var binding: DialogInfoNoteBinding

    companion object {
        private const val NOTE_KEY = "note_key"

        fun newInstance(note: Note): NotePlanningInfoDialog {
            return NotePlanningInfoDialog().apply {
                arguments = bundleOf(
                    NOTE_KEY to note
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogInfoNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
}