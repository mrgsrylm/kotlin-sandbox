package io.github.mrgsrylm.composeshop.ui.components.spinner

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.common.SpinnerType
import io.github.mrgsrylm.composeshop.ui.theme.Gray

@Composable
fun CustomSpinner(spinnerType: SpinnerType) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp, color = when (spinnerType) {
                    is SpinnerType.Size -> MaterialTheme.colorScheme.primary
                    is SpinnerType.Color -> Gray
                }
            )
            .background(color = MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = when (spinnerType) {
                is SpinnerType.Size -> "Size"
                is SpinnerType.Color -> "Color"
            },
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )
        Icon(
            modifier = Modifier.padding(10.dp),
            imageVector = Icons.Outlined.ArrowDropDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}