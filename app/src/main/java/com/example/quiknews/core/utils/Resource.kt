package com.example.quiknews.core.utils

sealed class Resource<T>(val data:T?=null,val message:String?=null) {
    class Loading<T>(data:T?=null,message:String?=null):Resource<T>(data, message )
    class Success<T>(data:T?,message: String?):Resource<T>(data,message)
    class Error<T>(message:String,data: T?=null):Resource<T>(data,message)
}