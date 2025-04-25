package com.example.carnetmascotas.items

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController

@Composable
fun Carnet(
    navController: NavController,
    mascotas: MutableList<Mascotas>,
    onEliminar: (Mascotas) -> Unit,
    onEditar: (Mascotas) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var mascotaToDelete by remember { mutableStateOf<Mascotas?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Carnets de tus Mascotas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        mascotas.forEach { mascota ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = mascota.fotoUrl,
                        contentDescription = "Foto de ${mascota.nombre}",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text("Nombre: ${mascota.nombre}", style = MaterialTheme.typography.bodyLarge)
                        Text("Raza: ${mascota.raza}")
                        Text("Tamaño: ${mascota.tamano}")
                        Text("Edad: ${mascota.edad}")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Botón de editar
                    IconButton(onClick = { onEditar(mascota) }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")
                    }

                    // Botón de eliminar
                    IconButton(onClick = {
                        mascotaToDelete = mascota
                        showDialog = true
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            }
        }

        // Confirmación de eliminación
        if (showDialog && mascotaToDelete != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirmación de eliminación") },
                text = { Text("¿Estás seguro de que deseas eliminar a ${mascotaToDelete?.nombre}?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            mascotaToDelete?.let { onEliminar(it) }
                            showDialog = false
                        }
                    ) {
                        Text("Sí, eliminar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}
