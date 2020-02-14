package com.vascomm.vascwork.additional.validation

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

interface ValidationInterface{
    fun validationSuccess(data:HashMap<String,String>)
}

data class ValidationType1(val editText: EditText,val key:String,val rules:MutableCollection<ValidationRules>)

data class ValidationType2(val editText: EditText,val textInput:TextInputLayout,val key:String,
                           val rules: MutableCollection<ValidationRules>)