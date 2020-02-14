package com.vascomm.vascwork.architecture.core

data class Result(val tag:String,
                  val code:String,
                  val message:String,
                  val data:Any,
                  val isLoading:Boolean = false)