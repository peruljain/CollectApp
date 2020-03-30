package com.example.collectapp.authentication.model

data class AuthenticationModel(
    var message: String,
    var success: Boolean,
    var access_token: String? = null
)