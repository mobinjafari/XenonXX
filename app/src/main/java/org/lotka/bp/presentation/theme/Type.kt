package org.lotka.bp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.lotka.bp.R

private val Chrome = FontFamily(
        Font(R.font.segoe, FontWeight.W300),
        Font(R.font.segoe_bing, FontWeight.W400),
        Font(R.font.segoe_semibold, FontWeight.W500),
)




val ChromeTypography = Typography(
        h1 = TextStyle(
                fontFamily = Chrome,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
        ),
        h2 = TextStyle(
                fontFamily = Chrome,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
        ),
        h3 = TextStyle(
                fontFamily = Chrome,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
        ),
        h4 = TextStyle(
                fontFamily = Chrome,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
        ),
        h5 = TextStyle(
                fontFamily = Chrome,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
        ),

)

private val QuickSand = FontFamily(
        Font(R.font.quicksand_light, FontWeight.W300),
        Font(R.font.quicksand_regular, FontWeight.W400),
        Font(R.font.quicksand_medium, FontWeight.W500),
        Font(R.font.quicksand_bold, FontWeight.W600)
)

val QuickSandTypography = Typography(
        h1 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W500,
                fontSize = 30.sp,
        ),
        h2 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W500,
                fontSize = 24.sp,
        ),
        h3 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
        ),
        h4 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
        ),
        h5 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
        ),
        h6 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
        ),
        subtitle1 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
        ),
        subtitle2 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
        ),
        body1 = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        body2 = TextStyle(
                fontFamily = QuickSand,
                fontSize = 14.sp
        ),
        button = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp,
                color = Color.White
        ),
        caption = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
        ),
        overline = TextStyle(
                fontFamily = QuickSand,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp
        )
)



val MontserratFontFamily = FontFamily(
        listOf(
                Font(R.font.segoe),
                Font(R.font.segoe_bing, FontWeight.Medium),
                Font(R.font.segoe_semibold, FontWeight.SemiBold)
        )
)

val Typography = androidx.compose.material3.Typography(
        // Display Large - Montserrat 57/64 . -0.25px
        displayLarge = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 57.sp,
                lineHeight = 64.sp,
                letterSpacing = (-0.25).sp,
        ),

        // Display Medium - Montserrat 45/52 . 0px
        displayMedium = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 45.sp,
                lineHeight = 52.sp,
                letterSpacing = 0.sp,
        ),

        // Display Small - Montserrat 36/44 . 0px
        displaySmall = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                letterSpacing = 0.sp,
        ),

        // Headline Large - Montserrat 32/40 . 0px
        headlineLarge = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp,
        ),

        // Headline Medium - Montserrat 28/36 . 0px
        headlineMedium = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp,
        ),

        // Headline Small - Montserrat 24/32 . 0px
        headlineSmall = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                letterSpacing = 0.sp,
        ),

        // Title Large - Montserrat 22/28 . 0px
        titleLarge = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
        ),

        // Title Medium - Montserrat 16/24 . 0.15px
        titleMedium = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp,
        ),

        // Title Small - Montserrat 14/20 . 0.1px
        titleSmall = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
        ),

        // Label Large - Montserrat 14/20 . 0.1px
        labelLarge = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.1.sp,
        ),

        // Label Medium - Montserrat 12/16 . 0.5px
        labelMedium = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
        ),

        // Label Small - Montserrat 11/16 . 0.5px
        labelSmall = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp,
        ),

        // Body Large - Montserrat 16/24 . 0.5px
        bodyLarge = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
        ),

        // Body Medium - Montserrat 14/20 . 0.25px
        bodyMedium = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
        ),
        // Body Small - Montserrat 12/16 . 0.4px
        bodySmall = TextStyle(
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
        ),
)