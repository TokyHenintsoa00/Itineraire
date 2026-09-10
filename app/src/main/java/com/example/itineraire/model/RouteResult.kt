package com.example.itineraire.model

data class RouteStep(
    val lieu: String,
    val ligne: String?,
    val duree: Int,
    val tarif: Int
)

data class RouteResult(
    val etapes: List<RouteStep>,
    val dureeTotale: Int,
    val tarifTotal: Int
)