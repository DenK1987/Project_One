package com.example.projectone.ui.listnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectone.R
import com.example.projectone.databinding.FragmentListOfNotesBinding
import com.example.projectone.model.Note
import com.example.projectone.ui.addnote.AddNoteFragment
import com.example.projectone.ui.authorization.LoginFragment
import com.example.projectone.ui.listnotes.dialogs.NotePlanningInfoDialog
import com.example.projectone.ui.listnotes.notesadapter.NotesAdapter
import com.example.projectone.ui.listnotes.dialogs.ViewingNoteBottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ListOfNotesFragment : Fragment() {

    private lateinit var binding: FragmentListOfNotesBinding

    private val viewModel: ListNotesViewModel by viewModels()

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
            adapter = NotesAdapter(
                onClickNote = { note -> onClickNote(note) },
                onClickInfo = { note -> onClickInfo(note) }
            )
        }

        viewModel.listNotes.observe(viewLifecycleOwner) {
            (binding.listOfNotes.adapter as NotesAdapter).setList(it)
        }

        viewModel.getListNotes()
    }

    private fun onClickNote(note: Note) {
        ViewingNoteBottomSheetDialog.newInstance(note, ::deleteNote).show(childFragmentManager, "")
    }

    private fun onClickInfo(note: Note) {
        NotePlanningInfoDialog.newInstance(note).show(childFragmentManager, "")
    }

    private fun deleteNote(note: Note) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(note.title)
            .setMessage(note.message)
            .setNegativeButton(getString(R.string.negative_button_cancel)) { _, _ -> }
            .setPositiveButton(getString(R.string.positive_button_delete)) { _, _ ->
                viewModel.deleteNote(note)
                viewModel.listNotes.observe(viewLifecycleOwner) {
                    (binding.listOfNotes.adapter as NotesAdapter).setList(it)
                }
                viewModel.getListNotes()
            }
            .setCancelable(false)
            .show()
    }
}