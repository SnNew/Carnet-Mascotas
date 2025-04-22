package com.example.carnetmascotas

import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carnetmascotas.items.Carnet
import com.example.carnetmascotas.items.Mascotas
import com.example.carnetmascotas.items.Registro
import com.example.carnetmascotas.ui.theme.CarnetMascotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarnetMascotasTheme {
                val navController = rememberNavController()
                val mascotas = remember { mutableStateListOf<Mascotas>() }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "registro",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("registro") {
                            Registro(navController, mascotas)
                        }
                        composable("carnet") {
                            Carnet(navController, mascotas)
                        }
                    }
                }

            }
        }
    }
}