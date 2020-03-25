package com.vascomm.vascwork.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/*
 Untuk editext dengan auto format currency Rupiah
 contoh : 1.000.000
  */
fun EditText?.setCurrencyFormat(){
    this?.addTextChangedListener(object :TextWatcher{
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            this@setCurrencyFormat.removeTextChangedListener(this)

            val nominal  = p0.toString().convertRupiahCurrency(false)
            this@setCurrencyFormat.setText(nominal)
            this@setCurrencyFormat.setSelection(nominal.length)
            this@setCurrencyFormat.addTextChangedListener(this)
        }

    })
}

/*
 Untuk editext dengan auto format card number
 contoh : 0000 1234 1241 1234
  */
fun EditText.setCardNumberFormant(){
    addTextChangedListener(object :TextWatcher{
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            this@setCardNumberFormant.removeTextChangedListener(this)
            val nominal = StringBuilder()
            when{
                p0.isNullOrEmpty() -> nominal.append("")
                p0.length > 19 -> nominal.append(p0.dropLast(1))
                else -> {
                    nominal.append(this@setCardNumberFormant.text.toString().replace(" ",""))
                    val loop = nominal.length / 4 - if (nominal.length %4 == 0) 1 else 0

                    for (i in 1..loop)
                        nominal.insert((i * 4) + (i - 1), " ")
                }
            }

            this@setCardNumberFormant.setText(nominal)
            this@setCardNumberFormant.setSelection(nominal.length)
            this@setCardNumberFormant.addTextChangedListener(this)
        }

    })
}