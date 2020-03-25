package com.vascomm.vascwork.additional.validation

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.vascomm.vacswork.R

class Validation  constructor(var iValidation: ValidationInterface){
    private var registerFormData = ArrayList<Any>()
    private var data = HashMap<String,String>()
    private var error = ArrayList<TextInputLayout>()

    private var errorCount = 0

    fun registerField(editText: EditText,key:String,listRules:MutableCollection<ValidationRules>){
        registerFormData.add(
            ValidationType1(
                editText,
                key,
                listRules
            )
        )
    }

    fun registerField(editText: EditText,textInputLayout: TextInputLayout,key: String,
                      listRules: MutableCollection<ValidationRules>){
        registerFormData.add(
            ValidationType2(
                editText,
                textInputLayout,
                key,
                listRules
            )
        )
    }

    fun removeField(key:String){
       val find = registerFormData.find {
           when (it){
               is ValidationType1 -> it.key == key
               is ValidationType2 -> it.key == key
               else -> throw IllegalArgumentException("type not support")
           }
        }
        registerFormData.remove(find)
    }

    fun validation(){
        startValidate()
        processValidate()
    }

    private fun startValidate(){
        errorCount = 0
        data.clear()
        error.clear()
    }

    private fun finisValidate(){
        when(errorCount){
            0 -> iValidation.validationSuccess(data)
        }
    }

    private fun findError(textInputLayout: TextInputLayout):Boolean = error.contains(textInputLayout)

    private fun processValidate(){
        fun validateType1(item: ValidationType1){
            for(rule in item.rules){
                when(rule.validation(item.editText.text.toString())){
                    true -> data[item.key]  = item.editText.text.toString()
                    false -> errorCount++.also { item.editText.error = rule.getErrorMessage() }
                }
            }
        }

        fun validateType2(item: ValidationType2){
            for (rule in item.rules){
                when(rule.validation(item.editText.text.toString())){
                    true -> if(!findError(item.textInput)){
                                item.textInput.isErrorEnabled = false
                                data[item.key] = item.editText.text.toString()
                            }else{
                                data[item.key] = item.editText.text.toString() }
                    false -> {
                            item.textInput.setErrorTextAppearance(R.style.text_error)
                            if (!findError(item.textInput)){
                                item.textInput.apply {
                                    isErrorEnabled = true
                                    error = rule.getErrorMessage()
                                }
                            }

                            error.add(item.textInput)
                            errorCount++
                    }
                }
            }
        }
        for(validation in registerFormData){
            when(validation){
                is ValidationType1 -> validateType1(validation)
                is ValidationType2 -> validateType2(validation)
            }
        }

        finisValidate()
    }
}