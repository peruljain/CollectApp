package com.example.collectapp.sessioncreate.provider.model

import java.util.*

data class SessionDataModel (
    var sessionID : Long,
    var sessionName : String,
    var sessionToken : String ? = null,
    var createdOn : Date,
    var createdBy : String,
    var createdById : Int
)