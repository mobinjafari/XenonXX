import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

@Composable
fun GradientAnimation(modifier: Modifier) {
    val colorAnimation = rememberInfiniteTransition()
        .animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(20000, easing = EaseOutSine),
                repeatMode = RepeatMode.Reverse
            )
        )

    val color1 = remember { Color(0xff222831) }
    val color2 = remember { Color(0xFF393e46) }

    val animatedColor = lerpColor(color1, color2, colorAnimation.value)

    Canvas(modifier = modifier) {
        drawGradientBackground(animatedColor, size)
    }
}

private fun DrawScope.drawGradientBackground(color: Color, size: Size) {
    val gradient = Brush.linearGradient(
        colors = listOf(color, color),
        start = Offset(0f, 0f),
        end = Offset(size.width, size.height)
    )
    drawRect(brush = gradient)
}

@Composable
private fun lerpColor(start: Color, end: Color, fraction: Float): Color {
    return Color(
        red = lerp(start.red, end.red, fraction),
        green = lerp(start.green, end.green, fraction),
        blue = lerp(start.blue, end.blue, fraction),
        alpha = lerp(start.alpha, end.alpha, fraction)
    )
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}
