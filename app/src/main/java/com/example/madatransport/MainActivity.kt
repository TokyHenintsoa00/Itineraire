package com.example.madatransport

import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private val madaService = MadaTransportService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configuration obligatoire d'OsmDroid pour le cache des cartes
        Configuration.getInstance().load(applicationContext, PreferenceManager.getDefaultSharedPreferences(applicationContext))

        setContentView(R.layout.activity_main)

        // Composants UI
        val etDepart = findViewById<EditText>(R.id.etDepart)
        val etDestination = findViewById<EditText>(R.id.etDestination)
        val btnRechercher = findViewById<Button>(R.id.btnRechercher)
        val tvResultat = findViewById<TextView>(R.id.tvResultat)
        mapView = findViewById(R.id.mapView)

        // Initialisation de la carte OpenStreetMap
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)

        // Centrer la carte sur Antananarivo par défaut
        val centerTana = GeoPoint(-18.9101, 47.5251)
        mapView.controller.setZoom(13.5)
        mapView.controller.setCenter(centerTana)

        // Clic sur le bouton de recherche
        btnRechercher.setOnClickListener {
            val depart = etDepart.text.toString()
            val destination = etDestination.text.toString()

            val res = madaService.chercherItineraireEtPoints(depart, destination)
            tvResultat.text = res.descriptionText

            // Mise à jour de la carte
            afficherSurLaCarte(res.pointsItineraire)
        }
    }

    private fun afficherSurLaCarte(pointsTrajet: List<GeoPoint>) {
        // Nettoyer les anciens tracés et marqueurs
        mapView.overlays.clear()

        // 1. Afficher les incidents routiers (Embouteillages & Accidents)
        for (incident in madaService.listeIncidents) {
            val marker = Marker(mapView)
            marker.position = incident.location
            marker.title = when (incident.type) {
                TypeIncident.EMBOUTEILLAGE -> "🚗 🛑 EMBOUTEILLAGE"
                TypeIncident.ACCIDENT -> "💥 🚨 ACCIDENT"
                TypeIncident.DEVIATION -> "🚧 DÉVIATION"
            }
            marker.snippet = incident.description
            mapView.overlays.add(marker)
        }

        // 2. Si un itinéraire est trouvé, le tracer sur la carte
        if (pointsTrajet.isNotEmpty()) {
            val line = Polyline()
            line.setPoints(pointsTrajet)
            line.outlinePaint.color = Color.BLUE
            line.outlinePaint.strokeWidth = 10f
            mapView.overlays.add(line)

            // Marqueur Départ
            val startMarker = Marker(mapView)
            startMarker.position = pointsTrajet.first()
            startMarker.title = "📍 Point de départ"
            mapView.overlays.add(startMarker)

            // Marqueur Arrivée
            val endMarker = Marker(mapView)
            endMarker.position = pointsTrajet.last()
            endMarker.title = "🎓 Destination"
            mapView.overlays.add(endMarker)

            // Recentrer la carte sur le départ
            mapView.controller.animateTo(pointsTrajet.first())
            mapView.controller.setZoom(14.5)
        }

        // Rafraîchir l'affichage de la carte
        mapView.invalidate()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}