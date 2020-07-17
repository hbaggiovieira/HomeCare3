package com.example.henriquevieira.homecare3.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.henriquevieira.homecare3.R
import com.example.henriquevieira.homecare3.infra.ClientConstants
import com.example.henriquevieira.homecare3.infra.SecurityPreferences
import com.example.henriquevieira.homecare3.ui.helper.Mask
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonNext3.setOnClickListener (this)

        //EditText Mask
        editTextDate.addTextChangedListener(Mask.insert(Mask.MaskType.DATE, editTextDate))
        editTextTime.addTextChangedListener(Mask.insert(Mask.MaskType.TIME, editTextTime))
        editTextTimeRequest.addTextChangedListener(Mask.insert(Mask.MaskType.TIME, editTextTimeRequest))

        //retorna dados na main
        mSecurityPreferences = SecurityPreferences(this)

        mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_NAME)
        mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_CPF)
        mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_PHONE)

        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_NAME)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CPF)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CEP)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_AGE)

        mSecurityPreferences.getClientData(ClientConstants.KEY.DATE_SET)
        mSecurityPreferences.getClientData(ClientConstants.KEY.TIME_SET)
        mSecurityPreferences.getClientData(ClientConstants.KEY.TIME_REQ)


    }

    override fun onClick(v: View) {

        val id = v.id
        if (id == R.id.buttonNext3) {
            handleSave()
        }
    }

    private fun handleSave() {
        val date = editTextDate.text.toString()
        val timeSet: String = editTextTime.text.toString()
        val timeRequest: String = editTextTimeRequest.text.toString()

        val textWhatsapp: String = "Nome: ${mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_NAME)} " +
                "+CPF: ${mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_CPF)} " +
                "+Tel: ${mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_PHONE)} " +
                "+Paciente: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_NAME)} " +
                "+Paciente CPF: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CPF)} " +
                "+Paciente CEP: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CEP)} " +
                "+Paciente Idade: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_AGE)} "

        if (date != "" && timeSet != "" && timeRequest != "") {
            mSecurityPreferences.storeClientData("date", date)
            mSecurityPreferences.storeClientData("time", timeSet)
            mSecurityPreferences.storeClientData("timereq", timeRequest)

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/5511950707333?text=$textWhatsapp " +
                    "+Data: ${mSecurityPreferences.getClientData(ClientConstants.KEY.DATE_SET)} " +
                    "+Hora: ${mSecurityPreferences.getClientData(ClientConstants.KEY.TIME_SET)} " +
                    "+Tempo: ${mSecurityPreferences.getClientData(ClientConstants.KEY.TIME_REQ)}"))
            startActivity(i)

        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }

}