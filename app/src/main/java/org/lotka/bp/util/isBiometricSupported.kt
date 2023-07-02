package org.lotka.bp.util

import android.content.Context
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.content.ContextCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.presentation.MainActivity

fun isBiometricSupported(context: Context): Boolean {
    val biometricManager = BiometricManager.from(context)
    val canAuthenticate = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
    when (canAuthenticate) {
        BiometricManager.BIOMETRIC_SUCCESS -> {
            // The user can authenticate with biometrics, continue with the authentication process
            return true
        }

        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE, BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            // Handle the error cases as needed in your app
            return false
        }

        else -> {
            // Biometric status unknown or another error occurred
            return false
        }
    }
}


 @OptIn(ExperimentalMaterialApi::class, ExperimentalCoroutinesApi::class)
 fun showBiometricPrompt(mainActivity: MainActivity) {
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric Authentication")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Cancel")
        .build()

    val biometricPrompt = BiometricPrompt(mainActivity, ContextCompat.getMainExecutor(mainActivity),
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // Handle authentication error
                showMessage(mainActivity,"Authentication error: $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // Handle authentication success
                showMessage(mainActivity,"Authentication succeeded!")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // Handle authentication failure
                showMessage(mainActivity,"Authentication failed.")
            }
        })

    biometricPrompt.authenticate(promptInfo)
}

private fun showMessage(context: Context,message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}