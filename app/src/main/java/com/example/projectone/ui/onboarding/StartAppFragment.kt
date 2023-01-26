package com.example.projectone.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectone.databinding.FragmentStartAppBinding
import com.example.projectone.ui.auth.LoginFragment
import com.example.projectone.ui.onboarding.viewpagerfragments.ViewPageFragment
import com.example.projectone.utils.navigationFragments
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartAppFragment : Fragment() {

    private lateinit var binding: FragmentStartAppBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartAppBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDiscoverPlatform.setOnClickListener {
            navigationFragments(parentFragmentManager, ViewPageFragment())
        }

        binding.textLogin.setOnClickListener {
            navigationFragments(parentFragmentManager, LoginFragment())
        }
    }
}