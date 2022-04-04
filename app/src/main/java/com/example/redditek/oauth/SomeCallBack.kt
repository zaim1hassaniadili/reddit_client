package com.example.redditek.oauth


interface SomeCallBack{
    fun onSuccess(token : String)
    fun onFailure(error: String)

}
