package com.example.collectapp.authentication.model

data class UserModel (
    var userId : Int,
    var phoneNumber : Long,
    var userName : String,
    var access_token : String? = null
    )
