package com.rudhashi.techlibadminpanel.model

import com.google.firebase.Timestamp

data class Book(
    val BCode : String = "",
    val BCover: String = "",
    val BCredit: String = "",
    val BDes: String = "",
    val BName: String = "",
    val BTotalU: String = "",
    val BView: Long = 0,
    val BUpdates: Long = 0,
    val TimeStamp: Timestamp? = null,
    val xDep: List<String> = listOf(),  // Store as List<String> instead of List<Int>
    val xSem: List<String> = listOf()   // Store as List<String>
)
