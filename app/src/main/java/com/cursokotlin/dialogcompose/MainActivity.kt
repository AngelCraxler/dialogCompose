package com.cursokotlin.dialogcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                // Variable de estado para controlar el diálogo
                var show by remember { mutableStateOf(false) }

                // Contenedor principal centrado
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    // Botón que abre el diálogo (Paso 2 y 12)
                    Button(onClick = { show = true }) {
                        Text(text = "Dialog")
                    }
                }

                // Llamada a la función pasando el estado y las acciones
                MyDialog(
                    show = show,
                    onDismiss = { show = false }, // Cierra el diálogo
                    onConfirm = {
                        show = false

                    }
                )
            }
        }
    }
}

// Función composable con parámetros de estado y acciones
@Composable
fun MyDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    // Si show es true, se muestra el AlertDialog
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() }, // Se activa al tocar fuera o atrás
            confirmButton = {
                // Botón de confirmar
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "ConfirmButton")
                }
            },
            dismissButton = {
                // Botón de cancelar/cerrar
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "DismissButton")
                }
            },
            title = { Text(text = "Suscribete") }, // Título del diálogo
            text = { Text(text = "Esto es el body de mi dialog") } // Cuerpo del diálogo
        )
    }
}

// Vista previa (Nota: la vista previa estática no muestra el diálogo si show=false por defecto)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        var show by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = { show = true }) {
                Text(text = "Dialog")
            }
        }
        MyDialog(show = show, onDismiss = { show = false }, onConfirm = { show = false })
    }
}