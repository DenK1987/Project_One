package com.example.projectone.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.projectone.R
import com.example.projectone.databinding.FragmentProfileBinding
import com.example.projectone.repositories.UserStatus
import com.example.projectone.ui.auth.LoginFragment
import com.example.projectone.ui.auth.SignupFragment
import com.example.projectone.utils.navigationFragments
import com.example.projectone.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    private var userEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userEmail = viewModel.getUserEmail()

        binding.run {
            userName.text = viewModel.getUserFullName()

            lifecycleScope.launch {
                val count = viewModel.getNotesCount(userEmail.toString())
                notesCount.text = resources.getQuantityString(R.plurals.plurals_note, count, count)
            }

            deleteAllNotes.setOnClickListener { lifecycleScope.launch { deleteAllNotes() } }

            logout.setOnClickListener { logOut() }

            buttonDeleteProfile.setOnClickListener { deleteProfile() }
        }
    }

    private fun logOut() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.logout_of_profile_dialog))
            .setNegativeButton(getString(R.string.negative_button_cancel)) { _, _ -> }
            .setPositiveButton(getString(R.string.positive_button_exit)) { _, _ ->
                viewModel.setUserStatus(UserStatus.USER_LOGOUT)

                navigationFragments(parentFragmentManager, LoginFragment())
            }
            .setCancelable(false)
            .show()
    }

    private suspend fun deleteAllNotes() {
        if (viewModel.getNotesCount(userEmail.toString()) > 0) {
            AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.delete_all_notes_dialog))
                .setNegativeButton(getString(R.string.negative_button_cancel)) { _, _ -> }
                .setPositiveButton(getString(R.string.positive_button_delete)) { _, _ ->
                    viewModel.deleteAllNotes(userEmail.toString())
                    binding.notesCount.text = getString(R.string.zero_notes)
                }
                .setCancelable(false)
                .show()
        }
    }

    private fun deleteProfile() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_profile_dialog))
            .setNegativeButton(getString(R.string.negative_button_cancel)) { _, _ -> }
            .setPositiveButton(getString(R.string.positive_button_delete)) { _, _ ->
                viewModel.run {
                    setUserStatus(UserStatus.USER_DELETE)
                    deleteUserProfile()
                    deleteAllNotes(userEmail.toString())
                    deleteUser(userEmail.toString())
                }
                toast(getString(R.string.profile_deleted))

                navigationFragments(parentFragmentManager, SignupFragment())
            }
            .setCancelable(false)
            .show()
    }
}