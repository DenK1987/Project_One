package com.example.projectone.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectone.R
import com.example.projectone.databinding.ActivityMainBinding
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.repositories.UserStatus
import com.example.projectone.ui.addnote.AddNoteFragment
import com.example.projectone.ui.auth.LoginFragment
import com.example.projectone.ui.listnotes.ListOfNotesFragment
import com.example.projectone.ui.onboarding.StartAppFragment
import com.example.projectone.ui.profile.ProfileFragment
import com.example.projectone.ui.searchnotes.SearchFragment
import com.example.projectone.utils.navigationFragments
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectAndGoToStartScreen()

        bottomNavigation()
    }

    private fun selectAndGoToStartScreen() {
        when (sharedPreferencesRepository.getUserStatus()) {
            UserStatus.USER_SIGNUP.name ->
                navigationFragments(supportFragmentManager, ListOfNotesFragment())

            UserStatus.USER_LOGOUT.name ->
                navigationFragments(supportFragmentManager, LoginFragment())

            else -> navigationFragments(supportFragmentManager, StartAppFragment())
        }
    }

    private fun bottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            var fragment: Fragment = ListOfNotesFragment()

            when (item.itemId) {
                R.id.listNotes -> {
                    fragment = ListOfNotesFragment()
                }
                R.id.searchNote -> {
                    fragment = SearchFragment()
                }
                R.id.addNote -> {
                    fragment = AddNoteFragment()
                }
                R.id.profile -> {
                    fragment = ProfileFragment()
                }
            }

            navigationFragments(supportFragmentManager, fragment)

            true
        }
    }
}