package io.github.mrgsrylm.composeshop.ui.components.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
    title: String = "",
    elevation: Dp = 0.dp,
    actions: Array<ImageVector> = emptyArray(),
    isBackEnabled: Boolean = true
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = elevation,
        title = {
            if (title != "") {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        navigationIcon = {
            if (isBackEnabled) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Outlined.KeyboardArrowLeft,
                        null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        actions = {
            if (actions.isNotEmpty()) {
                actions.forEach { action ->
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = action,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    )
}