package io.github.mrgsrylm.composeshop.ui.components.product

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@Composable
fun ColorAndSize() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row {
            Text(text = "Color: ", style = MaterialTheme.typography.headlineSmall, color = Gray)
            Text(
                text = "Black",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Row {
            Text(text = "Size: ", style = MaterialTheme.typography.headlineSmall, color = Gray)
            Text(
                text = "L",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}