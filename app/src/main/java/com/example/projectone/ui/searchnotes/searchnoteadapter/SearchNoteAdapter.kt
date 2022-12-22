package com.example.projectone.ui.searchnotes.searchnoteadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.databinding.ItemSearchNoteBinding
import com.example.projectone.models.Note

class SearchNoteAdapter : RecyclerView.Adapter<SearchNoteViewHolder>() {

    private var listNotes = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNoteViewHolder {
        return SearchNoteViewHolder(
            ItemSearchNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchNoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listNotes: List<Note>) {
        this.listNotes = listNotes
        notifyDataSetChanged()
    }
}