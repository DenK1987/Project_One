package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectone.R
import com.example.projectone.databinding.FragmentLoginBinding
import com.example.projectone.utils.addTextWatcher
import com.example.projectone.utils.isValid

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

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

        binding.textSignup.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SignupFragment())
                .addToBackStack("")
                .commit()
        }

        binding.emailLoginInputEditText.addTextWatcher(binding.emailLoginInputLayout)
        binding.passwordLoginInputEditText.addTextWatcher(binding.passwordLoginInputLayout)

        binding.buttonLogin.setOnClickListener {
            binding.emailLoginInputEditText.isValid(
                binding.emailLoginInputLayout,
                getString(R.string.text_error_on_emptiness)
            )
            binding.passwordLoginInputEditText.isValid(
                binding.passwordLoginInputLayout,
                getString(R.string.text_error_on_emptiness)
            )

            if (binding.emailLoginInputEditText.text.toString().isNotBlank() &&
                binding.passwordLoginInputEditText.text.toString().isNotBlank()
            ) {
                Toast.makeText(requireContext(), getString(R.string.message_valid), Toast.LENGTH_LONG).show()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AddNoteFragment())
                    .addToBackStack("")
                    .commit()
            }
        }
    }
}