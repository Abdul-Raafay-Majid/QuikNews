package com.example.quiknews.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quiknews.R

val roboto= FontFamily(
    Font(R.font.roboto_bold,FontWeight.Bold),
    Font(R.font.roboto_bold,FontWeight.SemiBold),
    Font(R.font.roboto_regular,FontWeight.Normal),
    Font(R.font.roboto_light,FontWeight.Light),
    Font(R.font.roboto_thin,FontWeight.ExtraLight),
)
// Set of Material typography styles to start with
val Typography = Typography(
    roboto,
    h5= TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    overline = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.ExtraLight,
        fontSize = 11.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)