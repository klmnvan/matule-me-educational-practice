package com.example.matuleme.presentation.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.matuleme.R

data class Typography(
    val authTitleScreen: TextStyle = TextStyle(),
    val authDescriptionScreen: TextStyle = TextStyle(),
    val authTitleField: TextStyle = TextStyle(),
    val authContentField: TextStyle = TextStyle(),
    val authHintField: TextStyle = TextStyle(),
    val textInButton: TextStyle = TextStyle(),
    val titleDialog: TextStyle = TextStyle(),
    val descDialog: TextStyle = TextStyle(),
    val textCheckbox: TextStyle = TextStyle(),
)

val raleway = FontFamily(
    Font(R.font.raleway_italic, FontWeight.Light),
    Font(R.font.raleway_bold,FontWeight.Bold),
    Font(R.font.raleway_black,FontWeight.ExtraBold),
    Font(R.font.poppins_regular,FontWeight.Normal),
    Font(R.font.poppins_medium,FontWeight.Medium),
    Font(R.font.raleway_vedium,FontWeight.W600),
    Font(R.font.raleway_semibold,FontWeight.SemiBold),
    Font(R.font.raleway_light,FontWeight.ExtraLight)
)

val typography = Typography(
    authTitleScreen = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight(400),
        fontSize = 32.sp,
        lineHeight = 32.75.sp,
        textAlign = TextAlign.Center,
        color = text
    ),
    authDescriptionScreen = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center,
        color = hint
    ),
    authTitleField = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = text
    ),
    authContentField = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),
    authHintField = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 16.sp,
        color = hint
    ),
    textInButton = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 22.sp,
        color = background
    ),
    titleDialog = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = text
    ),
    descDialog = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = subtextdark,
        textAlign = TextAlign.Center
    ),
    textCheckbox = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = subtextdark,
        textDecoration = TextDecoration.Underline
    ),
)

val LocalTypography = staticCompositionLocalOf { typography }