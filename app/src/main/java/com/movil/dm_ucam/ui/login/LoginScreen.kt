package com.movil.dm_ucam.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movil.dm_ucam.R
import com.movil.dm_ucam.ui.theme.Pink40
import com.movil.dm_ucam.ui.theme.UcamPrimary

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Hombre") }
    val genders = listOf("Hombre", "Mujer", "Otro")

    var montanaValue by remember { mutableFloatStateOf(50f) }
    var sprintValue by remember { mutableFloatStateOf(50f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(UcamPrimary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Placeholder de Imagen superior (si lo necesitas)
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .background(Pink40)
            ) {
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 2. Contenedor con TextField
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 6.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = { Text("Ingrese su nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 3. Selector de Genero
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Género:",
                    color = Color.White,
                    fontSize = 16.sp
                )

                genders.forEach { gender ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedGender = gender }
                    ) {
                        RadioButton(
                            selected = (selectedGender == gender),
                            onClick = { selectedGender = gender },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.White,
                                unselectedColor = Color.LightGray
                            )
                        )
                        Text(
                            text = gender,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // 4. Sliders
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                UcamSlider(
                    label = "Montañ",
                    value = montanaValue,
                    onValueChange = { montanaValue = it },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                UcamSlider(
                    label = "Sprint",
                    value = sprintValue,
                    onValueChange = { sprintValue = it },
                    modifier = Modifier.weight(1f)
                )
            }

            // 5. Boton enviar
            Button(
                onClick = { onLoginSuccess() },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A5568)
                ),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Enviar",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 6. Imagen Ucam horizontal
            Image(
                painter = painterResource(id = R.drawable.logo_ucam_horizontal),
                contentDescription = "Logo UCAM",
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(160.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

/**
 * Componente reutilizable (Util / Custom Composable) para los Sliders con steps
 */
@Composable
fun UcamSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..100f,
            steps = 4
        )
        Text(
            text = "$label: ${value.toInt()}",
            color = Color.White,
            fontSize = 14.sp
        )
    }
}
