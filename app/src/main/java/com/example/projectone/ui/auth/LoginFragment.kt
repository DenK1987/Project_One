package com.example.projectone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.projectone.R
import com.example.projectone.databinding.FragmentLoginBinding
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.repositories.UserStatus
import com.example.projectone.ui.addnote.AddNoteFragment
import com.example.projectone.ui.listnotes.ListOfNotesFragment
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.isValid
import com.example.projectone.utils.navigationFragments
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: AuthViewModel by viewModels()

    private var bottomNavigation: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigation = requireActivity().findViewById(R.id.bottomNavigation)
        bottomNavigation?.visibility = View.GONE

        binding.textSignup.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SignupFragment())
                .addToBackStack("")
                .commit()
        }

        binding.emailLoginInputEditText.addTextWatcher(binding.emailLoginInputLayout)
        binding.passwordLoginInputEditText.addTextWatcher(binding.passwordLoginInputLayout)

        binding.buttonLogin.setOnClickListener {
            logIn()
        }
    }

    private fun logIn() {
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        val email = binding.emailLoginInputEditText.text.toString()
        val password = binding.passwordLoginInputEditText.text.toString()

        binding.emailLoginInputEditText.isValid(
            binding.emailLoginInputLayout,
            getString(R.string.text_error_on_emptiness)
        )
        binding.passwordLoginInputEditText.isValid(
            binding.passwordLoginInputLayout,
            getString(R.string.text_error_on_emptiness)
        )

        if (email.isNotBlank() && password.isNotBlank()) {
            val user = viewModel.getUser(email)

            if (user == null) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.user_is_not_registered_please_register),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (binding.passwordLoginInputEditText.text.toString() != user.password) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.incorrect_login_or_password),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.message_valid),
                        Toast.LENGTH_LONG
                    ).show()

                    sharedPreferencesRepository.setUserStatus(UserStatus.USER_SIGNUP)
                    sharedPreferencesRepository.setUserEmail(email)

                    bottomNavigation?.menu?.findItem(R.id.listNotes)?.isChecked = true

                    navigationFragments(parentFragmentManager, ListOfNotesFragment())
                }
            }
        }
    }
}