package com.example.madatransport

import org.osmdroid.util.GeoPoint

data class Arret(
    val id: Int,
    val nom: String,
    val location: GeoPoint
)

enum class TypeIncident {
    EMBOUTEILLAGE,
    ACCIDENT,
    DEVIATION
}

data class IncidentRoutier(
    val id: Int,
    val type: TypeIncident,
    val description: String,
    val location: GeoPoint
)

data class TrajetResultat(
    val descriptionText: String,
    val pointsItineraire: List<GeoPoint>
)