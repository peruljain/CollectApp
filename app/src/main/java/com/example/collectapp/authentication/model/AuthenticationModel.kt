package com.example.collectapp.authentication.model

data class AuthenticationModel (
    var message : String,
    var success : Boolean,
    var data : UserModel
)