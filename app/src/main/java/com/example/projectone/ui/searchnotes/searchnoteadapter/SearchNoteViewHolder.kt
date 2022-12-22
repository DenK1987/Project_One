package com.example.projectone.ui.searchnotes.searchnoteadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.R
import com.example.projectone.databinding.ItemSearchNoteBinding
import com.example.projectone.models.Note
import com.example.projectone.models.NoteType
import com.example.projectone.utils.Constant
import com.example.projectone.utils.transformDateLongInString

class SearchNoteViewHolder(
    private val binding: ItemSearchNoteBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.apply {
            titleItem.text = note.title
            messageItem.text = note.message
            dateOfCreationItem.text =
                transformDateLongInString(
                    dateFormat = Constant.DATE_FORMAT,
                    date = note.dateOfCreation
                )
            scheduleDateItem.text =
                if (note.scheduleDate == null) binding.root.context.getString(R.string.not_scheduled)
                else transformDateLongInString(
                    Constant.DATE_FORMAT,
                    note.scheduleDate
                )
        }

        when (note.noteType) {
            NoteType.EQUAL_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.green),
                    drawable = R.drawable.bg_item_search_note_equal_current
                )
            }
            NoteType.AFTER_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.blue),
                    drawable = R.drawable.bg_item_search_note_after_current
                )
            }
            NoteType.BEFORE_CURRENT -> {
                setColorNote(
                    colorText = binding.root.context.getColor(R.color.red),
                    drawable = R.drawable.bg_item_search_note_before_current
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

    @SuppressLint("ResourceAsColor")
    private fun setColorNote(
        colorText: Int,
        drawable: Int = R.drawable.bg_item_search_note_current,
        isDefaultColorTitle: Boolean = false
    ) {
        if (!isDefaultColorTitle) {
            binding.titleItem.setTextColor(colorText)
        } else {
            binding.titleItem.setTextColor(binding.titleItem.context.getColor(R.color.text))
        }
        binding.messageItem.setTextColor(colorText)
        binding.dateOfCreationItem.setTextColor(colorText)
        binding.scheduleDateItem.setTextColor(colorText)
        binding.root.setBackgroundResource(drawable)
    }
}