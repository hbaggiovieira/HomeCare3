package com.example.henriquevieira.homecare3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.henriquevieira.homecare3.R
import com.example.henriquevieira.homecare3.infra.ClientConstants
import com.example.henriquevieira.homecare3.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        textViewtest.text = mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_NAME)
        //mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_CPF)
    }
}