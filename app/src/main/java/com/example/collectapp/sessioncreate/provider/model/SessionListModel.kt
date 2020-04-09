package com.example.collectapp.sessioncreate.provider.model

import com.example.collectapp.sessioncreate.provider.model.SessionDataModel

data class SessionListModel (
    var message : String,
    var success : Boolean,
    var data : List<SessionDataModel>
)