package com.example.itineraire.model

data class TransportLine(
    val id: Int,
    val numero: String,
    val nom: String,
    val tarif: Int,
    val terminusDepart: String,
    val terminusArrivee: String,
    val statut: String
)