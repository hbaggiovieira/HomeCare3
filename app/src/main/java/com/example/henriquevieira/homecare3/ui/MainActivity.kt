package com.example.henriquevieira.homecare3.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import com.example.henriquevieira.homecare3.R
import com.example.henriquevieira.homecare3.infra.ClientConstants
import com.example.henriquevieira.homecare3.infra.SecurityPreferences
import com.example.henriquevieira.homecare3.ui.helper.Mask
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadSpinner()

        spinnerTime.onItemSelectedListener = this

        buttonNext3.setOnClickListener(this)

        //EditText Mask
        editAdressCep.addTextChangedListener(Mask.insert(Mask.MaskType.CEP, editAdressCep))
        editAdressTimeStart.addTextChangedListener(
            Mask.insert(
                Mask.MaskType.TIME,
                editAdressTimeStart
            )
        )

        //retorna dados na main
        mSecurityPreferences = SecurityPreferences(this)

        mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_NAME)
        mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_CPF)
        mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_PHONE)

        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_NAME)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CPF)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_AGE)

        /*mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CEP)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_RUA)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_NUMERO)
        mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CIDADE)
        mSecurityPreferences.getClientData(ClientConstants.KEY.HORA_INICIO)
        mSecurityPreferences.getClientData(ClientConstants.KEY.DURACAO)*/


    }

    //Spinner
    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "Nothing Selected", Toast.LENGTH_LONG)
            .show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0?.id) {
            R.id.spinnerTime -> {
                val spinnerTimeText = p0.getItemAtPosition(p2)
            }
        }
    }
    //Spinner

    //Button
    override fun onClick(v: View) {




        val id = v.id
        if (id == R.id.buttonNext3) {

            AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
                .setTitle("Informações Sobre Uso")
                .setMessage(R.string.info)
                .setCancelable(false)
                .setPositiveButton("Confirmar") {dialogInterface: DialogInterface?, i: Int ->
                    handleSave()
                }
                .show()
        }
    }
    //Button


    private fun loadSpinner() {
        val mList = listOf(
            "1 hora", "2 horas", "3 horas", "4  horas", "5  horas", "6 horas",
            "7 horas", "8 horas", "9 horas", "10  horas", "11  horas", "12 horas"
        )
        val adapter = ArrayAdapter(this, R.layout.spinner_layout, mList)
        spinnerTime.adapter = adapter
    }

    private fun handleSave() {
        val cep = editAdressCep.text.toString()
        val rua = editAdressRua.text.toString()
        val numero = editAdressNumber.text.toString()
        val cidade = editAdressCidade.text.toString()
        val horario = editAdressTimeStart.text.toString()
        val duracao = spinnerTime.selectedItem.toString()


        val textWhatsapp: String =
            "Nome Responsável: ${mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_NAME)} ;" +
                    "+CPF Responsável: ${mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_CPF)} ;" +
                    "+Tel Responsável: ${mSecurityPreferences.getClientData(ClientConstants.KEY.CLIENT_PHONE)} ;" +
                    "+Paciente Nome: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_NAME)} ;" +
                    "+Paciente CPF: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CPF)} ;" +
                    "+Paciente Idade: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_AGE)} ;"


        if (cep != "" && rua != "" && numero != "" && cidade != "" && horario != "" && duracao != "") {
            mSecurityPreferences.storeClientData("pcep", cep)
            mSecurityPreferences.storeClientData("prua", rua)
            mSecurityPreferences.storeClientData("pnumero", numero)
            mSecurityPreferences.storeClientData("pcidade", cidade)
            mSecurityPreferences.storeClientData("timeset", horario)
            mSecurityPreferences.storeClientData("duracao", duracao)

            val i = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "https://wa.me/5511950707333?text=$textWhatsapp " +
                            "+CEP: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CEP)} ;" +
                            "+Rua: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_RUA)} ;" +
                            "+Número: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_NUMERO)} ;" +
                            "+Cidade: ${mSecurityPreferences.getClientData(ClientConstants.KEY.PACIENT_CIDADE)} ;" +
                            "+Hora de início: ${mSecurityPreferences.getClientData(ClientConstants.KEY.HORA_INICIO)} ;" +
                            "+Duração do atendimento: ${mSecurityPreferences.getClientData(
                                ClientConstants.KEY.DURACAO
                            )} ."
                )
            )
            startActivity(i)

        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }


}