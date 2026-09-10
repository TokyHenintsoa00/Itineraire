package com.example.itineraire.model

data class Signalement(
    val id: Int,
    val userId: Int,
    val ligneId: Int,
    val type: String,
    val description: String,
    val latitude: Double?,
    val longitude: Double?,
    val dateSignalement: String,
    val statut: String
)