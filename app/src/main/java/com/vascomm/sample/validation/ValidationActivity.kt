package com.vascomm.sample.validation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vascomm.sample.R
import com.vascomm.vascwork.additional.validation.*
import com.vascomm.vascwork.extension.convertToNoninal
import com.vascomm.vascwork.extension.setCardNumberFormant
import com.vascomm.vascwork.extension.setCurrencyFormat
import kotlinx.android.synthetic.main.activity_sample_validation.*

class ValidationActivity :AppCompatActivity(),ValidationInterface {
    private val validation by lazy { Validation(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_validation)
        setSample()
    }

    private fun setSample(){
       // validation.
        validation.registerField(field1,input1,"username",
            mutableListOf(RequireRule("Field tidak boleh kosong"),LenghtRule("test",10,12)))
        validation.registerField(field2,input2,"email",
            mutableListOf(EmailRule("Email tidak valid")))
        validation.registerField(field3,input3,"password",
            mutableListOf( RegexRule("Format password tidak valid ","(?=.*[0-9])(?=.*[a-z]).{8,}"),
            LenghtRule("Minimal length 10 digit dan maksimal 12",10,12)))
        validation.registerField(field4,input4,"confirm",
            mutableListOf(ConfrimationRule("Confrimasi password tidk sama",field3)))


        validation.removeField("username")

        btn_submit.setOnClickListener { validation.validation() }
        btn_add.setOnClickListener { }
        btn_remove.setOnClickListener { }
    }

    override fun validationSuccess(data: HashMap<String, String>) {
        Toast.makeText(this,"Validation Pass",Toast.LENGTH_SHORT).show()
    }

}