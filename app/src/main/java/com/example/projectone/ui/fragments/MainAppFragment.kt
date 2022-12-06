package com.example.projectone.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectone.R
import com.example.projectone.databinding.FragmentMainAppBinding
import com.example.projectone.ui.viewpagerfragments.ViewPageFragment

class MainAppFragment : Fragment() {

    private lateinit var binding: FragmentMainAppBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainAppBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDiscoverPlatform.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ViewPageFragment())
                .addToBackStack("")
                .commit()
        }
        binding.textLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .addToBackStack("")
                .commit()
        }
    }
}