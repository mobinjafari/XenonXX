package org.lotka.bp.presentation


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.datastore.SettingsDataStore
import org.lotka.bp.presentation.util.ConnectivityManager
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity()  {

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    @Inject
    lateinit var settingsDataStore: SettingsDataStore


    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(lifecycleOwner = this@MainActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(lifecycleOwner = this@MainActivity)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // send fcm token to backend
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Push Notification", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log and toast
            Log.d("TAG", "Fcm token : $token ")
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })


        setContent {
            MobinApp(
                activity = this@MainActivity,
                connectivityManager = connectivityManager,
                settingsDataStore = settingsDataStore
            )
        }

    }


}


