package com.example.projectone.ui.viewpagerfragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.projectone.R
import com.example.projectone.ui.viewpagerfragments.PageFragment.Companion.getPagerFragmentInstance

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int = 5

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> getPagerFragmentInstance(
                R.drawable.bg_bubbles_step2,
                R.drawable.image_planet_step2,
                R.string.find_projects_from_companies_everywhere_in_the_word
            )
            1 -> getPagerFragmentInstance(
                R.drawable.bg_bubbles_step3,
                R.drawable.image_dollars_in_circles_step3,
                R.string.make_money_while_working_on_awesome_projects
            )
            2 -> getPagerFragmentInstance(
                R.drawable.bg_bubbles_step4,
                R.drawable.image_phone_step4,
                R.string.chat_with_others_freelancers
            )
            3 -> getPagerFragmentInstance(
                R.drawable.bg_bubbles_step5,
                R.drawable.image_arrow_step5,
                R.string.work_hard_and_level_up
            )
            else -> getPagerFragmentInstance(
                R.drawable.bg_bubbles_step6,
                R.drawable.image_heart_step6,
                R.string.enjoy_your_progress
            )
        }
    }
}