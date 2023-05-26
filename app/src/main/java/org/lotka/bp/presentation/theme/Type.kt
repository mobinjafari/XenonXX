package org.lotka.bp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.lotka.bp.R


private val YekanBakh = FontFamily(
    Font(R.font.iran_yekan_mobile_regular, weight = FontWeight(weight = 500)),
    Font(R.font.iran_yekan_mobile_bold, weight = FontWeight(weight = 600)),
    Font(R.font.iran_yekan_mobile_extra_bold, weight = FontWeight(weight = 700)),
)

val YekanBakhTypography = Typography(
    bodySmall = TextStyle(
        fontFamily = YekanBakh,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = YekanBakh,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = YekanBakh,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
    )

)
