package com.example.henriquevieira.homecare3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.henriquevieira.homecare3.R
import com.example.henriquevieira.homecare3.R.id.buttonNext2
import com.example.henriquevieira.homecare3.infra.SecurityPreferences
import com.example.henriquevieira.homecare3.ui.helper.Mask
import kotlinx.android.synthetic.main.activity_pacient_form.*
import kotlinx.android.synthetic.main.activity_splash.*

class PacientFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacient_form)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonNext2.setOnClickListener (this)



        //EditText Mask
        editTextCpfPacient.addTextChangedListener(Mask.insert(Mask.MaskType.CPF, editTextCpfPacient))

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeClientData("", "")

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.buttonNext2) {
            handleSave()
        }
    }

    private fun handleSave() {
        val pName: String = editTextNamePacient.text.toString()
        val pCpf: String = editTextCpfPacient.text.toString()
        val pAge: String = editTextAgePacient.text.toString()

        if (pName != "" && pCpf != "" && pAge != "") {
            mSecurityPreferences.storeClientData("pname", pName)
            mSecurityPreferences.storeClientData("pcpf", pCpf)
            mSecurityPreferences.storeClientData("page", pAge)

            val i: Intent = Intent(this, MainActivity::class.java)
            startActivity(i)

        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }
}