package com.example.collectapp.session.chat.view

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.joda.time.DateTime

val sessionCollectionReference by lazy {
    FirebaseFirestore
        .getInstance()
        .collection("sessions")
}

val messagesQuery by lazy {
    sessionCollectionReference
        .orderBy("date", Query.Direction.DESCENDING)
}
//
//val newMessagesQuery by lazy {
//    messagesQuery.whereAfterTimestamp()
//}

fun Query.whereAfterDate(): Query =
    whereGreaterThan("date", DateTime.now().millis/1000)

fun getChatCollection(sessionId:String) = sessionCollectionReference.document(sessionId).collection("messages")