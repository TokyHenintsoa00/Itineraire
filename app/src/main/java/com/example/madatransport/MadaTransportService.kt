package com.example.madatransport

import org.osmdroid.util.GeoPoint

class MadaTransportService {

    // Coordonnées GPS réelles à Antananarivo
    val arretAnkatso = Arret(1, "Ankatso", GeoPoint(-18.9152, 47.5552))
    val arretAnalakely = Arret(2, "Analakely", GeoPoint(-18.9101, 47.5251))
    val arretMahamasina = Arret(3, "Mahamasina", GeoPoint(-18.9200, 47.5300))

    // Exemples d'incidents signalés en temps réel
    val listeIncidents = listOf(
        IncidentRoutier(
            id = 1,
            type = TypeIncident.EMBOUTEILLAGE,
            description = "⚠️ Embouteillage dense - Travaux en cours",
            location = GeoPoint(-18.9125, 47.5400)
        ),
        IncidentRoutier(
            id = 2,
            type = TypeIncident.ACCIDENT,
            description = "🚨 Accident de la circulation",
            location = GeoPoint(-18.9140, 47.5480)
        )
    )

    fun chercherItineraireEtPoints(departQuery: String, destinationQuery: String): TrajetResultat {
        val dep = departQuery.trim()
        val dest = destinationQuery.trim()

        if (dep.isEmpty() || dest.isEmpty()) {
            return TrajetResultat("⚠️ Veuillez renseigner le départ et la destination.", emptyList())
        }

        // Trajet Ankatso <-> Analakely (Exemple)
        if (dep.contains("Ankatso", ignoreCase = true) && dest.contains("Analakely", ignoreCase = true)) {
            val points = listOf(
                arretAnkatso.location,
                GeoPoint(-18.9140, 47.5480), // Point intermédiaire (Accident)
                GeoPoint(-18.9125, 47.5400), // Point intermédiaire (Embouteillage)
                arretAnalakely.location
            )
            val info = "✅ Itinéraire Ligne 119 (Ankatso ➔ Analakely)\n⏱️ Durée : 25 min | 💰 Tarif : 600 Ar"
            return TrajetResultat(info, points)
        }

        // Trajet Analakely <-> Mahamasina (Exemple)
        if (dep.contains("Analakely", ignoreCase = true) && dest.contains("Mahamasina", ignoreCase = true)) {
            val points = listOf(
                arretAnalakely.location,
                arretMahamasina.location
            )
            val info = "✅ Itinéraire Ligne 194 (Analakely ➔ Mahamasina)\n⏱️ Durée : 15 min | 💰 Tarif : 600 Ar"
            return TrajetResultat(info, points)
        }

        return TrajetResultat("❌ Aucun itinéraire trouvé.", emptyList())
    }
}