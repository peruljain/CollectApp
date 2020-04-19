package com.example.collectapp.session.members.provider.model

data class MembersListModel (
    var message : String,
    var success : Boolean,
    var data : List<MemberDataModel>
)