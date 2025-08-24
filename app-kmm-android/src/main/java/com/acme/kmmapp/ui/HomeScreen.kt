package com.acme.kmmapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acme.legacy.ui.ProductListViewModel
import com.acme.legacy.ui.UiState
import io.insert-koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(onOpen: (String) -> Unit, vm: ProductListViewModel = getViewModel()) {
    val state by vm.state.collectAsState()
    var query by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("KMM Products") }) }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = query, onValueChange = { query = it }, label = { Text("Search") }, modifier = Modifier.weight(1f))
                Button(onClick = { vm.search(query) }) { Text("Go") }
                Button(onClick = { vm.refresh() }) { Text("Refresh") }
            }
            Spacer(Modifier.height(16.dp))
            when (val s = state) {
                is UiState.Loading -> CircularProgressIndicator()
                is UiState.Error -> Text("Error: ${'$'}{s.error.message}")
                is UiState.Data -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(s.products) { p ->
                        Card(Modifier.fillMaxWidth(), onClick = { onOpen(p.id) }) {
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