package com.example.projectone.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.projectone.R

fun navigationFragments(manager: FragmentManager, fragment: Fragment) {
    manager.beginTransaction()
        .replace(R.id.container, fragment)
        .commit()
}