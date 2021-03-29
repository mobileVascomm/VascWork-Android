package com.vascomm.vascwork.additional.validation

import android.util.Patterns
import android.widget.EditText
import java.util.regex.Pattern

interface ValidationRules {
    fun validation(text:String):Boolean
    fun getErrorMessage():String
}

/** Required Rules register */
class RequireRule constructor(private val message:String):
    ValidationRules {
    override fun validation(text: String): Boolean = text.isNotEmpty()
    override fun getErrorMessage(): String  = message
}

/** Confirmation Rule register */
class ConfrimationRule constructor(val message: String,val confrimationText:EditText) :
    ValidationRules {
    override fun validation(text: String): Boolean = text.equals(confrimationText.text.toString(),false)
    override fun getErrorMessage(): String = message
}

/** Email Rules register */
class EmailRule constructor(val message: String):
    ValidationRules {
    override fun validation(text: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(text).matches()
    override fun getErrorMessage(): String = message
}

/** Length Rule register  */
class LengthRule constructor(val message: String, val minLenght:Int, val maxLenght:Int):
    ValidationRules {
    override fun validation(text: String): Boolean = text.length in minLenght..maxLenght
    override fun getErrorMessage(): String = message
}

/** Regex Rule register
 *
^                # start-of-string
(?=.*[0-9])       # a digit must occur at least once
(?=.*[a-z])       # a lower case letter must occur at least once
(?=.*[A-Z])       # an upper case letter must occur at least once
(?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
(?=\\S+$)         # no whitespace allowed in the entire string
.{4,}             # anything, at least six places though
$                 # end-of-string
 */
class RegexRule constructor(val message: String,var regex:String) :
    ValidationRules {
    override fun validation(text: String): Boolean = Pattern.compile(regex).matcher(text).find()
    override fun getErrorMessage(): String = message
}