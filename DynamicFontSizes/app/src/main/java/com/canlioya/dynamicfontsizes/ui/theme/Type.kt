package com.canlioya.dynamicfontsizes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.canlioya.dynamicfontsizes.fontsizeprefs.FontSizePrefs

private const val lineHeightMultiplier = 1.15

fun getPersonalizedTypography(fontSizePrefs: FontSizePrefs): Typography {
    return Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = (16 + fontSizePrefs.fontSizeExtra).sp,
            lineHeight = ((16 + fontSizePrefs.fontSizeExtra) * lineHeightMultiplier).sp,
            letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = (22 + fontSizePrefs.fontSizeExtra).sp,
            lineHeight = ((22 + fontSizePrefs.fontSizeExtra) * lineHeightMultiplier).sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = (18 + fontSizePrefs.fontSizeExtra).sp,
            lineHeight = ((18 + fontSizePrefs.fontSizeExtra) * lineHeightMultiplier).sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = (11 + fontSizePrefs.fontSizeExtra).sp,
            lineHeight = ((11 + fontSizePrefs.fontSizeExtra) * lineHeightMultiplier).sp,
            letterSpacing = 0.5.sp
        ),
    )
}
