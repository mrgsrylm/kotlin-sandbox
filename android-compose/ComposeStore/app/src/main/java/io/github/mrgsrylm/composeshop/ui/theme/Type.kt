package io.github.mrgsrylm.composeshop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.mrgsrylm.composeshop.R

val Metrophobic = FontFamily(Font(R.font.metrophobic_regular, FontWeight.Bold))

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Metrophobic,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Metrophobic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Metrophobic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Metrophobic,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Metrophobic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Metrophobic,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Metrophobic,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)