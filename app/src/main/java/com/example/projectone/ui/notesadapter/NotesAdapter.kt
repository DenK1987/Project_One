package com.example.projectone.ui.notesadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.databinding.ItemNoteBinding
import com.example.projectone.model.Note

class NotesAdapter(
    private val onClickNote: (Note) -> Unit
) :
    RecyclerView.Adapter<NotesViewHolder>() {
    private var notes = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            binding = ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickNote = onClickNote
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}