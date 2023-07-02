package org.lotka.bp.presentation.ui.signinsignup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import org.lotka.bp.R

//class GoogleApiContract : ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
//
//
//
//    override fun createIntent(context: Context, input: Int): Intent {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(context.getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        val intent = GoogleSignIn.getClient(context, gso)
//        return intent.signInIntent
//    }
//
//    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
//        return when (resultCode) {
//            Activity.RESULT_OK -> {
//                GoogleSignIn.getSignedInAccountFromIntent(intent)
//            }
//            else -> null
//        }
//    }
//}
