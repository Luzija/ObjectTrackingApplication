import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class TrackedItem(val sku: String, val name: String)

@Composable
fun TableScreen(onNavigateToCamera: () -> Unit) {
    var trackedItems by remember { mutableStateOf(listOf<TrackedItem>()) }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = onNavigateToCamera,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Back to Camera")
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TableCell(text = "SKU", weight = 1f)
                    TableCell(text = "Name", weight = 2f)
                }
            }
            items(trackedItems) { item ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    TableCell(text = item.sku, weight = 1f)
                    TableCell(text = item.name, weight = 2f)
                }
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(8.dp)
    )
}