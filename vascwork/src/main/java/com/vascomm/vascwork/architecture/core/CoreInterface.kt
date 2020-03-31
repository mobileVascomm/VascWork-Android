package com.vascomm.vascwork.architecture.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface  ViewStateInterface{
    fun onSuccess(result: Result)
    fun onFailure(result: Result)
    fun onLoading(result: Result)
}

class NetworkState constructor(val tag:String,
                               val viewState: ViewStateInterface,
                               val scope: CoroutineScope,
                               val isActive:Boolean){

    fun success(code:String = "",message:String = "", data:Any = ""){
        scope.launch(Dispatchers.Main) { if(isActive) viewState.onSuccess(
            Result(
                tag,
                code,
                message,
                data
            )
        ) }
    }

    fun failure(code: String = "", message: String = "", data: Any= ""){
        scope.launch(Dispatchers.Main) { if (isActive) viewState.onFailure(
            Result(
                tag,
                code,
                message,
                data
            )
        ) }
    }

    fun loading(code:String = "",message: String = "",isLoading:Boolean = false,data: Any = ""){
        scope.launch(Dispatchers.Main) { if (isActive)
            viewState.onLoading(
                Result(
                    tag,
                    code,
                    message,
                    data,
                    isLoading
                )
            ) }
    }
}