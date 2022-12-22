package com.example.projectone.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.projectone.R
import com.example.projectone.databinding.FragmentProfileBinding
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.repositories.UserStatus
import com.example.projectone.ui.auth.LoginFragment
import com.example.projectone.ui.auth.SignupFragment
import com.example.projectone.utils.navigationFragments

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

        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        userEmail = sharedPreferencesRepository.getUserEmail()

        binding.userName.text = sharedPreferencesRepository.getUserFullName()

        binding.notesCount.text = getNotesCount()

        binding.deleteAllNotes.setOnClickListener { deleteAllNotes() }

        binding.logout.setOnClickListener { logOut() }

        binding.buttonDeleteProfile.setOnClickListener { deleteProfile() }
    }

    private fun getNotesCount(): String {
        return if (viewModel.getNotesCount(userEmail.toString()) == 1) {
            "${viewModel.getNotesCount(userEmail.toString())} ${getString(R.string.one_note)}"
        } else {
            "${viewModel.getNotesCount(userEmail.toString())} ${getString(R.string.not_one_note)}"
        }
    }

    private fun logOut() {
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())

        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.logout_of_profile_dialog))
            .setNegativeButton(getString(R.string.negative_button_cancel)) { _, _ -> }
            .setPositiveButton(getString(R.string.positive_button_exit)) { _, _ ->
                sharedPreferencesRepository.setUserStatus(UserStatus.USER_LOGOUT)

                navigationFragments(parentFragmentManager, LoginFragment())
            }
            .setCancelable(false)
            .show()
    }

    private fun deleteAllNotes() {
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
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())

        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_profile_dialog))
            .setNegativeButton(getString(R.string.negative_button_cancel)) { _, _ -> }
            .setPositiveButton(getString(R.string.positive_button_delete)) { _, _ ->
                sharedPreferencesRepository.setUserStatus(UserStatus.USER_DELETE)
                sharedPreferencesRepository.deleteUserProfile()

                viewModel.deleteAllNotes(userEmail.toString())
                viewModel.deleteUser(userEmail.toString())

                Toast.makeText(
                    requireContext(),
                    getString(R.string.profile_deleted),
                    Toast.LENGTH_LONG
                ).show()

                navigationFragments(parentFragmentManager, SignupFragment())
            }
            .setCancelable(false)
            .show()
    }
}