package com.example.collectapp.home.provider.model


data class SessionDataModel (
    var sessionID : Long,
    var sessionName : String,
    var sessionToken : String?,
    var createdOn : String,
    var createdBy : String,
    var createdById : Int
)