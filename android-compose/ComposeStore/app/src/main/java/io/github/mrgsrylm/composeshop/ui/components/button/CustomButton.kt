package io.github.mrgsrylm.composeshop.ui.components.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier,
    textModifier: Modifier,
    text: String
) {
    Button(
        onClick = {
            onClick()
        }, modifier = modifier,
        shape = RoundedCornerShape(30.dp)
    ) {
        Text(
            text = text,
            modifier = textModifier,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}