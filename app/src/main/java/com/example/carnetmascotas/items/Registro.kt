package com.example.carnetmascotas.items

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

@Composable
fun Registro(
    navController: NavController,
    mascotas: MutableList<Mascotas>
) {
    var nombre by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var tamano by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var fotoUrl by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Identificación de tu mascota", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = raza,
            onValueChange = { raza = it },
            label = { Text("Raza") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tamano,
            onValueChange = { tamano = it },
            label = { Text("Tamaño") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fotoUrl,
            onValueChange = { fotoUrl = it },
            label = { Text("URL de la foto") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (fotoUrl.isNotBlank()) {
            AsyncImage(
                model = fotoUrl,
                contentDescription = "Vista previa de la foto",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (nombre.isNotBlank() && raza.isNotBlank() && tamano.isNotBlank() &&
                    edad.isNotBlank() && fotoUrl.isNotBlank()
                ) {
                    val nuevaMascota = Mascotas(nombre, raza, tamano, edad, fotoUrl)
                    mascotas.add(nuevaMascota)

                    // Limpiar campos
                    nombre = ""
                    raza = ""
                    tamano = ""
                    edad = ""
                    fotoUrl = ""
                }
            }) {
                Text("Registrar")
            }

            Button(onClick = {
                navController.navigate("Carnet")
            }) {
                Text("Ver Carnets")
            }
        }
    }
}
