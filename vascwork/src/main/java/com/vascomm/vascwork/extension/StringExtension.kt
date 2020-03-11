package com.vascomm.vascwork.extension

import android.util.Base64

/*
   convert nominal ke currency format
   contoh : 100000 -> Rp100.000
 */
fun String.convertRupiahCurrency(userCurrency:Boolean = false): String{
    if(this == "0") return  if (userCurrency) "Rp0" else "0"
    val value  = if (this.contains(" ")) this.split(" ")[1] else this
    val result = StringBuilder(value.replace("." , ""))
    val size = result.length - 3

    for (i in size downTo 1 step 3)
        result.insert(i, ".")

    var str = result.toString()
    while (str.isNotEmpty() && (str[0] == '0' || str[0] == '.'))
        str = str.removeRange(0,1)

    if(userCurrency && str.isNotEmpty()) str = "Rp$str"

    return  str
}

/*
    convert currency format ke nominal
    contoh : Rp100.000 -> 100000
 */
fun String.convertToNoninal() :String{
    return this.replace("Rp", "").replace(".","")
}

/*
    convert string ke base64
 */
fun String.toBase64():String{
    val data = this.toByteArray(Charsets.UTF_8)
    val base = Base64.encode(data,Base64.NO_WRAP)
    return String(base)
}