package com.example.henriquevieira.homecare3.infra

import android.content.Context

class SecurityPreferences(context: Context) {

    private val mSharedPreferences = context.getSharedPreferences("clientdata", Context.MODE_PRIVATE)

    fun storeClientData (key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }
    fun getClientData (key: String): String {
        return mSharedPreferences.getString(key, "") ?: ""
    }

}