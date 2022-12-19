package com.demo.news.ui.composables.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.demo.news.ui.composables.theme.CUSTOM_BLACK
import com.demo.news.ui.composables.theme.FontSizes

val Typography = Typography(
    h6 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizes.large,
        color = CUSTOM_BLACK
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = FontSizes.small,
        color = CUSTOM_BLACK
    )

)