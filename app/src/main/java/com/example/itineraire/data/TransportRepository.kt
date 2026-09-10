package com.example.itineraire.data

import com.example.itineraire.model.RouteResult
import com.example.itineraire.model.RouteStep
import com.example.itineraire.model.TransportLine

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

    fun obtenirLignes(): List<TransportLine> {

        return listOf(

            TransportLine(
                id = 1,
                numero = "119",
                nom = "Arrêt A - Analakely",
                tarif = 600,
                terminusDepart = "Arrêt A",
                terminusArrivee = "Analakely",
                statut = "Normal"
            ),

            TransportLine(
                id = 2,
                numero = "194",
                nom = "Analakely - Université",
                tarif = 400,
                terminusDepart = "Analakely",
                terminusArrivee = "Université",
                statut = "Normal"
            ),

            TransportLine(
                id = 3,
                numero = "120",
                nom = "Ambohijatovo - Anosy",
                tarif = 600,
                terminusDepart = "Ambohijatovo",
                terminusArrivee = "Anosy",
                statut = "Perturbé"
            )
        )
    }
}