package io.github.mrgsrylm.composeshop.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.R
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@Composable
fun ProductAmount() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularButton(
            modifier = Modifier
                .height(40.dp)
                .width(40.dp),
            iconModifier = Modifier
                .width(20.dp)
                .height(20.dp),
            icon = null,
            tint = Gray,
            onClick = {},
            iconDrawable = R.drawable.ic_minus
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "1",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(20.dp))
        CircularButton(
            modifier = Modifier
                .height(40.dp)
                .width(40.dp),
            iconModifier = Modifier
                .width(20.dp)
                .height(20.dp),
            icon = Icons.Outlined.Add,
            tint = Gray,
            onClick = {},
            iconDrawable = R.drawable.ic_plus
        )
    }
}