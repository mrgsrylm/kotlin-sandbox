package io.github.mrgsrylm.composeshop.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CircularButton(
    modifier: Modifier,
    iconModifier: Modifier = Modifier,
    icon: ImageVector?,
    iconDrawable: Int?,
    tint: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        elevation = 3.dp,
        modifier = modifier
    ) {
        IconButton(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary),
            onClick = { onClick() }) {
            if (icon != null) {
                Icon(
                    modifier = iconModifier,
                    imageVector = icon,
                    contentDescription = null,
                    tint = tint
                )
            }
            if (iconDrawable != null) {
                Icon(
                    modifier = iconModifier,
                    painter = painterResource(id = iconDrawable),
                    contentDescription = null,
                    tint = tint
                )
            }
        }
    }
}