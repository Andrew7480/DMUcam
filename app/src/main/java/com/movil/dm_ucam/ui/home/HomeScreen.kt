package com.movil.dm_ucam.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    name: String,
    gender: String,
    montana: Int,
    sprint: Int,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Nombre: $name", fontSize = 18.sp)
        Text(text = "Género: $gender", fontSize = 18.sp)
        Text(text = "Montaña: $montana", fontSize = 18.sp)
        Text(text = "Sprint: $sprint", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onLogout() }) {
            Text(text = "Cerrar Sesión")
        }
    }
}
