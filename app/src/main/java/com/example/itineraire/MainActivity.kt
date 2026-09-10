package com.example.itineraire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MadaTransportApp()
        }
    }
}

@Composable
fun MadaTransportApp() {

    var ecranRecherche by remember {
        mutableStateOf(false)
    }

    MaterialTheme {

        if (ecranRecherche) {

            SearchScreen(
                retourAccueil = {
                    ecranRecherche = false
                }
            )

        } else {

            HomeScreen(
                ouvrirRecherche = {
                    ecranRecherche = true
                }
            )
        }
    }
}
// ecran d'acceuil
@Composable
fun HomeScreen(
    ouvrirRecherche: () -> Unit
) {

    var destination by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "MadaTransport",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Trouvez votre itinéraire en Taxi-be"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = destination,
            onValueChange = {
                destination = it
            },
            label = {
                Text("Où allez-vous ?")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {
                ouvrirRecherche()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔎 Rechercher")
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("🚌 Lignes")
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("📍 Carte")
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("⚠️ Alertes")
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("👤 Profil")
            }
        }
    }
}

//ecnran de recherche
@Composable
@Composable
fun SearchScreen(
    retourAccueil: () -> Unit
) {

    var depart by remember {
        mutableStateOf("")
    }

    var destination by remember {
        mutableStateOf("")
    }

    var resultat by remember {
        mutableStateOf<com.example.itineraire.model.RouteResult?>(null)
    }

    var erreur by remember {
        mutableStateOf("")
    }

    val repository = remember {
        com.example.itineraire.data.TransportRepository()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Recherche d'itinéraire",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = depart,
            onValueChange = {
                depart = it
            },
            label = {
                Text("Départ")
            },
            placeholder = {
                Text("Ex : Nouveau quartier")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = destination,
            onValueChange = {
                destination = it
            },
            label = {
                Text("Destination")
            },
            placeholder = {
                Text("Ex : Université")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {

                resultat = repository.rechercherItineraire(
                    depart,
                    destination
                )

                erreur = if (resultat == null) {
                    "Aucun itinéraire trouvé."
                } else {
                    ""
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔎 Rechercher l'itinéraire")
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (erreur.isNotEmpty()) {

            Text(
                text = erreur,
                color = MaterialTheme.colorScheme.error
            )
        }

        resultat?.let { route ->

            RouteResultCard(route)
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        OutlinedButton(
            onClick = {
                retourAccueil()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← Retour")
        }
    }
}
@Composable
fun RouteResultCard(
    route: com.example.itineraire.model.RouteResult
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {

        Text(
            text = "Itinéraire recommandé ⭐",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        route.etapes.forEach { etape ->

            if (etape.ligne == null) {

                Text(
                    text = "🚶 ${etape.lieu} — ${etape.duree} min"
                )

            } else {

                Text(
                    text = "🚌 Ligne ${etape.ligne} → ${etape.lieu} — ${etape.duree} min"
                )
            }

            Spacer(
                modifier = Modifier.height(6.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Durée totale : ${route.dureeTotale} min"
        )

        Text(
            text = "Tarif total : ${route.tarifTotal} Ar"
        )
    }
}