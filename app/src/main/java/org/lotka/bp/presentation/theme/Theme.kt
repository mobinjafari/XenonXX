package org.lotka.bp.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.lotka.bp.presentation.components.*
import java.util.*


const val stronglyDeemphasizedAlpha = 0.6f
const val slightlyDeemphasizedAlpha = 0.87f

val crane_caption = Color.DarkGray
val crane_divider_color = Color.LightGray
private val crane_red = Color(0xFFE30425)
private val crane_white = Color.White
private val crane_purple_700 = Color(0xFF720D5D)
private val crane_purple_800 = Color(0xFF5D1049)
private val crane_purple_900 = Color(0xFF4E0D3A)

val craneColors = lightColors(
  primary = crane_purple_800,
  secondary = crane_red,
  surface = crane_purple_900,
  onSurface = crane_white,
  primaryVariant = crane_purple_700
)

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
  primary = Blue600,
  primaryVariant = Blue400,
  onPrimary = Black2,
  secondary = Color.White,
  secondaryVariant = Teal300,
  onSecondary = Color.Black,
  error = RedErrorDark,
  onError = RedErrorLight,
  background = Grey1,
  onBackground = Color.Black,
  surface = Color.White,
  onSurface = Black2,
)

private val DarkThemeColors = darkColors(
  primary = Blue700,
  primaryVariant = Color.White,
  onPrimary = Color.White,
  secondary = Black1,
  onSecondary = Color.White,
  error = RedErrorLight,
  background = Color.Black,
  onBackground = Color.White,
  surface = Black1,
  onSurface = Color.White,
)

val BottomSheetShape = RoundedCornerShape(
  topStart = 20.dp,
  topEnd = 20.dp,
  bottomStart = 0.dp,
  bottomEnd = 0.dp
)


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AppTheme(
  darkTheme: Boolean,
  isNetworkAvailable: Boolean,
  displayProgressBar: Boolean,
  scaffoldState: ScaffoldState,
  dialogQueue: Queue<GenericDialogInfo>? = null,
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colors = if (darkTheme) DarkThemeColors else LightThemeColors,
    typography = QuickSandTypography,
    shapes = AppShapes
  ){
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(color = if (!darkTheme) Grey1 else Color.Black)
    ){
      Column{
        ConnectivityMonitor(isNetworkAvailable = isNetworkAvailable)
        content()
      }
      CircularIndeterminateProgressBar(isDisplayed = displayProgressBar, 0.3f)
      DefaultSnackbar(
        snackbarHostState = scaffoldState.snackbarHostState,
        onDismiss = {
          scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
        },
        modifier = Modifier.align(Alignment.BottomCenter)
      )
      ProcessDialogQueue(
        dialogQueue = dialogQueue,
      )
    }
  }
}



@Composable
fun ProcessDialogQueue(
  dialogQueue: Queue<GenericDialogInfo>?,
) {
  dialogQueue?.peek()?.let { dialogInfo ->
    GenericDialog(
      onDismiss = dialogInfo.onDismiss,
      title = dialogInfo.title,
      description = dialogInfo.description,
      positiveAction = dialogInfo.positiveAction,
      negativeAction = dialogInfo.negativeAction
    )
  }
}












private val LightColors = lightColorScheme(
  primary = md_theme_light_primary,
  onPrimary = md_theme_light_onPrimary,
  primaryContainer = md_theme_light_primaryContainer,
  onPrimaryContainer = md_theme_light_onPrimaryContainer,
  secondary = md_theme_light_secondary,
  onSecondary = md_theme_light_onSecondary,
  secondaryContainer = md_theme_light_secondaryContainer,
  onSecondaryContainer = md_theme_light_onSecondaryContainer,
  tertiary = md_theme_light_tertiary,
  onTertiary = md_theme_light_onTertiary,
  tertiaryContainer = md_theme_light_tertiaryContainer,
  onTertiaryContainer = md_theme_light_onTertiaryContainer,
  error = md_theme_light_error,
  onError = md_theme_light_onError,
  errorContainer = md_theme_light_errorContainer,
  onErrorContainer = md_theme_light_onErrorContainer,
  background = md_theme_light_background,
  onBackground = md_theme_light_onBackground,
  surface = md_theme_light_surface,
  onSurface = md_theme_light_onSurface,
  surfaceVariant = md_theme_light_surfaceVariant,
  onSurfaceVariant = md_theme_light_onSurfaceVariant,
  outline = md_theme_light_outline,
  inverseSurface = md_theme_light_inverseSurface,
  inverseOnSurface = md_theme_light_inverseOnSurface,
  inversePrimary = md_theme_light_inversePrimary,
  surfaceTint = md_theme_light_surfaceTint,
)

private val DarkColors = darkColorScheme(
  primary = md_theme_dark_primary,
  onPrimary = md_theme_dark_onPrimary,
  primaryContainer = md_theme_dark_primaryContainer,
  onPrimaryContainer = md_theme_dark_onPrimaryContainer,
  secondary = md_theme_dark_secondary,
  onSecondary = md_theme_dark_onSecondary,
  secondaryContainer = md_theme_dark_secondaryContainer,
  onSecondaryContainer = md_theme_dark_onSecondaryContainer,
  tertiary = md_theme_dark_tertiary,
  onTertiary = md_theme_dark_onTertiary,
  tertiaryContainer = md_theme_dark_tertiaryContainer,
  onTertiaryContainer = md_theme_dark_onTertiaryContainer,
  error = md_theme_dark_error,
  onError = md_theme_dark_onError,
  errorContainer = md_theme_dark_errorContainer,
  onErrorContainer = md_theme_dark_onErrorContainer,
  background = md_theme_dark_background,
  onBackground = md_theme_dark_onBackground,
  surface = md_theme_dark_surface,
  onSurface = md_theme_dark_onSurface,
  surfaceVariant = md_theme_dark_surfaceVariant,
  onSurfaceVariant = md_theme_dark_onSurfaceVariant,
  outline = md_theme_dark_outline,
  inverseSurface = md_theme_dark_inverseSurface,
  inverseOnSurface = md_theme_dark_inverseOnSurface,
  inversePrimary = md_theme_dark_inversePrimary,
  surfaceTint = md_theme_dark_surfaceTint,
)










@Composable
fun JetsurveyTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (!useDarkTheme) {
    LightColors
  } else {
    DarkColors
  }

  val systemUiController = rememberSystemUiController()
  DisposableEffect(systemUiController, useDarkTheme) {
    systemUiController.setSystemBarsColor(
      color = colors.surface,
      darkIcons = !useDarkTheme
    )

    onDispose { }
  }

  androidx.compose.material3.MaterialTheme(
    colorScheme = colors,
    shapes = Shapes,
    typography = Typography,
    content = content,
  )
}
















