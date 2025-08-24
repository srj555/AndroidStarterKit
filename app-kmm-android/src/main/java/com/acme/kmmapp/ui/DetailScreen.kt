package com.acme.kmmapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(id: String, onBack: () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text("Detail") }) }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Selected id: ${'$'}id")
            Button(onClick = onBack) { Text("Back") }
        }
    }
}