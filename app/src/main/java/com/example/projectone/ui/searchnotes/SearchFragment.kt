package com.example.projectone.ui.searchnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectone.R
import com.example.projectone.databinding.FragmentSearchBinding
import com.example.projectone.ui.searchnotes.searchnoteadapter.SearchNoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listNotes.observe(viewLifecycleOwner) {
            (binding.listNotes.adapter as SearchNoteAdapter).setList(it)
        }

        binding.searchNoteInputEditText.doAfterTextChanged {
            viewModel.searchNotes(
                it.toString(),
                viewModel.getUserEmail().toString()
            )
        }

        binding.listNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = SearchNoteAdapter()
        }

        viewModel.getListNotes(viewModel.getUserEmail().toString())

        binding.buttonFilterNotes.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                setOnMenuItemClickListener(this@SearchFragment)
                menuInflater.inflate(R.menu.menu_popup_filter_notes, menu)
            }.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filterByTitle -> {
                viewModel.getListNotesSortedByTitle()
                true
            }
            R.id.filterByDateOfCreation -> {
                viewModel.getListNotesSortedByDateOfCreation()
                true
            }
            R.id.filterByScheduleDate -> {
                viewModel.getListNotesSortedByScheduleDate()
                true
            }
            else -> false
        }
    }
}