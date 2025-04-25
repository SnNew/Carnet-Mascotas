package com.example.carnetmascotas

import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carnetmascotas.items.Carnet
import com.example.carnetmascotas.items.Mascotas
import com.example.carnetmascotas.items.Registro
import com.example.carnetmascotas.ui.theme.CarnetMascotasTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import com.example.carnetmascotas.items.Editar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarnetMascotasTheme {
                val navController = rememberNavController()
                val mascotas = remember { mutableStateListOf<Mascotas>() }
                var showFAB by remember { mutableStateOf(true) }

                // Monitorear cambios en la ruta activa
                LaunchedEffect(navController.currentBackStackEntry) {
                    showFAB = navController.currentBackStackEntry?.destination?.route == "carnet"
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        if (showFAB) {
                            FloatingActionButton(
                                onClick = { navController.navigate("Registro") },
                                content = {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar mascota")
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "carnet",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("registro") {
                            Registro(navController, mascotas)
                        }
                        composable("carnet") {
                            Carnet(
                                navController = navController,
                                mascotas = mascotas,
                                onEliminar = { mascota -> mascotas.remove(mascota) },
                                onEditar = { mascota ->
                                    navController.navigate("editar/${mascota.nombre}")
                                }
                            )
                        }
                        composable("editar/{nombre}") { backStackEntry ->
                            val nombre = backStackEntry.arguments?.getString("nombre")
                            val mascotaParaEditar = mascotas.find { it.nombre == nombre }
                            mascotaParaEditar?.let {
                                Editar(navController, it, mascotas)
                            }
                        }
                    }
                }
            }
        }
    }
}
