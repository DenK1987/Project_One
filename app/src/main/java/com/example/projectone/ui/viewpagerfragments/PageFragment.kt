package com.example.projectone.ui.viewpagerfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.projectone.R
import com.example.projectone.databinding.FragmentPageBinding
import com.example.projectone.ui.fragments.SignupFragment

private const val BACKGROUND_EXTRA = "background_extra"
private const val IMAGE_EXTRA = "image_extra"
private const val TEXT_EXTRA = "text_extra"

class PageFragment : Fragment() {

    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            arguments?.getInt(BACKGROUND_EXTRA)?.let { backgroundPage.setImageResource(it) }
            arguments?.getInt(IMAGE_EXTRA)?.let { imagePage.setImageResource(it) }
            arguments?.getInt(TEXT_EXTRA)?.let { textPage.setText(it) }
            skipStep.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignupFragment())
                    .addToBackStack("")
                    .commit()
            }
        }
    }

    companion object {
        fun getPagerFragmentInstance(backgroundId: Int, imageId: Int, textId: Int): PageFragment {
            return PageFragment().apply {
                arguments = bundleOf(
                    BACKGROUND_EXTRA to backgroundId,
                    IMAGE_EXTRA to imageId,
                    TEXT_EXTRA to textId
                )
            }
        }
    }
}