package com.example.collectapp.session.members.provider.model

import java.util.*

data class MemberDataModel(
    var userId : Long,
    var userName : String,
    var phone : String,
    var joinedOn : String

)