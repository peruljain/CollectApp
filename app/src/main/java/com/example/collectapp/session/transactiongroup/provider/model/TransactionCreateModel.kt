package com.example.collectapp.session.transactiongroup.provider.model

data class TransactionCreateModel(
    var message : String,
    var success : Boolean,
    var data : TransactionGroupModel
)