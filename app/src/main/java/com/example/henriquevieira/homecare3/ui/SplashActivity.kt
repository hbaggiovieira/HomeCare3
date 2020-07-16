package com.example.henriquevieira.homecare3.ui

import android.content.Intent
import android.graphics.MaskFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.MaskFilterSpan
import android.view.View
import android.widget.Toast
import com.example.henriquevieira.homecare3.R
import com.example.henriquevieira.homecare3.R.id.buttonNext1
import com.example.henriquevieira.homecare3.infra.SecurityPreferences
import com.example.henriquevieira.homecare3.ui.helper.Mask
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

        buttonNext1.setOnClickListener(this)

        editTextPhone.addTextChangedListener(Mask.insert(Mask.MaskType.TEL, editTextPhone))
        editTextClientCpf.addTextChangedListener(Mask.insert(Mask.MaskType.CPF, editTextClientCpf))

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeClientData("", "")
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonNext1) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = editTextTextClientName.text.toString()
        val clientCpf: String = editTextClientCpf.text.toString()
        val clientPhone: String = editTextPhone.text.toString()

        if (name != "" && clientCpf != "" && clientPhone != "") {
            mSecurityPreferences.storeClientData("name", name)
            mSecurityPreferences.storeClientData("cpf", clientCpf)
            mSecurityPreferences.storeClientData("phone", clientPhone)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }
}