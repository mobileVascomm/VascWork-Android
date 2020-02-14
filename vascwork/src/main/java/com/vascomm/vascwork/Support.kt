package com.vascomm.vascwork

import android.util.Log
import com.vascomm.vacswork.BuildConfig

fun log(tag:String = "vascwork",msg:String) {
    if(BuildConfig.DEBUG) Log.d(tag,msg)
}