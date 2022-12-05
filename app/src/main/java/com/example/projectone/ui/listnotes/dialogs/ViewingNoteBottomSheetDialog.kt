package com.example.projectone.ui.listnotes.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectone.R
import com.example.projectone.databinding.DialogViewingNoteBinding
import com.example.projectone.model.Note
import com.example.projectone.ui.StartActivity
import com.example.projectone.utils.Constant.DATE_FORMAT
import com.example.projectone.utils.shareNote
import com.example.projectone.utils.transformDateInString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class ViewingNoteBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogViewingNoteBinding

    private var deleteNote: ((Note) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogViewingNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getParcelable<Note>(NOTE_KEY)

        with(binding) {
            titleNoteDialog.text = note?.title
            messageNoteDialog.text = note?.message
            dateOfCreationNoteDialog.text = transformDateInString(
                DATE_FORMAT,
                note?.dateOfCreation ?: Date(System.currentTimeMillis())
            )
            scheduleDateNoteDialog.text = if (note?.scheduleDate == null) getString(R.string.not_scheduled)
            else transformDateInString(
                DATE_FORMAT,
                note.scheduleDate
            )

            shareNoteDialog.setOnClickListener {
                if (note != null) (requireActivity() as? StartActivity)?.shareNote(note)
            }

            deleteNoteDialog.setOnClickListener {
                val deleteNote = this@ViewingNoteBottomSheetDialog.deleteNote
                if (deleteNote != null) note?.let { note -> deleteNote(note) }
                dismiss()
            }
        }
    }

    companion object {
        private const val NOTE_KEY = "note_key"

        fun newInstance(note: Note, deleteNote: (Note) -> Unit): ViewingNoteBottomSheetDialog =
            ViewingNoteBottomSheetDialog().apply {
                val bundle = Bundle()
                bundle.putParcelable(NOTE_KEY, note)
                arguments = bundle
                this.deleteNote = deleteNote
            }
    }
}