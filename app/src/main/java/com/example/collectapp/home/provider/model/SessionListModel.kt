package com.example.collectapp.home.provider.model

data class SessionListModel (
    var message : String,
    var success : Boolean,
    var data : List<SessionDataModel>
)