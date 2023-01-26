package com.example.projectone.repositories

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_preferences"
private const val GLOBAL_PREFERENCES = "global_preferences"
private const val USER_EMAIL = "user_email"
private const val USER_PASSWORD = "user_password"
private const val USER_STATUS = "user_status"
private const val USER_FIRST_NAME = "user_first_name"
private const val USER_LAST_NAME = "user_last_name"

@Singleton
class SharedPreferencesRepository @Inject constructor(@ApplicationContext context: Context) {

    private val userPreferences =
        context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)

    private val globalPreferences =
        context.getSharedPreferences(GLOBAL_PREFERENCES, Context.MODE_PRIVATE)

    fun setUserEmail(userEmail: String) {
        userPreferences.edit {
            putString(USER_EMAIL, userEmail)
        }
    }

    fun getUserEmail(): String? {
        return userPreferences.getString(USER_EMAIL, null)
    }

    fun setUserPassword(userPassword: String) {
        userPreferences.edit {
            putString(USER_PASSWORD, userPassword)
        }
    }

    fun getUserStatus(): String {
        return userPreferences.getString(USER_STATUS, UserStatus.USER_DELETE.name)
            ?: UserStatus.USER_DELETE.name
    }

    fun setUserStatus(status: UserStatus) {
        userPreferences.edit {
            putString(USER_STATUS, status.name)
        }
    }

    fun getUserFullName(): String {
        return "${userPreferences.getString(USER_FIRST_NAME, null)} ${
            userPreferences.getString(
                USER_LAST_NAME,
                null
            )
        }"
    }

    fun setUserFullName(userFirstName: String, userLastName: String) {
        userPreferences.edit {
            putString(USER_FIRST_NAME, userFirstName)
            putString(USER_LAST_NAME, userLastName)
        }
    }

    fun deleteUserProfile() {
        globalPreferences.edit {
            clear()
        }
    }
}