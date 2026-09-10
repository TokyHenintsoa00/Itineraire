package com.example.itineraire.data

import com.example.itineraire.model.RouteResult
import com.example.itineraire.model.RouteStep

class TransportRepository {

    fun rechercherItineraire(
        depart: String,
        destination: String
    ): RouteResult? {

        if (
            depart.equals("Nouveau quartier", ignoreCase = true) &&
            destination.equals("Université", ignoreCase = true)
        ) {

            return RouteResult(

                etapes = listOf(

                    RouteStep(
                        lieu = "Nouveau quartier",
                        ligne = null,
                        duree = 5,
                        tarif = 0
                    ),

                    RouteStep(
                        lieu = "Analakely",
                        ligne = "119",
                        duree = 20,
                        tarif = 600
                    ),

                    RouteStep(
                        lieu = "Université",
                        ligne = "194",
                        duree = 25,
                        tarif = 400
                    )
                ),

                dureeTotale = 45,
                tarifTotal = 1000
            )
        }

        return null
    }
}