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
fun SearchScreen(
    retourAccueil: () -> Unit
) {

    var depart by remember {
        mutableStateOf("")
    }

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

                // La recherche réelle sera programmée
                // à l'étape suivante.

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔎 Rechercher l'itinéraire")
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