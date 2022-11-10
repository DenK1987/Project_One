package com.example.projectone.ui.notesadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.R
import com.example.projectone.databinding.ItemNoteBinding
import com.example.projectone.model.Note
import com.example.projectone.model.NoteType
import com.example.projectone.utils.transformDateInString

class NotesViewHolder(
    private val binding: ItemNoteBinding,
    private val actionShare: (Note) -> Unit,
    private val deleteNote: (Note) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.apply {
            titleItem.text = note.title
            messageItem.text = note.message
            dateOfCreationItem.text =
                transformDateInString(dateFormat = "dd/MM/yyyy", date = note.dateOfCreation)
            shareItem.setOnClickListener {
                actionShare(note)
            }
        }
        binding.noteItem.setOnClickListener {
            deleteNote(note)
        }

        when (note.noteType) {
            NoteType.EQUAL_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.green),
                    drawable = R.drawable.bg_item_note_equal_current,
                    colorShare = binding.root.context.getColor(R.color.green)
                )
            }
            NoteType.AFTER_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.blue),
                    drawable = R.drawable.bg_item_note_after_current,
                    colorShare = binding.root.context.getColor(R.color.blue)
                )
            }
            NoteType.BEFORE_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.red),
                    drawable = R.drawable.bg_item_note_before_current,
                    colorShare = binding.root.context.getColor(R.color.red)
                )
            }
            NoteType.CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.text_input_layout),
                    isDefaultColorTitle = true
                )
            }
        }
    }

    private fun setColorNote(
        colorText: Int,
        drawable: Int = R.drawable.bg_item_note_current,
        colorShare: Int = binding.shareItem.context.getColor(R.color.text_input_layout),
        isDefaultColorTitle: Boolean = false
    ) {
        if (!isDefaultColorTitle) {
            binding.titleItem.setTextColor(colorText)
        } else {
            binding.titleItem.setTextColor(binding.titleItem.context.getColor(R.color.text))
        }
        binding.messageItem.setTextColor(colorText)
        binding.dateOfCreationItem.setTextColor(colorText)

        binding.noteItem.setBackgroundResource(drawable)

        binding.shareItem.setColorFilter(colorShare)
    }
}