package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectone.R
import com.example.projectone.databinding.FragmentListOfNotesBinding
import com.example.projectone.model.Note
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.ui.notesadapter.NotesAdapter
import com.example.projectone.utils.deleteNote

class ListOfNotesFragment : Fragment() {

    private lateinit var binding: FragmentListOfNotesBinding

    private val repository = NotesRepository()

    private val notesAdapter = NotesAdapter(::onClickNote)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarCustom) {
            toolbarBack.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment())
                    .addToBackStack("")
                    .commit()
            }
            toolbarTitle.text = getString(R.string.logout)
            toolbarAction.visibility = View.VISIBLE
            toolbarAction.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AddNoteFragment())
                    .addToBackStack("")
                    .commit()
            }
        }

        binding.listOfNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notesAdapter.apply {
                setList(repository.getListNotes())
            }
        }
    }

    private fun onClickNote(note: Note) {
        ViewingNoteBottomSheetDialog.newInstance(note, ::deleteNote).show(childFragmentManager, "")
    }

    private fun deleteNote(note: Note) {
        this.deleteNote(note, repository, notesAdapter)
    }
}