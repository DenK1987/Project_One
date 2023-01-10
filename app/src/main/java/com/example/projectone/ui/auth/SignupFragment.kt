package com.example.projectone.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.projectone.R
import com.example.projectone.databinding.FragmentSignupBinding
import com.example.projectone.models.User
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.repositories.UserStatus
import com.example.projectone.ui.listnotes.ListOfNotesFragment
import com.example.projectone.utils.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

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

        binding.run {
            textLogin.setOnClickListener {
                navigationFragments(parentFragmentManager, LoginFragment())
            }

            firstNameInputEditText.addTextWatcher(firstNameInputLayout)
            lastNameInputEditText.addTextWatcher(lastNameInputLayout)
            emailSignupInputEditText.addTextWatcher(emailSignupInputLayout)
            passwordSignupInputEditText.addTextWatcher(passwordSignupInputLayout)

            buttonSignup.setOnClickListener {
                lifecycleScope.launch { signUp() }
            }
        }
    }

    private suspend fun signUp() {
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        val firstName = binding.firstNameInputEditText.text.toString()
        val lastName = binding.lastNameInputEditText.text.toString()
        val email = binding.emailSignupInputEditText.text.toString()
        val password = binding.passwordSignupInputEditText.text.toString()

        binding.run {
            firstNameInputEditText.validName(firstNameInputLayout)
            lastNameInputEditText.validName(lastNameInputLayout)
            emailSignupInputEditText.validEmail(emailSignupInputLayout)
            passwordSignupInputEditText.validPassword(passwordSignupInputLayout)
        }

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

                sharedPreferencesRepository.run {
                    setUserStatus(UserStatus.USER_SIGNUP)
                    setUserEmail(email)
                    setUserPassword(password)
                    setUserFullName(firstName, lastName)
                }

                toast(getString(R.string.registration_is_successful))

                navigationFragments(parentFragmentManager, ListOfNotesFragment())
            } else {
                toast(getString(R.string.user_with_this_email_is_already_registered))
            }
        }
    }
}