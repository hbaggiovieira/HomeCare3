package com.example.henriquevieira.homecare3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.henriquevieira.homecare3.R
import com.example.henriquevieira.homecare3.R.id.buttonNext1
import com.example.henriquevieira.homecare3.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonNext1.setOnClickListener (this)

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeClientData("", "")
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonNext1) {
            handleSave()
        }
    }
    private fun handleSave () {
        val name = editTextTextClientName.text.toString()
        //val clientCpf: String = editTextClientCpf.text.toString()

        if (name != "") {
            mSecurityPreferences.storeClientData("name", name)
          //  mSecurityPreferences.storeClientData("cpf", clientCpf)

            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }
}