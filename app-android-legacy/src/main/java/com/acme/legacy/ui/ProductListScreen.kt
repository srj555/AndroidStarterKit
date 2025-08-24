package com.acme.legacy.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.insert-koin.androidx.compose.getViewModel

@Composable
fun ProductListScreen(viewModel: ProductListViewModel = getViewModel()) {
    val state by viewModel.state.collectAsState()
    var query by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Legacy Products") })
    }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("Search") }
                )
                Button(onClick = { viewModel.search(query) }) { Text("Go") }
                Button(onClick = { viewModel.refresh() }) { Text("Refresh") }
            }
            Spacer(Modifier.height(16.dp))
            when (val s = state) {
                is UiState.Loading -> CircularProgressIndicator()
                is UiState.Error -> Text("Error: ${'$'}{s.error.message ?: s.error::class.simpleName}")
                is UiState.Data -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(s.products) { p ->
                        Card(Modifier.fillMaxWidth()) {
                            Column(Modifier.padding(16.dp)) {
                                Text(p.name, style = MaterialTheme.typography.titleMedium)
                                Text("$${'$'}{p.price}")
                            }
                        }
                    }
                }
            }
        }
    }
}