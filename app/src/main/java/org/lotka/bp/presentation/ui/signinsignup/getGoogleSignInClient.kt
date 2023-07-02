package org.lotka.bp.presentation.ui.signinsignup

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


//fun getGoogleSignInClient(context: Context): GoogleSignInClient {
//    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
////         Request id token if you intend to verify google user from your backend server
////        .requestIdToken(context.getString(R.string.backend_client_id))

//        .build()
//
//    return GoogleSignIn.getClient(context, signInOptions)
//}
//



fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("125976201604-kgpnsddcgmu5284p48p1ugmk1m4d7dpm.apps.googleusercontent.com")
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context, signInOptions)
}