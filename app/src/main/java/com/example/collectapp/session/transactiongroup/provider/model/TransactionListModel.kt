package com.example.collectapp.session.transactiongroup.provider.model

data class TransactionListModel(
    var message : String,
    var success : Boolean,
    var data : List<TransactionGroupModel>
)