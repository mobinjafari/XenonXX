package org.lotka.bp.util


import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

fun GoogleSignInClient(context: Context): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken("125976201604-kgpnsddcgmu5284p48p1ugmk1m4d7dpm.apps.googleusercontent.com")
        .requestId()
        .requestProfile()
        .build()
    return GoogleSignIn.getClient(context, gso)
}