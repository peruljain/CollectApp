package com.example.collectapp.session.alltransactions.provider.model

import java.util.*

data class AllTransactionModel(
    var payerName : String,
    var payerPhoneNumber : Long,
    var groupName : String,
    var amount : Int,
    var paidOn : Date,
    var collectedById : Int,
    var collectedBy : String
)