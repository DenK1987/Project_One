package com.example.projectone.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectone.R
import com.example.projectone.databinding.FragmentSignupBinding
import com.example.projectone.ui.authorization.LoginFragment
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.validEmail
import com.example.projectone.utils.validName
import com.example.projectone.utils.validPassword

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

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

        binding.textLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .addToBackStack("")
                .commit()
        }

        binding.firstNameInputEditText.addTextWatcher(binding.firstNameInputLayout)
        binding.lastNameInputEditText.addTextWatcher(binding.lastNameInputLayout)
        binding.emailSignupInputEditText.addTextWatcher(binding.emailSignupInputLayout)
        binding.passwordSignupInputEditText.addTextWatcher(binding.passwordSignupInputLayout)

        binding.buttonSignup.setOnClickListener {
            binding.firstNameInputEditText.validName(binding.firstNameInputLayout)
            binding.lastNameInputEditText.validName(binding.lastNameInputLayout)
            binding.emailSignupInputEditText.validEmail(binding.emailSignupInputLayout)
            binding.passwordSignupInputEditText.validPassword(binding.passwordSignupInputLayout)
        }
    }
}