package com.test.com.util

import android.content.SharedPreferences
import javax.inject.Inject

class Preferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveBearerToken(bearerToken: String) {
        editor.putString(Constants.TOKEN, bearerToken)
        editor.apply()
    }

    fun readBearerToken(): String {
        return sharedPreferences.getString(Constants.TOKEN, "").toString()
    }
}