package com.example.collectapp.session.alltransactions.provider.model

data class AllTransactionListModel(
    var message : String,
    var success : Boolean,
    var data : List<AllTransactionModel>
)