package com.example.projectone.ui.listnotes.notesadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.R
import com.example.projectone.databinding.ItemNoteBinding
import com.example.projectone.models.Note
import com.example.projectone.models.NoteType
import com.example.projectone.utils.Constant.DATE_FORMAT
import com.example.projectone.utils.transformDateLongInString

class NotesViewHolder(
    private val binding: ItemNoteBinding,
    private val onClickNote: (Note) -> Unit,
    private val onClickInfo: (Note) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.apply {
            titleItem.text = note.title
            messageItem.text = note.message
            dateOfCreationItem.text =
                transformDateLongInString(dateFormat = DATE_FORMAT, date = note.dateOfCreation)
            infoItem.setOnClickListener {
                onClickInfo(note)
            }
        }

        binding.noteItem.setOnClickListener {
            onClickNote(note)
        }

        when (note.noteType) {
            NoteType.EQUAL_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.green),
                    drawable = R.drawable.bg_item_note_equal_current,
                    colorInfo = binding.root.context.getColor(R.color.green)
                )
            }
            NoteType.AFTER_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.blue),
                    drawable = R.drawable.bg_item_note_after_current,
                    colorInfo = binding.root.context.getColor(R.color.blue)
                )
            }
            NoteType.BEFORE_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.red),
                    drawable = R.drawable.bg_item_note_before_current,
                    colorInfo = binding.root.context.getColor(R.color.red)
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
        colorInfo: Int = binding.infoItem.context.getColor(R.color.text_input_layout),
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
        binding.infoItem.setColorFilter(colorInfo)
    }
}