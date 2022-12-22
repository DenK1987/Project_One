package com.example.projectone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.projectone.R
import com.example.projectone.databinding.FragmentSignupBinding
import com.example.projectone.models.User
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.repositories.UserStatus
import com.example.projectone.ui.listnotes.ListOfNotesFragment
import com.example.projectone.utils.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private val viewModel: AuthViewModel by viewModels()

    private var bottomNavigation: BottomNavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigation = requireActivity().findViewById(R.id.bottomNavigation)
        bottomNavigation?.visibility = View.GONE

        binding.textLogin.setOnClickListener {
            navigationFragments(parentFragmentManager, LoginFragment())
        }

        binding.firstNameInputEditText.addTextWatcher(binding.firstNameInputLayout)
        binding.lastNameInputEditText.addTextWatcher(binding.lastNameInputLayout)
        binding.emailSignupInputEditText.addTextWatcher(binding.emailSignupInputLayout)
        binding.passwordSignupInputEditText.addTextWatcher(binding.passwordSignupInputLayout)

        binding.buttonSignup.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        val firstName = binding.firstNameInputEditText.text.toString()
        val lastName = binding.lastNameInputEditText.text.toString()
        val email = binding.emailSignupInputEditText.text.toString()
        val password = binding.passwordSignupInputEditText.text.toString()

        binding.firstNameInputEditText.validName(binding.firstNameInputLayout)
        binding.lastNameInputEditText.validName(binding.lastNameInputLayout)
        binding.emailSignupInputEditText.validEmail(binding.emailSignupInputLayout)
        binding.passwordSignupInputEditText.validPassword(binding.passwordSignupInputLayout)

        if (binding.firstNameInputEditText.isValidName()
            && binding.lastNameInputEditText.isValidName()
            && binding.emailSignupInputEditText.isValidEmail()
            && binding.passwordSignupInputEditText.isValidPassword()
        ) {
            val user = viewModel.getUser(email)

            if (user == null) {
                val newUser = User(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )
                viewModel.addUser(newUser)

                sharedPreferencesRepository.setUserStatus(UserStatus.USER_SIGNUP)
                sharedPreferencesRepository.setUserEmail(email)
                sharedPreferencesRepository.setUserPassword(password)
                sharedPreferencesRepository.setUserFullName(firstName, lastName)

                Toast.makeText(
                    requireContext(),
                    getString(R.string.registration_is_successful),
                    Toast.LENGTH_LONG
                ).show()

                navigationFragments(parentFragmentManager, ListOfNotesFragment())
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.user_with_this_email_is_already_registered),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}