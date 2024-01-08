package io.github.mrgsrylm.composeshop.features.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.mrgsrylm.composeshop.features.login.utils.LoginFieldType
import io.github.mrgsrylm.composeshop.ui.theme.Gray
import io.github.mrgsrylm.composeshop.ui.theme.Success

@Composable
fun LoginTextField(
    type: LoginFieldType,
    text: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        singleLine = true,
        value = text,
        shape = RoundedCornerShape(10.dp),
        onValueChange = {
            onValueChange(it)
        },
        trailingIcon = {
            if (type is LoginFieldType.Email && text.isNotEmpty()) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Outlined.Check,
                        null,
                        modifier = Modifier.size(20.dp),
                        tint = Success
                    )
                }
            }
        },
        label = {
            Text(
                text = when (type) {
                    is LoginFieldType.Email -> "Email"
                    is LoginFieldType.Password -> "Password"
                }, color = MaterialTheme.colorScheme.onSecondary
            )
        },
        visualTransformation = if (type is LoginFieldType.Email) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = {
            Text(
                text = when (type) {
                    is LoginFieldType.Email -> "Email"
                    is LoginFieldType.Password -> "Password"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            focusedLabelColor = Gray
        )
    )
}